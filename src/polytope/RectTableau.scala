package polytope

/*
 * The RectTableau class requires that the environment variable LD_LIBRARY_PATH
 * be set to the directory containing liblpsolve55j.so due to its dependence
 * on lpsolve.
 * 
 * It requires different lpsolve libraries for 32-bit and 64-bit 
 * implementations.
 * 
 * Perhaps fix library with: execstack -c <libfile>
 */

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

import lpsolve._

class RectTableau(val rows: Int, val cols: Int, tableau: ArrayBuffer[Int]) {
  def this(rows: Int, cols: Int) = this(rows, cols, 
      ArrayBuffer.tabulate[Int](rows*cols)(k => k/cols + 1))
  
  val matrixForm = new ArrayBuffer[ArrayBuffer[Int]]()
  
  /*
   * getRow() -- Return the row containing the cell labeled by n.
   */
  def getRow(n: Int) = tableau(n)
  
  /*
   * @param k Label of cell of tableau
   * 
   * @result  Row and column in which the cell labeled by k lies
   */
  def apply(k: Int) = {
    val row = tableau(k)
    var col = 1
    var i = 0
    while (i < k) {
      if (tableau(i) == row) col += 1
      i += 1
    }
    (row, col) 
  }

  def update(index: Int, newVal: Int) = { tableau(index) = newVal }

  def toMatrix = {
    if (matrixForm.length == 0) {
      var i = 0
      while (i < rows) { matrixForm += ArrayBuffer[Int](); i += 1 }
      i = 0
      while (i < rows*cols) { matrixForm(tableau(i)-1).append(i+1); i += 1 }
    }
    matrixForm
  }

  def toCone = {
    assert(rows > 0)
    assert(cols > 0)
    val P = PolyhedralCone.positiveWeylChamber(rows + cols, 0, rows).intersection(
          PolyhedralCone.positiveWeylChamber(rows + cols, rows, cols))
    val ieqs = Array.newBuilder[Array[Int]]
    val ieq = ArrayBuffer.fill[Int](rows + cols)(0)
    for (i <- 0 to rows-1) {
      for (j <- 0 to cols-1) {
        for (k <- 0 to rows-1) {
          for (l <- 0 to cols-1) {
            ieq.update(i, 1)
            ieq.update(rows + j, 1)
            ieq(k) -= 1
            ieq(cols + l) -= 1
            // Add inequality to the array of inequalities
            ieqs += ieq.toArray[Int]
            // Reset ArrayBuffer to zero for next loop
            ieq(i)=0; ieq(rows+j)=0; ieq(k)=0; ieq(rows+l)=0
          }
        }
      }
    }
    P.intersection(new PolyhedralCone(Array[Array[Int]](), ieqs.result))
  }
  
  /*
   * isTrivial -- True if the tableau is labeled row-by-row sequentially 
   */
  def isTrivial: Boolean = {
    if (rows*cols == 0) return true
    var i = 0
    while (i < rows*cols) { 
      if (tableau(i) != i/cols + 1) {
        return false
      }
      i += 1
    }
    return true
  }

  def isAdmissible: Boolean = {  
    // Define MIP
    val solver = LpSolve.makeLp(0, rows + cols)
    
    // Makes building a model row by row faster
    // WARNING: This caused a memory fault
    // solver.setAddRowmode(true)
    
    // Consider quantities less than 0.1 to be zero
    solver.setEpsb(0.1)
    // solver.setEpsel(0.1)
    
    // Break at first solution
    solver.setBreakAtFirst(true)
    
    val eps = 0.1
        
    // Add constraint that A variables minus B variables is zero
    val sum = ArrayBuffer.fill[Double](rows)(1.0)
    val varlist = (1 to rows + cols).toArray
    sum.appendAll(ArrayBuffer.fill[Double](cols)(-1.0))
    solver.addConstraint(sum.toArray, LpSolve.EQ, 0)
    val a = Array[Double](1.0, -1.0)
    val pairs = Array.tabulate[Array[Int]](rows+cols-1)(i => Array(i+1, i+2))
    // add order condition
    solver.addConstraintex(rows + cols, Array[Double](1.0, -1.0, 1.0, -1.0), Array(1, 2, 3, 4), LpSolve.GE, eps)
    
    for (i <- 1 to rows-1)
      solver.addConstraintex(rows + cols, 
                             Array(1.0, -1.0, 0.0), 
                             Array(i, i+1, rows+1), 
                             LpSolve.GE, 
                             eps)
    for (i <- 1 to cols-1)
      solver.addConstraintex(rows + cols, 
                             Array(1.0, -1.0, 0.0), 
                             Array(rows+i, rows+i+1, 1), 
                             LpSolve.GE, 
                             eps)
    // add order condition  
    for (i <- 1 to rows) {
      for (j <- 1 to cols) {
        for (k <- 1 to rows) {
          for (l <- 1 to cols) {
            if (this.toMatrix(i)(j) < this.toMatrix(k)(l))
              solver.addConstraintex(rows + cols, 
                                     Array[Double](1.0, -1.0, 1.0, -1.0), 
                                     Array(i, k, rows+j, rows+l), 
                                     LpSolve.GE, 
                                     eps)
          }
        }
      }
    }
    println(a)
    //solver.setPresolve(LpSolve.PRESOLVE_ROWS + LpSolve.PRESOLVE_COLS, 0)
        
    // Solve the problem
    solver.solve()
    
    // Test whether the solution was empty
    val result = (solver.getSolutioncount() > 0)

    // delete the problem and free memory
    solver.deleteLp();
    
    return result
  }
}

object RectTableau {
  def apply(rows: Int, cols: Int) = new RectTableau(rows, cols)
  def apply(rows: Int, cols: Int, tableau: ArrayBuffer[Int]) = new RectTableau(rows, cols, tableau)
  
  def standardTableaux(rows: Int, cols: Int): ListBuffer[RectTableau] = {
    /*
     * [[1, 3],
     * [2, 4]]
     * [1, 2, 1, 2]
     * j = 2
    Locate the smallest integer j such that j is not
    in the lowest corner of the subtableau T_j formed by
    1,...,j.  This happens to be first j such that
    tableau_vector[j]<tableau_vector[j-1].
    l will correspond to the shape of T_j
    */
    // Initialize to the trivially sequential tableau
    var T = new RectTableau(rows, 
                            cols, 
                            ArrayBuffer.tabulate[Int](rows*cols)(
                                n => (n % rows) + 1))
    
    if (rows == 1 || cols == 1) return ListBuffer(T)
    val size = rows*cols
    var listTableaux = ListBuffer[RectTableau]()
    var subTabHeight = 0
    var nextRow = 0
    
    var i, m, r = 0
    var subTabSize = 0
    
    // Initialize subTabShape to the correct length
    val subTabShape = ArrayBuffer.fill[Int](size)(0)
    
    // Separate part of the algorithm into a function so that the second while
    // loop can be broken by a return statement.
    def calcSubTabShape(): Unit = {
      // Initialize subTabShape to [1, 0, ...]
      subTabShape(0) = 1
      i = 1
      while (i < size) {
        subTabShape(i) = 0
        i += 1
      }
      // Calculate subTabShape
      i = 1; subTabSize = 0
      while (i < size) {
        subTabShape(T.getRow(i)) += 1
        if (T.getRow(i) < T.getRow(i-1)) {
          subTabSize = i
          return
        }
        i += 1
      }
    }
         
    do {
      calcSubTabShape()
      
      // Find the last nonzero row of l and store it in k
      i = size - 1
      while ( subTabShape(i) == 0 ) i -= 1
      subTabHeight = i
      
      // Find a new row for the largest element of the subtableau (next lowest corner)
      nextRow = subTabShape(T.getRow(subTabSize) + 1)
      i = subTabHeight
      while (subTabShape(i) != nextRow) i -= 1
      
      // Move subTabSize to row i
      T.update(subTabSize, i)
      subTabShape(i) -= 1
      // Fill in the columns of T_j using 1,...,j-1 in increasing order
      m = 0
      while (m < subTabSize) {
        r = 0
        while (subTabShape(r) != 0) {
          T.update(m, r)
          subTabShape(r) -= 1
          m += 1
          r += 1
        }
      }
      listTableaux.prepend(T)
    } while (!T.isTrivial)
    return listTableaux
  }
}

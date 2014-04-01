package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._
/*
import net.sf.javailp._
import net.sf.javailp.SolverGLPK.Hook
import org.gnu.glpk._
*/
import com.google.ortools.constraintsolver.DecisionBuilder
import com.google.ortools.constraintsolver.IntVar
import com.google.ortools.constraintsolver.IntExpr
import com.google.ortools.constraintsolver.Solver
import com.google.ortools.constraintsolver.Solver._

class RectTableau(val rows: Int, val cols: Int, tableau: ArrayBuffer[Int]) {
  def this(rows: Int, cols: Int) = this(rows, cols, 
      ArrayBuffer.tabulate[Int](rows*cols)(k => k/cols + 1))
  
  val matrixForm = new ArrayBuffer[ArrayBuffer[Int]]()
  
  /*
   * getRow() -- Return the row containing the cell labeled by n.
   */
  def getRowOf(n: Int) = tableau(n-1)
  
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

  def updateRow(label: Int, newRow: Int) = { tableau(label-1) = newRow }

  def copy(): RectTableau = new RectTableau(rows, cols, tableau.clone())
  
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
    // Initialize the cone with the intersection of positive Weyl chambers
    val P = PolyhedralCone.positiveWeylChamber(rows + cols, 0, rows).
            intersection(
              PolyhedralCone.positiveWeylChamber(rows + cols, rows, cols)
            )
    // Initialize the inequality list to be empty
    val ieqs = Array.newBuilder[Array[Int]]
    // Initialize the inequality to be 0 >= 0
    val ieq = ArrayBuffer.fill[Int](rows + cols)(0)
    // Loop through every quadruple (a[i], b[j], a[k], b[l])
    for (i <- 0 to rows-1) {
      for (j <- 0 to cols-1) {
        for (k <- 0 to rows-1) {
          for (l <- 0 to cols-1) {
            if (toMatrix(i)(j) < toMatrix(k)(l)) {
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
    }
    P.intersection(new PolyhedralCone(Array[Array[Int]](), ieqs.result))
  }
  
  /*
   * isTrivial -- True if the tableau is labeled row-by-row sequentially 
   */
  def isTrivialTableau: Boolean = {
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

  def isStandardTableau: Boolean = {
    val rowLength = ArrayBuffer.fill[Int](rows)(0)
    var i = 0
    while (i < rows*cols) {
      if (tableau(i) > 0 && tableau(i) <= rows) {
        rowLength(tableau(i)-1) += 1
        // If a row extends beyond the specified number of columns, 
        // then it's not standard
        if (rowLength(tableau(i)-1) > cols) 
          return false
        // If a cell was filled in before the cell above it was filled in
        // then the column will have a decrease
        if (tableau(i) > 1 && rowLength(tableau(i)-1) > rowLength(tableau(i)-2)) 
          return false
      } else {
        return false
      }
      i += 1
    }
    return true
  }
  
  /*
   * isAdmissible -- Returns true if the RectTableau corresponds to a cubicle.
   *                 This happens if its corresponding system of equations has
   *                 a one-dimensional family of solutions. 
   */
  def isAdmissible: Boolean = {
    // FIXME!
    if (rows == 0 || cols == 0) return true
    if (rows == 1 || cols == 1) return true
    
    // Sanity check
    if (!isStandardTableau) return false
            
    val solver: Solver = new Solver("Admissibility");

    // The maximum value which a variable can take. Every cubicle must have an 
    // integral ray in its interior whose coordinates are less than this number.
    val vmax = 10000
    // Define variables and their negatives for solver
    val a = solver.makeIntVarArray(rows, 0, vmax, "a")
    val b = solver.makeIntVarArray(cols, 0, vmax, "b")
    val minusA = a.map(solver.makeOpposite(_))
    val minusB = b.map(solver.makeOpposite(_))
    
    // Add constraint that A variables minus B variables is zero
    val sum = minusB.foldLeft(solver.makeSum(a))(
                              (bvar, sum) => solver.makeSum(bvar, sum))
    solver.addConstraint(solver.makeEquality(sum, 0))
    
    // Add constraint that variables are ordered in a decreasing manner
    for (i <- 0 to rows-2) {
      solver.addConstraint(solver.makeGreaterOrEqual(a(i), a(i+1)))
    }
    for (i <- 0 to cols-2) {
      solver.addConstraint(solver.makeGreaterOrEqual(b(i), b(i+1)))
    }

    // Add conditions: a(i) + b(j) >= a(k) + b(l) + eps 
    for (i <- 1 to rows) {
      for (j <- 1 to cols) {
        for (k <- 1 to rows) {
          for (l <- 1 to cols) {
            if (this.toMatrix(i-1)(j-1) < this.toMatrix(k-1)(l-1)) {
              if (i != k || j != l)
                solver.addConstraint(
                  solver.makeGreaterOrEqual(
                    solver.makeSum(
                        solver.makeSum(a(i), b(j)), 
                        solver.makeSum(minusA(k), minusB(l))
                    ), 
                  0))
            }
          }
        }
      }
    }
    
    // Create a solver
    val db = solver.makePhase(a ++ b, Solver.INT_VAR_DEFAULT, Solver.INT_VALUE_DEFAULT)
    return solver.solve(db)
  }
}

object RectTableau {
  def apply(rows: Int, cols: Int) = new RectTableau(rows, cols)
  def apply(rows: Int, cols: Int, tableau: ArrayBuffer[Int]) 
    = new RectTableau(rows, cols, tableau)
  
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
    // Initialize to the vertically sequential tableau
    var T = new RectTableau(rows, 
                            cols,
                            ArrayBuffer.tabulate[Int](rows*cols)(
                                n => (n % rows) + 1))
    
    var listTableaux = ListBuffer[RectTableau](T.copy())
    if (rows <= 1 || cols <= 1) return listTableaux
    val size = rows*cols
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
      var j = 1
      while (j < size) {
        subTabShape(j) = 0
        j += 1
      }
      // Calculate subTabShape
      subTabSize = 1
      j = 2
      while (j <= size) {
        subTabShape(T.getRowOf(j)-1) += 1
        if (T.getRowOf(j) < T.getRowOf(j-1)) {
          subTabSize = j
          return
        }
        j += 1
      }
    }

    do {
      calcSubTabShape()
      
      // Find the last nonzero row of l and store it in k
      i = size - 1
      while (subTabShape(i) == 0) i -= 1
      subTabHeight = i+1
      
      // Find a new row for the largest element of the subtableau 
      // (next lowest corner)
      nextRow = subTabShape(T.getRowOf(subTabSize))
      //i = subTabHeight
      while (subTabShape(i) != nextRow) i -= 1
      
      // Move label subTabSize to row i+1
      T.updateRow(subTabSize, i+1)
      subTabShape(i) -= 1
      
      // WARNING: T must be allowed to temporarily be a non-tableau for the 
      // updates to work. Fill in the columns of T_j using 1,...,j-1 in 
      // increasing order
      m = 1
      while (m < subTabSize) {
        r = 1
        while (subTabShape(r-1) != 0) {
          T.updateRow(m, r)
          subTabShape(r-1) -= 1
          m += 1
          r += 1
        }
      }
      listTableaux.prepend(T.copy())
    } while (!T.isTrivialTableau)
    return listTableaux
  }
}

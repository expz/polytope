package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions._

import scala.util.parsing.combinator._

import com.google.ortools.linearsolver.MPConstraint
import com.google.ortools.linearsolver.MPSolver
import com.google.ortools.linearsolver.MPSolver.OptimizationProblemType
import com.google.ortools.linearsolver.MPSolver.ResultStatus
import com.google.ortools.linearsolver.MPVariable

import java.util.UUID

/**
  * Represents a rectangular tableau with `rows` rows and `cols` columns.
  *
  * @constructor Returns a new rectangular tableau.
  * @param rows The number of rows of the rectangular tableau.
  * @param cols The number of columns of the rectangular tableau.
  * @param rowOfLabel
  */
class RectTableau(val rows: Int, 
                  val cols: Int, 
                  val rowOfLabel: ArrayBuffer[Int]) {
  
  // Check that rowOfLabel describes a rectangular shape
  assert(rowOfLabel.length == rows*cols)
  assert(Array.tabulate[Int](rows)(
    r => rowOfLabel.count(_ == r+1)).find(_ != cols) == None)
  
  // Load library for doing MIP to check admissibility
  System.loadLibrary("jniortools")
  
  /** 
    * The representation of this rectangular tableau as a matrix whose entries
    * are the numbers between 1 and `rows`*`cols`.
    */
  val matrixForm = new ArrayBuffer[ArrayBuffer[Int]]()
  
  /**
    * Return the row containing the cell labeled by n.
    */
  def getRowOf(n: Int) = rowOfLabel(n-1)
  
  /**
    * @param k Label of cell of tableau
    * @return  Row and column in which the cell labeled by k lies
    */
  def apply(k: Int) = {
    val row = rowOfLabel(k)
    var col = 1
    var i = 0
    while (i < k) {
      if (rowOfLabel(i) == row) col += 1
      i += 1
    }
    (row, col) 
  }

  def updateRow(label: Int, newRow: Int) = { rowOfLabel(label-1) = newRow }

  def copy(): RectTableau = new RectTableau(rows, cols, rowOfLabel.clone())
  
  def toMatrix = {
    if (matrixForm.length == 0) {
      var i = 0
      while (i < rows) { matrixForm += ArrayBuffer[Int](); i += 1 }
      i = 0
      while (i < rows*cols) { matrixForm(rowOfLabel(i)-1).append(i+1); i += 1 }
    }
    matrixForm
  }
  
  /** Returns the cone of mixed states of distinguishable particles associated
    * to this tableau.
    */
  def toCone = {
    // Initialize the cone to the trace=lcm(dims) slice of the pos Weyl chamber
    val P = PolyhedralCone.positiveWeylChamberDM(List(rows, cols))
    
    // If this is a trivial tableau, then return the positive Weyl Chamber
    if (rows == 0 || cols == 0) P
    
    // Initialize the inequality list to be empty
    val ieqs = Array.newBuilder[Array[Int]]
    // Initialize the inequality to be 0 >= 0
    val ieq = ArrayBuffer.fill[Int](rows + cols + 1)(0)
    // Loop through every quadruple (a[i], b[j], a[k], b[l])
    for (i <- 0 to rows-1) {
      for (j <- 0 to cols-1) {
        for (k <- 0 to rows-1) {
          for (l <- 0 to cols-1) {
            if (toMatrix(i)(j) < toMatrix(k)(l)) {
              // a(i) + b(j) >= a(k) + b(l)
              ieq(i) = 1
              ieq(rows + j) = 1
              ieq(k) -= 1
              ieq(rows + l) -= 1
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
  
  def csvHeaders(): String =     
    (1 to rows) map(i => 
      (1 to cols) map("T(" + i + "," + _ + ")") mkString(",")
      ) mkString(",")

  def toCSV(): String = toMatrix map(_ mkString(",")) mkString(",")
    
  override def toString(): String =
    if (rows*cols < 10) 
      toMatrix.map(_ mkString " ") mkString "\n"
    else 
      toMatrix.map(
              _.map(i => if (i < 10) " " + i else i.toString) mkString " " 
              ) mkString "\n"
  
  /**
    * Returns true if this tableau is labeled row-by-row sequentially. 
    */
  def isTrivialTableau: Boolean = {
    if (rows*cols == 0) return true
    var i = 0
    while (i < rows*cols) { 
      if (rowOfLabel(i) != i/cols + 1) {
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
      if (rowOfLabel(i) > 0 && rowOfLabel(i) <= rows) {
        rowLength(rowOfLabel(i)-1) += 1
        // If a row extends beyond the specified number of columns, 
        // then it's not standard
        if (rowLength(rowOfLabel(i)-1) > cols) 
          return false
        // If a cell was filled in before the cell above it was filled in
        // then the column will have a decrease
        if (rowOfLabel(i) > 1 && rowLength(rowOfLabel(i)-1) > rowLength(rowOfLabel(i)-2)) 
          return false
      } else {
        return false
      }
      i += 1
    }
    return true
  }
  
  /** Returns true if the RectTableau corresponds to a cubicle. This happens if
    * its corresponding system of equations has a one-dimensional family of 
    * solutions.
    * 
    * @return whether the tableau corresponds to a cubicle 
    */
  def isAdmissible: Boolean = {
    // FIXME!
    if (rows == 0 || cols == 0) return true
    if (rows == 1 || cols == 1) return true
    
    // Sanity check
    if (!isStandardTableau) return false
    
    val solver = new MPSolver(UUID.randomUUID().toString(), OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING)
    
    val eps = 1.0
    
    // The maximum value which a variable can take. Every cubicle must have an 
    // integral ray in its interior whose coordinates are less than this number.
    val vmax = MPSolver.infinity()
    val vmin = -MPSolver.infinity()
    
    // Define variables and their negatives for solver
    val a = solver.makeNumVarArray(rows, vmin, vmax)
    val b = solver.makeNumVarArray(cols, vmin, vmax)
    
    // Add constraint that A variables minus B variables is zero
    var ct = solver.makeConstraint(0.0, eps)
    for (ai <- a) ct.setCoefficient(ai, 1.0)
    for (bi <- b) ct.setCoefficient(bi, -1.0)
    
    solver.objective().setCoefficient(a(0), 0.0)
    // Add constraint that variables are ordered in a decreasing manner
    for (i <- 0 to rows-2) {
      ct = solver.makeConstraint(eps, vmax)
      ct.setCoefficient(a(i), 1.0)
      ct.setCoefficient(a(i+1), -1.0)
    }
    for (i <- 0 to cols-2) {
      ct = solver.makeConstraint(eps, vmax)
      ct.setCoefficient(b(i), 1.0)
      ct.setCoefficient(b(i+1), -1.0)
    }
    
    // Add conditions: a(i) + b(j) >= a(k) + b(l) + eps
    val coeffs = ArrayBuffer.fill[Double](4)(0.0)
    for (i <- 0 until rows) {
      for (j <- 0 until cols) {
        for (k <- 0 until rows) {
          for (l <- 0 until cols) {
            if (this.toMatrix(i)(j) < this.toMatrix(k)(l)) {
              coeffs.map(_ => 0.0)
              if (i != k) {
                coeffs(0) = 1.0
                coeffs(2) = -1.0
              }
              if (j != l) {
                coeffs(1) = 1.0
                coeffs(3) = -1.0
              }
              if (i != k || j != l) {
                ct = solver.makeConstraint(eps, vmax)
                ct.setCoefficient(a(i), coeffs(0))
                ct.setCoefficient(b(j), coeffs(1))
                ct.setCoefficient(a(k), coeffs(2))
                ct.setCoefficient(b(l), coeffs(3))
              }
            }
          }
        }
      }
    }
    
    // Attempt to solve
    val result = solver.solve()
    return (result == MPSolver.ResultStatus.OPTIMAL)
  }
}

object RectTableau {
  def apply(rows: Int, cols: Int, rowOfLabel: ArrayBuffer[Int]): RectTableau = 
      new RectTableau(rows, cols, rowOfLabel)
  
  def apply(rows: Int, cols: Int): RectTableau = 
      apply(rows, cols, ArrayBuffer.tabulate[Int](rows*cols)(k => k/cols + 1))
  
  def apply(tableau: Array[Array[Int]]): RectTableau = 
      apply(tableau.length, tableau(0).length,
          {
            val t = tableau.flatten[Int]
            ArrayBuffer.tabulate[Int](t.length)(
                n => t.indexWhere(_ == n+1) / tableau(0).length + 1)
          })
  
  def apply(str: String): RectTableau = apply(stringToMatrix(str))
  
  object matrixParser extends RegexParsers {
    def entry: Parser[Int] = regex("""\d+""".r).map(s => s.toInt) 
    def row: Parser[Array[Int]] = repsep(entry, ",").map(L => L.toArray)
    def matrix: Parser[Array[Array[Int]]] = "[" ~> repsep(row, ";").map(L => L.toArray) <~ "]" 
    def parse(str: String): ParseResult[Array[Array[Int]]] = parseAll(matrix, str)
    def apply(str: String): Array[Array[Int]] = parse(str) match {
      case Success(result: Array[Array[Int]], _) => result
      case _ => throw new Exception("The string could not be parsed into a matrix. Input:\n" + str)
    }
  }
  def stringToMatrix(str: String): Array[Array[Int]] = matrixParser(str)
  
  /**
    * Returns a ListBuffer of all rectangular, standard tableaux of dimension
    * `rows` by `cols`.
    *
    * Algorithm written in consultation with the Python source code of the Sage 
    * library freely available under the GPLv3 license from 
    *   [[http://www.sagemath.org]].
    */
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

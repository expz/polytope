package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashSet

import scala.util.Sorting

import parma_polyhedra_library._

/**
  * Represents a polyhedral cone given by equations and inequalities.
  * 
  * @constructor Returns a polyhedral cone defined by equations, `eqs`, and 
  *   inequalities, `ieqs`.
  * @param eqs An array of equations defining the cone.
  * @param ieqs An array of inequalities defining the cone.
  * 
  * WARNING: the lengths of the arrays in eqs and ieqs must be equal, but there
  * is no check. Otherwise, an error can be thrown when calling edges(). 
  */
class PolyhedralCone(val eqs: Array[Array[Int]], val ieqs: Array[Array[Int]]) {  
  // Check that eqs and ineqs all have the same number of coefficients
  assert((eqs ++ ieqs).map(_.length).distinct.length <= 1)
 
  /** The dimension of the ambient space of the polyhedral cone. */
  val dim: Int = if (eqs.length > 1) (eqs(0).length - 1)
            else if (ieqs.length > 1) (ieqs(0).length - 1)
            else 0

  // Load native libraries
  try {
    System.loadLibrary("gmpxx")
    System.loadLibrary("gmp")
    System.loadLibrary("ppl")
    System.loadLibrary("ppl_java")
  } catch {
    case e: UnsatisfiedLinkError => {
      System.err.println(
        "Native code library (libppl_java.so) failed to load.\n" + e)
      System.exit(1)
    }
  }
  
  /**
    * Returns the intersection of this polyhedral cone with a given cone.
    *
    * @param P2 A polyhedral cone to intersect.
    * @returns The intersection of `P2` with this cone.
    */
  def intersection(P2: PolyhedralCone): PolyhedralCone = {
    val eqsToAdd = 
      if (eqs.deep == Array(Array(0)).deep) Array[Array[Int]]() else eqs
    val ieqsToAdd = 
      if (ieqs.deep == Array(Array(0)).deep) Array[Array[Int]]() else ieqs
    val P2eqsToAdd = 
      if (P2.eqs.deep == Array(Array(0)).deep) Array[Array[Int]]() else P2.eqs
    val P2ieqsToAdd = 
      if (P2.ieqs.deep == Array(Array(0)).deep) Array[Array[Int]]() else P2.ieqs
      
    return new PolyhedralCone(eqsToAdd ++ P2eqsToAdd, ieqsToAdd ++ P2ieqsToAdd)
  }
  
  /**
    * Returns the extremal edges of this polyhedral cone.
    *
    * NOTE: Calculates the edges anew every time it is called.
    */
  def edges(): (Array[Edge], Array[Edge]) = {
    // The library must be initialized and later finalized
    Parma_Polyhedra_Library.initialize_library()
    
    val cs = new Constraint_System()
    val vars = (
      for (i <- 0 until dim) yield 
        new Linear_Expression_Variable(new parma_polyhedra_library.Variable(i))
    ) ++ Array(new Linear_Expression_Coefficient(
                 new parma_polyhedra_library.Coefficient(1)))
    
    val zero = new Linear_Expression_Coefficient(
                 new parma_polyhedra_library.Coefficient(0))
    
    // Convert an array of coefficients into a PPL Linear_Expression
    def toLinear_Expression(coeffs: Array[Int]): Linear_Expression =
      coeffs.foldLeft[(Linear_Expression, Int)]((zero, 0))(
        (keyVal, c) => (new Linear_Expression_Sum(keyVal._1, 
                          new Linear_Expression_Times(
                            new parma_polyhedra_library.Coefficient(c),
                            vars(keyVal._2)
                            )), 
                         keyVal._2 + 1)
       )._1
    
    // Convert equations to constraints and add them to cs
    eqs.map(toLinear_Expression(_)).
        map(new Constraint(
          _, parma_polyhedra_library.Relation_Symbol.EQUAL, zero)).
        foreach(cs.add(_))
    
    // Convert inequalities to constraints and add them to cs
    ieqs.map(toLinear_Expression(_)).
        map(new Constraint(
          _, parma_polyhedra_library.Relation_Symbol.GREATER_OR_EQUAL, zero)).
        foreach(cs.add(_))
    
    val myPoly = new C_Polyhedron(cs)
    val gs = myPoly.generators()
    
    //
    /*
      SAMPLE ascii_dump() OUTPUT:
      topology NECESSARILY_CLOSED
      3 x 2 SPARSE (not_sorted)
      index_first_pending 3
      size 3 1 2 0 P (C)
      size 3 1 0 2 R (C)
      ...
     */
    // The only way to get at the data seems to be to dump it to a string
    val asciiDump = gs.ascii_dump().lines.drop(3).toArray
    val strRays = asciiDump.filter(_.indexOf('R')>=0)
    val rays = strRays.map(
                 _ drop(5) dropRight(6) split(' ') drop(2) map(_.toInt)
               )
    val strLines = asciiDump.filter(_.indexOf('L')>=0)
    val lines = strLines.map(
                  _ drop(5) dropRight(6) split(' ') drop(2) map(_.toInt)
                )
    val allRays = lines ++ (lines.map(_.map(-_))) ++ rays
    
    val strPoints = asciiDump.filter(_.indexOf('P')>=0)
    val points = strPoints.map(
                   _ drop(5) dropRight(6) split(' ') drop(2) map(_.toInt)
                 )
    // Convert rays (Array[Int]) to edges and save
    val rs = allRays.map(new Edge(_))
    val ps = points.map(new Edge(_))
    
    // Now we can free the Polyhedron and finalize the library
    Parma_Polyhedra_Library.finalize_library()
    myPoly.free()
    
    // But we saved the edges so return them
    return (rs, ps)
  } 
  
  // Calculate ABEdges
  def edges(dimA: Int): (Array[ABEdge], Array[ABEdge]) = {
    val (ps, rs) = edges()
    return (rs.map(r => new ABEdge(r.edge, dimA)), 
            ps.map(p => new ABEdge(p.edge, dimA)))
  }
  
  override def toString(): String =
    "PolyhedralCone\nEqualities\n" + 
    eqs.map(a => "(" + a.mkString(", ") + ")").mkString("\n") + "\n" +
    "Inequalities\n" + 
    ieqs.map(a => "(" + a.mkString(", ") + ")").mkString("\n")
}

/**
  * Companion object of the class `PolyhedralCone`.
  */
object PolyhedralCone {  
  def apply(eqs: Array[Array[Int]], ieqs: Array[Array[Int]]): PolyhedralCone = 
      new PolyhedralCone(eqs, ieqs)
  
  // HashSet[T] is invariant in T, therefore its insufficient to use
  // HashSet[Inequality]
  def apply[T <: Inequality](ieqs: HashSet[T]): PolyhedralCone =
    if (ieqs.isEmpty) {
      apply(Array(), Array())
    } else {
      apply(Array[Array[Int]](), ieqs.map(
              ieq => ieq.toArray() ++ Array(ieq.const)).toArray
              )
    }
  
  def positiveWeylChamberDM(dims: List[Int]): PolyhedralCone = 
    positiveWeylChamberDP(dims)

  /**
    * Returns the positive Weyl chamber for the numChamberVars starting at 
    * firstChamberVar in a total space of numTotalVars
    * 
    * @param dims dimensions of subspace                       
    * @return The cone where consecutive variables decrease and sum to zero
    */
  def positiveWeylChamberDP(dims: List[Int]): PolyhedralCone = {
    //numTotalVars: Int, firstChamberVar: Int, numChamberVars: Int)
    
    val totalDim = dims.sum
    
    // Equations coeffs*vars + lastcoeff*1 == 0
    val eqs = ArrayBuffer[Array[Int]]()
    // Inequalities coeffs*vars + lastcoeff*1 >= 0
    val ieqs = ArrayBuffer[Array[Int]]()
    
    // Deal with the trivial cases separately
    if (totalDim == 0) {
      return new PolyhedralCone(Array(), Array())
    }

    var sumOfDims = 0
    var d = 0
    while (d < dims.length) {
      // The sum of chamber variables is trace
      eqs += (Array.tabulate[Int](totalDim)(
        i => if (i >= sumOfDims && i < sumOfDims + dims(d)) 1 else 0
      ) ++ Array(-Arithmetic.lcm(dims)))
      
      if (dims(d) > 1) {
        // Define the array to hold the inequalities
        val ieq = ArrayBuffer.fill[Int](totalDim+1)(0)
            
        // x(i) > x(i+1) for every x(i), x(i+1) in the chamber variables
        var i = sumOfDims
        while (i < sumOfDims + dims(d) - 1) {
          // Create equations in ieq
          ieq(i) = 1; ieq(i+1) = -1
          ieqs.append(ieq.toArray)
          ieq(i) = 0; ieq(i+1) = 0  // Reset ieq
          i += 1
        }
      }
      sumOfDims += dims(d)
      d += 1      
    }
    
    return new PolyhedralCone(eqs.toArray, ieqs.toArray)
  }
}

/**
  * Represents a rational edge of a polyhedral cone.
  *
  * @constructor Returns a new edge based at the origin passing through the 
  * point `edge`.
  * @param edge An point with integral coordinates defining the edge.
  */
class Edge(val edge: Array[Int]) {
  /**
    * Returns `(c(0), ..., c(k))` where 
    *
    *   c(0) = the number of consecutive entries equal to v(0),
    *   c(1) = the number of consecutive entries equal to the next distinct 
    *          element of v,
    *   ...
    *
    * @param v An array of integers.
    * @returns The consecutive multiplicities of `v`.
    */
  protected def mult(v: Array[Int]): Array[Int] = {
    val m = ArrayBuffer[Int]()
    if (v.length == 0) return m.toArray
    m.append(1)
    var i = 1
    while (i < v.length) {
      if (v(i) == v(i-1)) m(m.length-1) += 1
      else m.append(1)
      i += 1
    }
    return m.toArray
  }
  
  // WARNING: Returns true if a is an ABEdge with different Adim but same total
  // dimension
  override def equals(a: Any): Boolean = a match {
    case e: Edge => e.edge.deep == this.edge.deep
    case _ => false
  }
  
  def toCSV(): String = edge mkString(",")
  
  override def toString(): String = "(" + edge.mkString(", ") + ")"
}

/**
  * Represents a rational edge of a polyhedral cone embedded in a bipartite
  * tensor product.
  *
  * @param edge A point with integral coordinates defining the edge.
  * @param dimA The dimension of the first factor of the tensor product.
  */
class ABEdge(edge: Array[Int], val dimA: Int) extends Edge(edge) {
  assert(dimA <= edge.length)
  
  /** The dimension of the second factor of the tensor product. */
  def dimB: Int = edge.length - dimA

  /** The dimension of the tensor product (ambient space of this edge). */
  def dimAB: Int = dimA*dimB
  
  val A = edge.take(dimA).sorted(Ordering.Int.reverse)  // Decreasing order
  val B = edge.drop(dimA).sorted(Ordering.Int.reverse)
  
  override def equals(a: Any) = a match {
    case abedge: ABEdge => abedge.A.deep == this.A.deep && 
                           abedge.B.deep == this.B.deep
    case _ => false
  }
  
  override def hashCode() = this.A.deep.hashCode() + this.B.deep.hashCode()
  
  /** Returns a(i) + b(j) in decreasing order */
  def AB: Array[Int] = {
    val ab = Array.tabulate[Int](dimA*dimB)(n => A(n%dimA) + B(n/dimA))
    scala.util.Sorting.quickSort(ab)
    return ab.reverse
  } 
  
  /**  */
  def ABTriples(): Array[(Int,Int,Int)] = {
    val ab = Array.tabulate[(Int, Int, Int)](dimA*dimB)(
                 n => (n%dimA, n/dimA, A(n%dimA)+ B(n/dimA)))
    return ab.sorted(Ordering.by[(Int, Int, Int), Int](-_._3)).toArray
  }

  def multA(): Array[Int] = mult(A)
  def multB(): Array[Int] = mult(B)
  def multAB(): Array[Int] = mult(AB)

  def csvHeaders(): String = 
    (1 to dimA).map("a" + _).mkString(",") + "," +
    (1 to dimB).map("b" + _).mkString(",") + "," +
    (1 to dimAB).map("c" + _).mkString(",")

  override def toCSV(): String = 
    A.mkString(",") + "," + B.mkString(",") + "," + AB.mkString(",") 
  
  override def toString(): String = 
    "((" + A.mkString(", ") + "), (" + B.mkString(", ") + "))"
}

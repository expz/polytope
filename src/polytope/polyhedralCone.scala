package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashSet

import scala.util.Sorting

import parma_polyhedra_library._

/*
 * PolyhedralCone
 * 
 * ieqs are >= 0
 * 
 * WARNING: the lengths of the arrays in eqs and ieqs must be equal, but there
 *  is no check. Otherwise, an error can be thrown when calling edges(). 
 */
class PolyhedralCone(val eqs: Array[Array[Int]], val ieqs: Array[Array[Int]]) {  
  // Check that eqs and ineqs all have the same number of coefficients
  assert((eqs ++ ieqs).map(_.length).distinct.length <= 1)
  
  val dim: Int = if (eqs.length > 0) eqs(0).length
            else if (ieqs.length > 0) ieqs(0).length
            else 0
  
  try {
    System.loadLibrary("ppl_java")
  } catch {
    case e: UnsatisfiedLinkError => {
      System.err.println("Native code library (libppl_java.so) failed to load.\n" + e)
      System.exit(1)
    }
  }

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
  
  /*
   *  Calculates the edges anew every time it is called
   */
  def edges(): ArrayBuffer[Edge] = {
    // The library must be initialized and later finalized
    Parma_Polyhedra_Library.initialize_library()
    
    val cs = new Constraint_System()
    val vars = for (i <- 0 until dim) 
                 yield new Linear_Expression_Variable(new Variable(i))
    
    val zero = new Linear_Expression_Coefficient(new Coefficient(0))
    
    // Convert an array of coefficients into a PPL Linear_Expression
    def toLinear_Expression(coeffs: Array[Int]): Linear_Expression =
      coeffs.foldLeft[(Linear_Expression, Int)]((zero, 0))(
        (keyVal, c) => (new Linear_Expression_Sum(keyVal._1, 
                          new Linear_Expression_Times(
                            new Coefficient(c), 
                            vars(keyVal._2))), 
                         keyVal._2 + 1)
       )._1
    
    // Convert equations to constraints and add them to cs
    eqs.map(toLinear_Expression(_)).
        map(new Constraint(_, parma_polyhedra_library.Relation_Symbol.EQUAL, zero)).
        foreach(cs.add(_))
    
    // Convert inequalities to constraints and add them to cs
    ieqs.map(toLinear_Expression(_)).
        map(new Constraint(_, parma_polyhedra_library.Relation_Symbol.GREATER_OR_EQUAL, zero)).
        foreach(cs.add(_))
    
    val myPoly = new C_Polyhedron(cs)
    val gs = myPoly.generators()
    //
    /*
      SAMPLE ascii_dump() OUTPUT:
      
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
    
    // Convert rays (Array[Int]) to edges and save
    val es = allRays.map(new Edge(_))
    
    // Now we can free the Polyhedron and finalize the library
    myPoly.free()
    Parma_Polyhedra_Library.finalize_library()
    
    // But we saved the edges so return them
    return es.to[ArrayBuffer]
  } 
  
  // Calculate ABEdges
  def edges(dimA: Int): ArrayBuffer[ABEdge] = 
    edges().map(e => new ABEdge(e.edge, dimA))
  
  override def toString: String =
    "PolyhedralCone\nEqualities\n" + 
    eqs.map(a => "(" + a.mkString(", ") + ")").mkString("\n") + "\n" +
    "Inequalities\n" + 
    ieqs.map(a => "(" + a.mkString(", ") + ")").mkString("\n")
}

object PolyhedralCone {  
  def apply(eqs: Array[Array[Int]], ieqs: Array[Array[Int]]) = 
      new PolyhedralCone(eqs, ieqs)
  
  /*
   * positiveWeylChamber -- Return the positive Weyl chamber for the 
   *                        numChamberVars starting at firstChamberVar in a
   *                        total space of numTotalVars
   *                        
   *                        The cone where consecutive variables decrease and
   *                        sum to zero
   * 
   * @param numTotalVars                       
   * @param firstChamberVar starts counting at 0
   * @param numChamberVars
   */
  def positiveWeylChamber(numTotalVars: Int, 
                          firstChamberVar: Int, 
                          numChamberVars: Int): PolyhedralCone = {
    assert(numTotalVars >= 0)
    assert(firstChamberVar >= 0)
    assert(numChamberVars >= 0 && 
        (firstChamberVar + numChamberVars <= numTotalVars ||
        (firstChamberVar == numTotalVars && numChamberVars == 0)))
    
    // There will be a single equation
    val eq = ArrayBuffer.fill[Int](numTotalVars)(0)
    
    // Deal with the trivial cases separately
    if (numChamberVars == 0) {
      return new PolyhedralCone(Array(), Array())
    } else if (numChamberVars == 1) {
      eq(firstChamberVar) = 1
      return new PolyhedralCone(Array(eq.toArray), Array())
    }
    
    // The sum of chamber variables is zero (trace zero assumption) 
    var i = firstChamberVar
    while (i < firstChamberVar + numChamberVars) { eq(i) = 1; i += 1 }
    
    // Define the array to hold the inequalities
    val ieqs = ArrayBuffer[Array[Int]]()
    val ieq = ArrayBuffer.fill[Int](numTotalVars)(0)
        
    // x(i) > x(i+1) for every x(i), x(i+1) in the chamber variables
    i = firstChamberVar
    while (i < firstChamberVar + numChamberVars - 1) {
      // Create equations in ieq
      ieq(i) = 1; ieq(i+1) = -1
      ieqs.append(ieq.toArray)
      ieq(i) = 0; ieq(i+1) = 0  // Reset ieq
      i += 1
    }
    return new PolyhedralCone(Array(eq.toArray), ieqs.toArray)
  }
  
  def momentPolyhedron(ieqs: HashSet[Inequality]): PolyhedralCone = {
    if (ieqs.size == 0) {
      return PolyhedralCone(Array(), Array())
    } else {
      val dimA = ieqs.head.u.length
      val dimB = ieqs.head.v.length
      val dimAB = dimA*dimB
      val totalDim = dimA + dimB + dimAB
      return PolyhedralCone(Array[Array[Int]](),
                            ieqs.map(_.toArray()).toArray).
             intersection(positiveWeylChamber(totalDim, 0, dimA)).
             intersection(positiveWeylChamber(totalDim, dimA, dimB)).
             intersection(positiveWeylChamber(totalDim, dimA+dimB, dimAB))
    }
  }
}

class Edge(val edge: Array[Int]) {
  /*
   * mult(v) -- Given (v(0), ..., v(n-1)) returns (c(0), ..., c(k))
   *            where c(0) is the number of consecutive entries equal to v(0),
   *            c(1) is the number of consecutive entries equal to next distinct
   *            element, etc. 
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
  
  override def toString: String = "(" + edge.mkString(", ") + ")"
}


class ABEdge(edge: Array[Int], val dimA: Int) extends Edge(edge) {
  assert(dimA <= edge.length)
  
  def dimB: Int = edge.length - dimA
  val A = edge.take(dimA).sorted(Ordering.Int.reverse)  // Decreasing order
  val B = edge.drop(dimA).sorted(Ordering.Int.reverse)
  
  override def equals(a: Any) = a match {
    case abedge: ABEdge => abedge.A.deep == this.A.deep && 
                           abedge.B.deep == this.B.deep
    case _ => false
  }
  
  override def hashCode() = this.A.deep.hashCode() + this.B.deep.hashCode()
  
  // Return a(i) + b(j) in decreasing order
  def AB: Array[Int] = {
    val ab = Array.tabulate[Int](dimA*dimB)(n => A(n%dimA) + B(n/dimA))
    scala.util.Sorting.quickSort(ab)
    return ab.reverse
  } 
      
  def ABTriples(): Array[(Int,Int,Int)] = {
    val ab = Array.tabulate[(Int, Int, Int)](dimA*dimB)(
                 n => (n%dimA, n/dimA, A(n%dimA)+ B(n/dimA)))
    return ab.sorted(Ordering.by[(Int, Int, Int), Int](-_._3)).toArray
  }

  def multA(): Array[Int] = mult(A)
  def multB(): Array[Int] = mult(B)
  def multAB(): Array[Int] = mult(AB)
  
  override def toString = 
      "((" + A.mkString(", ") + "), (" + B.mkString(", ") + "))"
}

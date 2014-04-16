package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashSet

import scala.util.Sorting

import ch.javasoft.polco.adapter._

/*
 * PolyhedralCone
 * 
 * WARNING: the lengths of the arrays in eqs and ieqs must be equal, but there
 *  is no check. Otherwise, an error can be thrown when calling edges(). 
 */
class PolyhedralCone(_eqs: Array[Array[Int]], val _ieqs: Array[Array[Int]]) {
  // Check that eqs and ineqs all have the same number of coefficients
  assert((_eqs ++ _ieqs).map(_.length).distinct.length <= 1)
  
  // The Polco library has a bug: the first equality cannot be all zeros
  // The Polco library sometimes gives errors for passing empty arrays
  private var i = 0
  while (i < _eqs.length && _eqs(i).length != 0 && _eqs(i).find(_ != 0) == None)
    i += 1
  val eqs = if (_eqs.length > i) {
    _eqs.drop(i)
  } else if (_ieqs.length > 0) {
    Array(Array.fill[Int](_ieqs(0).length)(0))
  } else {
    Array(Array(0))
  }
  
  // The Polco library sometimes gives errors for passing empty arrays
  val ieqs = if (_ieqs.length > 0) {
    _ieqs
  } else if (eqs.length > 0) {
    Array(Array.fill[Int](eqs(0).length)(0))
  } else {
    Array(Array(0))
  }
  
  private final val PAOpt = new Options()
  PAOpt.setLoglevel(java.util.logging.Level.OFF)
  private final val PA = new PolcoAdapter(PAOpt)
  
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
  
  def edges(): Array[Edge] = PA.getBigIntegerRays(eqs, ieqs).map(
      arr => new Edge(arr.map(_.intValue()))) 
  
  def edges(dimA: Int): ArrayBuffer[ABEdge] = {
    val es = ArrayBuffer[ABEdge]()
    for (r <- PA.getBigIntegerRays(eqs, ieqs)) {
      es += new ABEdge(r.map(_.intValue()), dimA)
    }
    return es
  }
  
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
   *                        The cone where consecutive variables decrease
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

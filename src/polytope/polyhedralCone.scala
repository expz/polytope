package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

import ch.javasoft.polco.adapter._

/*
 * PolyhedralCone
 * 
 * WARNING: the lengths of the arrays in eqs and ieqs must be equal, but there
 *  is no check. Otherwise, an error can be thrown when calling edges(). 
 */
class PolyhedralCone(val eqs: Array[Array[Int]], val ieqs: Array[Array[Int]]) {
  private final val PAOpt = new Options()
  PAOpt.setLoglevel(java.util.logging.Level.OFF)
  private final val PA = new PolcoAdapter(PAOpt)
  
  def intersection(P2: PolyhedralCone): PolyhedralCone = {
    return new PolyhedralCone(eqs ++ P2.eqs, ieqs ++ P2.ieqs)
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
  def apply(eqs: Array[Array[Int]], ieqs: Array[Array[Int]]) = new PolyhedralCone(eqs, ieqs)
  
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
    assert(firstChamberVar >= 0 && firstChamberVar < numTotalVars)
    assert(numChamberVars >= 0 && firstChamberVar + numChamberVars <= numTotalVars)
    
    // There will be a single equation
    val eq = ArrayBuffer.fill[Int](numTotalVars)(0)
    
    // Deal with the trivial cases separately
    if (numChamberVars == 0) {
      return new PolyhedralCone(Array[Array[Int]](), Array[Array[Int]]())
    } else if (numChamberVars == 1) {
      val ieqs = Array(eq.toArray)
      eq(firstChamberVar) = 1
      return new PolyhedralCone(Array(eq.toArray), ieqs)
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
}

class Edge(val edge: Array[Int]) {
  def mult(): Array[Int] = mult(edge)
  
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
}


class ABEdge(edge: Array[Int], val dimA: Int) extends Edge(edge) {
  assert(dimA <= edge.length)
  
  def dimB: Int = edge.length - dimA
  def A(): Array[Int] = edge.take(dimA)
  def A(i: Int): Int = { assert(i >= 0 && i < dimA); return edge(i) }
  def B(): Array[Int] = edge.drop(dimA)
  def B(i: Int): Int = { assert(i >= 0 && i < dimB); return edge(dimA+i)}
  
  def AB(): Array[Int] = {
    val ab = Array.tabulate[Int](dimA*dimB)(n => A(n%dimA) + B(n/dimB))
    scala.util.Sorting.quickSort(ab)
    return ab.reverse
  } 
      
  def ABTriples(): Array[(Int,Int,Int)] = {
    val ab = Array.tabulate[(Int, Int, Int)](dimA*dimB)(
                 n => (n%dimA, n/dimB, A(n%dimA)+ B(n/dimB)))
    return ab.sorted(Ordering.by[(Int, Int, Int), Int](-_._3)).toArray
  }

  def multA(): Array[Int] = mult(A())
  def multB(): Array[Int] = mult(B())
  def multAB(): Array[Int] = mult(AB())
  
  override def toString = 
      "((" + A().mkString(", ") + "), (" + B().mkString(", ") + "))"
}

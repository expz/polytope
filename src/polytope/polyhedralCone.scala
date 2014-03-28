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
}

object PolyhedralCone {  
  def apply(eqs: Array[Array[Int]], ieqs: Array[Array[Int]]) = new PolyhedralCone(eqs, ieqs)
  
  /*
   * positiveWeylChamber -- Return the positive Weyl chamber for the 
   *                        numChamberVars starting at firstChamberVar in a
   *                        total space of numTotalVars
   * 
   * @param numTotalVars                       
   * @param firstChamberVar starts counting at 0
   * @param numChamberVars
   */
  def positiveWeylChamber(numTotalVars: Int, firstChamberVar: Int, numChamberVars: Int): PolyhedralCone = {
    assert(numTotalVars >= 0)
    assert(firstChamberVar >= 0 && firstChamberVar < numTotalVars)
    assert(numChamberVars >= 0 && firstChamberVar + numChamberVars <= numTotalVars)
    val eq = ArrayBuffer.fill[Int](numTotalVars)(0)
    if (numChamberVars == 0) {
      return new PolyhedralCone(Array[Array[Int]](), Array[Array[Int]]())
    } else if (numChamberVars == 1) {
      val ieqs = Array(eq.toArray)
      eq(firstChamberVar) = 1
      return new PolyhedralCone(Array(eq.toArray), ieqs)
    }
    var i = firstChamberVar
    while (i < firstChamberVar + numChamberVars) { eq(i) = 1; i += 1 }
    val ieqs = ArrayBuffer[Array[Int]]()
    val ieq = ArrayBuffer.fill[Int](numTotalVars)(0)
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

/*
 * WARNING: No check that dimA <= edge.length
 */
class ABEdge(edge: Array[Int], val dimA: Int) extends Edge(edge) {
  def dimB: Int = edge.length - dimA
  def A(): Array[Int] = edge.take(dimA)
  def A(i: Int): Int = { assert(i >= 0 && i < dimA); return edge(i) }
  def B(): Array[Int] = edge.drop(dimA)
  def B(i: Int): Int = { assert(i >= 0 && i < dimB); return edge(dimA+i)}
  def AB() = edge
  def ABTriples(): Array[(Int,Int,Int)] = {
    val ab = ArrayBuffer[(Int, Int, Int)]()
    var i = 0
    var j = 0
    while (i < dimA) {
      j = 0
      while (j < dimB) {
        ab.append((i, j, edge(i) + edge(dimA+j)))
        j += 1
      }
      i += 1
    }
    return ab.sorted[(Int, Int, Int)](Ordering.by[(Int, Int, Int), Int](_._3)).toArray
  }
  
  def multA(): Array[Int] = mult(A())
  def multB(): Array[Int] = mult(B())
  def multAB(): Array[Int] = mult(edge)
}

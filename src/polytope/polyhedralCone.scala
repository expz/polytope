package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

import ch.javasoft.polco.adapter._

class PolyhedralCone(val eqs: Array[Array[Int]], val ieqs: Array[Array[Int]]) {
  def intersection(P2: PolyhedralCone): PolyhedralCone = {
    return new PolyhedralCone(eqs ++ P2.eqs, ieqs ++ P2.ieqs)
  }
  
  def edges(dimA: Int): ArrayBuffer[Edge] = {
    val PA = new PolcoAdapter()
    val es = ArrayBuffer[Edge]()
    for (r <- PA.getBigIntegerRays(eqs, ieqs)) {
      val a = r.take(dimA).map(_.intValue())
      val b = r.drop(dimA).map(_.intValue())
      es += new Edge(a, b)
    }
    return es
  }
}

object PolyhedralCone {
  def positiveWeylChamber(dimAB: Int, dimA: Int, dimB: Int): PolyhedralCone = {
    val eq = ArrayBuffer.fill[Int](dimAB)(0)
    var i = dimA
    while (i < dimA + dimB) { eq(i) = 1; i += 1 }
    val ieqs = ArrayBuffer[Array[Int]]()
    i = dimA+1
    val ieq = ArrayBuffer.fill[Int](dimAB)(0)
    while (i < dimA + dimB) {
      ieq(i) = 1; ieq(i+1) = -1
      ieqs.append(ieq.toArray)
      ieq(i) = 0; ieq(i+1) = 0
      i += 1
    }
    return new PolyhedralCone(Array(eq.toArray), ieqs.toArray)
  }
}

class Edge(a: Array[Int], b: Array[Int]) {
  def A(): Array[Int] = a
  def B(): Array[Int] = b
  def AB(): Array[(Int,Int,Int)] = {
    val ab = ArrayBuffer[(Int, Int, Int)]()
    var i = 0
    var j = 0
    while (i < a.length) {
    j = 0
    while (j < b.length) {
      ab.append((i, j, a(i)+b(j)))
    }
    }
    return ab.sorted[(Int, Int, Int)](Ordering.by[(Int, Int, Int), Int](_._3)).toArray
  }
  
  private def mult(v: Array[Int]): Array[Int] = {
    val m = ArrayBuffer[Int](1)
    var i = 1
    while (i < v.length-1) {
      if (v(i) == v(i-1)) m(m.length-1) += 1
      else m.append(1)
    }
    return m.toArray
  }
  
  def multA(): Array[Int] = mult(a)
  def multB(): Array[Int] = mult(b)
  def multAB(): Array[Int] = mult(AB().map(_._3))
}

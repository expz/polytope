package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

@RunWith(classOf[JUnitRunner])
class packageSuite extends UnitSpec {
  "isInteger" should "return true iff a polynomial is an integer." in {
    val b1 = isInteger(HashMap[Long,Int]())
    b1 should be (true)
    
    val b2 = isInteger(HashMap(0L->(-10)))
    b2 should be (true)
    
    val b3 = isInteger(HashMap(38L->0))
    b3 should be (true)
    
    val b4 = isInteger(HashMap(2L->0, 0L->3))
    b4 should be (true)
    
    val b5 = isInteger(HashMap(1024L->(-3)))
    b5 should be (false)
    
    val b6 = isInteger(HashMap(2L->4, 0L->1))
    b6 should be (false)
  }
  
  "toLehmerCode" should "convert a permutation to a Lehmer code." in {
    val lc1 = toLehmerCode(Array[Int]())
    val lc2 = toLehmerCode(Array(1))
    val lc3 = toLehmerCode(Array(1, 2, 3))
    val lc4 = toLehmerCode(Array(3, 1, 4, 2))
    
    lc1.to[ArrayBuffer] should be (ArrayBuffer())
    lc2.to[ArrayBuffer] should be (ArrayBuffer(0))
    lc3.to[ArrayBuffer] should be (ArrayBuffer(0, 0, 0))
    lc4.to[ArrayBuffer] should be (ArrayBuffer(2, 0, 1, 0))
  }
  
  "reducedWord" should "work for trivial permutations." in {
    val rw1 = reducedWord(Array())
    val rw2 = reducedWord(Array(1))
    val rw3 = reducedWord(Array(1, 2, 3))
    
    rw1.deep should be (Array().deep)
    rw2.deep should be (Array().deep)
    rw3.deep should be (Array().deep)
  }
  
  // Note that the reduced word decomposition is not unique. Only its length is.
  it should "work for non-trivial permutations." in {
    val rw1 = reducedWord(Array(2, 1))
    val rw2 = reducedWord(Array(3, 2, 1))
    val rw3 = reducedWord(Array(2, 4, 3, 1))
    
    rw1.deep should be (Array(1).deep)
    rw2.length should be (3)
    rw2.toSet should be (Set(1, 2))
    rw3.length should be (4)
  }
  
  "delta" should "be the identity for the identity permutation." in {
    val f1 = delta(Array(), HashMap[Long, Int](), 0)
    val f2 = delta(Array(), HashMap(2L->2, 17L->1), 0)
    val f3 = delta(Array(1, 2, 3), HashMap[Long, Int](), 0)
    val f4 = delta(Array(1, 2, 3), 
                            HashMap(3L->4, 0L->1, 18L->2, 260L->1), 
                            0)
    
    f1 should be (HashMap[Long,Int]())
    f2 should be (HashMap(2L->2, 17L->1))
    f3 should be (HashMap[Long,Int]())
    f4 should be (HashMap(3L->4, 0L->1, 18L->2, 260L->1))
  }
  
  "delta" should "work for non-trivial permutations." in {
    // - x^2*y - x*y^2 
    val f0 = delta(Array(2, 1), HashMap(18L->(-1), 33L->(-1)), 0)
    // 2*x^2*y - 2*x*y^2
    val f1 = delta(Array(2, 1), HashMap(18L->2, 33L->(-2)), 0)
    // 2*x^2*y*z^2*w + 2*x^2*y*z*w^2
    val f2 = delta(Array(2, 1), HashMap(4626L->2, 8466L->2), 2)
    
    isZero(f0) should be (true)
    f1 should be (HashMap(17L->4))
    isZero(f2) should be (true)
  }
  
  "collectTerms" should "collect terms into a HashMap." in {
    val p1 = ArrayBuffer[Long]()
    val p2 = ArrayBuffer(0L)
    val p3 = ArrayBuffer(18L, 20L, 18L, 288L, 20L)
    val p4 = ArrayBuffer(1L, 2L, 3L)
    
    collectTerms(p1) should be (HashMap[Long,Int]())
    collectTerms(p2) should be (HashMap(0L->1))
    collectTerms(p3) should be (HashMap(18L->2, 20L->2, 288L->1))
    collectTerms(p4) should be (HashMap(1L->1, 2L->1, 3L->1))
  }
  
  "subst" should "substitute x(i) + y(j) into a polynomial" + 
                 " according to a tableau T(k) = (i, j)." in {
    val f1 = HashMap()
    
  }
  
  "hashMapToString" should "return a string expressing a polynomial" + 
                           " in lexicographic order." in {
    val f1 = HashMap[Long,Int]()
    val f2 = HashMap(2L->0)
    val f3 = HashMap(0L->(-2))
    val f4 = HashMap(2L->3)
    val f5 = HashMap(1L->1, 17L->1, 33L->3, 34L->1)
    
    hashMapToString(f1).toString should be ("0")
    hashMapToString(f2).toString should be ("0")
    hashMapToString(f3).toString should be ("-2")
    hashMapToString(f4).toString should be ("3*x0^2")
    // Lexicographic order
    hashMapToString(f5).toString should be ("x0 + x0*x1 + 3*x0*x1^2 + x0^2*x1^2")
  }
  
  "inverse" should "invert a permutation." in {
    val p1 = Array[Int]()
    val p2 = Array(1)
    val p3 = Array(2, 3, 1)
    
    inverse(p1).deep should be (Array[Int]().deep)
    inverse(p2).deep should be (Array(1).deep)
    inverse(p3).deep should be (Array(3, 1, 2).deep)
  }
}

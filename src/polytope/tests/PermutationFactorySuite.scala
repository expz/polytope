package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer

object Timer {
  def time[A](a: => A): Long = {
    val start = System.nanoTime
    val result = a
    val nanos = System.nanoTime - start
    nanos
  }
}

@RunWith(classOf[JUnitRunner])
class PermutationFactorySuite extends UnitSpec {
  
  "PermutationFactory" should 
    "produce an empty array when given no multiplicities." in {
    PermutationFactory.shuffles(Array[Int]()).length should be (0)
  }
  
  it should "produce a single shuffle for trivial multiplicities." in {
    val m1 = Array(1)
    val m2 = Array(3)
    
    PermutationFactory.shuffles(m1).length should be (1)
    PermutationFactory.shuffles(m1).toArray.deep should be (Array(Array(1)).deep)
    PermutationFactory.shuffles(m2).length should be (1)
    PermutationFactory.shuffles(m2)(0).sameElements(Array(1,2,3)) should be (true)    
  }

  it should "correctly calculate shuffles for two or three multiplicites." in {
    val m1 = Array(2, 2)
    val m2 = Array(1, 1, 1)
    val m3 = Array(2, 1, 2)
    
    val res1 = PermutationFactory.shuffles(m1)
    val res2 = PermutationFactory.shuffles(m2)
    val res3 = PermutationFactory.shuffles(m3)
    
    res1.length should be (6)
    res1.map(_.to[ArrayBuffer]).toSet should be (
        Set(
            ArrayBuffer(1, 2, 3, 4), ArrayBuffer(1, 3, 2, 4), 
            ArrayBuffer(1, 3, 4, 2), ArrayBuffer(3, 1, 2, 4), 
            ArrayBuffer(3, 1, 4, 2), ArrayBuffer(3, 4, 1, 2)
        ))
    res2.length should be (6)
    res2.map(_.to[ArrayBuffer]).toSet should be (
        Set(
            ArrayBuffer(1, 2, 3), ArrayBuffer(1, 3, 2), ArrayBuffer(2, 1, 3),
            ArrayBuffer(2, 3, 1), ArrayBuffer(3, 1, 2), ArrayBuffer(3, 2, 1)
        ))
    res3.length should be (30)
  }
  
  /* WARNING: Requires lots of memory
  it should "calculate shuffles of length twelve within two seconds." in {
    val m1 = Array(2, 1, 2, 1, 3, 2)
    val m2 = Array(1, 2, 2, 1, 2, 1, 2)
    
    var res1: ArrayBuffer[Permutation] = null
    var res2: ArrayBuffer[Permutation] = null
    
    val t1 = Timer.time(res1 = PermutationFactory.shuffles(m1))/1000000000
    val t2 = Timer.time(res2 = PermutationFactory.shuffles(m2))/1000000000 
    
    println(res1.length)
    println(res2.length)

    t1 should be < 2L
    t2 should be < 2L    
  }
  */
}
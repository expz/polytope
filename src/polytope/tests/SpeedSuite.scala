package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet

@RunWith(classOf[JUnitRunner])
class SpeedSuite extends UnitSpec {
  /*
   * Timing functions
   */
  def printMicroTime[A](a: => A) = {
    val start = System.nanoTime
    val result = a
    val micros = (System.nanoTime - start) / 1000
    println("%d microseconds".format(micros))
    result
  }
  
  def printNanoTime[A](a: => A) = {
    val start = System.nanoTime
    val result = a
    val nanos = System.nanoTime - start
    println("%d nanoseconds".format(nanos))
    result
  }
  
  def returnMicroTime[A](a: => A) = returnNanoTime(a) / 1000
  
  def returnNanoTime[A](a: => A): Long = {
    val start = System.nanoTime
    val result = a
    val nanos = System.nanoTime - start
    nanos
  }
  
  def avgMicroTime(f: => Unit, runs: Int = 1000) = avgNanoTime(f, runs) / 1000
  
  def avgNanoTime(f: => Unit, runs: Int = 1000): Long = {
    val warmupRuns = runs/10
    var tot = 0L
    var count = 0
    for (k <- (1 to runs)) { 
      val t = returnNanoTime { f } 
      if (k > warmupRuns) {
        // Reset count if the time decreased by at least 50%
        if (count != 0 && t < (tot/count) / 1.5) {
          count = 1
          tot = t
        // Count the test if its the first one or if the time was not too high
        //   This avoids counting run times skewed by garbage collection
        } else if (count == 0 || t < 1.5*(tot/count)) { 
          count += 1
          tot += t
        }
      } 
    }
    return tot/count 
  }
  
  /*
   * Functions to test
   */
  val n = 10
  def f1(): Unit = {
    (1 to n).toArray
  }
  def f2(): Unit = {
    Array.tabulate[Int](n)(i => i)
  }
  
  "My test code" should "be timed" in {
    println("f1: " + avgNanoTime(f1, 1000))
    println("f2: " + avgNanoTime(f2, 1000))
  }
}

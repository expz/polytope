package polytope.tests

import org.junit.runner.RunWith

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import polytope._

import scala.collection.immutable.Set
import scala.collection.mutable.ArrayBuffer


@RunWith(classOf[JUnitRunner])
class SchubertFactorySuite extends UnitSpec {

  "SchubertFactory" should "calculate a schubert polynomial." in {
    println(hashMapToString(collectTerms(SchubertFactory.schubertPolynomial(Array(2, 4, 5, 1, 3, 6, 7)))))
  }

}

@Ignore
class SchubertFactorBenchmark extends UnitSpec {
  def time[R](block: => R): R = {
      val t0 = System.nanoTime()
      val result = block
      val t1 = System.nanoTime()
      println("Elapsed time: " + Math.round((t1 - t0)/1000.0) + "μs")
      result
  }

  "SchubertFactory" should "be timed at calculating schubert polynomials." in {
    println("Iterate Calculation 20 Times")
    Vector.range(0, 20).foreach(_ =>
      time { SchubertFactory.schubertPolynomial(Array(3, 1, 4, 5, 2)) }
    )
    // allow garbage collection
    Thread sleep 200
    println("Iterate Calculation 20 Times")
    Vector.range(0, 20).foreach(_ =>
      time { 
        SchubertFactory.schubertPolynomial(Array(3, 1, 4, 5, 2))
      }
    )
    // allow garbage collection
    Thread sleep 200
    println("Iterate Calculation 20 Times")
    Vector.range(0, 20).foreach(_ =>
      time {
        SchubertFactory.schubertPolynomial(
          Array(9, 3, 5, 1, 10, 2, 4, 8, 6, 7))
      }
    )
    // allow garbage collection
    Thread sleep 200
    println("Iterate 4 Calculations 10 Times")
    Vector.range(0, 10).foreach(_ => {
      time {
        SchubertFactory.schubertPolynomial(
          Array(9, 3, 5, 1, 10, 2, 4, 8, 6, 7))
      }
      time {
        SchubertFactory.schubertPolynomial(
          Array(8, 2, 4, 9, 1, 10, 3, 6, 7, 5))
      }
      time {
        SchubertFactory.schubertPolynomial(
          Array(9, 1, 3, 4, 2, 10, 7, 8, 6, 5))
      }
      time {
        SchubertFactory.schubertPolynomial(
          Array(6, 2, 3, 7, 1, 4, 8, 10, 5, 9))
      }
    })
      /* too slow?
      time {
        SchubertFactory.schubertPolynomial(
          Array(10, 5, 2, 4, 15, 1, 3, 16, 12, 13, 9, 6, 7, 14, 8, 11))
      }
      time {
        SchubertFactory.schubertPolynomial(
          Array(2, 7, 13, 10, 5, 9, 1, 12, 16, 13, 8, 4, 3, 6, 11, 15))
      }
      time {
        SchubertFactory.schubertPolynomial(
          Array(1, 5, 4, 10, 3, 2, 12, 13, 16, 8, 7, 11, 9, 15, 9, 6))
      }
      */
  }

  "SchubertFactory" should "be timed on regular families of permutations of increasing length." in {
    val shortmed = Array(
      Array[Int](2, 1),
      Array[Int](3, 2, 1),
      Array[Int](4, 3, 2, 1),
      Array[Int](5, 4, 3, 1, 2),
      Array[Int](6, 5, 4, 1, 2, 3),
      Array[Int](7, 6, 5, 1, 2, 3, 4),
      Array[Int](8, 7, 6, 1, 2, 3, 5, 4),
      Array[Int](9, 8, 7, 1, 2, 3, 6, 5, 4),
      Array[Int](10, 9, 8, 1, 2, 3, 7, 6, 5, 4),
      Array[Int](11, 10, 9, 1, 2, 3, 8, 7, 6, 4, 5),
      Array[Int](12, 11, 10, 1, 2, 3, 9, 8, 7, 4, 5, 6),
      Array[Int](13, 12, 11, 1, 2, 3, 10, 9, 8, 4, 5, 6, 7),
      Array[Int](14, 13, 12, 1, 2, 3, 11, 10, 9, 4, 5, 6, 8, 7),
      Array[Int](15, 14, 13, 1, 2, 3, 12, 11, 10, 4, 5, 6, 9, 8, 7),
      Array[Int](16, 15, 14, 1, 2, 3, 13, 12, 11, 4, 5, 6, 10, 9, 8, 7)
    )
    val med = Array(
      Array[Int](2, 1),
      Array[Int](3, 2, 1),
      Array[Int](4, 3, 1, 2),
      Array[Int](5, 4, 1, 2, 3),
      Array[Int](6, 5, 1, 2, 4, 3),
      Array[Int](7, 6, 1, 2, 5, 4, 3),
      Array[Int](8, 7, 1, 2, 6, 5, 3, 4),
      Array[Int](9, 8, 1, 2, 7, 6, 3, 4, 5),
      Array[Int](10, 9, 1, 2, 8, 7, 3, 4, 6, 5),
      Array[Int](11, 10, 1, 2, 9, 8, 3, 4, 7, 6, 5),
      Array[Int](12, 11, 1, 2, 10, 9, 3, 4, 8, 7, 5, 6),
      Array[Int](13, 12, 1, 2, 11, 10, 3, 4, 9, 8, 5, 6, 7),
      Array[Int](14, 13, 1, 2, 12, 11, 3, 4, 10, 9, 5, 6, 8, 7),
      Array[Int](15, 14, 1, 2, 13, 12, 3, 4, 11, 10, 5, 6, 9, 8, 7),
      Array[Int](16, 15, 1, 2, 14, 13, 3, 4, 12, 11, 5, 6, 10, 9, 7, 8)
    )
    val long = Array(
      Array[Int](2, 1),
      Array[Int](3, 1, 2),
      Array[Int](4, 1, 3, 2),
      Array[Int](5, 1, 4, 2, 3),
      Array[Int](6, 1, 5, 2, 4, 3),
      Array[Int](7, 1, 6, 2, 5, 3, 4),
      Array[Int](8, 1, 7, 2, 6, 3, 5, 4),
      Array[Int](9, 1, 8, 2, 7, 3, 6, 4, 5),
      Array[Int](10, 1, 9, 2, 8, 3, 7, 4, 6, 5),
      Array[Int](11, 1, 10, 2, 9, 3, 8, 4, 7, 5, 6),
      Array[Int](12, 1, 11, 2, 10, 3, 9, 4, 8, 5, 7, 6),
      Array[Int](13, 1, 12, 2, 11, 3, 10, 4, 9, 5, 8, 6, 7),
      Array[Int](14, 1, 13, 2, 12, 3, 11, 4, 10, 5, 9, 6, 8, 7),
      Array[Int](15, 1, 14, 2, 13, 3, 12, 4, 11, 5, 10, 6, 9, 7, 8),
      Array[Int](16, 1, 15, 2, 14, 3, 13, 4, 12, 5, 11, 6, 10, 7, 9, 8)
    )
  
    def testPerm(p: Permutation) = {
      val result = time { SchubertFactory.schubertPolynomial(p) }
      println("ArrayBuffer Length: " + result.length)
      println("Number of Terms: " + collectTerms(result).size)
    }
    
    def testPerms(ps: Array[Permutation]) = {
      for (k <- (0 to 11)) {
        testPerm(ps(k))
      }
    }
    
    testPerms(shortmed)
    testPerms(med)
    testPerms(long)
    
  }
}

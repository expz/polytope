package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Range
import scala.collection.immutable.Vector

import scala.reflect.runtime.universe._
import scala.reflect.ClassTag

/*
 *  TODO run from command line with enough memory
 *  TODO change polynomial to the fastest collection for updates: HashSet? Vector?
 *  TODO estimate of number of recursions: plot run time, memory usage, no. of recursions for Vector(1, 2, 3, 4, ..., n)
 *    Memory usage: construct collection, call System.gc, open Eclipse MAT 
 *  TODO is it possible to encode the situation as BitSet?
 *  TODO is it possible to save the intermediate calculations?
 *  TODO should I save all the vectors in a single hashmap to prevent garbage collection?
 *  TODO How much memory does each node have?
 *  TODO If I run 8 copies of the algorithm, one per core, will the node run out of memory? If so, should I parallelize the algorithm?
 */

object SchubertFactory {
  
  def readPermutation(): Permutation = {
    println("Schubert Polynomial Calculator")
    println("Please enter the number of symbols: ")
    val n = readInt()
    return Array.tabulate(n){i => { 
        printf("%d => ", i+1)
        readInt()
    }} 
  }
  
  // Makes no attempt to check that the Permutation is actually a Permutation
  // i.e., that it contains every number 1..n exactly once
  def schubertPolynomial(perm: Permutation): Polynomial = { 
      if (isIdentity(perm)) return ArrayBuffer[Term]()
      
      val leadFactor: Term = 0L
      return schubertAlgorithm(leadFactor, 0, perm.length-1, perm)
  }
  
  def schubertAlgorithm(leadFactor: Term, index: Int, exponent: Int, perm: Permutation): Polynomial = {    
    // The code actually runs slower when you only define result when its needed
    val result = ArrayBuffer[Term]()
    
    // Set limits of the optimized code
    /*
    require(index < 16)
    require(exponent < 16)
    require(perm.length < 16)
    */
    if (perm.length == 2) {
      if (perm(0) == 2) {
        result.append(incExp(leadFactor, index))
      } else {
        result.append(leadFactor)
      }
    } else if (perm(0) == perm.length) {
        val newPerm: Permutation = perm.drop(1)
        val newLeadFactor: Term = changeExp(leadFactor, index, exponent)
        return schubertAlgorithm(newLeadFactor, index+1, newPerm.length - 1, newPerm)
    } else {
        var max: Int = perm.length + 1
        var i: Int = 1
        while (i < perm.length) {
            if (perm(i) < max && perm(i) > perm(0)) {
              max = perm(i)
              val newPerm: Permutation = perm.updated(0, perm(i)).updated(i, perm(0))
                addInPlace(result, schubertAlgorithm(leadFactor, index, exponent-1, newPerm))
            }
            i += 1
        }
    }
    return result
  }
  
  /*
   * Test performance of calculation of Schubert polynomials
   */
  def time[R](block: => R): R = {
      val t0 = System.nanoTime()
      val result = block
      val t1 = System.nanoTime()
      println("Elapsed time: " + Math.round((t1 - t0)/1000.0) + "μs")
      result
  }
  def test() = {
    
      Vector.range(0, 20).foreach(
              _ =>
          time { SchubertFactory.schubertPolynomial(Array(3, 1, 4, 5, 2)) }
          )
      Thread sleep 200
      println("===")
      Vector.range(0, 10).foreach( _ => {
        time { SchubertFactory.schubertPolynomial(Array(9, 3, 5, 1, 10, 2, 4, 8, 6, 7)) }
        time { SchubertFactory.schubertPolynomial(Array(8, 2, 4, 9, 1, 10, 3, 6, 7, 5)) }
        time { SchubertFactory.schubertPolynomial(Array(9, 1, 3, 4, 2, 10, 7, 8, 6, 5)) }
        time { SchubertFactory.schubertPolynomial(Array(6, 2, 3, 7, 1, 4, 8, 10, 5, 9)) }
      })
      println("====")
      Thread sleep 200
      //time { SchubertFactory.schubertPolynomial(Array(10, 5, 2, 4, 15, 1, 3, 16, 12, 13, 9, 6, 7, 14, 8, 11))}
      //time { SchubertFactory.schubertPolynomial(Array(2, 7, 13, 10, 5, 9, 1, 12, 16, 13, 8, 4, 3, 6, 11, 15))}
      //time { SchubertFactory.schubertPolynomial(Array(1, 5, 4, 10, 3, 2, 12, 13, 16, 8, 7, 11, 9, 15, 9, 6))}
      
      Vector.range(0, 20).foreach(
              _ =>
          time { SchubertFactory.schubertPolynomial(Array(3, 1, 4, 5, 2)) }
          )
      Thread sleep 200
      Vector.range(0, 20).foreach(
              _ =>
          time { SchubertFactory.schubertPolynomial(Array(9, 3, 5, 1, 10, 2, 4, 8, 6, 7)) }
          )
      println(polyToString(SchubertFactory.schubertPolynomial(Array(1,4,3,2))))
      println(polyToString(SchubertFactory.schubertPolynomial(Array(2,4,1,3))))
      println(polyToString(SchubertFactory.schubertPolynomial(Array(1,3,4,2))))
      println(polyToString(SchubertFactory.schubertPolynomial(Array(1,4,2,3))))
      println(polyToString(SchubertFactory.schubertPolynomial(Array(1,2,3,4))))
      println(polyToString(SchubertFactory.schubertPolynomial(Array(2,1,3,4))))
      println(polyToString(SchubertFactory.schubertPolynomial(Array(4,3,2,1))))

      
      val shortmed = Vector(
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
    
    /*
    testPerms(shortmed)
    testPerms(med)
    testPerms(long)
    */
  }
}
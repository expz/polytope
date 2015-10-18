package polytope

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Range
import scala.collection.immutable.Vector

import scala.reflect.runtime.universe._
import scala.reflect.ClassTag

/**
  * Collection of algorithms for generating Schubert polynomials.
  */
object SchubertFactory {
  
  /**
    * Returns a permutation read from the standard input.
    */
  def readPermutation(): Permutation = {
    println("Schubert Polynomial Calculator")
    println("Please enter the number of symbols: ")
    val n = scala.io.StdIn.readInt
    return Array.tabulate(n){i => { 
        printf("%d => ", i+1)
        scala.io.StdIn.readInt
    }} 
  }
  
  /**
    * Returns the Schubert polynomial associated with a given permutation.
    * 
    * Makes no attempt to check that the Permutation is actually a Permutation
    * i.e., that it contains every number 1..n exactly once.
    *
    * @param perm The permutation.
    * @returns The Schubert polynomial of `perm`.
    */
   def schubertPolynomial(perm: Permutation): Polynomial = { 
      if (isIdentity(perm)) return ArrayBuffer[Term](0L)
      
      val leadFactor: Term = 0L
      return schubertAlgorithm(leadFactor, 0, perm.length-1, perm)
  }
 
  /**
    * Algorithm adapted from the C source code of the Symmetrica 
    * library which is freely available from
    *   http://www.algorithm.uni-bayreuth.de/en/research/SYMMETRICA/
    */
  def schubertAlgorithm(leadFactor: Term, index: Int, exponent: Int, 
                        perm: Permutation): Polynomial = {    
    // The code actually runs slower when you only define result when it's needed
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
      return schubertAlgorithm(newLeadFactor, index+1, newPerm.length - 1, 
                               newPerm)
    } else {
      var max: Int = perm.length + 1
      var i: Int = 1
      while (i < perm.length) {
        if (perm(i) < max && perm(i) > perm(0)) {
          max = perm(i)
          val newPerm: Permutation = 
                                    perm.updated(0, perm(i)).updated(i, perm(0))
          addInPlace(result, 
                     schubertAlgorithm(leadFactor, index, exponent-1, newPerm))
        }
        i += 1
      }
    }
    return result
  }
}

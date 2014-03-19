package polytope

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

object PermutationFactory {
  def shiftedShuffleProduct(w1: Permutation, w2: Permutation): ArrayBuffer[Permutation] = {
    distinctShuffleProduct((w1 ++ w2.map(k => k + w1.length)).to[ArrayBuffer], w1.length)
  }
  
  def distinctShuffleProduct[A](seq: ArrayBuffer[A], left: Int)
    (implicit T: ClassTag[A]): ArrayBuffer[Array[A]] = {
    val ab = ArrayBuffer[Array[A]]()
    if (left == 0) return ArrayBuffer(seq.toArray[A])
    for (leftShift <- 1 to left) {
      val temp = seq(left-leftShift)
      seq(left-leftShift) = seq(left-leftShift+1)
      seq(left-leftShift+1) = temp
      ab += seq.toArray
      if (seq.length > left+1)
        distinctShuffleProduct(seq.drop(left-leftShift+1), leftShift).foreach(
            a => ab += (seq.take(left-leftShift+1).toArray ++ a)
            )
    }
    return ab
  }
  
  def shuffles(mults: Array[Int]): ArrayBuffer[Permutation] = {
    val S1 = ArrayBuffer[Permutation]((1 to mults(0)).toArray)
    val S2 = ArrayBuffer[Permutation]()
    var i = 1
    var odd = true
    while (i < mults.length) {
      if (odd) {
        S2.clear()
        for (w <- S1) {
          S2 ++= shiftedShuffleProduct(w, (1 to mults(i)).toArray)   // shuffle product of [1, ..., mults[i]]
        }
      } else {
        S1.clear()
        for (w <- S2) {
          S1 ++= shiftedShuffleProduct(w, (1 to mults(i)).toArray)  // shuffle product of [1, ..., mults[i]]
        }
      }
      odd = !odd
    }
    if (odd) return S2
    else return S1
  }
  
  def shuffles_of_given_length(mults: Array[Int], numInversions: Int): ArrayBuffer[Permutation] = {
    val perms = ArrayBuffer[Permutation]()
    val n = mults.sum    // Length of permutations (Number of symbols)
    val dontPass = ArrayBuffer[Int]()
    var total: Int = 0
    for (m <- mults) {
      total += m
      dontPass.appendAll(for (i <- 1 to m) yield total)
    }
    
    /*
    Generate all permutations of the ordered list S with k inversions and add them to perms
    (after adding the optional suffix).
    */  
    @inline
    def delete[A](v: Array[A], n: Int) = v.filter(_ != n)
    
    def gen(S: Array[Int], k: Int, suffix: Array[Int]): Unit = {
      if (k == 0) {
        perms.append(S ++ suffix)
      } else {
        val n = S.length
        for (i <- (1 to n)) {
          if (n - (i+1) <= k && k <= binomial(n-1, 2) + n - (i+1)) {
          // If moving S[i] to the suffix does not break the shuffle property
          if (i+1 >= S.length || S(i+1) > dontPass(i))
            gen(delete(S, i), k - n + (i+1), suffix :+ S(i))
        }
      }
      }
    }
    gen((1 to n).toArray, numInversions, Array[Int]())
    return perms
  }
  
}

package polytope

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

/**
  * Contains algorithms for generating various types of permutation such
  * as shuffles and shuffle products.
  */
object PermutationFactory {
  /** Returns ArrayBuffer of all permutations on n letters
    *  
    * @param n The size (number of elements) of the permutations.
    * @returns All permutations on n letters
    */
  def permutationsOfSize(n: Int): ArrayBuffer[Permutation] = 
        ArrayBuffer[Permutation]() ++ Array.tabulate[Int](n)(_+1).permutations
 
  /** Returns the shiffted shuffle product of two permutations.
    *
    * @param w1 A permutation.
    * @param w2 A permutation.
    * @returns The shifted shuffle product of `w1` and `w2`.
    */
  def shiftedShuffleProduct(w1: Permutation, w2: Permutation): 
        ArrayBuffer[Permutation] = {
    distinctShuffleProduct((w1 ++ w2.map(k => k + w1.length)).to[ArrayBuffer], 
                           w1.length)
  }
  
  /**
    * Returns the shuffle product of two permutations, all of whose labels are
    * unique.
    *
    * @param seq
    * @param left
    * @returns
    */
  def distinctShuffleProduct[A](seq: ArrayBuffer[A], left: Int)
    (implicit T: ClassTag[A]): ArrayBuffer[Array[A]] = {
    val ab = ArrayBuffer[Array[A]](seq.toArray[A])
    if (left == 0) return ab
    for (leftShift <- 1 to left) {
      val temp = seq(left-leftShift)
      seq(left-leftShift) = seq(left-leftShift+1)
      seq(left-leftShift+1) = temp
      if (seq.length > left+1)
        distinctShuffleProduct(seq.drop(left-leftShift+1), leftShift).foreach(
            a => ab += (seq.take(left-leftShift+1).toArray ++ a)
            )
      else
        ab += seq.toArray
    }
    return ab
  }

  /**
    * Returns an ArrayBuffer of all shuffles with given multiplicities.
    *
    * @param mults
    * @returns
    */
  def shuffles(mults: Array[Int]): ArrayBuffer[Permutation] = {
    val S2 = ArrayBuffer[Permutation]()
    if (mults.length == 0) return S2
    val S1 = ArrayBuffer[Permutation]((1 to mults(0)).toArray)
    var i = 1
    var odd = false
    while (i < mults.length) {
      odd = !odd      
      if (odd) {
        S2.clear()
        for (w <- S1) {
          // shuffle product of [1, ..., mults[i]]
          S2 ++= shiftedShuffleProduct(w, (1 to mults(i)).toArray)
        }
      } else {
        S1.clear()
        for (w <- S2) {
          // shuffle product of [1, ..., mults[i]]
          S1 ++= shiftedShuffleProduct(w, (1 to mults(i)).toArray)
        }
      }
      i += 1
    }
    if (odd) return S2
    else return S1
  }
 
  /**
    * Returns all shuffls of a given length.
    *
    * @param mults
    * @param numInversions
    * @returns All permutations with multiplicities `mults` with exactly
    *   `numInversions` inversions.
    */
  def shufflesOfGivenLength(mults: Array[Int], numInversions: Int): 
        ArrayBuffer[Permutation] = {
    // Buffer to hold the generated shuffles
    val perms = ArrayBuffer[Permutation]()
    
    // Size of permutations (Number of symbols)
    val n = mults.sum    
    
    // When moving S(i) to the beginning of the suffix, it should not pass any
    // integer less than or equal to dontPass(i)
    val dontPass = ArrayBuffer[Int]()
    var total: Int = 0
    var i = 0
    while (i < mults.length) {
      total += mults(i)
      var j = 1
      while (j <= mults(i)) {
        dontPass.append(total)
        j += 1
      }
      i += 1
    }
    
    // Generate the shuffles
    moveToSuffix(Array.tabulate[Int](n)(_+1), numInversions, Array[Int]())
    
    //////////////////////////////////
    // HELPER FUNCTIONS
    
    // WARNING: No check that index is within valid range
    def delete(v: Array[Int], index: Int) = 
        Array.tabulate(v.length-1)(n => if (n < index) v(n) else v(n+1)) 
    //def delete[A](v: Array[A], index: Int) = 
    //    v.dropRight(v.length-index) + v.drop(index+1)
      
    /*
     * 
     * For every element of S 
     *   If it doesn't break the shuffle property
     *     Move it to the front of the suffix
     *     Then recurse
     *
     * Begin with:
     * 
     * 1, 2, 3, 4 + (empty)
     * remainInv = 4
     * 
     * 
     * Recurse to:
     * 
     * S = 1, 3, 4 + 2
     * remainInv = 2
     * 
     * S = 1, 2, 3 + 4
     * remainInv = 4
     *
     */    
    def moveToSuffix(S: Array[Int], remainInv: Int, suffix: Array[Int]): Unit = {
      if (remainInv == 0) {
        perms.append(S ++ suffix)
      } else {
        var hops = 0
        val n = S.length
        var i = 0
        while (i < n) {
          hops = n - i - 1
          if (hops <= remainInv && 
              remainInv <= Arithmetic.binomial(n-1, 2) + hops) {
            // If moving S[i] to the suffix does not break the shuffle property
            if (i == n-1 || S(i+1) > dontPass(S(i)-1))
              moveToSuffix(delete(S, i), remainInv - hops, S(i) +: suffix)
          }
          i += 1     
        }
      }
    }
    
    // Return shuffles
    return perms
  }
}

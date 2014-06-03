#!/bin/bash
exec scala \
-Djava.library.path="../lib/linux_x86" \
-classpath "../bin:../lib:../lib/linux_x86/com.google.ortools.jar" \
"$0" "$@"
!#

/** USAGE: ./coeffTest1.scala DIM_A DIM_B
  * where DIM_A*DIM_B < 16
  */
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import polytope._

/** For various shuffles w, prints \delta_w(z_1^{n-1}...z_{n-1}) 
  * with the variables permuted by t_0, t_1, ..., t_l coming 
  * from a cubicle 
  */
object AllCoeffs extends App {
  def swap(m: Long, i: Int): Long = {
    val expi = getExp(m, i)
    val expi1 = getExp(m, i+1)
    return changeExp(changeExp(m, i, expi1), i+1, expi)
  }
  val dims = List(args(0).toInt, args(1).toInt)
  val cubicles: ListBuffer[Permutation] = InequalityFactory.cubiclesDM(dims).map(T => reducedWord(T.toMatrix.flatten.toArray))
  val desc: Array[Long] = Array.tabulate[Long](dims(0)*dims(1)-1)(k => dims(0)*dims(1) - 1L - k)
  val monomial: Long = desc.foldLeft(0L)((sum, k) => sum + (k << ((dims(0)*dims(1)-1-k)*4)))
  val monoms: ListBuffer[Long] = cubicles.map(_.foldLeft(monomial)((m, i) => swap(m, i)))

  println("w;delta_w(T(z(1)^(n-1)...z(n-1)))")

  for (w <- PermutationFactory.permutationsOfSize(dims(0)*dims(1))) {
    val wr = reducedWord(w)
    if (wr.length <= dims(0) + dims(1)) {
      for (m <- monoms) { 
        println(wr.mkString(",") + ";" + 
                hashMapToString(delta(w, HashMap(m -> 1), 0)))
      }
    }
  }
}

// Run the code
AllCoeffs.main(args)

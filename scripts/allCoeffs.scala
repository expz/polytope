#!/bin/bash
exec scala \
-Djava.library.path="../lib/linux_x86" \
-classpath "../bin:../lib:../lib/linux_x86/com.google.ortools.jar" \
"$0" "$@"
!#

/** USAGE: ./allCoeffs DIM_A DIM_B
  *
  */
import scala.collection.mutable.ArrayBuffer
import polytope._

/** Prints all coefficients c_{uv}^w(T) for all u, v and
  * for all w such that l(w) = l(u) + l(v) and all cubicles T 
  *
  */
object AllCoeffs extends App {
  val dims = List(args(0).toInt, args(1).toInt)
  val cubicles = InequalityFactory.cubiclesDM(dims)
  val cs = ArrayBuffer[PFCoefficient]()
  for (u <- PermutationFactory.permutationsOfSize(dims(0))) {
    for (v <- PermutationFactory.permutationsOfSize(dims(1))) {
      for (w <- PermutationFactory.permutationsOfSize(dims(0)*dims(1))) {
        val (ur, vr, wr) = (reducedWord(u), reducedWord(v), reducedWord(w))
        if (wr.length == ur.length + vr.length) {
          for (T <- cubicles) {
            val cc = hashMapToString(InequalityFactory.c(u, v, w, T), 2, 2)
            println(ur.mkString(",") + ";" + vr.mkString(",") + ";" + wr.mkString(",") + ";" + T.toCSV + ";" + cc)
          }
        }
      }
    }
  }
}

// Run the code
AllCoeffs.main(args)

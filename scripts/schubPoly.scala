#!/bin/bash
exec scala \
-Djava.library.path="../lib/linux_x86" \
-classpath "../bin:../lib:../lib/linux_x86/com.google.ortools.jar" \
"$0" "$@"
!#

/** USAGE: ./coeffTest1.scala p1 p2 p3 ...
  * p1 p2 p3 ... is a permutation of 1 2 3 ...
  */
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import polytope._

/** Print the Schubert polynomial associated with the given permutation
  */
object SchubPoly extends App {
  val w = args.map(_.toInt)
  
  val wr = reducedWord(w)
  val Sw = SchubertFactory.schubertPolynomial(w)
  println(hashMapToString(collectTerms(Sw)))
}

// Run the code
SchubPoly.main(args)

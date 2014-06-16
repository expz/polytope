#!/bin/bash
exec scala \
-Djava.library.path="../lib/linux_x86" \
-classpath "../bin:../lib:../lib/linux_x86/com.google.ortools.jar" \
"$0" "$@"
!#

/** USAGE: ./redWord.scala [p1 p2 p3 ...]
  *
  */
import scala.collection.mutable.ArrayBuffer
import polytope._

object ReducedWord extends App {
  val p = args.map(_.toInt)
  println("Reduced decomposition of [" + args.mkString(" ") + "]")
  println("[There may be other decompositions]")
  println("[Multiplication read from left to right]")
  println(reducedWord(p).map(i => "(" + i + "," + (i+1) + ")").mkString(""))
}

// Run the code
ReducedWord.main(args)

#!/bin/bash
exec scala \
-Djava.library.path="../lib/linux_x86" \
-classpath "../bin:../lib:../lib/linux_x86/com.google.ortools.jar" \
"$0" "$@"
!#

/** USAGE: ./coeffTest1.scala DIM_A DIM_B
  */
import scala.collection.mutable.HashSet
import polytope._

/** Print extremal edges for given dimensions
  */
object ExtEdges extends App {
  val dims = List(args(0).toInt, args(1).toInt)
  val cubicles = InequalityFactory.cubiclesDM(dims)
  val es = HashSet[ABEdge]()
  
  if (!cubicles.isEmpty) {
    for (T <- cubicles) {
      for (e <- T.toCone.edges(dims(0))._2) {
        if (!es.contains(e)) {
          println(e.toCSV)
          es += e
        }
      }
    }
  }
}

// Run the code
ExtEdges.main(args)

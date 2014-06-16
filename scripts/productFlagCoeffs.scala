#!/bin/bash
exec scala \
-Djava.library.path="../lib/linux_x86" \
-classpath "../bin:../lib:../lib/linux_x86/com.google.ortools.jar" \
"$0" "$@"
!#

/** USAGE: ./coeff.scala DIMA DIMB - [u1 u2 ..] / [v1 v2 ...] / [w1 w2 ...]
  *  
  *  DIMA and DIMB are the size of the cubicles
  *  ui, vi and wi are permutations of 1 2 3 ...
  *  length of ui AND number of letters of permutation
  *    must be at most DIMA
  *  length of vi AND --- must be at most DIMB
  *  length of wi AND --- must be at most DIMA*DIMB
  */
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap
import polytope._

/** For every cubicle, print the product flag coefficient associated 
  * with the given permutations and cubicle
  */
object productFlagCoeff {
  def main(args: Array[String]): Unit = {
    if (args.count(_ == "/") != 2) {
      println((args.count(_ == "/") + 1).toString +
              " permutations were supplied, but exactly 3 are required.")
      return
    }
    if (args.count(_ == "-") != 1) {
      println("Two dimensions must be supplied and separated from the permutations by a -")
      return
    }
    if (args.length < 5) {
      println("Not enough arguments")
      return
    }
    val dims = List(args(0).toInt, args(1).toInt)
    val perms = args.drop(args.indexOf("-") + 1)
    val u = perms.takeWhile(_ != "/").map(_.toInt)
    val v = perms.drop(u.length + 1).takeWhile(_ != "/").map(_.toInt)
    val w = perms.drop(u.length + v.length + 2).map(_.toInt)

    println("dims = (" + dims.mkString(", ") + ")")
    println("u: " + u.mkString(" "))
    println("v: " + v.mkString(" "))
    println("w: " + w.mkString(" "))

    if (reducedWord(u).length > dims(0) || u.length > dims(0) ||
        reducedWord(v).length > dims(1) || v.length > dims(1) ||
        reducedWord(w).length > dims(0)*dims(1) || w.length > dims(0)*dims(1)) {
      println("The number of letters of the permutations must be bounded by the dimensions")
      println("The length of the permutations must also be bounded by the dimensions")
      return
    }
    
    val cubicles = InequalityFactory.cubiclesDM(dims)
    for (T <- cubicles) {
      val c = InequalityFactory.c(u, v, w, T)
      println("For T=" + T.toCSV() + ", c(u,v,w,T)=" + hashMapToString(c))
    }
  }
}

// Run the code
//productFlagCoeff.main(args)

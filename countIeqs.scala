#!/bin/bash
exec scala -classpath "./bin:./lib:./lib/linux_x86/com.google.ortools.jar" "$@" "$0"
!#

import scala.collection.mutable.ArrayBuffer
import com.google.ortools.graph._

object Test extends App {
  println("Hello")
  val ab = ArrayBuffer.fill[Int](10)(1)
  println(ab)
}

Test.main(args)

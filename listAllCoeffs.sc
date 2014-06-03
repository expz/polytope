import scala.collection.mutable.ArrayBuffer

import polytope._

object listAllCoeffs {
	System.setProperty( "java.library.path", "../lib/linux_86" )
                                                  //> res0: String = /usr/lib/jvm/java-7-openjdk-i386/jre/lib/i386/server:/usr/lib
                                                  //| /jvm/java-7-openjdk-i386/jre/lib/i386:/usr/lib/jvm/java-7-openjdk-i386/jre/.
                                                  //| ./lib/i386:/usr/lib/jvm/java-7-openjdk-i386/jre/lib/i386/client:/usr/lib/jvm
                                                  //| /java-7-openjdk-i386/jre/lib/i386::/usr/java/packages/lib/i386:/usr/lib/i386
                                                  //| -linux-gnu/jni:/lib/i386-linux-gnu:/usr/lib/i386-linux-gnu:/usr/lib/jni:/lib
                                                  //| :/usr/lib
	
	
	val fieldSysPath = classOf[java.lang.ClassLoader].getDeclaredField( "sys_paths" )
                                                  //> fieldSysPath  : java.lang.reflect.Field = private static java.lang.String[] 
                                                  //| java.lang.ClassLoader.sys_paths
	fieldSysPath.setAccessible( true )
	fieldSysPath.set( null, null )
  System.loadLibrary("libjniortools.so")          //> java.lang.UnsatisfiedLinkError: no libjniortools.so in java.library.path
                                                  //| 	at java.lang.ClassLoader.loadLibrary(ClassLoader.java:1886)
                                                  //| 	at java.lang.Runtime.loadLibrary0(Runtime.java:849)
                                                  //| 	at java.lang.System.loadLibrary(System.java:1088)
                                                  //| 	at listAllCoeffs$$anonfun$main$1.apply$mcV$sp(listAllCoeffs.scala:12)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at listAllCoeffs$.main(listAllCoeffs.scala:5)
                                                  //| 	at listAllCoeffs.main(listAllCoeffs.scala)
  val dims = List(2, 2)
  val cubicles = InequalityFactory.cubiclesDM(dims)
  val cs = ArrayBuffer[PFCoefficient]()
  for (u <- PermutationFactory.permutationsOfSize(dims(0))) {
    for (v <- PermutationFactory.permutationsOfSize(dims(1))) {
      for (w <- PermutationFactory.permutationsOfSize(dims(0)*dims(1))) {
        val (ur, vr, wr) = (reducedWord(u), reducedWord(v), reducedWord(w))
        for (T <- cubicles) {
          // In this case, c(u,v,w,T) will be a constant
          val cc = InequalityFactory.c(u, v, w, T).getOrElse(0L, 0)
          println(ur.mkString(",") + ";" + vr.mkString(",") + ";" + wr.mkString(",") + ";" + cc)
        }
      }
    }
  }
}
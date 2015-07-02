import scala.collection.mutable.ArrayBuffer

import polytope._

object listAllCoeffs {
  val dims = List(2, 2)                           //> dims  : List[Int] = List(2, 2)
  val cubicles = InequalityFactory.cubiclesDM(dims)
                                                  //> java.lang.UnsatisfiedLinkError: no jniortools in java.library.path
                                                  //| 	at java.lang.ClassLoader.loadLibrary(ClassLoader.java:1886)
                                                  //| 	at java.lang.Runtime.loadLibrary0(Runtime.java:849)
                                                  //| 	at java.lang.System.loadLibrary(System.java:1088)
                                                  //| 	at polytope.RectTableau.<init>(RectTableau.scala:23)
                                                  //| 	at polytope.RectTableau$.standardTableaux(RectTableau.scala:271)
                                                  //| 	at polytope.InequalityFactory$.cubiclesDM(inequalityFactory.scala:30)
                                                  //| 	at listAllCoeffs$$anonfun$main$1.apply$mcV$sp(listAllCoeffs.scala:7)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at listAllCoeffs$.main(listAllCoeffs.scala:5)
                                                  //| 	at listAllCoeffs.main(listAllCoeffs.scala)
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
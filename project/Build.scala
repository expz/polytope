import sbt._
import sbt.Keys._
import scala.collection.immutable.HashMap

object PolytopeBuild extends Build {
  // Dependencies
  lazy val scalaCompiler = "org.scala-lang" % "scala-compiler" % "2.11.7"
  lazy val scalaLibrary = "org.scala-lang" % "scala-library" % "2.11.7"
  lazy val scalaReflect = "org.scala-lang" % "scala-reflect" % "2.11.7"
  lazy val scalaParserCombinators = "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4"
  lazy val scallop = "org.rogach" % "scallop_2.11" % "0.9.5"
  lazy val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test"
  lazy val junit = "junit" % "junit" % "4.12" % "test"
  lazy val jcommon = "org.jfree" % "jcommon" % "1.0.23" % "test"
  lazy val jfreechart = "org.jfree" % "jfreechart" % "1.0.19" % "test"

  // The directory for native libraries depends on the OS and arch
  lazy val libsPath = "lib" + java.io.File.separator
  lazy val nativeLibsPath = libsPath + osName + "_" + archName + java.io.File.separator

  override lazy val settings = super.settings ++ Seq(
    version := "0.9.0",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-Yinline-warnings"
    )
  )

  // Main project
  lazy val polytope = Project("polytope", file(".")).
    settings(polytopeSettings: _*)

  lazy val polytopeSettings = Seq(
    name := "polytope",
    libraryDependencies ++= Seq(
      scalaLibrary,
      scalaReflect,
      scalaParserCombinators,
      scallop,
      scalaTest,
      junit,
      jcommon,
      jfreechart
    ),
    unmanagedJars in Compile <++= baseDirectory map { base =>
      val baseDirectories = (base / "lib")
      val customJars = (baseDirectories ** "*.jar")
      customJars.classpath
    },
    unmanagedJars in Test <++= baseDirectory map { base =>
      val baseDirectories = (base / "lib")
      val customJars = (baseDirectories ** "*.jar")
      customJars.classpath
    },
    // Set native library path (this requires forking the sbt JVM)
    javaOptions := Seq(
      "-Djava.library.path=" + 
        System.getProperty("java.library.path") + java.io.File.pathSeparator +
        nativeLibsPath,
      "-Dfile.encoding=UTF-8"
    ),
    fork := true
  )

  def osName = {
    lazy val names = HashMap(
      "Windows" -> "win",
      "Linux" -> "linux",
      "Mac" -> "mac",
      "SunOS" -> "solaris",
      "Solaris" -> "solaris"
    )
    names.find(keyval => 
        System.getProperty("os.name").contains(keyval._1)
      ) match {
      case Some((searchString, osString)) => osString;
      case None => "unknown";
    }
  }

  def archName = {
    lazy val archs = HashMap(
      "amd64" -> "amd64",
      "x86_64" -> "amd64",
      "x86" -> "x86",
      "i386" -> "x86",
      "i686" -> "x86",
      "arm64" -> "arm64",
      "arm" -> "arm"
    )
    archs.find(keyval => 
        System.getProperty("os.arch").contains(keyval._1)
      ) match {
      case Some((searchString, osString)) => osString;
      case None => "unknown";
    }
  }
  //lazy val nativeLibsDir = new File("." + java.io.File.separator + nativeLibsPath)

  /*initialCommands in Compile := 
    """(new File("." + java.io.File.separator + """" + nativeLibsPath +
      """")).listFiles().foreach(f =>
      java.nio.file.Files.createSymbolicLink(
        java.nio.file.Paths.get(libsPath + f.getName()),
        java.nio.file.Paths.get(f.getCanonicalPath())))"""
  */
}

import sbt._
import sbt.Keys._

import scala.collection.immutable.HashMap
import scala.collection.immutable.List
import scala.util.matching.Regex

import java.io.File
import sys.process.Process

object PolytopeBuild extends Build {
  // Scala version
  lazy val scalaMajorVersion = "2"
  lazy val scalaMinorVersion = "11"
  lazy val scalaUpdate = "7"

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
  lazy val libsPath = "lib" + File.separator
  lazy val nativeLibsPath = libsPath + osName + "_" + archName + File.separator

  // Global settings
  override lazy val settings = super.settings ++ Seq(
    version := "0.9.0",
    scalaVersion := 
      List(scalaMajorVersion, scalaMinorVersion, scalaUpdate).mkString("."),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-Yinline-warnings",
      "-Xexperimental"  /* Use Java 8 anonymous classes */
    )
  )

  /////////////////////////////////////////////////////
  // Prepare native libraries
  
  lazy val downloadNativeLibs = TaskKey[Unit](
    "download any missing native libraries to lib/ directory"
  )

  /*
  lazy val copyNativeLibs = TaskKey[Unit](
    "copy the native libraries for the current os and arch to lib/ directory"
  )
  
  lazy val nativeLibsDir = new File("." + java.io.File.separator + nativeLibsPath)

  copyNativeLibs := {
    nativeLibsDir.listFiles().foreach { f =>
      java.nio.file.Files.createSymbolicLink(
        java.nio.file.Paths.get(libsPath + f.getName()),
        java.nio.file.Paths.get(f.getCanonicalPath())
      )
    }
  }

  compile in Compile <<= (compile in Compile).dependsOn(copyNativeLibs)
*/
  //////////////////////////////////////////////////////
  // Main project
  
  lazy val protectMath = taskKey[Unit](
    "protect latex symbols before running scaladoc"
  )

  lazy val mathFormulaInDoc  = taskKey[Unit](
    "compile latex formulas in scaladocs using MathJax"
  )


  lazy val polytope = Project("polytope", file(".")).
    settings(polytopeSettings: _*)

  lazy val polytopeSettings = Seq(
    name := "polytope",

    // Download native libraries and supporting jars if they are missing
    downloadNativeLibs := {
      Process("./getlibs.sh", (baseDirectory map { base => base / "lib" }).value).!!
    },
    compile in Compile <<= (compile in Compile).dependsOn(downloadNativeLibs),

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
      val customJars = (baseDirectories / "com.google.ortools.jar") +++ (baseDirectories / "ppl_java.jar")
      customJars.classpath
    },
    unmanagedJars in Test <++= baseDirectory map { base =>
      val baseDirectories = (base / "lib")
      val customJars = (baseDirectories / "com.google.ortools.jar") +++ (baseDirectories / "ppl_java.jar")
      customJars.classpath
    },
   
    // Set native library path (this requires forking the sbt JVM)
    javaOptions := Seq(
      "-Djava.library.path=" + 
        System.getProperty("java.library.path") + File.pathSeparator +
        libsPath,
      "-Dfile.encoding=UTF-8"
    ),

    // We must fork in order to load the native libraries
    fork := true,

    /*
    protectMath := {
      val srcDir = ...

      listFilesRecursive(srcDir, """.*\.scala$""".r).foreach { f =>
        val content = scala.io.Source.fromFile(f).getLines().mkString("\n")
        if(content.contains("")) {
          val writer = new java.io.PrintWriter(f)
          writer.write(content.replace("</head>", scriptLine + "</head>"))
          writer.close()
        }
    },
    compile in Compile <<= (compile in Compile).dependsOn(protectMath),
    */

    mathFormulaInDoc := {
      val apiDir = (doc in Compile).value
      val docDir = baseDirectory.value / 
        "target" / ("scala-" + scalaMajorVersion + "." + scalaMinorVersion) / "api"
     
      val mathJaxURL = "https://cdn.mathjax.org/mathjax/latest/MathJax.js" +
        "?config=TeX-AMS_HTML"
      val scriptLine =
        s"""<script type="text/javascript" src="$mathJaxURL"></script>"""

      // find all html file and apply patch
      if(docDir.isDirectory)
        listFilesRecursive(docDir, """.*\.html$""".r).foreach { f =>
          val content = scala.io.Source.fromFile(f).getLines().mkString("\n")
          if(content.contains("$$")) {
            val writer = new java.io.PrintWriter(f)
            writer.write(content.replace("</head>", scriptLine + "</head>"))
            writer.close()
          }
        }
    },

    // attach this task to doc task
    mathFormulaInDoc <<= mathFormulaInDoc triggeredBy (doc in Compile)
  )

  ////////////////////////////////////////////
  // Latex in documentation

  // Based on code from @Juh_ at stackoverflow.com

  // function that find html files recursively
  def listFilesRecursive(f: File, r: Regex): Array[File] = {
    val fs = f.listFiles
    fs.filter(f => r.findFirstIn(f.getName).isDefined) ++
      fs.filter(_.isDirectory).flatMap(listFilesRecursive(_, r))
  }

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
}

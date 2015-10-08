import scala.collection.immutable.HashMap

lazy val commonSettings = Seq(
  version := "0.9.0",
  scalaVersion := "2.11.7"
)

// Calculate OS and architecture
lazy val names = HashMap(
  "Windows" -> "win",
  "Linux" -> "linux",
  "Mac" -> "mac",
  "SunOS" -> "solaris",
  "Solaris" -> "solaris"
)
lazy val archs = HashMap(
  "amd64" -> "amd64",
  "x86_64" -> "amd64",
  "x86" -> "x86",
  "i386" -> "x86",
  "i686" -> "x86",
  "arm64" -> "arm64",
  "arm" -> "arm"
)
lazy val osName = names.find(keyval => System.getProperty("os.name").contains(keyval._1)) match {
  case Some((searchString, osString)) => osString;
  case None => "unknown";
}
lazy val archName = archs.find(keyval => System.getProperty("os.arch").contains(keyval._1)) match {
  case Some((searchString, osString)) => osString;
  case None => "unknown";
}

// The directory for native libraries depends on the OS and arch
lazy val libsPath = "lib" + java.io.File.separator
lazy val nativeLibsPath = libsPath + osName + "_" + archName + java.io.File.separator

lazy val nativeLibsDir = new File("." + java.io.File.separator + nativeLibsPath)
/*initialCommands in Compile := 
  """(new File("." + java.io.File.separator + """" + nativeLibsPath +
    """")).listFiles().foreach(f =>
    java.nio.file.Files.createSymbolicLink(
      java.nio.file.Paths.get(libsPath + f.getName()),
      java.nio.file.Paths.get(f.getCanonicalPath())))"""
*/
lazy val root = Project(
  id = "root",
  base = file("."),
  settings = commonSettings ++ Seq(
    name := "polytope",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler" % "2.11.7",
      "org.scala-lang" % "scala-library" % "2.11.7",
      "org.scala-lang" % "scala-reflect" % "2.11.7",
      "org.scala-lang.modules" % "scala-parser-combinators_2.11" % "1.0.4",
      "org.rogach" % "scallop_2.11" % "0.9.5",
      "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test",
      "junit" % "junit" % "4.12" % "test",
      "org.jfree" % "jcommon" % "1.0.23" % "test",
      "org.jfree" % "jfreechart" % "1.0.19" % "test"
    ),
    unmanagedJars in Compile <++= baseDirectory map { base =>
      val baseDirectories = (base / "lib") +++ (base / nativeLibsPath)
      val customJars = (baseDirectories ** "*.jar")
      customJars.classpath
    },
    unmanagedJars in Test <++= baseDirectory map { base =>
      val baseDirectories = (base / "lib") +++ (base / nativeLibsPath)
      val customJars = (baseDirectories ** "*.jar")
      customJars.classpath
    }/*
    javaOptions in Compile ++= Seq(
      "-Djava.library.path=" + 
        System.getProperty("java.library.path") + java.io.File.pathSeparator +
        nativeLibsPath,
      "-Dfile.encoding=UTF-8"
    ),
    javaOptions in Test ++= Seq(
      "-Djava.library.path=" + 
        System.getProperty("java.library.path") + java.io.File.pathSeparator +
        nativeLibsPath,
      "-Dfile.encoding=UTF-8"
    )*/
  )
)

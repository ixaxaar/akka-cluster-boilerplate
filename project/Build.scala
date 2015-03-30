
import sbt._
import sbt.Keys._

object AppBuild extends Build {
  import Settings._
  import Dependencies._

  println("//////////////////////")
  println("// Building Project //")
  println("//////////////////////")

  lazy val common = (project in file("./common")).
    settings(libraryDependencies ++= Dependencies.common).
    settings(defaultSettings: _*)
    // settings(parentSettings: _*)
}


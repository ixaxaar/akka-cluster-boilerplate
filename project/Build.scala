
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

  lazy val spark = (project in file("./spark")).
    settings(libraryDependencies ++= Dependencies.spark).
    settings(defaultSettings: _*)

  lazy val scalding = (project in file("./scalding")).
    settings(libraryDependencies ++= Dependencies.scalding).
    settings(defaultSettings: _*)

  // lazy val app = (project in file("./app")).
  //   settings(libraryDependencies ++= Dependencies.common).
  //   settings(defaultSettings: _*)
}


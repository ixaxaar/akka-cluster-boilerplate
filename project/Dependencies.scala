
import sbt._
import sbt.Keys._


object Dependencies {
  import Versions._

  object Compile {

    val akkaStream        = "com.typesafe.akka"   %% "akka-stream-experimental"           % AkkaStreams
    val akkaHttpCore      = "com.typesafe.akka"   %% "akka-http-core-experimental"        % AkkaStreams
    val akkaActor         = "com.typesafe.akka"   %% "akka-actor"                         % Akka
    val akkaCluster       = "com.typesafe.akka"   %% "akka-cluster"                       % Akka
    val akkaRemote        = "com.typesafe.akka"   %% "akka-remote"                        % Akka
    val akkaSlf4j         = "com.typesafe.akka"   %% "akka-slf4j"                         % Akka
    val logback           = "ch.qos.logback"      % "logback-classic"                     % Logback
    val slf4jApi          = "org.slf4j"           % "slf4j-api"                           % Slf4j
    val sigar             = "org.fusesource"      % "sigar"                               % Sigar
  }

  object Test {
    // val akkaTestKit     = "com.typesafe.akka"     %% "akka-testkit"                       % Akka      % "test,it"
    // val scalatest       = "org.scalatest"         %% "scalatest"                          % ScalaTest % "test,it"
  }

  import Compile._

  val akka = Seq(akkaStream, akkaHttpCore, akkaActor, akkaCluster, akkaRemote, akkaSlf4j)

  val logging = Seq(logback, slf4jApi)

  // val test = Seq(Test.akkaTestKit, Test.scalatest)

  val common = akka ++ logging
}

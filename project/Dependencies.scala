
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
    // val cascadingCore     = "cascading"           %  "cascading-core"                     % Cascading
    // val cascadingLocal    = "cascading"           %  "cascading-local"                    % Cascading
    // val cascadingHadoop   = "cascading"           %  "cascading-hadoop2-mr1"              % Cascading
    val scaldingCore      = "com.twitter"         %%  "scalding-core"                     % Scalding
    val scaldingJson      = "com.twitter"         %%  "scalding-json"                     % Scalding
    val scaldingAvro      = "com.twitter"         %%  "scalding-avro"                     % Scalding
    val hadoopCore        = "org.apache.hadoop"   % "hadoop-common"                       % Hadoop       % "provided"
    val hadoopClientCore  = "org.apache.hadoop"   % "hadoop-mapreduce-client-core"        % Hadoop       % "provided"
    val sparkML           = "org.apache.spark"    %% "spark-mllib"                        % Spark
  }

  object Test {
    val akkaTestKit     = "com.typesafe.akka"     %% "akka-testkit"                       % Akka      % "test,it"
    val scalatest       = "org.scalatest"         %% "scalatest"                          % ScalaTest % "test,it"
    val specs2            = "org.specs2"          %% "specs2"                             % Specs2       % "test"
  }

  import Compile._

  val akka = Seq(akkaStream, akkaHttpCore, akkaActor, akkaCluster, akkaRemote, akkaSlf4j)

  val logging = Seq(logback, slf4jApi)

  val spark = Seq(sparkML) ++ logging ++ akka

  val scalding = Seq(scaldingCore, scaldingJson, scaldingAvro, hadoopCore, hadoopClientCore) ++ logging ++ akka

  val test = Seq(Test.akkaTestKit, Test.scalatest)

  val common = akka ++ logging
}

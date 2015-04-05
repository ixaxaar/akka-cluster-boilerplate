package com.scalding

import com.typesafe.config.{Config, ConfigFactory}


final class Settings(conf: Option[Config] = None) extends Serializable {
  val AppName = "akka-cluster-boilerplate"

  // hadoop config
  val jobTracker = "hadoop-master:8021"
  val defaultFS = "hdfs://hadoop-master:8020"
}

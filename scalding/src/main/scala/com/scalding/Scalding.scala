package com.scalding

import com.twitter.scalding._
import org.apache.hadoop.conf.Configuration
import akka.actor.{Actor, ActorLogging, Props, ActorRef}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


abstract class Scalding extends Actor with ActorLogging {
  val settings = new Settings
  import settings._

  var mode:Mode = new Local(true)
  var config:Config = Config.default
  var jobConf:Configuration = new Configuration

  def setMode(m:String):Unit = {
    m match {
      case "local" =>
      case "hdfs" =>
        jobConf.set("mapred.job.tracker", jobTracker)
        jobConf.set("fs.defaultFS", defaultFS)
        config = Config.hadoopWithDefaults(jobConf)
        mode = Hdfs(strict=true, jobConf)
    }
  }

  // override this
  def job:Execution[Unit]

  // override
  def actorReceive: Actor.Receive

  def exec(j:Execution[Unit]):Future[Unit] = {
    j.run(config, mode)
  }

  def execBlock(j:Execution[Unit]):Unit = {
    j.waitFor(config, mode)
  }
}


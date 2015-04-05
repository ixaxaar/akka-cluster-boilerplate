package com.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SQLContext
import akka.actor.{Actor, ActorLogging, Props, ActorRef, Cancellable}

import scala.concurrent.duration._
// import system.dispatcher


abstract class SparkBatch(name:String) extends Actor with ActorLogging {
  import Events._

  // the spark context
  val sparkConf = new SparkConf().setAppName(name)
  val sc = new SparkContext(sparkConf)
  val sqlContext = new org.apache.spark.sql.SQLContext(sc)
  import sqlContext._

  // changing this will cause batch to be called repeatedly
  // with time period = cyclic
  var cyclic:Int = 0
  var handle:Cancellable

  def actorReceive:Actor.Receive = maintainState

  def maintainState:Actor.Receive = {
    case SparkBatchShuttingDown =>
      self ! SparkBatchDown
    case SparkBatchStart =>
      self ! SparkBatchRunning
    case SparkBatchStop =>
      self ! SparkBatchShuttingDown
    case SparkBatchDown =>
    case SparkBatchUninitialized =>
    case SparkBatchInitialized =>
    case SparkBatchRunning =>
      batch(sc, sqlContext)
      self ! SparkBatchDown
  }

  // template for the batch process
  def batch(spk:SparkContext, sql:SQLContext):Unit
}

// object BatchRunner {
//   def run(ref:ActorRef, period:Int, event:SparkBatchEvent):Cancellable = {
//     val system = akka.actor.ActorSystem("system")

//     val cancellable:Cancellable = system.scheduler().schedule(Duration.Zero(),
//       Duration.create(50, TimeUnit.MILLISECONDS), ref, "Tick",
//       system.dispatcher(), null)
//     return cancellable
//   }

//   def stop(cancellable:Cancellable):Unit = {
//     cancellable.cancel
//   }
// }

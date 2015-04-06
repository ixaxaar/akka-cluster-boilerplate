package com.app

import akka.actor.{Actor, ActorLogging, Props, ActorRef}
import scala.util.Random
import scala.collection.immutable.Vector

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors

import com.spark._


class SparkApp(name:String) extends SparkBatch(name:String) {

  def createData(seed:Int, len:Int):Vector[LabeledPoint] = {
    val r = new Random(seed)
    val d =  Vector.fill(len, 2)(r.nextGaussian)
    d.map { p => LabeledPoint(p(0), Vectors.dense(p.to[Array])) }
  }

  override def batch(spk:SparkContext, sql:SQLContext):Unit = {
    val dat = createData(Random.nextInt, 100)
    val rdd = sc.parallelize(dat)
    val model = LinearRegressionWithSGD.train(rdd, 100)

    println(s"The model has intercept $model.intercept with weights $model.weights")
  }
}

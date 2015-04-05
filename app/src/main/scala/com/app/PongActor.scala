package com.common

import akka.actor.{Actor, ActorLogging, Props}


// class PongActor(settings:CommonSettings) extends Actor with ActorLogging {
class PongActor extends Actor with ActorLogging {
  import PongActor._
  import Events._

  def receive = {
  	case PingMessage(text) =>
  	  log.info("In PongActor - received message: {}", text)
  	  sender() ! PongMessage("pong")
  }
}

object PongActor {
  val props = Props[PongActor]
}

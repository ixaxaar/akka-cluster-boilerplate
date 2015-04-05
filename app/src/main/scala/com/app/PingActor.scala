package com.common

import akka.actor.{Actor, ActorLogging, Props, ActorRef}


// class PingActor(settings:CommonSettings) extends Actor with ActorLogging {
class PingActor extends Actor with ActorLogging {
  import PingActor._
  import Events._

  var counter = 0
  val pongActor = context.actorOf(PongActor.props, "pongActor")

  def receive = {
  	case Start =>
	    log.info("In PingActor - starting ping-pong")
  	  pongActor ! PingMessage("ping")
  	case PongMessage(text) =>
  	  log.info("In PingActor - received message: {}", text)
  	  counter += 1
  	  if (counter == 3) context.system.shutdown()
  	  else sender() ! PingMessage("ping")
  }
}

object PingActor {
  val props = Props[PingActor]
}

package it.unibo.buttonLedSystem

import akka.actor.{Actor, Props}

class LoggerActor extends Actor {
  import LoggerActor._

  def receive = {
  	case LogMessage(message : String) => log(message)
  }

  def log(message : String) = println("Logger Actor: " + message)
}

object LoggerActor {
  val props = Props[LoggerActor]
  case class LogMessage(message : String)
}

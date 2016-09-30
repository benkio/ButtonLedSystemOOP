package it.unibo.buttonLedSystem

import akka.actor.{Actor, Props}

class LoggerActor extends Actor {
  import LoggerActor._

  val loggerPrependMessage = "loggerActor: "

  val buttonPressedLogMessage = "The button is pressed!!!"
  val ledStatusChangedMessage = "Led Status Changed to "
  def receive = {
  	case LogMessage(message : String) => log(message)
    case ButtonPressedMessage => log(buttonPressedLogMessage)
    case LedStatusChangedMessage(s : Boolean) => log(ledStatusChangedMessage + s)
  }

  def log(message : String) = println(loggerPrependMessage + message)
}

object LoggerActor {
  val props = Props[LoggerActor]
  case class LogMessage(message : String)
  case object ButtonPressedMessage
  case class LedStatusChangedMessage(status : Boolean)
}

package it.unibo.buttonLedSystem

import akka.actor.{Actor, Props}

class LedActor extends Actor {
  import LedActor._

  var on: Boolean = false

  def receive = {
  	case SwitchMessage =>
  	 switch
  }

  def switch = on = !on
}

object LedActor {
  val props = Props[LedActor]
  case object SwitchMessage
}

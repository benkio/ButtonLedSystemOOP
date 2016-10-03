package it.unibo.buttonLedSystem

import akka.actor.{Actor, Props, ActorRef}
import interfaces._

class ControlActor() extends Actor {
  import ControlActor._

  val led: ActorRef = context.actorOf(LedActor.props, "ledActor")
  val button : ActorRef = context.actorOf(ButtonActor.props, "GUIButtonActor")
  val logger : ActorRef = context.actorOf(LoggerActor.props, "loggerActor")
  val frameActor : ActorRef = context.actorOf(FrameActor.props, "GUIFrameActor")

  button ! ButtonActor.RegisterObserverMessage(self)
  frameActor ! FrameActor.DisplayMainFrame(button)

  def receive = {
  	case ButtonActor.NotifyPushMessage => {
      logger ! LoggerActor.ButtonPressedMessage
      led ! LedActor.SwitchMessage
      led ! LedActor.Status
    }
    case (on : Boolean) => {
      logger ! LoggerActor.LedStatusChangedMessage(on)
      frameActor ! LoggerActor.LedStatusChangedMessage(on)
    }
  }
}

object ControlActor {
  val props = Props[ControlActor]
}


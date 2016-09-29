package it.unibo.buttonLedSystem

import akka.actor.{Actor, Props, ActorRef}
import interfaces._

class ButtonActor extends Actor with IObservable {
  import ButtonActor._

  var observers : Set[ActorRef] = Set()

  def receive = {
  	case PushMessage => notifyObservers
    case RegisterObserverMessage(o : ActorRef) => registerObserver(o)
    case RemoveObserverMessage(o : ActorRef) => removeObserver(o)
  }

  def registerObserver(observer : ActorRef) = observers += observer
  def removeObserver(observer : ActorRef) = observers -= observer
  def notifyObservers = observers.foreach((o : ActorRef) => o ! NotifyPushMessage)
}

object ButtonActor {
  val props = Props[ButtonActor]
  // Incoming Messages
  case object PushMessage

  // OutComing Messages
  case class RegisterObserverMessage(o : ActorRef)
  case class RemoveObserverMessage(o : ActorRef)
  case object NotifyPushMessage
}


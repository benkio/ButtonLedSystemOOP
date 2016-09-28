package it.unibo.buttonLedSystem.interfaces

import akka.actor.ActorRef

trait IObservable {
  def registerObserver(o : ActorRef)
  def removeObserver(o : ActorRef)
  def notifyObservers()
}

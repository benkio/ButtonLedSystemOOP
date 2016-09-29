package it.unibo.buttonLedSystem.test

import org.specs2.{ Specification, ScalaCheck }
import org.specs2.specification.Step
import org.specs2.time.NoTimeConversions

import akka.actor.{ Props, ActorSystem }
import akka.testkit.{ TestKit, ImplicitSender }
import scala.concurrent.duration._
import it.unibo.buttonLedSystem.ButtonActor

class ButtonActorSpec(system: ActorSystem) extends TestKit(system) with AkkaScalaCheck {
  def this() = this(ActorSystem("ButtonActorSpec"))


  "the button Actor" should {
    "notify the observer with exactly one message" in {
      val button = system.actorOf(Props[ButtonActor])
      button ! ButtonActor.RegisterObserverMessage(self)
      button ! ButtonActor.PushMessage
      expectMsg(ButtonActor.NotifyPushMessage)
      expectNoMsg(1 second)
      ok
    }

    "send no message to removed observers" in {
      val button = system.actorOf(Props[ButtonActor])
      button ! ButtonActor.PushMessage
      button ! ButtonActor.RegisterObserverMessage(self)
      button ! ButtonActor.RemoveObserverMessage(self)
      button ! ButtonActor.PushMessage
      expectNoMsg(1 second)
      ok
    }
  }
}

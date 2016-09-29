package it.unibo.buttonLedSystem.test

import org.specs2.{ Specification, ScalaCheck }
import org.specs2.specification.Step
import org.specs2.time.NoTimeConversions
import org.scalacheck._

import akka.actor.{ Props, ActorSystem }
import akka.testkit.{ TestKit, ImplicitSender }
import scala.concurrent.duration._
import it.unibo.buttonLedSystem.ButtonActor

class ButtonActorSpec(system: ActorSystem) extends TestKit(system) with AkkaScalaCheck {
  def this() = this(ActorSystem("ButtonActorSpec"))

  override def is = s2"""

  This is a specification for the Button Actor

  The Button Actor string should
    notify the observer with exactly one message $e1
    send no message to removed observers $e2"""

  val button = system.actorOf(Props[ButtonActor])
  button ! ButtonActor.RegisterObserverMessage(self)

  def message[A] = receiveOne(1 second).asInstanceOf[A]

  def positiveInts = Gen.choose(0,1000)

  implicit def pInts: Arbitrary[Int] =
    Arbitrary(positiveInts)

  def e1 = prop((p : Int) =>{
                  for (i <- 1 to p) {
                    button ! ButtonActor.PushMessage
                    expectMsg(ButtonActor.NotifyPushMessage)
                  }
                  expectNoMsg(10 millisecond)
                  ok
                }).setArbitrary(pInts)

  def e2 = prop((p : Int) =>{
                  button ! ButtonActor.RemoveObserverMessage(self)
                  for (i <- 1 to p) {
                    button ! ButtonActor.PushMessage
                  }
                  expectNoMsg(10 millisecond)
                  ok
                }).setArbitrary(pInts)
}

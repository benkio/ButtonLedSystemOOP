package it.unibo.buttonLedSystem.test

import org.specs2.{ Specification, ScalaCheck }
import org.specs2.specification.Step
import org.specs2.time.NoTimeConversions
import org.scalacheck._

import akka.actor.{ Props, ActorSystem }
import akka.testkit.{ TestKit, ImplicitSender }
import scala.concurrent.duration._
import it.unibo.buttonLedSystem.LedActor

class LedActorSpec(system: ActorSystem) extends TestKit(system) with AkkaScalaCheck {
  def this() = this(ActorSystem("LedActorSpec"))

  override def is = s2"""

  This is a specification for the Led Actor

  The Led Actor string should
    Change it's state if there's an odd number of incoming messages, stay the same otherwise $e1              """

  val led = system.actorOf(Props[LedActor])

  def message[A] = receiveOne(1 second).asInstanceOf[A]

  def positiveInts = Gen.choose(0,1000)

  implicit def pInts: Arbitrary[Int] =
    Arbitrary(positiveInts)

  def e1 = prop((positiveInts : Int) => {
                  led ! LedActor.Status
                  val status : Boolean = message
                  for (i <- 1 to positiveInts) led ! LedActor.SwitchMessage
                  led ! LedActor.Status
                  val lastStatus : Boolean = message
                  if (positiveInts % 2 == 0) lastStatus must be equalTo status
                  else lastStatus must be equalTo !status
                }).setArbitrary(pInts)
}

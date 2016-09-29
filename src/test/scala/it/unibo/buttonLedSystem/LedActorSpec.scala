package it.unibo.buttonLedSystem.test

import org.specs2.{ Specification, ScalaCheck }
import org.specs2.specification.Step
import org.specs2.time.NoTimeConversions

import akka.actor.{ Props, ActorSystem }
import akka.testkit.{ TestKit, ImplicitSender }
import scala.concurrent.duration._
import it.unibo.buttonLedSystem.LedActor

class LedActorSpec(system: ActorSystem) extends TestKit(system) with AkkaScalaCheck {
  def this() = this(ActorSystem("LedActorSpec"))



}

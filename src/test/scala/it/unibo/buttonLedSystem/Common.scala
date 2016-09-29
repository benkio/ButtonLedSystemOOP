package it.unibo.buttonLedSystem.test

import org.specs2.ScalaCheck
import org.specs2.specification.Step
import org.specs2.time.NoTimeConversions
import org.specs2.mutable.SpecificationLike

import akka.actor.{ Props, ActorSystem }
import akka.testkit.{ TestKit, ImplicitSender }
import scala.concurrent.duration._

trait AkkaScalaCheck extends
    SpecificationLike with
    NoTimeConversions with
    ScalaCheck with
    ImplicitSender { self: TestKit => }

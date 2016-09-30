import it.unibo.buttonLedSystem

import com.typesafe.config.ConfigFactory
import scala.util.Random
import akka.actor.ActorSystem
import akka.actor.Props
import it.unibo.buttonLedSystem._

object ButtonLedSystemApplication {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty || args.head == "Led")
      startRemoteLedSytem()
    if (args.isEmpty || args.head == "GUI")
      startRemoteGUISystem()
    if (args.isEmpty || args.head == "Logger")
      startRemoteLoggerSystem()
    if (args.isEmpty || args.head == "Control")
      startRemoteControlSystem()
  }

  def startRemoteControlSystem(): Unit = {
    val system =
      ActorSystem("ControlSystem", ConfigFactory.load("control"))
    val actor = system.actorOf(Props[ControlActor],
                               name = "controlActor")

    println("Started ControlSystem")
  }

  def startRemoteLedSytem(): Unit = {
    ActorSystem("LedSystem", ConfigFactory.load("led"))
    println("Started ledSystem")
  }

  def startRemoteGUISystem(): Unit = {
    ActorSystem("GUISystem", ConfigFactory.load("gui"))
    println("Started GUISystem")
  }

  def startRemoteLoggerSystem(): Unit = {
    ActorSystem("LoggerSystem", ConfigFactory.load("logger"))
    println("Started loggerSystem")
  }
}

package it.unibo.buttonLedSystem

import akka.actor.{Actor, Props, ActorRef}
import javax.swing.{JFrame, JPanel, JLabel, JButton, SwingUtilities}
import java.awt.{BorderLayout, Dimension, Color}
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Font
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import akka.actor.ActorRef

class FrameActor extends Actor {
  import FrameActor._

  val led = new JLabel("Off")

  def receive = {
    case DisplayMainFrame(b : ActorRef) => {
      val mainframe = createMainFrame(b)
      showMainFrame(mainframe)
    }
    case LoggerActor.LedStatusChangedMessage(status : Boolean) =>
      SwingUtilities.invokeLater(new Runnable {
                                   def run {
                                     if (status){
                                       led.setForeground(Color.GREEN)
                                       led.setText("On")
                                     }
                                     else{
                                       led.setForeground(Color.RED)
                                       led.setText("Off")
                                     }
                                   }
                                 })

  }

  def createMainFrame(b : ActorRef) : JFrame = {
    // JPanel
    val pnlButton = new JPanel()
    //  Buttons
    val btn = new JButton("Turn the Led")

    val ledStatusDescription = new JLabel("Led Status: ")

    led.setForeground(Color.red);

    val mainFrame = new JFrame {
      btn.addActionListener(new ActionListener()
                              {
                                def actionPerformed(e: ActionEvent )
                                {
                                  b ! ButtonActor.PushMessage
                                }
                              })

      // Adding to JFrame
      pnlButton.add(btn)
      pnlButton.add(ledStatusDescription)
      pnlButton.add(led)
      add(pnlButton)
      // JFrame properties
      setSize(200, 100)
      setBackground(Color.BLACK)
      setTitle("Button Led Subsystem")
      setLocationRelativeTo(null)
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
      setVisible(true)
    }

    mainFrame
  }

  def showMainFrame(mainFrame : JFrame) =  {
    SwingUtilities.invokeLater(new Runnable {
                                 def run {
                                   mainFrame.setVisible(true)
                                 }
                               })
  }

}

object FrameActor {
  val props = Props[FrameActor]
  case class DisplayMainFrame(b : ActorRef)
}


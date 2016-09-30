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

  def receive = {
    case DisplayMainFrame(b : ActorRef) => {
      val mainframe = createMainFrame(b)
      showMainFrame(mainframe)
    }

  }

  def createMainFrame(b : ActorRef) : JFrame = {
    // JPanel
    val pnlButton = new JPanel();
    //  Buttons
    val btn = new JButton("Turn the Led");

    val mainFrame = new JFrame {
      btn.addActionListener(new ActionListener()
                              {
                                def actionPerformed(e: ActionEvent )
                                {
                                  b ! ButtonActor.PushMessage
                                }
                              });

      // Adding to JFrame
      pnlButton.add(btn);
      add(pnlButton);
      // JFrame properties
      setSize(400, 400);
      setBackground(Color.BLACK);
      setTitle("Button Led Subsystem");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
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


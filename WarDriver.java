/**
   Kevin Gottfried
   CS 110 Final Project
   4/29/15
   WarDriver demonstrates basic functionality of the WarGUI class.
   It creates an interface that develop the card game war.
*/
import javax.swing.*;
public class WarDriver
{
   public static void main(String [] args)
   {
      // Create the frame
      WarGUI2 frame = new WarGUI2("War");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 1000); // set the frame size
      frame.setVisible(true);
   }
}
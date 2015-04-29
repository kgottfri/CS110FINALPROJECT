/**
   Kevin Gottfried
   CS 110 Final Project
   4/29/15
   WarGUI demonstrates basic functionality of the warcmd class.
   Constructs a GUI.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class WarGUI2 extends JFrame
{
   private JLabel pic, pic1, pic2, pic3, pic4, pic5, pic6; //labels that hold card images
   private JLabel p1Score, p2Score; // labels that hold player's scores
   private JLabel winner, war; // labels that holds winner
   private JPanel panel1, panel2, panel3, panel4, panel5, panel6;
   private ImageIcon p1,p2, p3, p4, back, blank; // icons that represent card images
   private JButton button; // deal button
   private Deck1 d1, d2; // reference of player 1 and player 2 deck
   Card c1, c2; // holds dealt cards
   Card c3, c4, c5, c6; // holds dealt cards during war
   WarCmd game; // create instance of war back-bone
   
   /**
      Constructor
      Construct the basic outline of the gui
      @param s The string to be added as the frame title.
   */
   public WarGUI2(String s)
   {
      super(s);
      
      // Set layout to grid layout with 2 rows, 3 columns
      setLayout(new GridLayout(2, 3));
      
      // Create the deal button and edit size
      button = new JButton("deal");
      button.addActionListener(new ButtonListener());
      button.setPreferredSize(new Dimension(200,210)); 
      
      // Create 6 panels for each grid cell
      panel1 = new JPanel();
      panel2 = new JPanel();
      panel3 = new JPanel();
      panel4 = new JPanel();
      panel5 = new JPanel();
      panel6 = new JPanel();
      
      // Set the background color of each panel
      panel1.setBackground(new Color(30,119,20));
      panel2.setBackground(new Color(30,119,20));
      panel3.setBackground(new Color(30,119,20));
      panel4.setBackground(new Color(30,119,20));
      panel5.setBackground(new Color(30,119,20));
      panel6.setBackground(new Color(30,119,20));
      
      // Set an additional Border layout in bottom corner panels
      panel4.setLayout(new BorderLayout());
      panel6.setLayout(new BorderLayout());
      
      back = new ImageIcon("cardpics/back.jpg"); // assign the image of a card back
      
      // Set the card labels 
      pic1 = new JLabel(back); // sets the image to the back of a card
      pic2 = new JLabel(back); // sets the image to the back of a card
      pic5 = new JLabel(blank); // sets the image to blank
      pic3 = new JLabel(blank);
      pic4 = new JLabel(blank);
      pic6 = new JLabel(blank);
      
      // create new labels for score and winner
      p1Score = new JLabel(); 
      p2Score = new JLabel();
      winner = new JLabel();
      war = new JLabel();
      
      // create a new instance of War game
      game = new WarCmd(); // creates a new potDeck
      game.split(); // split the potDeck
      d1 = game.getP1(); // references player 1's deck
      d2 = game.getP2(); // references player 2's deck
      
      // add the labels to their significant panels
      panel1.add(new JLabel("Player 1"));
      panel1.add(p1Score);
      panel1.add(pic1);
      panel2.add(button);
      panel3.add(new JLabel("Player 2"));
      panel3.add(p2Score); 
      panel3.add(pic2);
      panel4.add(pic3, BorderLayout.LINE_END);
      panel4.add(pic5, BorderLayout.LINE_START);
      panel5.add(war);
      panel5.add(winner);
      panel6.add(pic4, BorderLayout.LINE_START);
      panel6.add(pic6, BorderLayout.LINE_END);
      
      // add the panels to the window
      add(panel1);
      add(panel2);
      add(panel3);
      add(panel4);
      add(panel5);
      add(panel6);
      
      // Creates a message dialog of the rules.
      JOptionPane.showMessageDialog(null, "Welcome to WAR!, a Java implementation of the"+
                " classic card game War.\n\nThe rules are simple, just press 'Deal'" +
                " to start playing. Your deck \nis on the left, the computer's deck"+
                " is on the right. The winner of each hand\nis the one that draws the"+
                " highest card, and gets to take both cards, aces are low!\n"+ 
                "If both cards are the same, then one card is played face down"+
                " and then another\n card face up, the winner takes all six cards!\n" +
                "\nGood Luck!");
   }
   
   /**
      setImage method takes 2 dealt cards and assigns the specific card
      image.
      @param c1 Player 1's dealt card
      @param c2 Player 2's dealt card
   */
   public void setImage(Card c1, Card c2)
   {
      // Take string value of card value and assign a reference
      String s1 = c1.getValueAsString();
      String s2 = c1.getSuitAsString();
      String s3 = c2.getValueAsString();
      String s4 = c2.getSuitAsString();
      
      // draw the image from the reference variable
      p1 = new ImageIcon("cardpics/"+s1+s2+".jpg");
      p2 = new ImageIcon("cardpics/"+s3+s4+".jpg");
   }
   /**
      setImageWar method takes two dealt cards resultant from a war
      and assigns the specific card image.
      @param c3 Player 1's dealt card
      @param c4 Player 2's dealt card
   */
   public void setImageWar(Card c3, Card c4)
   {
      // Take string value of card and assign a reference
      String s1 = c3.getValueAsString();
      String s2 = c3.getSuitAsString();
      String s3 = c4.getValueAsString();
      String s4 = c4.getSuitAsString();

      // draw the image from the reference variable
      p3 = new ImageIcon("cardpics/"+s1+s2+".jpg");
      p4 = new ImageIcon("cardpics/"+s3+s4+".jpg");
   }
   /**
      Listener which monitors clicks of the deal button, which controls the gameplay. 
      When the listener detects a click it deals two cards which are pushed into the
      Check method which determines the higher card.  If a war is drawn, then 4 more cards 
      are dealt and two are checked.  The specific images and texts are assigned and shown.
      An exception is thrown a player runs out of cards. 
   */
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String loser = null; // represents the first player to run out of cards
         
         // Deal 2 cards
         try
         {  
            c1 = new Card(d1.dealCard()); // deal player 1's card 
            c2 = new Card(d2.dealCard()); // deal player 2's card
         }
         // Catch an exception if one player runs out of cards
         catch(OutOfCards d)
         {
               // determines which player is out of cards
               if(d1.cardsRemaining() == 0)
                  loser = "Player 1";
               else if(d2.cardsRemaining() == 0)                
                  loser = "Player 2";
               // A message is displayed and the game is exited
               JOptionPane.showMessageDialog(null, "Sorry, "+loser+" has run out of cards! Game over, you lose!");
               System.exit(0);
         }
         // determines if there was a war
         if(game.Check(c1, c2).equals("war"))
         {
            try
            {
               // deals 4 more cards
               c3 = new Card(d1.dealCard()); // deal p1 face down card  
               c4 = new Card(d2.dealCard()); // deal p2 face down card
               c5 = new Card(d1.dealCard()); // deal p1 face up card
               c6 = new Card(d2.dealCard()); // deal p2 face up card
               game.War(c3, c4, c5, c6); // push cards to be checked an added to winning deck
               // set the specific text and images of all cards
               setImageWar(c5, c6); 
               war.setText("WAR! ");
               pic3.setIcon(p3);
               pic4.setIcon(p4); 
               pic5.setIcon(back); 
               pic6.setIcon(back);
            }
            // Catch an exception if one player runs out of cards during a war
            catch(OutOfCards f)
            {
               // determines which player is out of cards
               if(d1.cardsRemaining() == 0)
                  loser = "Player 1";
               else if(d2.cardsRemaining() == 0)                
                  loser = "Player 2";
               // A message is displayed and the game is exited   
               JOptionPane.showMessageDialog(null, "Sorry, "+loser+" has run out of cards! Game over, you lose!");
               System.exit(0);
           }

         }
         // if there was not a war set all war images to blank
         else
         {
            war.setText(null);
            pic3.setIcon(blank);
            pic4.setIcon(blank);
            pic5.setIcon(blank);
            pic6.setIcon(blank);
         }   
         // set images of 2 dealt cards
         setImage(c1, c2);
         pic1.setIcon(p1);
         pic2.setIcon(p2);
         
         //sets the player's remaining cards score
         String p1 = String.valueOf(d1.cardsRemaining());
         p1Score.setText("Cards remaining: "+p1);
         String p2 = String.valueOf(d2.cardsRemaining());
         p2Score.setText("Cards remaining: "+p2);
         //displays the winner of the round
         winner.setText(game.getWinner());
         
         // update the decks  
         d1 = game.getP1();
         d2 = game.getP2();
      }
   } 
}
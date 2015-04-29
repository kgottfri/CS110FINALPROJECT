/**
   Kevin Gottfried
   CS 110 Final Project
   4/29/15
   WarCmd class is the backbone for the war card game.
   This class is implementable in the Command window.  It handles
   basic dealing and card functions.
*/
import javax.swing.*;
public class WarCmd
{
   final int NUM_PLAYERS = 2;  // Number of players
   final int NUM_CARDS = 52; // Number of cards in a deck
   final int CARDS_P1 = (NUM_CARDS/NUM_PLAYERS); // determines # of cards p1 has
   final int CARDS_P2 = (NUM_CARDS/NUM_PLAYERS); // determines # of cards p2 has
   Card card;
   Card card1, card2; // Holds the currently dealt card for p1 & p2
   Card c3, c4, c5, c6; // Holds the cards dealt during a war
   Deck deck; // Create 1 deck
   Deck1 p1, p2; // Create a deck for p1 & p2
   private String won = null; // Holds data for the winner for command window
   private String winner = null; // Holds winner for GUI
   
   /**
      Constructor
      Creates a new potdeck and one deck for each player 
   */  
   public WarCmd()
   {
      deck = new Deck(); // potDeck
      deck.shuffle(); // shuffle the cards
      p1 = new Deck1(); // Player 1's deck
      p2 = new Deck1(); // Player 2's deck
      
   }  
      
   /**
      Split method
      Takes the potdeck and splits it in half.
      Adds the first half to player 1's deck.
      Adds the second half to player 2's deck. 
   */
   public void split()
   {
      for(int i = 0; i < NUM_CARDS + 1; i++) // loops 52 times
      {
         if(!deck.isEmpty()) // checks for empty deck
         {
            // add the first half of deck to player 1
            if(i < CARDS_P1)
            {
               card = new Card(deck.dealCard()); // deals a card from potdeck
               p1.add(card); // adds to player 1 deck
            }
            
            // add the second half of deck to player 2
            else if(i >= CARDS_P2)
            {
               card = new Card(deck.dealCard()); // deals a card from potdeck
               p2.add(card);  // adds to player 2 deck
            }
         }  
      }
   }
   
   /**
      getP1
      This method returns player 1's deck.
      @return p1 Player 1's deck
   */
   public Deck1 getP1()
   {
      return p1;
   }  
   
   /**
      getP2
      This method returns player 2's deck.
      @return p2 Player 2's deck
   */
   public Deck1 getP2()
   {
      return p2;
   }
   
   /**
      Check
      This method compares player 1 and player 2 card.  Returns the winner of the round.
      @param c1 Player 1's card 
      @param c2 Player 2's card
      @return winner The winner of the round
   */
   public String Check(Card c1, Card c2)
   {                   
      card1 = c1;
      card2 = c2;
      // Determines if card 1 is greater than card 2
      if(card1.GreaterThan(card2))
      {
         System.out.println("Player 1's card: " + card1.getValueAsString());
         System.out.println("Player 2's card: " + card2.getValueAsString());
         System.out.println("Player 1 Wins");
         won = "p1";
         winner = "Player 1's Card is Higher.";
         p1.add(card2); // adds player 2's card to player 1 deck
         p1.add(card1); // adds player 1's card to player 1 deck
            
      }
      // Determines if card 2 is greater than card 1
      else if (card2.GreaterThan(card1))
      {
         System.out.println("Player 1's card: " + card1.getValueAsString());
         System.out.println("Player 2's card: " + card2.getValueAsString());
         System.out.println("Player 2 Wins");
         won = "p2";  
         winner = "Player 2's Card is Higher.";      
         p2.add(card1); // adds player 1's card to player 2 deck
         p2.add(card2); // adds player 2's card to player 2 deck
      }
      
      // Determines if the 2 cards are equal
      else if (card1.equals(card2))
      {
         
         System.out.println("WAR");
         System.out.println("Player 1's card: " + card1.getValueAsString());
         System.out.println("Player 2's card: " + card2.getValueAsString()); 
         won = "war";
         winner = "war";  
      }     
      return won;
   } 
   
   /**
      War
      This method is called when a war is evoked.
      It takes in 4 cards, 2 face down, 2 face up.
      It checks the 2 face up cards and adds the cards
      to the winner's deck.
      @param c3 Player 1's face down card
      @param c4 Player 2's face down card
      @param c5 Player 1's face up card
      @param c6 Player 2's face up card
   */
   public void War(Card c3, Card c4, Card c5, Card c6)
   {
      this.c3 = c3;
      this.c4 = c4;
      this.c5 = c5;
      this.c6 = c6;
      
      Check(c5, c6);  // Pass p1 card and p2 card to be checked for winner
      
      // if winner is player 1 add all cards to p1 deck
      if(won.equals("p1"))
      {
         p1.add(c3);
         p1.add(c4);
         p1.add(c5);
         p1.add(c6);
      }
      // if winner is player 2 add all cards to p2 deck
      else if(won.equals("p2"))
      {
         p2.add(c3);
         p2.add(c4);
         p2.add(c5);
         p2.add(c6);
      }
      // *special case
      // if another war, then player 1 gets cards
      else if(won.equals("war"))
      {
         p1.add(c3);
         p1.add(c4);
         p1.add(c5);
         p1.add(c6);
      }
   }
   
   /**
      getWinner method returns the winner of each round
      @return winner The player with the higher card
   */
   public String getWinner()
   {
      return winner;
   }   
}

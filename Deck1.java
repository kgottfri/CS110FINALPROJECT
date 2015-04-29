/**
   Kevin Gottfried
   CS 110 Final Project
   4/29/15
   Deck class creates a new array list of cards and demonstrates
   basic functionality of deck operations.
*/
import java.util.Random;
import java.util.ArrayList;

public class Deck1 
{
   /** 
   *  Number of cards in standard deck {@value #CARDS_IN_DECK}
   **/
   final int CARDS_IN_DECK = 52;
   private int top = 0;
   /** The collection of Cards */
   private ArrayList<Card> deck;
   
   /**
    * Constructs a regular 52-card deck.  Initially, the cards
    * are in a sorted order.  The shuffle() method can be called to
    * randomize the order.  
    */
   public Deck1()
   {
      deck = new ArrayList<Card>();        
   }
   /**
    * Create a new collection of 52 cards, in sorted order
    */
   public void freshDeck()
   {
      deck = new ArrayList<Card>();
      System.out.println(deck.size());

      for(int p=3;p>=0;p--)
	   {
	      for(int i=1; i<=13; i++)
	      {
           deck.add(new Card(p,i));
         }
      }
   }

   /**
      Add a card to the array list.
      @param card The card to be added to the deck.
   */
   public void add(Card card)
   {
      deck.add(card);  
   }
   
   /**
      Remove a card from the array list.
      @param card The card to be removed from the deck.
   */
   public void remove(Card card)
   {
      deck.remove(card);
   }
   /** 
     * Remove and return the top Card on the Deck
     * @return A reference to a Card that was top on the Deck
       @throws OutOfCards Thrown when the deck is out of cards.
     */
   public Card dealCard() throws OutOfCards
   {
      if(deck.isEmpty())
         throw new OutOfCards();
         
      Card c = deck.remove(0);  //  remove it (returns removed object)
      return c;
   }
   /** 
     * Return current number of Cards in Deck
     * @return number of Cards in Deck
     */

   public int cardsRemaining()
   {  
      return deck.size();
   }
   /** 
     * Randomize the order of Cards in Deck
     */

   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < deck.size(); i++)
      {
         randNum = r.nextInt(deck.size());
         temp = deck.get(i);
         deck.set(i,deck.get(randNum));
         deck.set(randNum,temp);
      }      
   }
   /** 
     * Determine if Deck is empty
     * @return true if there are no more cards, false otherwise
     */
   
   public boolean isEmpty()
   {
      return (deck.size() == 0);
   }
}
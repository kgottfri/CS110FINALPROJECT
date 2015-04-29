/**
   Kevin Gottfried
   CS 110 Final Project
   4/29/15
   Relatable interface is used to compare two cards.
*/
public interface Relatable
{
   /** 
      Determines whether the card is greater than 2nd card.
      @param card The second card to be compared.
      @return true if the card is greater, otherwise returns false.
  */
   public boolean GreaterThan(Card card);
   
   /**Determines whether two cards have the same value.
   @param card The second card to be compared.
   @return true if the cards are equal.
   */
   public boolean equals(Card card);
   
   
}
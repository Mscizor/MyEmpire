package model.spaces;

import model.cards.Card;
import java.util.ArrayList;
import model.Player;

/**
 * This class inherits the attributes of the Space class.
 * This class holds the values for spaces that can be attributed to an owner.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public abstract class OwnableSpace extends Space
{
   private double price;
   private ArrayList<Card> cards;

   /**
    * Gets the price of the space
    *
    * @return a <code> int </code>
    * specifying the price of the specific space
    */
   public double getPrice ()
   {
      return this.price;
   }

   /**
    * Gets the owner of the space
    *
    * @return a <code> Player </code>
    * specifying the current player that owns the space
    */
   public Player getOwner ()
   {
      return this.owner;
   }

   /**
    * Gets the array list of cards
    *
    * @return a <code> ArrayList </code> of @see Card
    * specifying the array list of cards
    */
   public ArrayList<Card> getCards ()
   {
      return this.cards;
   }

   public void setPrice (int price)
   {
      this.price = price;
   } 

   public void setCards (ArrayList<Card> cards)
   {
      this.cards = cards;
   }

   /**
    * method that adds a card to the space
    *
    * @param card is the card added to the space
    */
   public void addCard (Card card)
   {
      this.cards.add (card);
   }

   /**
    * method that removes a card from the space
    *
    * @param card is the card added to the space
    */
   public void removeCard (Card card)
   {
      this.cards.remove (card);
   }

   public abstract int getRent ();
}

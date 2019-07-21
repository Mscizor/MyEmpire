package model;

import model.spaces.OwnableSpace;
import model.cards.Card;
import java.util.ArrayList;

/**
 * This class holds player information and lets them choose between their
 * playable actions
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Player
{

   private String name;
   private double cash;
   private int position;
   private boolean inJail;
   private final ArrayList<Card> cards;

   /**
    * Player constructor that initializes player name and cash value
    *
    * @param name the name of the current player
    */
   public Player (String name)
   {
      this.cards = new ArrayList<> ();
      this.name = new String ();
      this.name = name;
      this.cash = 1500;
   }

   /**
    * Gets the player name
    *
    * @return a <code> String </code> specifying the name of the current player
    */
   public String getName ()
   {
      return this.name;
   }

   /**
    * Gets the player's cash value
    *
    * @return a <code> double </code> specifying the cash value of the current
    * player
    */
   public double getCash ()
   {
      return this.cash;
   }

   /**
    * Gets the player's current position
    *
    * @return a <code> int </code> specifying the position of the current player
    * in the array list of spaces.
    */
   public int getPosition ()
   {
      return this.position;
   }

   /**
    * Gets and returns true if the player is in jail
    *
    * @return a <code> boolean </code> specifying the inJail value of the player
    */
   public boolean isInJail ()
   {
      return this.inJail;
   }

   /**
    * Gets the array list of cards
    *
    * @return a <code> ArrayList </code> of @see Card specifying the list of
    * cards.
    */
   public ArrayList<Card> getCards ()
   {
      return this.cards;
   }

   public void addCard (Card card)
   {
      this.cards.add (card);
   }

   public void removeCard (Card card)
   {
      this.cards.remove (card);
   }

   /**
    * sets player's inJail value to true
    */
   public void arrest ()
   {
      this.inJail = true;
   }

   /**
    * sets player's inJail value to false
    */
   public void bail ()
   {
      this.inJail = false;
   }

   /**
    * method that updates the player's cash value by the cashChange value
    *
    * @param cashChange the amount of change in a player's cash value
    */
   public void changeCash (double cashChange)
   {
      this.cash += cashChange;
   }

   /**
    * updates the player's position into the current one
    *
    * @param position the position of the current player in the array list of
    * spaces
    */
   public void changePosition (int position)
   {
      this.position = position;
   }

   /**
    * updates the player's position depending on the value of the dice rolled
    *
    * @param numForward the number of spaces a player's position is moved
    */
   public void moveForward (int numForward)
   {
      this.position = (this.position + numForward) % 32;
   }

   public double getTotalValue ()
   {
      /*
       * temp, what does property value mean?
       */
      return 0;
   }

   @Override
   public String toString ()
   {
      String hold;
      hold = "Name: " + this.name;
      hold += "\nCash: " + this.cash;
//      hold += "\nPosition: " + MyEmpire.spaces.get(position).getName();

      return hold;
   }

   public void trade (Player other, ArrayList<OwnableSpace> giving, ArrayList<OwnableSpace> taking)
   {
      /*
       * Not yet implemented
       */
   }
}

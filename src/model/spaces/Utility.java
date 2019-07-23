package model.spaces;

import controller.Transactions;
import java.util.ArrayList;
import model.*;

/**
 * It is a special type of ownable space that increases its value
 * the more of it a player owns, as well as the value of
 * the rolled dice.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Utility extends OwnableSpace
{

   /**
    * Utility constructor that initializes utility name, location, and cash
    * value.
    *
    * @param name the name of specified utility
    * @param location the location of the utility in the array list of spaces
    * @param price the price of the utility
    */
   public Utility (String name, int location, int price)
   {
      super (name, location, price);
   }

   /**
    * Gets the utility's rent value
    *
    * @param players
    * @param spaces
    * @param player
    * @return a <code> int </code> specifying the utility's current rent
    */
   @Override
   public double getRent (ArrayList <Player> players, 
           ArrayList<Space> spaces, Player player)
   {
      int diceRoll;
      double baseRent;
      double finalRent;
      boolean bothOwned = true;
      Utility uHold;

      if (player == this.getOwner (players))
      {
         return 0;
      }

      for (int i = 0; i < spaces.size (); i++)
      {
         if (spaces.get (i) instanceof Utility)
         {
            uHold = (Utility) spaces.get (i);
            if (uHold.getOwner (players) == null)
            {
               bothOwned = false;
            }
         }
      }

      diceRoll = player.getDiceRoll ();
      // If both are owned by a player, rent is 10 times the number of the dice
      if (bothOwned)
      {
         baseRent = 10 * diceRoll;
      }
      // If one is owned by a player, rent is 4 times the number of the dice
      else
      {
         baseRent = 4 * diceRoll;
      }

      finalRent = baseRent;
      for (int i = 0; i < this.getCards ().size (); i++)
      {
         if (this.getCards ().get (i) instanceof CardApplyOwnableSpace)
         {
            finalRent *= ((CardApplyOwnableSpace) this.getCards ().get (i)).getChangeToRent ();
         }
      }

      return finalRent;
   }
   
   @Override
   public void buySpace (ArrayList <Player> players, 
           ArrayList <Space> spaces, Player player, Bank bank)
   {
      Player owner = this.getOwner (players);
      if (owner == null && player.getCash () >= this.getPrice ())
      {
         Transactions.cashToBank (player, bank, this.getPrice ());
         player.addOwnable (this);
      }
   }
   
   @Override
   public void payRent (ArrayList <Player> players,
           ArrayList <Space> spaces, Player player)
   {
      Player owner = this.getOwner (players);
      if (owner != player)
      {
         Transactions.cashToOtherPlayer (player, owner, 
                 this.getRent (players, spaces, player));
      }
   }
}

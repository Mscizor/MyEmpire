package model.spaces;

import java.util.ArrayList;

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
      this.setName (name);
      this.setLocation (location);
      this.setPrice (price);
      this.setCards (new ArrayList<> ());
   }

   /**
    * Gets the utility's rent value
    *
    * @return a <code> int </code> specifying the utility's current rent
    */
   @Override
   public int getRent ()
   {
      int i;
      int diceRoll;
      int baseRent = 0;
      int finalRent;
      boolean bothOwned = true;

      Utility uHold;
//
//		for (i = 0; i < MyEmpire.spaces.size (); i++)
//		{
//			if (MyEmpire.spaces.get (i) instanceof Utility)
//			{
//				uHold = (Utility) MyEmpire.spaces.get (i);
//				if (uHold.getOwner () == null)
//					bothOwned = false;
//			}
//		}
//
//		diceRoll = MyEmpire.diceRoll ();
//
//		// If both are owned by a player, rent is 10 times the number of the dice
//		if (bothOwned)
//			baseRent = 10 * diceRoll;
//		// If one is owned by a player, rent is 4 times the number of the dice
//		else
//			baseRent = 4 * diceRoll;

//		finalRent = baseRent;
      for (i = 0; i < this.getCards ().size (); i++)
      {
//			if (this.getCards ().get (i) instanceof CardApply)
//				finalRent *= (int) (((CardApply) this.getCards ().get (i)).getChange ());
      }

//		return (int) finalRent;
      return 0;
   }
}

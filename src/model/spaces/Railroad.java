package model.spaces;

import model.cards.CardApply;
import java.util.ArrayList;

/**
 * This class inherits the attributes of the OwnableSpace class.
 * It is a special type of ownable space that increases its value the more of it
 * a player owns.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Railroad extends OwnableSpace
{
   /**
    * Railroad constructor that initializes railroad name, location, and cash
    * value.
    *
    * @param name the name of specified railroad
    * @param location the location of the railroad in the array list of spaces
    * @param price the price of the railroad
    */
   public Railroad (String name, int location, int price)
   {
      this.setName (name);
      this.setLocation (location);
      this.setPrice (price);
      this.setCards (new ArrayList<> ());
   }

   /**
    * Gets the railroad's rent value
    *
    * @return a <code> int </code>
    * specifying the railroad's current rent
    */
   public int getRent ()
   {
      int i;
      int baseRent = 0;
      int finalRent;
      int countRailroads = 0;

      Railroad rHold;
//
//		//checks the number of railroads owned by the player
//		for (i = 0; i < MyEmpire.spaces.size (); i++)
//		{
//			if (MyEmpire.spaces.get (i) instanceof Railroad)
//			{
//				rHold = (Railroad) MyEmpire.spaces.get (i);
//				if (rHold.getOwner () == this.getOwner ())
//					countRailroads++;
//			}
//		}
      //decides value based on railroads owned
      switch (countRailroads)
      {
         case 1:
            baseRent = 25;
            break;
         case 2:
            baseRent = 50;
            break;
         case 3:
            baseRent = 150;
      }
      //applies card effects to the base rent value of the railroad
      finalRent = baseRent;
      for (i = 0; i < this.getCards ().size (); i++)
      {
         if (this.getCards ().get (i) instanceof CardApply)
         {
            finalRent *= (int) (((CardApply) (this.getCards ().get (i))).getChange ());
         }
      }

      return (int) finalRent;
   }
}

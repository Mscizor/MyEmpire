package model.spaces;

import java.util.ArrayList;

import model.*;

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
      super (name, location, price);
   }

   /**
    * Gets the railroad's rent value
    *
    * @return a <code> int </code>
    * specifying the railroad's current rent
    */
   public double getRent (ArrayList <Player> players, ArrayList <Space> spaces, Player player)
   {
      int i;
      int baseRent = 0;
      int finalRent;
      int countRailroads = 0;
      Player owner = this.getOwner (players);
      
      if (player == owner)
    	  return 0;
      
      // checks the number of railroads owned by the player
      for (i = 0; i < owner.getOwned ().size (); i++)
      {
    	  if (owner.getOwned ().get (i) instanceof Railroad)
    		  countRailroads++;
      }
      
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
         if (this.getCards ().get (i) instanceof CardApplyOwnableSpace)
         {
            finalRent *= ((CardApplyOwnableSpace) this.getCards ().get (i)).getChangeToRent ();
         }
      }

      return finalRent;
   }
}

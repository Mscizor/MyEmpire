package model.spaces;

/**
 * This class inherits the attributes of the Space class.
 * It is a special type of space that allows a player to draw from the deck of
 * chance cards
 * when landed on.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Chance extends Space
{
   /**
    * This constructor accepts a string and an int datatype as parameter
    * for name and location and initializes values of the object.
    *
    * @param name the name of the space
    * @param location the location of the space on the array list of spaces
    */
   public Chance (String name, int location)
   {
      this.setName (name);
      this.setLocation (location);
   }
   /**
    * This method accepts a Player object as parameter
    * and applies the effect of the landed-on corner on the player.
    *
    * @param player the player with the current turn
    */
}

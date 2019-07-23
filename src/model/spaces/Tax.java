package model.spaces;

/**
 * This class inherits the attributes of the Space class
 * and transfers a certain amount of currency from the player to the bank
 * when player lands on said space.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Tax extends Space
{
   /**
    * This constructor accepts the type of Tax (as an integer)
    * and the location and initializes the Tax.
    *
    * @param type The type of Tax to be generated.
    * @param location The location of the tax space on the list of spaces.
    */
   public Tax (String name, int location)
   {
      super (name, location);
   }
}

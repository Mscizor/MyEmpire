package model.spaces;

/**
 * It is a special type of space that has different types of effects
 * if the Player if the player lands on it.
 * 
 * There are four types of corner spaces: start, jail, community service, 
 * and free parking.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Corner extends Space
{

   /**
    * This constructor accepts the type of Corner (as an integer)
    * and the location and initializes the Corner.
    *
    * @param type The type of corner to be generated.
    * @param location The location of the space on the array list of spaces.
    */
   public Corner (int type, int location)
   {
      switch (type)
      {
         case 0:
            this.setName ("START");
            break;
         case 1:
            this.setName ("Community Service");
            break;
         case 2:
            this.setName ("Free Parking");
            break;
         case 3:
            this.setName ("JAIL");
      }
      
      this.setLocation (location);
   }

}

package model.spaces;

/**
 * The Space class consists of 32 possible spaces the player can land on.
 * The space can be a property, utility, a corner space, a chance space, or a
 * railroad space.
 * The different kinds of spaces hold different attributes.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public abstract class Space
{
   private String name;
   private int location;

   public Space (String name, int location)
   {
      this.name = name;
      this.location = location;
   }

   /**
    * Gets the name of the space
    *
    * @return a <code> String </code>
    * specifying the name of the space.
    */
   public String getName ()
   {
      return this.name;
   }

   /**
    * Gets the location of the space
    *
    * @return a <code> int </code>
    * specifying the position of the space in the array list of spaces.
    */
   public int getLocation ()
   {
      return this.location;
   }

   public void setName (String name)
   {
      this.name = name;
   }

   public void setLocation (int location)
   {
      this.location = location;
   }
}

package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.Property;
import model.spaces.Space;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardCRandomProperty extends Card implements CardMovePlayer
{
   private final Property random;

   public CardCRandomProperty (String name, String text, Property random)
   {
      super (name, text);
      this.random = random;
   }

   public void doCardEffect (Player player, ArrayList<Space> spaces, Bank bank)
   {
      if (spaces.get (random.getLocation ()) == random)
      {
         player.changePosition (random.getLocation ());
         // TODO : do land thing and start thing
      }

      this.discard ();
   }
}

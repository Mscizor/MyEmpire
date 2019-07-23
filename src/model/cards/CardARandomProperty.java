package model.cards;

import java.util.ArrayList;

import model.*;
import model.spaces.Property;
import model.spaces.Space;

/**
 *
 * @author janur
 */
public class CardARandomProperty extends Card implements CardMovePlayer
{
   private final Property random;

   public CardARandomProperty (String name, String text, Property random)
   {
      super (name, text);
      this.random = random;
   }

   public void doCardEffect (Player player, ArrayList<Space> spaces, Bank bank)
   {
      player.changePosition (random.getLocation ());
      // TODO: do land thing and start thing

      this.discard ();
   }
}

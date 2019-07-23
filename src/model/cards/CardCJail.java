package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.Space;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardCJail extends Card implements CardMovePlayer
{
   public CardCJail (String name, String text)
   {
      super (name, text);
   }

   @Override
   public void doCardEffect (Player player, ArrayList<Space> spaces, Bank bank)
   {
      if (spaces.get (16).getName ().equals ("JAIL"))
      {
         player.changePosition (16);
         player.arrest ();
      }
      this.discard ();
   }
}

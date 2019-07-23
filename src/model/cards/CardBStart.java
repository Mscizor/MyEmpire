package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.Space;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardBStart extends Card implements CardMovePlayer
{
   public CardBStart (String name, String text)
   {
      super (name, text);
   }

   @Override
   public void doCardEffect (Player player, ArrayList<Space> spaces, Bank bank)
   {
      if (spaces.get (0).getName ().equals ("START"))
      {
         player.changePosition (0);
         // TODO: do land thing

      }

      this.discard ();
   }
}

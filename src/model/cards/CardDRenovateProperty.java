package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.*;
import controller.Transactions;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardDRenovateProperty extends Card implements CardApplyOwnableSpace
{
   private final double changeToRent;
   private boolean applied;

   public CardDRenovateProperty (String name, String text,
           double changeToRent)
   {
      super (name, text);
      this.changeToRent = changeToRent;
   }

   public void doCardEffect (Player player, ArrayList<Space> spaces,
           OwnableSpace owned, Bank bank)
   {
      if (owned instanceof Property)
      {
         int ownedHouses = 0, ownedHotels = 0;
         for (int i = 0; i < player.getOwned ().size (); i++)
         {
            if (player.getOwned ().get (i) instanceof Property)
            {
               Property pHold = (Property) player.getOwned ().get (i);
               ownedHouses += pHold.getNumHouses ();
               ownedHotels += pHold.getNumHotels ();
            }
         }
         Transactions.cashToBank (player, bank, ownedHouses * 25 + ownedHotels * 50);
         owned.addCard (this);
         this.applied = true;
      }
      else
      {
         this.discard ();
      }
   }

   public double getChangeToRent ()
   {
      return this.changeToRent;
   }

   public boolean isApplied ()
   {
      return this.applied;
   }
}

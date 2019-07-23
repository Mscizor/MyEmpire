package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.*;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardDDoubleRent extends Card implements CardApplyOwnableSpace
{
   private final double changeToRent;
   private boolean applied;

   public CardDDoubleRent (String name, String text, double changeToRent)
   {
      super (name, text);
      this.changeToRent = changeToRent;
   }

   @Override
   public void doCardEffect (Player player, ArrayList<Space> spaces,
           OwnableSpace owned, Bank bank)
   {
      if (owned instanceof Property)
      {
         owned.addCard (this);
      }
   }

   @Override
   public boolean isApplied ()
   {
      return this.applied;
   }

   @Override
   public double getChangeToRent ()
   {
      return this.changeToRent;
   }

   public void removeApplied ()
   {
      this.applied = false;
   }
}

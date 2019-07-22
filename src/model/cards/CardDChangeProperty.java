package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.*;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardDChangeProperty extends Card implements CardApplyOwnableSpace
{
   private final double changeToRent;
   private boolean applied;
   
   public CardDChangeProperty (String name, String text, double changeToRent)
   {
      super (name, text);
      this.changeToRent = changeToRent;
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces, 
           OwnableSpace owned, Bank bank)
   {   
      if (owned instanceof Property)
      {
         owned.addCard (this);
         this.applied = true;
      }
   }
   
   public boolean isApplied ()
   {
	   return this.applied;
   }
   
   public double getChangeToRent ()
   {
      return this.changeToRent;
   }
}

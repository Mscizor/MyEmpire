package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.*;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */

public class CardDChangeNonProperty extends Card implements CardApplyOwnableSpace
{
   private final double changeToRent;
   private boolean applied = false;
   
   public CardDChangeNonProperty (String name, String text, double changeToRent)
   {
      super (name, text);
      this.changeToRent = changeToRent;
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces, 
           OwnableSpace owned, Bank bank)
   {   
      if (owned instanceof Railroad || owned instanceof Utility)
      {
    	  owned.addCard (this);
    	  this.applied = true;
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

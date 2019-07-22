/*
 * To changeToRent this license header, choose License Headers in Project Properties.
 * To changeToRent this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cards;

import java.util.ArrayList;
import model.Player;
import model.spaces.*;

/**
 *
 * @author janur
 */
public class CardDChangeProperty extends Card
{
   private final double changeToRent;
   
   public CardDChangeProperty (String name, String text, double changeToRent)
   {
      super (name, text);
      this.changeToRent = changeToRent;
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces, 
           OwnableSpace owned)
   {   
      if (owned instanceof Property)
         owned.addCard (this);
   }
   
   public double getChangeToRent ()
   {
      return this.changeToRent;
   }
}

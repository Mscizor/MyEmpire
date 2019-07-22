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
public class CardDChangeNonProperty extends Card
{
   private final double changeToRent;
   
   public CardDChangeNonProperty (String name, String text, double changeToRent)
   {
      super (name, text);
      this.changeToRent = changeToRent;
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces, 
           OwnableSpace owned)
   {   
      if (owned instanceof Railroad || owned instanceof Utility)
         owned.addCard (this);
   }
   
   public double getchangeToRent ()
   {
      return this.changeToRent;
   }
}

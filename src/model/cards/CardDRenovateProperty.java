/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class CardDRenovateProperty extends Card
{
   private final double changeToRent;
   
   public CardDRenovateProperty (String name, String text, 
           double changeToRent)
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
   
   public double getChange ()
   {
      return this.changeToRent;
   }
}

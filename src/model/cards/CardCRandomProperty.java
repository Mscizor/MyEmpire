/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cards;

import java.util.ArrayList;
import model.Player;
import model.spaces.Property;
import model.spaces.Space;

/**
 *
 * @author janur
 */
public class CardCRandomProperty extends Card
{
   private final Property random;
   public CardCRandomProperty (String name, String text, Property random)
   {
      super (name, text);
      this.random = random;      
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces)
   {
      if (spaces.get (random.getLocation ()) == random)
      {
         player.changePosition (random.getLocation ());
         // TODO : do land thing and start thing
      }
      
      this.discard ();
   }
}

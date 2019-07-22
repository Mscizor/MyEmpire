/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cards;

import java.util.ArrayList;
import model.Player;
import model.spaces.Space;

/**
 *
 * @author janur
 */
public class CardCJail extends Card
{
   public CardCJail (String name, String text)
   {
      super (name, text);
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces)
   {
      if (spaces.get (16).getName ().equals ("JAIL"))
      {
         player.changePosition (16);
         player.arrest ();
      }
      this.discard ();
   }
}

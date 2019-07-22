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
public class CardBStart extends Card
{
   public CardBStart (String name, String text)
   {
      super (name, text);
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces)
   {
      if (spaces.get (0).getName ().equals ("START"))
      {
         player.changePosition (0);
         // TODO: do land thing
         
      }
      
      this.discard ();
   }
}

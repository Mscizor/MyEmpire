/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cards;

import model.Player;

/**
 *
 * @author janur
 */
public class CardBGetCash extends Card
{
   private final double cash;
   public CardBGetCash (String name, String text, double cash)
   {
      super (name, text);
      this.cash = cash;
   }
   
   public void doCardEffect (Player player)
   {
      player.changeCash (this.cash);
      this.discard ();
   }
}

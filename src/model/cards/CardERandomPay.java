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
public class CardERandomPay extends Card
{
   private final double randomPayment;
   
   public CardERandomPay (String name, String text, double randomPayment)
   {
      super (name, text);
      this.randomPayment = randomPayment;
   }
   
   public void doCardEffect (Player player)
   {
      player.changeCash (-this.randomPayment);
      
      this.discard ();
   }
}

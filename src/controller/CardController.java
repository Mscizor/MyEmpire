package controller;

import model.cards.Card;
import model.cards.CardFJailFree;
import model.Bank;
import model.spaces.Space;
import model.Player;
import model.cards.*;
import java.util.ArrayList;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardController
{

   /**
    *
    */
   public CardController ()
   {
      
   }

   /**
    *
    * @param card The card to perform the effect with
    * @param player The player that drew the card
    * @param spaces The spaces of the game
    * @param bank The bank containing the bank's money
    */
   public static void doCardEffect (Card card, Player player, 
           ArrayList <Space> spaces, Bank bank)
   {
      if (card instanceof CardApply)
      {
         // apply
      }
      else if (card instanceof CardGetMoney)
      {
         double money = ((CardPayMoney) card).getMoney ();
         player.changeCash (money);
         bank.changeCash (-money);
      }
      else if (card instanceof CardFJailFree)
      {
         player.addCard (card);
      }
      else if (card instanceof CardMoveLand)
      {
         /*
          * land on the space thing
          */
      }
      else if (card instanceof CardMoveOnly)
      {
         /*
          * if (card.getEffectIndex() == 0)
          *    move directly jail
          * else
          *    land on thing
          */
      }
      else if (card instanceof CardPayMoney)
      {
         double money = ((CardPayMoney) card).getMoney ();
         player.changeCash (-money);
         bank.changeCash (money);
      }
      
      
   }
}

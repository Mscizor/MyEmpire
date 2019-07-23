package model.cards;

import model.Player;
import model.Bank;
import model.CardMoneyOnly;
import controller.Transactions;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardBGetCash extends Card implements CardMoneyOnly
{
   private final double cash;

   public CardBGetCash (String name, String text, double cash)
   {
      super (name, text);
      this.cash = cash;
   }

   public void doCardEffect (Player player, Bank bank)
   {
      Transactions.cashToBank (player, bank, cash);
      this.discard ();
   }
}

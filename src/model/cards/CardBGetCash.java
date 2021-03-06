package model.cards;

import controller.staticcontroller.Transactions;
import model.Bank;
import model.CardMoneyOnly;
import model.Player;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardBGetCash extends Card implements CardMoneyOnly {
    private final double cash;

    public CardBGetCash(String name, String text, double cash) {
        super(name, text);
        this.cash = cash;
    }

    @Override
    public boolean doCardEffect(Player player, Bank bank) {
        Transactions.cashToBank(player, bank, cash);
        this.discard();
        return false;
    }
}

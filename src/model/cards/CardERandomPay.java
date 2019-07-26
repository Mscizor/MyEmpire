package model.cards;

import controller.Transactions;
import model.Bank;
import model.CardMoneyOnly;
import model.Player;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardERandomPay extends Card implements CardMoneyOnly {
    private final double randomPayment;

    public CardERandomPay(String name, String text, double randomPayment) {
        super(name, text);
        this.randomPayment = randomPayment;
    }

    @Override
    public void doCardEffect(Player player, Bank bank) {
        Transactions.cashToBank(player, bank, randomPayment);

        this.discard();
    }
}

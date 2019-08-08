package model.cards;

import controller.staticcontroller.Transactions;
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
    public boolean doCardEffect(Player player, Bank bank) {
        boolean playerBankrupt;
        playerBankrupt = Transactions.cashToBank(player, bank, randomPayment);
        this.discard();
        return playerBankrupt;
    }
}

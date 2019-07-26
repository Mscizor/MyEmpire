package controller;

import model.Bank;
import model.Ownable;
import model.Player;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class Transactions {
    public static void cashToBank(Player player, Bank bank, double cash) {
        player.changeCash(-cash);
        bank.changeCash(cash);
    }

    public static void cashToOtherPlayer(Player player, Player other, double cash) {
        player.changeCash(-cash);
        other.changeCash(cash);
    }

    public static void tradeWithOtherPlayer(Player player, Player other,
                                            ArrayList<Ownable> give, ArrayList<Ownable> take) {
        for (Ownable own : give) {
            other.addOwnable(own);
        }

        for (Ownable own : take) {
            player.addOwnable(own);
        }

    }
}

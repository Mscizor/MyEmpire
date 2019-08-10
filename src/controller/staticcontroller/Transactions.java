package controller.staticcontroller;

import model.Bank;
import model.Ownable;
import model.Player;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class Transactions {
    public static boolean cashToBank(Player player, Bank bank, double cash) {
        if (player.getCash() >= cash) {
            player.changeCash(-cash);
            bank.changeCash(cash);
            return false;
        }
        return true;
    }

    public static boolean cashToOtherPlayer(Player player, Player other, double cash) {
        if (player.getCash () >= cash) {
            player.changeCash(-cash);
            other.changeCash(cash);
            return false;
        }
        return true;
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

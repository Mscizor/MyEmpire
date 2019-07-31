package model.spaces;

import controller.staticcontroller.Transactions;
import model.Bank;
import model.Player;

import javax.swing.*;

/**
 * This class inherits the attributes of the Space class
 * and transfers a certain amount of currency from the player to the bank
 * when player lands on said space.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Tax extends Space {
    private final double tax;
    private final boolean isIncome;

    /**
     * This constructor accepts the type of Tax (as an integer)
     * and the location and initializes the Tax.
     *
     * @param name
     * @param location The location of the tax space on the list of spaces.
     * @param tax
     * @param isIncome
     */
    public Tax(String name, int location, double tax, boolean isIncome, ImageIcon icon) {
        super(name, location, icon);
        this.tax = tax;
        this.isIncome = isIncome;
    }

    public void fineTax(Player player, Bank bank) {
        double fine;
        if (this.isIncome && player.getCash() * 0.1 >= tax)
            fine = 0.1 * player.getCash();
        else
            fine = this.tax;

        Transactions.cashToBank(player, bank, fine);
    }
}

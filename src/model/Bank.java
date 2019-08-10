package model;

/**
 * This class acts as an object that holds a value of type double which
 * acts as the game's money.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Bank {
    private double cash;

    /**
     * Constructor that initializes the bank's
     * money based on the amount of players that are currently
     * in a game.
     *
     * @param num the amount of players in the current game
     */
    public Bank(int num) {
        this.cash = num * 1500;
    }

    /**
     * Method that gets the amount of cash stored in the bank
     *
     * @return Double representing the amount of money currently in the bank
     */
    public double getCash() {
        return this.cash;
    }

    /**
     * Method that modifies the bank's cash value
     *
     * @param cash  the amount of money added to or subtracted from the bank
     */
    public void changeCash(double cash) {
        this.cash += cash;
    }
}

package model.spaces;

import controller.staticcontroller.Transactions;
import model.Bank;
import model.CardApplyOwnableSpace;
import model.Player;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class inherits the attributes of the OwnableSpace class.
 * It is a special type of ownable space that increases its value the more of it
 * a player owns.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Railroad extends OwnableSpace {
    /**
     * Constructor that initializes railroad name, location, and cash
     * value.
     *
     * @param name          the name of the railroad
     * @param location      the location of the railroad in the array list of spaces
     * @param price         the price of the railroad
     * @param spaceIcon     the image of the railroad players can land on
     * @param displayIcon   the image of the railroad that holds its information
     */
    public Railroad(String name, int location, double price, ImageIcon spaceIcon, ImageIcon displayIcon) {
        super(name, location, price, spaceIcon, displayIcon);
    }

    /**
     * Method that gets the railroad's rent value
     *
     * @param players   the list of players
     * @param spaces    the list of spaces
     * @param player    the current player
     *
     * @return Integer specifying the railroad's current rent
     */
    @Override
    public double getRent(ArrayList<Player> players,
                          ArrayList<Space> spaces, Player player) {
        int i;
        int baseRent = 0;
        int finalRent;
        int countRailroads = 0;
        Player owner = this.getOwner(players);

        if (player == owner) {
            return 0;
        }

        // checks the number of railroads owned by the player
        for (i = 0; i < owner.getOwned().size(); i++) {
            if (owner.getOwned().get(i) instanceof Railroad) {
                countRailroads++;
            }
        }

        //decides value based on railroads owned
        switch (countRailroads) {
            case 1:
                baseRent = 25;
                break;
            case 2:
                baseRent = 50;
                break;
            case 3:
                baseRent = 150;
        }

        //applies card effects to the base rent value of the railroad
        finalRent = baseRent;
        for (i = 0; i < this.getCards().size(); i++) {
            if (this.getCards().get(i) instanceof CardApplyOwnableSpace) {
                finalRent *= ((CardApplyOwnableSpace) this.getCards().get(i)).getChangeToRent();
            }
        }

        return finalRent;
    }

    /** Method that checks if the railroad is owned
     *  and if the player is able to afford it.
     *  If railroad has no owner and the player is able to afford it,
     *  player buys the railroad.
     *
     * @param players   the list of players
     * @param spaces    the list of spaces
     * @param player    the current player
     * @param bank      the bank of the game
     */
    @Override
    public void buySpace(ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank) {
        Player owner = this.getOwner(players);
        if (owner == null && player.getCash() >= this.getPrice()) {
            Transactions.cashToBank(player, bank, this.getPrice());
            player.addOwnable(this);
        }
    }

    /**
     * @return String that contains the name and location of the property.
     */
    @Override
    public String toString() {
        String string = "";

        string += this.getName() + " at Space " + this.getLocation();

        return string;
    }
}

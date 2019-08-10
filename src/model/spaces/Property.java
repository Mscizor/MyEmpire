package model.spaces;

import controller.staticcontroller.Transactions;
import model.Bank;
import model.CardApplyOwnableSpace;
import model.Ownable;
import model.Player;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class represents the available properties on the board rand
 * includes methods that check for interactions like development and
 * rent calculation.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Go
 * @version 1.0
 * @since 1.0
 */
public class Property extends OwnableSpace {
    private final String color;
    private final double pricePerBuilding;
    /**
     * The base rent of the property as the number of buildings go up.
     */
    private final double[] baseRents;
    private final double multiplier;
    private int numHouses;
    private int numHotels;
    private double totalCollected;
    private int footTraffic;

    /**
     * Constructor that constructs a property with the input attributes.
     *
     * @param name             Name of the property
     * @param color            Color value of the property
     * @param location         Location index of the property along the board
     * @param price            Price to pay when buying the property
     * @param pricePerBuilding Price to pay when developing
     * @param baseRents        Index array of the base rents of the property
     * @param multiplier       Multiplier that determines how much foot traffic is
     *                         needed for development
     * @param spaceIcon        Image of the property players can land on
     * @param displayIcon      Image of the property that holds its information
     */
    public Property(String name, String color, int location, double price, double pricePerBuilding, double[] baseRents,
                    double multiplier, ImageIcon spaceIcon, ImageIcon displayIcon) {
        super(name, location, price, spaceIcon, displayIcon);
        this.baseRents = baseRents;
        this.color = color;
        this.pricePerBuilding = pricePerBuilding;
        this.multiplier = multiplier;
    }

    /**
     * Calculates the total rent of a property.
     * Takes into account the base rent, the number of properties the owner has
     * of the same color, and the cards applied to it.
     *
     * @param players   The list of players
     * @param spaces    The list of spaces
     * @param player    The current player
     *
     * @return Double representing the total rent value.
     */
    @Override
    public double getRent(ArrayList<Player> players, ArrayList<Space> spaces, Player player) {
        int i;
        double baseRent;
        double finalRent;
        int count = 1;
        Property pHold;

        baseRent = this.baseRents[numHouses];
        finalRent = baseRent;

        if (player == this.getOwner(players)) {
            return 0;
        }

        for (i = 0; i < spaces.size(); i++) {
            Player owner = this.getOwner(players);

            if (spaces.get(i) instanceof Property) {
                pHold = (Property) spaces.get(i);
                if (pHold != this && pHold.color.equals(this.color) && pHold.getOwner(players) != null) {
                    if (pHold.getOwner(players).getName().equals(owner.getName())) {
                        count++;
                    }
                }
            }
        }

        for (i = 0; i < this.getCards().size(); i++) {
            if (this.getCards().get(i) instanceof CardApplyOwnableSpace) {
                finalRent *= ((CardApplyOwnableSpace) (this.getCards().get(i))).getChangeToRent();
            }
        }

        if (count == 2) {
            finalRent += 10;
        } else if (count == 3) {
            finalRent += 20;
        }

        return finalRent;
    }

    /**
     * Method that gets the string representation of the color.
     *
     * @return String representing the color of the property.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Method that gets the price of each building of the property.
     *
     * @return Integer representing the price of each building.
     */
    public double getPricePerBuilding() {
        return this.pricePerBuilding;
    }

    /**
     * Method that gets the number of buildings.
     *
     * @return Integer representing the number of buildings.
     */
    public int getNumHouses() {
        return this.numHouses;
    }

    /**
     * Method that gets the number of hotels.
     *
     * @return Integer representing the number of hotels.
     */
    public int getNumHotels() {
        return this.numHotels;
    }

    /**
     * Method that gets the base rents of this property.
     *
     * @return Integer Array representing the base rents of the property.
     */
    public double[] getBaseRents() {
        return this.baseRents;
    }

    /**
     * Method that gets the multiplier used in calculating foot traffic threshold.
     *
     * @return Double representing the multiplier of the property.
     */
    public double getMultiplier() {
        return this.multiplier;
    }

    /**
     * Method that gets the current foot traffic of this property.
     *
     * @return Integer representing the current foot traffic of the property.
     */
    public int getFootTraffic() {
        return this.footTraffic;
    }

    /**
     * Method that transfers money from the player to the bank
     * depending on whether a building was added
     *
     * @param players   the list of players
     * @param bank      the bank of the game
     */
    public void develop (ArrayList <Player> players, Bank bank) {
        boolean wasAdded = this.addBuilding (players);
        if (wasAdded)
            Transactions.cashToBank (this.getOwner (players), bank, this.getPricePerBuilding());
    }

    /**
     * Method that adds a building an if there is still room to develop.
     *
     * @param players   The list of players in the game
     *
     * @return Boolean specifying if a building can be added
     */
    public boolean addBuilding(ArrayList<Player> players) {
        if (this.isAbleToDevelop(players)) {
            if (this.numHouses < 4 && this.numHouses >= 0) {
                this.numHouses++;
                return true;
            } else if (this.numHouses >= 4 && this.isOwnedFullyDeveloped(players)) {
                this.numHotels++;
                return true;
            }
        }
        return false;
    }

    /**
     * Method that adds foot traffic to the property.
     */
    public void addFootTraffic() {
        this.footTraffic++;
    }

    /**
     * Method that checks if the owner is able to develop the property. Takes into
     * consideration if the owner meets the requirements for development.
     *
     * @param players   the list of players
     *
     * @return Boolean specifying if the owner is able to develop.
     */
    public boolean isAbleToDevelop(ArrayList<Player> players) {
        return this.getOwner(players).getCash() >= this.pricePerBuilding
                && (this.footTraffic >= players.size() * multiplier
                || this.totalCollected >= this.pricePerBuilding);
    }

    /**
     * Method that checks if the owner's other properties of the same color are all fully
     * developed (aside from hotels).
     *
     * @param players   the list of players
     *
     * @return Boolean specifying if the owner's other properties of the same color have
     * 4 houses or a hotel.
     */
    public boolean isOwnedFullyDeveloped(ArrayList<Player> players) {
        int i;
        boolean ownedFullyDeveloped = true;
        Property hold;
        ArrayList<Ownable> owned = this.getOwner(players).getOwned();

        i = 0;
        while (i < owned.size() && ownedFullyDeveloped) {
            if (owned.get(i) instanceof Property) {
                hold = (Property) owned.get(i);
                if (hold.getColor().equals(this.getColor())) {
                    if (hold.numHouses < 4) {
                        ownedFullyDeveloped = false;
                    }
                }
            }

            i++;
        }
        return ownedFullyDeveloped;
    }

    /**
     *  Method that checks if the property is owned
     *  and if the player is able to afford it.
     *  If space has no owner and the player is able to afford it,
     *  player buys the space.
     *
     * @param players   the list of players
     * @param spaces    the list of spaces
     * @param player    the current player
     * @param bank      the bank of the game
     */
    @Override
    public void buySpace (ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank) {
        Player owner = this.getOwner(players);
        if (owner == null && player.getCash() >= this.getPrice()) {
            Transactions.cashToBank(player, bank, this.getPrice());
            player.addOwnable(this);
            System.out.println (this.getName ());
        }
    }

    /**
     * @return String that contains the name, color, and location of the property.
     */
    @Override
    public String toString() {
        String string = "";

        string += this.getName() + " - " + this.getColor() + " at Space " + this.getLocation();

        return string;
    }
}

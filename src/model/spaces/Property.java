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
     * A constructor that constructs a property with the input attributes.
     *
     * @param name             Name of the property
     * @param color            Color value of the property
     * @param location         Location index of the property along the board
     * @param price            Price to pay when buying the property
     * @param pricePerBuilding Price to pay when developing
     * @param baseRents        Index array of the base rents of the property
     * @param multiplier       Multiplier that determines how much foot traffic is
     *                         needed for development
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
     * @param players
     * @param spaces
     * @param player
     * @return The total rent value.
     */
    @Override
    public double getRent(ArrayList <Player> players, ArrayList <Space> spaces, Player player) {
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
            Player owner = this.getOwner (players);

            if (spaces.get(i) instanceof Property) {
                pHold = (Property) spaces.get(i);
                if (pHold != this && pHold.color.equals(this.color) && pHold.getOwner(players) != null) {
                    if (pHold.getOwner(players).getName() == owner.getName()) {
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
     * Gets the string representation of the color.
     *
     * @return String representing the color of the property.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Gets the price of each building of the property.
     *
     * @return Integer representing the price of each building.
     */
    public double getPricePerBuilding() {
        return this.pricePerBuilding;
    }

    /**
     * Gets the number of buildings.
     *
     * @return Integer representing the number of buildings.
     */
    public int getNumHouses() {
        return this.numHouses;
    }

    public int getNumHotels() {
        return this.numHotels;
    }

    /**
     * Gets the base rents of this property.
     *
     * @return Integer array representing the base rents of the property.
     */
    public double[] getBaseRents() {
        return this.baseRents;
    }

    /**
     * Gets the multiplier used in calculating foot traffic threshold.
     *
     * @return Double representing the multiplier of the property.
     */
    public double getMultiplier() {
        return this.multiplier;
    }

    /**
     * Gets the current foot traffic of this property.
     *
     * @return Integer representing the current foot traffic of the property.
     */
    public int getFootTraffic() {
        return this.footTraffic;
    }

    /**
     * Adds a building and takes money from the owner according to the price.
     * Only adds if able to develop and there is still room to develop.
     *
     * @param players
     */
    public void addBuilding(ArrayList<Player> players) {
        if (this.isAbleToDevelop(players)) {
            if (this.numHouses < 4 && this.numHouses >= 0) {
                this.numHouses++;
            }
            else if (this.numHouses >= 4 && this.isOwnedFullyDeveloped (players)) {
                this.numHotels++;
            }
        }
    }

    /**
     * Adds foot traffic to the property.
     */
    public void addFootTraffic() {
        this.footTraffic++;
    }

    /**
     * Checks if the owner is able to develop the property. Takes into
     * consideration if the owner meets the requirements for development.
     *
     * @param players
     * @return Truth value if the owner is able to develop.
     */
    public boolean isAbleToDevelop(ArrayList<Player> players) {
        return this.getOwner(players).getCash() >= this.pricePerBuilding
                && (this.footTraffic >= players.size() * multiplier
                || this.totalCollected >= this.pricePerBuilding);
    }

    /**
     * Checks if the owner's other properties of the same color are all fully
     * developed (aside from hotels).
     *
     * @param players
     * @return Truth value if the owner's other properties of the same color have
     * 4 houses or a hotel.
     */
    public boolean isOwnedFullyDeveloped (ArrayList<Player> players) {
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

    @Override
    public void buySpace(ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank) {
        Player owner = this.getOwner(players);
        if (owner == null && player.getCash() >= this.getPrice()) {
            Transactions.cashToBank(player, bank, this.getPrice());
            player.addOwnable(this);
        }
    }
}

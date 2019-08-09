package model;

import model.spaces.OwnableSpace;
import model.spaces.Property;

import java.util.ArrayList;

/**
 * This class holds player information and lets them choose between their playable actions
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Player {

    private final ArrayList<Ownable> owned;
    private String name;
    private double cash;
    private int position;
    private boolean inJail;
    private int diceRoll = 0;

    /**
     * Player constructor that initializes player name and cash value
     *
     * @param name the name of the current player
     */
    public Player(String name) {
        this.owned = new ArrayList<>();
        this.name = name;
        this.cash = 1500;
    }

    /**
     * Gets the player name
     *
     * @return a <code> String </code> specifying the name of the current player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the player's cash value
     *
     * @return a <code> double </code> specifying the cash value of the current player
     */
    public double getCash() {
        return this.cash;
    }

    /**
     * Gets the player's current position
     *
     * @return a <code> int </code> specifying the position of the current player in the array list of spaces.
     */
    public int getPosition() {
        return this.position;
    }

    public int getDiceRoll() {
        return this.diceRoll;
    }

    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;
    }

    /**
     * Gets and returns true if the player is in jail
     *
     * @return a <code> boolean </code> specifying the inJail value of the player
     */
    public boolean isInJail() {
        return this.inJail;
    }

    public ArrayList<Ownable> getOwned() {
        return this.owned;
    }

    public void addOwnable(Ownable own) {
        this.owned.add(own);
    }

    /**
     * sets player's inJail value to true
     */
    public void arrest() {
        this.inJail = true;
    }

    /**
     * sets player's inJail value to false
     */
    public void bail() {
        this.inJail = false;
    }

    /**
     * method that updates the player's cash value by the cashChange value
     *
     * @param cashChange the amount of change in a player's cash value
     */
    public void changeCash(double cashChange) {
        this.cash += cashChange;
    }

    /**
     * updates the player's position into the current one
     *
     * @param position the position of the current player in the array list of spaces
     */
    public void changePosition(int position) {
        this.position = position;
    }

    public double getTotalValue() {
        double totalValue = 0;
        for (Ownable own : this.owned) {
            if (own instanceof Property) {
                Property pHold = (Property) own;
                totalValue += pHold.getPricePerBuilding() * (pHold.getNumHouses() + pHold.getNumHotels());
            }

            if (own instanceof OwnableSpace) {
                totalValue += ((OwnableSpace) own).getPrice();
            }
        }
        return totalValue;
    }
}

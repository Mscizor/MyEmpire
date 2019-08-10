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
     * Constructor that initializes player name and cash value
     *
     * @param name the name of the current player
     */
    public Player(String name) {
        this.owned = new ArrayList<>();
        this.name = name;
        this.cash = 1500;
    }

    /**
     * Method that gets the player name
     *
     * @return String specifying the name of the current player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method that gets the player's cash value
     *
     * @return Double specifying the cash value of the current player
     */
    public double getCash() {
        return this.cash;
    }

    /**
     * Method that gets the player's current position
     *
     * @return Integer specifying the position of the current player in the array list of spaces.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Method that gets the player's rolled dice value
     *
     * @return Integer specifying the value of the rolled dice.
     */
    public int getDiceRoll() {
        return this.diceRoll;
    }

    /**
     * Method that sets the players rolled dice value
     * @param diceRoll  the value of the rolled dice
     */
    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;
    }

    /**
     * Method that checks whether or not the current player is in jail
     *
     * @return Boolean specifying whether or not the player is in jail
     */
    public boolean isInJail() {
        return this.inJail;
    }

    /**
     * Method that gets the player's list of ownable spaces
     * @return a <code> ArrayList </code> of @see Ownable
     * specifying the list of ownable spaces owned by the player
     */
    public ArrayList<Ownable> getOwned() {
        return this.owned;
    }

    /**
     * Method that adds an ownable space to the current list of ownable space
     * owned by the player
     *
     * @param own the ownable space to be added to the
     *            list of ownable spaces owned by the player
     */
    public void addOwnable(Ownable own) {
        this.owned.add(own);
    }

    /**
     * Method that sets player's inJail value to true
     */
    public void arrest() {
        this.inJail = true;
    }

    /**
     * Method that sets player's inJail value to false
     */
    public void bail() {
        this.inJail = false;
    }

    /**
     * Method that updates the player's cash value
     *
     * @param cashChange the amount of change to a player's current cash value
     */
    public void changeCash(double cashChange) {
        this.cash += cashChange;
    }

    /**
     * MEthod that updates the player's position
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

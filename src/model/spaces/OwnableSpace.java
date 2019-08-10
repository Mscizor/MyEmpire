package model.spaces;

import controller.staticcontroller.Transactions;
import model.Bank;
import model.Ownable;
import model.Player;
import model.cards.Card;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class inherits the attributes of the Space class.
 * This class holds the values for spaces that can be attributed to an owner.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public abstract class OwnableSpace extends Space implements Ownable {
    private double price;
    private ArrayList<Card> cards;

    /**
     * Constructor that accepts the type of Qwnable space
     * the location, and the price then initializes the vales for the Ownable space
     *
     * @param name          the name of the corner space
     * @param location      the location of the corner space on the array list of spaces
     * @param price         the price of the ownable space
     * @param spaceIcon     the image of the corner space players can land on
     * @param displayIcon   the image of the corner space that holds its information
     */
    public OwnableSpace(String name, int location, double price, ImageIcon spaceIcon, ImageIcon displayIcon) {
        super(name, location, spaceIcon, displayIcon);
        this.cards = new ArrayList<>();
        this.price = price;
    }

    /**
     * Method that gets the price of the space
     *
     * @return Integer specifying the price of the specific space
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Method that sets the price of the Ownable space
     * @param price the price of the Ownable space
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Method that gets the array list of cards
     *
     * @return a <code> ArrayList </code> of @see Card
     * specifying the array list of cards
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Method that sets the chance cards associated with the ownable space
     *
     * @param cards the list of cards
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * method that adds a card to the space
     *
     * @param card is the card added to the space
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

    /**
     * Method that removes a card from the space
     *
     * @param card the card added to the space
     */
    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public abstract double getRent(ArrayList<Player> players, ArrayList<Space> spaces, Player player);

    public abstract void buySpace(ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank);

    /**
     * Method that checks if a player is able to pay rent.
     * If player is able to, cash is transferred from the current player
     * to the owner of the space.
     *
     * @param players   the list of players in the game
     * @param spaces    the list if spaces in the game
     * @param player    the current player
     *
     * @return Boolean that specifies if a player is able to pay rent
     */
    public boolean payRent(ArrayList<Player> players, ArrayList<Space> spaces, Player player) {
        boolean playerBankrupt = false;
        Player owner = this.getOwner(players);
        if (owner != player && player.getCash() >= this.getRent(players, spaces, player)) {
            Transactions.cashToOtherPlayer(player, owner,
                    this.getRent(players, spaces, player));
        } else if (owner != player && player.getCash() < this.getRent(players, spaces, player)) {
            playerBankrupt = true;
        }
        return playerBankrupt;
    }
}

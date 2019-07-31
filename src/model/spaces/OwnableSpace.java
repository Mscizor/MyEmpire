package model.spaces;

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


    public OwnableSpace(String name, int location, double price, ImageIcon icon) {
        super(name, location, icon);
        this.cards = new ArrayList<>();
        this.price = price;
    }

    /**
     * Gets the price of the space
     *
     * @return a <code> int </code>
     * specifying the price of the specific space
     */
    public double getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the array list of cards
     *
     * @return a <code> ArrayList </code> of @see Card
     * specifying the array list of cards
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

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
     * method that removes a card from the space
     *
     * @param card is the card added to the space
     */
    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public abstract double getRent(ArrayList<Player> players, ArrayList<Space> spaces, Player player);

    public abstract void buySpace(ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank);

    public abstract void payRent(ArrayList<Player> players, ArrayList<Space> spaces, Player player);
}

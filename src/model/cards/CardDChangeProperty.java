package model.cards;

import model.Bank;
import model.CardApplyOwnableSpace;
import model.Player;
import model.spaces.OwnableSpace;
import model.spaces.Property;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardDChangeProperty extends Card implements CardApplyOwnableSpace {
    private final double changeToRent;
    private boolean applied;

    public CardDChangeProperty(String name, String text, double changeToRent) {
        super(name, text);
        this.changeToRent = changeToRent;
    }

    @Override
    public boolean doCardEffect(Player player, ArrayList<Space> spaces, OwnableSpace owned, Bank bank) {
        if (owned instanceof Property) {
            owned.addCard(this);
            this.applied = true;
        }
        return false;
    }

    @Override
    public boolean isApplied() {
        return this.applied;
    }

    @Override
    public double getChangeToRent() {
        return this.changeToRent;
    }
}

package model.cards;

import model.Bank;
import model.CardApplyOwnableSpace;
import model.Player;
import model.spaces.OwnableSpace;
import model.spaces.Railroad;
import model.spaces.Space;
import model.spaces.Utility;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardDChangeNonProperty extends Card implements CardApplyOwnableSpace {
    private final double changeToRent;
    private boolean applied = false;

    public CardDChangeNonProperty(String name, String text, double changeToRent) {
        super(name, text);
        this.changeToRent = changeToRent;
    }

    @Override
    public boolean doCardEffect(Player player, ArrayList<Space> spaces,
                                OwnableSpace owned, Bank bank) {
        if (owned instanceof Railroad || owned instanceof Utility) {
            owned.addCard(this);
            this.applied = true;
        }
        return false;
    }

    @Override
    public double getChangeToRent() {
        return this.changeToRent;
    }

    @Override
    public boolean isApplied() {
        return this.applied;
    }
}

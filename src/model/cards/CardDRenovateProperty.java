package model.cards;

import controller.Transactions;
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
public class CardDRenovateProperty extends Card implements CardApplyOwnableSpace {
    private final double changeToRent;
    private boolean applied;

    public CardDRenovateProperty(String name, String text,
                                 double changeToRent) {
        super(name, text);
        this.changeToRent = changeToRent;
    }

    @Override
    public void doCardEffect(Player player, ArrayList<Space> spaces, OwnableSpace owned, Bank bank) {
        if (owned instanceof Property) {
            int ownedHouses = 0, ownedHotels = 0;
            for (int i = 0; i < player.getOwned().size(); i++) {
                if (player.getOwned().get(i) instanceof Property) {
                    Property pHold = (Property) player.getOwned().get(i);
                    ownedHouses += pHold.getNumHouses();
                    ownedHotels += pHold.getNumHotels();
                }
            }
            Transactions.cashToBank(player, bank, ownedHouses * 25 + ownedHotels * 50);
            owned.addCard(this);
            this.applied = true;
        } else {
            this.discard();
        }
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

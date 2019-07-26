package controller;

import model.*;
import model.cards.Card;
import model.spaces.OwnableSpace;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardController {

    /**
     * @param card   The card to perform the effect with
     * @param player The player that drew the card
     * @param spaces The spaces of the game
     * @param bank   The bank containing the bank's money
     */
    public static void doCardEffect(Card card, Player player, ArrayList<Space> spaces, Bank bank) {
        if (card instanceof CardMoneyOnly) {
            // TODO: GUI
            ((CardMoneyOnly) card).doCardEffect(player, bank);
            // TODO: GUI
        } else if (card instanceof CardMovePlayer) {
            // TODO: GUI
            ((CardMovePlayer) card).doCardEffect(player, spaces, bank);
            // TODO: GUI
        } else if (card instanceof CardApplyOwnableSpace) {
            OwnableSpace owned = null;
            // TODO: GUI
            ((CardApplyOwnableSpace) card).doCardEffect(player, spaces, owned, bank);
            // TODO: GUI
        }
    }

}

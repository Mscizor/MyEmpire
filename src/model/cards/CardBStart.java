package model.cards;

import model.Bank;
import model.CardMovePlayer;
import model.Player;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardBStart extends Card implements CardMovePlayer {
    public CardBStart(String name, String text) {
        super(name, text);
    }

    @Override
    public boolean doCardEffect(Player player, ArrayList<Space> spaces, Bank bank) {
        if (spaces.get(0).getName().equals("START")) {
            int playerPos = player.getPosition();
            int destinationLoc = 0;
            int distance;
            if (destinationLoc - playerPos >= 0) {
                distance = destinationLoc - playerPos;
            } else {
                distance = destinationLoc - playerPos + 32;
            }
            player.setDiceRoll (distance);
        }
        this.discard();
        return false;
    }
}

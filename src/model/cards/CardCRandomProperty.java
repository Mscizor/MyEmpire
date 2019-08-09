package model.cards;

import model.Bank;
import model.CardMovePlayer;
import model.Player;
import model.spaces.Property;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardCRandomProperty extends Card implements CardMovePlayer {
    private final Property random;

    public CardCRandomProperty(String name, String text, Property random) {
        super(name, text);
        this.random = random;
    }

    @Override
    public boolean doCardEffect(Player player, ArrayList<Space> spaces, Bank bank) {
        boolean playerBankrupt = false;
        if (random == null) {
            player.changePosition(0);
        } else {
            int playerPos = player.getPosition();
            int destinationLoc = random.getLocation();
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

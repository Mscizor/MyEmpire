package model.cards;

import model.Bank;
import model.CardMovePlayer;
import model.Player;
import model.spaces.Railroad;
import model.spaces.Space;
import model.spaces.Utility;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardANearestNonProperty extends Card implements CardMovePlayer {
    private final boolean isRailroad;

    public CardANearestNonProperty(String name, String text, boolean isRailroad) {
        super(name, text);
        this.isRailroad = isRailroad;
    }

    @Override
    public boolean doCardEffect(Player player, ArrayList<Space> spaces, Bank bank) {
        boolean playerBankrupt = false;
        int playerPos = player.getPosition();
        int highestDistance = 0;
        int currentDistance;
        int destinationLoc;
        for (Space search : spaces) {
            if ((search instanceof Railroad && this.isRailroad)
                    || (search instanceof Utility && !this.isRailroad)) {
                destinationLoc = search.getLocation();

                if (destinationLoc - playerPos >= 0) {
                    currentDistance = destinationLoc - playerPos;
                } else {
                    currentDistance = destinationLoc - playerPos + 32;
                }

                if (currentDistance > highestDistance) {
                    highestDistance = currentDistance;
                }
            }
        }
        // TODO: move and land
        this.discard();

        return playerBankrupt;
    }
}

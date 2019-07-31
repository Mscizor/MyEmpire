package controller.staticcontroller;

import model.Bank;
import model.Player;
import model.spaces.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class SpaceController {
    public static void doLandEffect(ArrayList<Player> players,
                                    ArrayList<Space> spaces, Space space, Player player, Bank bank) {
        if (space instanceof Property) {
            Property property = (Property) space;
            Player owner = property.getOwner(players);
            if (owner == null && player.getCash() >= property.getPrice()) {
                property.buySpace(players, spaces, player, bank);
            } else if (owner == player && property.isAbleToDevelop(players)) {
                property.addBuilding(players);
            } else {
                property.payRent(players, spaces, player);
            }
        } else if (space instanceof Utility || space instanceof Railroad) {
            OwnableSpace oSpace = (OwnableSpace) space;
            Player owner = oSpace.getOwner(players);
            if (owner == null) {
                oSpace.buySpace(players, spaces, player, bank);
            } else if (owner == player) {
            } else {
                oSpace.payRent(players, spaces, player);
            }
        } else if (space instanceof Corner) {
            String name = space.getName();
            switch (name) {
                case "START":
                    Transactions.cashToBank(player, bank, 200);
                    break;
                case "Community Service":
                    Transactions.cashToBank(player, bank, 50);
                    break;
                case "Free Parking":
                    break;
                case "JAIL":
                    player.arrest();
                    break;
            }
        } else if (space instanceof Chance) {

        } else if (space instanceof Tax) {
            String name = space.getName();
            switch (name) {
                case "Luxury Tax":
                    player.changeCash(-75);
                    break;
                case "Income Tax":
                    double cash;
                    if (player.getCash() * 0.10 > 200) {
                        cash = player.getCash() * 0.10;
                    } else {
                        cash = 200;
                    }
                    player.changeCash(-cash);
                    break;
            }
        }
    }

    public static Property getRandomProperty(ArrayList<Space> spaces) {
        Random rand = new Random();
        int loc = rand.nextInt(32);
        Property random = null;
        boolean found = false;
        for (int j = 0; j < 31 && !found; j++) {
            if (spaces.get((loc + j) % 32) instanceof Property) {
                random = (Property) spaces.get((loc + j) % 32);
            }
            found = true;
        }

        return random;
    }
}
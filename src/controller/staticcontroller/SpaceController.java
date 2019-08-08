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
    public static boolean doLandEffect (ArrayList<Player> players, ArrayList<Space> spaces, Space space, Player player,
                                    Bank bank) {
        boolean playerBankrupt = false;
        // TODO: gui message
        if (space instanceof Property) {
            Property property = (Property) space;
            Player owner = property.getOwner(players);
            if (owner == null && player.getCash() >= property.getPrice()) {
                property.buySpace(players, spaces, player, bank);
            }
            else if (owner == player && property.isAbleToDevelop(players)) {
                property.addBuilding(players);
            }
            else if (player.getCash() > property.getRent (players, spaces, player)){
               playerBankrupt = property.payRent (players, spaces, player);
            }
        }
        else if (space instanceof Utility || space instanceof Railroad) {
            OwnableSpace oSpace = (OwnableSpace) space;
            Player owner = oSpace.getOwner(players);
            if (owner == null) {
                oSpace.buySpace(players, spaces, player, bank);
            }
            else if (owner == player) {
            }
            else if (player.getCash() > oSpace.getRent (players, spaces, player)){
                playerBankrupt = oSpace.payRent(players, spaces, player);
            }
        }
        else if (space instanceof Corner) {
            String name = space.getName();
            switch (name) {
                case "START":
                    if (player.getCash () > 200)
                        Transactions.cashToBank(player, bank, 200);
                    else
                        playerBankrupt = false;
                    break;
                case "Community Service":
                    if (player.getCash () > 50)
                        Transactions.cashToBank(player, bank, 50);
                    else
                        playerBankrupt = false;
                    break;
                case "Free Parking":
                    break;
                case "JAIL":
                    player.arrest();
                    break;
            }
        }
        else if (space instanceof Chance) {

        }
        else if (space instanceof Tax) {
            String name = space.getName();
            switch (name) {
                case "Luxury Tax":
                    player.changeCash(-75);
                    break;
                case "Income Tax":
                    double cash = 0;
                    if (player.getCash() * 0.10 > 200) {
                        cash = player.getCash() * 0.10;
                    }
                    else if (player.getCash () >= 200){
                        cash = 200;
                    }
                    else {
                        playerBankrupt = true;
                    }

                    if (!playerBankrupt)
                        player.changeCash(-cash);
                    break;
            }
        }

        return playerBankrupt;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.staticcontroller;

import model.Bank;
import model.Player;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author janur
 */
public class PlayerController {
    public static boolean moveAndLand(ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank,
                                   boolean direct) {
        boolean playerBankrupt;
        int playerPos = player.getPosition();
        int diceRoll = player.getDiceRoll();

        if (playerPos + diceRoll < playerPos && !direct) {
            // TODO: GUI Message
            Transactions.cashToBank(player, bank, -200);
            // TODO: update in GUI
        }

        playerPos = playerPos + diceRoll;
        player.changePosition(playerPos);

        playerBankrupt = SpaceController.doLandEffect(players, spaces, spaces.get(playerPos),
                player, bank);

        return playerBankrupt;
    }

    public static boolean moveToAndLand (ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank,
                                     Space space, boolean direct) {
        boolean playerBankrupt;
        int playerPos = player.getPosition();
        int destinationLoc = space.getLocation();

        if (destinationLoc < playerPos && !direct) {
            // TODO: GUI message
            Transactions.cashToBank(player, bank, -200);
            // TODO: update in GUI
        }

        player.changePosition(destinationLoc);

        playerBankrupt = SpaceController.doLandEffect(players, spaces, space, player, bank);
        return playerBankrupt;
    }
}

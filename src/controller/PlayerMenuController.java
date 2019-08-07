package controller;

import java.util.ArrayList;

import gui.PlayerListener;
import gui.PlayerMenuFrame;
import model.Bank;
import model.Player;

public class PlayerMenuController {

    private ArrayList <Player> players;
    private Bank bank;

    private PlayerMenuFrame playerMenu;

    public PlayerMenuController () {
        this.players = new ArrayList <> ();
        this.playerMenu = new PlayerMenuFrame (new PressedFinish ());
    }

    private class PressedFinish implements PlayerListener {
        public void playerNamesAdded (ArrayList <String> playerNames) {
            playerMenu.dispose ();
            for (String player : playerNames) {
                players.add (new Player (player));
                System.out.println (player);
            }

            bank = new Bank (playerNames.size ());
            new SpaceSelectController (players, bank);
        }
    }
}

package controller;

import gui.PlayingFrame;
import gui.ButtonListener;
import model.*;
import model.cards.*;
import model.spaces.*;

import javax.swing.*;
import java.util.ArrayList;

public class PlayingController {

    private ArrayList <Player> players;
    private ArrayList <Space> spaces;
    private CardSet cards;
    private Bank bank;
    private boolean gameOver = false;

    private PlayingFrame playing;

    public PlayingController (ArrayList <Player> players, ArrayList <Space> spaces, Bank bank) {
        this.players = players;
        this.spaces = spaces;
        this.cards = new CardSet (spaces);
        this.bank = bank;

        ArrayList <ImageIcon> spaceImages = new ArrayList <> ();
        for (Space space : spaces) {
            spaceImages.add (space.getSpaceIcon ());
        }

        ArrayList <String> names = new ArrayList <> ();
        for (Player player : players) {
            names.add (player.getName ());
        }

        this.playing = new PlayingFrame("My Empire", names, bank.getCash(),
                players.get (0).getCash (), spaceImages, new ButtonPressed ());
    }

    public void gameStart () {
        while (!gameOver) {

        }

    }

    private class ButtonPressed implements ButtonListener {
        @Override
        public void buttonPressed (int index) {
            switch (index) {
                case 0:
                    break;
                case 1: // Purchased
                    break;
                case 2: // Develop
                    break;
                case 3: // Pay Rent
                    break;
                case 4: // Trade
                    break;
                case 5: // Finished
                    break;
            }
        }
    }
}

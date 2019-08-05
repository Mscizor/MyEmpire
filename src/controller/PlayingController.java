package controller;

import gui.BoardFrame;
import gui.InfoFrame;
import model.*;
import model.cards.*;
import model.spaces.*;

import javax.swing.*;
import java.util.ArrayList;

public class PlayingController {

    ArrayList <Player> players;
    ArrayList <Space> spaces;
    CardSet cards;
    Bank bank;

    BoardFrame board;
    InfoFrame info;

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

        this.board = new BoardFrame (names, bank.getCash(), spaceImages);
//        this.info = new InfoFrame (names, bank.getCash ());
    }
}

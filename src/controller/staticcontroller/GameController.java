package controller.staticcontroller;

import model.Bank;
import model.Player;
import model.cards.CardSet;
import model.spaces.Space;

import java.util.ArrayList;

public class GameController {
    private final ArrayList <Player> players;
    private final ArrayList <Space> spaces;
    private final Bank bank;
    private final CardSet cards;

    public GameController (ArrayList <Player> players, ArrayList <Space> spaces, Bank bank, CardSet cards) {
        this.players = players;
        this.spaces = spaces;
        this.bank = bank;
        this.cards = cards;
    }
}

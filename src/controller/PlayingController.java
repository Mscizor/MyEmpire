package controller;

import controller.staticcontroller.Transactions;
import gui.PlayingFrame;
import gui.ButtonListener;
import model.*;
import model.cards.*;
import model.spaces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayingController {

    private ArrayList <Player> players;
    private ArrayList <Space> spaces;
    private CardSet cards;
    private Bank bank;

    public static final boolean[] JUST_ROLL_DICE = new boolean[] {true, false, false, false, false, false};
    public static final boolean[] JUST_PURCHASE = new boolean[] {false, true, false, false, false, false};
    public static final boolean[] JUST_DEVELOP = new boolean[] {false, false, true, false, false, false};
    public static final boolean[] JUST_FINISHED = new boolean[] {false, false, false, false, false, true};
    public static final boolean[] PAY_OR_TRADE = new boolean[] {false, false, false, true, true, false};
    public static final boolean[] TRADE_OR_FINISHED = new boolean[] {false, false, false, false, true, false};

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

    public PlayingFrame getPlayingFrame () {
        return this.playing;
    }
    public void gameStart () {
        while (!this.isGameOver ()) {
            // TODO: Reduce jail
            if (!this.isGameOver ()) {
                return;
            }
            // TODO: Wait for player input (roll dice)
            // TODO: Do land effect
            if (!this.isGameOver ()) {
                return;
            }
            // TODO: Next player
        }
        // TODO: Show winners
    }

    public void doCardEffect (Card card, Player player, ArrayList<Space> spaces, Bank bank) {
        boolean playerBankrupt = false;
        String text = card.getText ();
        if (card instanceof CardMoneyOnly) {
            playerBankrupt = ((CardMoneyOnly) card).doCardEffect(player, bank);
            this.updateGUI ();
        }
        else if (card instanceof CardMovePlayer) {
            playerBankrupt = ((CardMovePlayer) card).doCardEffect(player, spaces, bank);
            this.updateGUI ();
        }
        else if (card instanceof CardApplyOwnableSpace) {
            OwnableSpace owned = null;
            // TODO: get player input
            playerBankrupt = ((CardApplyOwnableSpace) card).doCardEffect(player, spaces, owned, bank);
            this.updateGUI ();
        }
    }

    public boolean doLandEffect (ArrayList <Player> players, ArrayList<Space> spaces, Space space, Player player,
                                        Bank bank) {
        boolean playerBankrupt = false;
        this.playing.setIcon (space.getDisplayIcon ());
        this.playing.setIconVisible (true);

        if (space instanceof Property) {
            Property property = (Property) space;
            Player owner = property.getOwner(players);
            if (owner == null && player.getCash() >= property.getPrice()) {
                // TODO: text
                this.playing.setButtonsEnabled (JUST_PURCHASE);
            }
            else if (owner == player && property.isAbleToDevelop(players)) {
                // TODO: text
                this.playing.setButtonsEnabled (JUST_DEVELOP);
            }
            else {
                // TODO: text
                this.playing.setButtonsEnabled (PAY_OR_TRADE);
            }
        }
        else if (space instanceof Utility || space instanceof Railroad) {
            OwnableSpace oSpace = (OwnableSpace) space;
            Player owner = oSpace.getOwner(players);
            if (owner == null) {
                // TODO: text
                this.playing.setButtonsEnabled (JUST_PURCHASE);
                oSpace.buySpace(players, spaces, player, bank);
            }
            else if (owner == player) {
                // TODO: text
                this.playing.setButtonsEnabled (JUST_FINISHED);
            }
            else {
                // TODO: text
//                if (player.getCash() > space.getRent)
//                this.playing.setButtonsEnabled (PAY_OR_TRADE);
//                else
//                this.playing.setButtonsEnabled (TRADE_OR_FINISHED);
                playerBankrupt = oSpace.payRent(players, spaces, player);
            }
        }
        else if (space instanceof Corner) {
            String name = space.getName();
            switch (name) {
                case "START":
                    // TODO: text
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    if (player.getCash () > 200)
                        Transactions.cashToBank(player, bank, 200);
                    else
                        playerBankrupt = true;
                    this.updateGUI ();
                    break;
                case "Community Service":
                    // TODO: text
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    if (player.getCash () > 50)
                        Transactions.cashToBank(player, bank, 50);
                    else
                        playerBankrupt = true;
                    this.updateGUI ();
                    break;
                case "Free Parking":
                    // TODO: text
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    this.updateGUI ();
                    break;
                case "JAIL":
                    // TODO: text
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    this.updateGUI ();
                    player.arrest();
                    break;
            }
        }
        else if (space instanceof Chance) {
            // TODO: text
            // TODO: draw card
            // TODO: do card effect
            this.updateGUI ();
        }
        else if (space instanceof Tax) {
            String name = space.getName();
            switch (name) {
                case "Luxury Tax":
                    // TODO: text
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    if (player.getCash() < 75) {
                        playerBankrupt = true;
                    }
                    else {
                        player.changeCash(-75);
                    }
                    this.updateGUI ();
                    break;
                case "Income Tax":
                    // TODO: text
                    this.playing.setButtonsEnabled (JUST_FINISHED);
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
                    this.updateGUI ();
                    break;
            }
        }

        return playerBankrupt;
    }

    public boolean moveAndLand (ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank,
                                      boolean direct) {
        boolean playerBankrupt;
        int playerPos = player.getPosition();
        int diceRoll = player.getDiceRoll();

        if (playerPos + diceRoll < playerPos && !direct) {
            // TODO: emit text
            Transactions.cashToBank(player, bank, -200);
            this.updateGUI ();
        }

        playerPos = playerPos + diceRoll;
        player.changePosition(playerPos);
        this.updateGUI ();

        playerBankrupt = this.doLandEffect(players, spaces, spaces.get(playerPos), player, bank);

        return playerBankrupt;
    }

    public boolean moveToAndLand (ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank,
                                         Space space, boolean direct) {
        boolean playerBankrupt;
        int playerPos = player.getPosition();
        int destinationLoc = space.getLocation();

        if (destinationLoc < playerPos && !direct) {
            // TODO: emit text
            Transactions.cashToBank(player, bank, -200);
            this.updateGUI ();
        }

        player.changePosition(destinationLoc);
        this.updateGUI ();

        playerBankrupt = this.doLandEffect(players, spaces, space, player, bank);
        return playerBankrupt;
    }

    public boolean isGameOver () {
        return false;
    }

    public void updateGUI () {
        for (int i = 0; i < players.size (); i++) {
            Player player = players.get (i);
            ArrayList <Ownable> owned = player.getOwned ();
            StringBuilder ownedText = new StringBuilder ();
            for (Ownable ownable : owned) {
                ownedText.append ("\n" + ownable);
            }
            playing.setPlayerCash (i, player.getCash ());
            playing.setPlayerText (i, ownedText.toString ());
            playing.refresh ();

            int movement = playing.getPlayerLocation (i) - player.getPosition ();
            if (movement < 0)
                movement += 32;
            playing.moveCurrentPlayer (movement);
        }

        playing.setBankCash (bank.getCash ());
    }

    private class ButtonPressed implements ButtonListener {
        @Override
        public void buttonPressed (int index) {
            switch (index) {
                case 0: // Roll Dice
                    for (Player player : players) {
                        player.setDiceRoll (0);
                    }
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

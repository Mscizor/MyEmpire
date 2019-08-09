package controller;

import controller.staticcontroller.Transactions;
import gui.ButtonListener;
import gui.PlayingFrame;
import model.*;
import model.cards.Card;
import model.cards.CardSet;
import model.spaces.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class PlayingController {

    int currentPlayer = 0;

    private ArrayList <Player> players;
    private ArrayList <Space> spaces;
    private CardSet cards;
    private Bank bank;

    private boolean turnOver = false;
    private boolean gameOver = false;

    public static final boolean[] JUST_ROLL_DICE = new boolean[] {true, false, false, false, false, false};
    public static final boolean[] JUST_PURCHASE = new boolean[] {false, true, false, false, false, false};
    public static final boolean[] JUST_DEVELOP = new boolean[] {false, false, true, false, false, false};
    public static final boolean[] JUST_FINISHED = new boolean[] {false, false, false, false, false, true};
    public static final boolean[] PAY_OR_TRADE = new boolean[] {false, false, false, true, true, false};
    public static final boolean[] TRADE_OR_FINISHED = new boolean[] {false, false, false, false, true, false};

    private PlayingFrame playing;

    private boolean waitingForButton = false;

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
        this.doTurn ();
    }

    public void doTurn () {
        Random rand = new Random ();
        Player player = this.players.get (currentPlayer);
        this.turnOver = false;
        System.out.println ("yeet1111");
        playing.emitMessage (2, String.format ("It is %s's turn!", player.getName ()));
        this.waitingForButton = false;
        playing.testPause (10);
        if (player.isInJail()) {
                playing.emitMessage(5, String.format("%s pays 50 to the bank as payment for JAIL.", player.getName()));
                player.bail();
                this.gameOver = Transactions.cashToBank(player, bank, -50);
                this.updateGUI();
        }
        if (gameOver) {
                return;
        }
        this.waitingForButton = true;
        this.playing.setButtonsEnabled(JUST_ROLL_DICE);
    }

    public void doNextPartTurn () {
        Player player = this.players.get (currentPlayer);
        this.gameOver = this.moveAndLand(players, spaces, player, bank, false);
        this.turnOver = true;
        if (gameOver) {
            return;
        }
        // TODO: Show winners
        System.out.println ("yeet finished");
    }

    public boolean doCardEffect (Card card, Player player, ArrayList<Space> spaces, Bank bank) {
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
        return playerBankrupt;
    }

    private boolean doLandEffect (ArrayList <Player> players, ArrayList<Space> spaces, Space space, Player player,
                                        Bank bank) {
        boolean playerBankrupt = false;
        String text;
        String pName = player.getName ();
        String sName = space.getName ();
        this.playing.setIcon (space.getDisplayIcon ());
        this.playing.setIconVisible (true);

        if (space instanceof Property) {
            Property property = (Property) space;
            Player owner = property.getOwner (players);
            if (owner == null && player.getCash() >= property.getPrice()) {
                text = String.format ("No one owns %s. %s buys the property for %.1f", pName, sName, property.getPrice ());
                this.playing.setButtonsEnabled (JUST_PURCHASE);
            }
            else if (owner == player && property.isAbleToDevelop(players)) {
                text = String.format ("%s lands on their own property (%s) is able to develop the property for %.1f", pName, sName, property.getPrice ());
                this.playing.setButtonsEnabled (JUST_DEVELOP);
            }
            else if (owner == player) {
                text = String.format ("%s lands on their own property (%s), but is not able to develop their property.", pName, property.getName ());
                this.playing.setButtonsEnabled (JUST_FINISHED);
            }
            else if (owner != null) {
                if (player.getCash () >= property.getRent (players, spaces, player))
                {
                    text = String.format("%s lands on %s's property (%s) and can either trade or pay them %.1f as rent.", pName, owner.getName(), pName,
                            property.getRent(players, spaces, player));
                    this.playing.setButtonsEnabled (PAY_OR_TRADE);
                }
                else {
                    text = String.format ("%s lands on %s's property (%s) and can either trade or declare bankruptcy.", pName, owner.getName (), pName);
                    this.playing.setButtonsEnabled(TRADE_OR_FINISHED);
                }
            }
            else {
                text = String.format ("No one owns %s. %s does not have enough money to buy it.", sName, pName);
                this.playing.setButtonsEnabled (JUST_FINISHED);
            }
            this.playing.setMessage (text);
            this.playing.setMessageVisible (true);
        }
        else if (space instanceof Utility || space instanceof Railroad) {
            OwnableSpace oSpace = (OwnableSpace) space;
            Player owner = oSpace.getOwner(players);
            String type = (oSpace instanceof Railroad) ? "railroad" : "utility";
            if (owner == null && player.getCash() >= oSpace.getPrice ()) {
                text = String.format ("No one owns %s. %s buys the %s for %.1f", sName, pName, type, oSpace.getPrice ());
                this.playing.setButtonsEnabled (JUST_PURCHASE);
                oSpace.buySpace(players, spaces, player, bank);
            }
            else if (owner == player) {
                text = String.format ("%s lands on their own %s with no effect.", pName, type);
                this.playing.setButtonsEnabled (JUST_FINISHED);
            }
            else if (owner != null) {
                if (player.getCash () >= oSpace.getRent (players, spaces, player))
                {
                    text = String.format("%s lands on %s's %s (%s) and can either trade or pay them %.1f as rent.", pName, owner.getName(), type, pName,
                            oSpace.getRent(players, spaces, player));
                    this.playing.setButtonsEnabled (PAY_OR_TRADE);
                }
                else {
                    text = String.format ("%s lands on %s's %s (%s) and can either trade or declare bankruptcy.", pName, owner.getName (), type, pName);
                    this.playing.setButtonsEnabled(TRADE_OR_FINISHED);
                }
            }
            else {
                text = String.format ("No one owns %s. %s does not have enough money to buy it.", sName, pName);
                this.playing.setButtonsEnabled (JUST_FINISHED);
            }
            this.playing.setMessage (text);
            this.playing.setMessageVisible (true);
        }
        else if (space instanceof Corner) {
            switch (sName) {
                case "START":
                    text = String.format("%s lands on %s and gains %.1f.", pName, sName, (double) 200);
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    Transactions.cashToBank(player, bank, -200);
                    this.updateGUI();
                    break;
                case "Community Service":
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    text = String.format("%s lands on %s and has to pay the bank %.1f for community service. ", pName, sName, (double) 50);
                    if (player.getCash() > 50) {
                        Transactions.cashToBank(player, bank, 50);
                    }
                    else {
                        text += String.format ("%s does not have enough money to pay the community service. Bankrupt!", pName);
                        playerBankrupt = true;
                    }
                    this.updateGUI ();
                    break;
                case "Free Parking":
                    text = String.format ("%s lands on %s and has free parking for a turn.", pName, sName);
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    this.updateGUI ();
                    break;
                case "JAIL":
                    text = String.format ("%s lands on %s and is arrested.", pName, sName);
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    player.arrest ();
                    this.updateGUI ();
                    break;
                default:
                    text = "Error";
            }
            this.playing.setMessage (text);
            this.playing.setMessageVisible (true);
        }
        else if (space instanceof Chance) {
            // TODO: text
            // TODO: draw card
            // TODO: do card effect
            this.updateGUI ();
        }
        else if (space instanceof Tax) {
            switch (sName) {
                case "Luxury Tax":
                    text = String.format ("%s lands on %s and has to pay the bank %.1f. ", pName, sName, (double) 75);
                    if (player.getCash() < 75) {
                        text += String.format ("%s does not have enough money to pay the tax. Bankrupt!", pName);
                        playerBankrupt = true;
                    }
                    else {
                        Transactions.cashToBank(player, bank,75);
                    }
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    this.updateGUI ();
                    break;
                case "Income Tax":
                    double cash;
                    text = String.format ("%s lands on %s and has to pay the bank %.1f or 10 percent of their money, whichever is higher. ", pName, sName, (double) 200);
                    cash = (player.getCash () * 0.1 > 200) ? player.getCash () * 0.1 : 200;

                    if (player.getCash () < cash) {
                        Transactions.cashToBank(player, bank, cash);
                    }
                    else {
                        text += String.format ("%s does not have enough money to pay the tax. Bankrupt!", pName);
                        playerBankrupt = true;
                    }
                    this.playing.setButtonsEnabled (JUST_FINISHED);
                    this.updateGUI ();
                    break;
                default:
                    text = "Error";
            }
            this.playing.setMessage (text);
            this.playing.setMessageVisible (true);
        }

        return playerBankrupt;
    }

    public boolean moveAndLand (ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank,
                                      boolean direct) {
        boolean playerBankrupt;
        int playerPos = player.getPosition();
        int diceRoll = player.getDiceRoll();

        if (playerPos + diceRoll < playerPos && !direct) {
            this.playing.emitMessage (5, "Passed START! 200 awarded!");
            Transactions.cashToBank(player, bank, -200);
            this.updateGUI ();
        }

        playerPos = playerPos + diceRoll;
        player.changePosition (playerPos);
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
            this.playing.emitMessage (5, "Passed START! 200 awarded!");
            Transactions.cashToBank(player, bank, -200);
            this.updateGUI ();
        }

        player.changePosition(destinationLoc);
        this.updateGUI ();

        playerBankrupt = this.doLandEffect(players, spaces, space, player, bank);

        return playerBankrupt;
    }

    public void updateGUI () {
        for (int i = 0; i < players.size (); i++) {
            Player player = players.get (i);
            ArrayList <Ownable> owned = player.getOwned ();
            StringBuilder ownedText = new StringBuilder ();
            for (Ownable ownable : owned) {
                ownedText.append (String.format ("\n%s", ownable));
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
            playing.stopShowingTimers ();
            playing.setMessageVisible (false);
            playing.setIconVisible (false);

            Player player = players.get (currentPlayer);
            Space landedSpace = spaces.get (player.getPosition());
            Random rand = new Random ();
            switch (index) {
                case 0: // Roll Dice
                    for (Player p : players) {
                        p.setDiceRoll (0);
                    }
                    player.setDiceRoll (rand.nextInt (6) + 1);
                    doNextPartTurn();
                    break;
                case 1: // Purchased
                    OwnableSpace oSpace = (OwnableSpace) landedSpace;
                    Transactions.cashToBank (player, bank, oSpace.getPrice ());
                    updateGUI();
                    playing.setButtonsEnabled (JUST_FINISHED);
                    break;
                case 2: // Develop
                    Property property = (Property) landedSpace;
                    Transactions.cashToBank (player, bank, property.getPrice ());
                    updateGUI();
                    playing.setButtonsEnabled (JUST_FINISHED);
                    break;
                case 3: // Pay Rent
                    oSpace = (OwnableSpace) landedSpace;
                    Player other = oSpace.getOwner (players);
                    Transactions.cashToOtherPlayer (player, other, oSpace.getRent (players, spaces, player));
                    updateGUI();
                    playing.setButtonsEnabled (JUST_FINISHED);
                    break;
                case 4: // Trade
                    // TODO: DO THE FUCKING TRADE
                    break;
                case 5: // Finished
                    currentPlayer = (++currentPlayer % players.size ());
                    doTurn ();
                    updateGUI();
                    break;
            }
            waitingForButton = false;
        }
    }
}

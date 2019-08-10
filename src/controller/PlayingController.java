package controller;

import controller.staticcontroller.Transactions;
import gui.ButtonListener;
import gui.PlayingFrame;
import model.*;
import model.cards.*;
import model.spaces.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PlayingController {

    int currentPlayer = 0;

    private ArrayList<Player> players;
    private ArrayList<Space> spaces;
    private CardSet cards;
    private Card drawnCard;
    private Bank bank;


    boolean playerBankrupt;
    boolean trading;

    private boolean turnOver = false;
    private boolean gameOver = false;

    public static final boolean[] JUST_ROLL_DICE = new boolean[]{true, false, false, false, false, false};
    public static final boolean[] JUST_PURCHASE = new boolean[]{false, true, false, false, false, false};
    public static final boolean[] JUST_DEVELOP = new boolean[]{false, false, true, false, false, false};
    public static final boolean[] JUST_PAY = new boolean[]{false, false, false, true, false, false};
    public static final boolean[] JUST_FINISHED = new boolean[]{false, false, false, false, false, true};
    public static final boolean[] PAY_OR_TRADE = new boolean[]{false, false, false, true, true, false};
    public static final boolean[] TRADE_OR_FINISHED = new boolean[]{false, false, false, false, true, true};

    private PlayingFrame playing;

    public PlayingController(ArrayList<Player> players, ArrayList<Space> spaces, Bank bank) {
        this.players = players;
        this.spaces = spaces;
        this.cards = new CardSet(spaces);
        this.bank = bank;

        ArrayList<ImageIcon> spaceImages = new ArrayList<>();
        for (Space space : spaces) {
            spaceImages.add(space.getSpaceIcon());
        }

        ArrayList<String> names = new ArrayList<>();
        for (Player player : players) {
            names.add(player.getName());
        }

        this.playing = new PlayingFrame("My Empire", names, bank.getCash(),
                players.get(0).getCash(), spaceImages, new ButtonPressed());
    }
    private class SafePause implements Runnable {
        int seconds;
        public SafePause (int seconds) {
            this.seconds = seconds;
        }
        @Override
        public void run() {
            try {
                Thread.sleep (1000 * seconds);
            }
            catch (InterruptedException e) {}
        }

    }
    public void doTurn() {
        Random rand = new Random();
        Player player = this.players.get(currentPlayer);
        this.turnOver = false;
        playing.setMessage(String.format("It is %s's turn! ", player.getName()));
        if (player.isInJail()) {
            playing.appendMessage(String.format("%s pays 50 to the bank as payment for JAIL. ", player.getName()));
            player.bail();
            this.gameOver = Transactions.cashToBank(player, bank, -50);
            this.updateGUI();
        }
        if (gameOver) {
            return;
        }
        this.playing.setButtonsEnabled(JUST_ROLL_DICE);
        return;
    }

    public void doNextPartTurn() {
        Player player = this.players.get(currentPlayer);
        this.gameOver = this.moveAndLand(players, spaces, player, bank, false);
        this.turnOver = true;
        if (!gameOver) {
            return;
        }

        ArrayList <String> colors = new ArrayList <> (Arrays.asList ("Gray", "Purple", "Pink", "Green", "Blue", "Red", "Yellow"));
        ArrayList <String> two = new ArrayList <> (Arrays.asList ("Gray", "Red", "Yellow"));
        ArrayList <Property> pList = new ArrayList <>();
        for (Player p : players) {
            for (Ownable o : p.getOwned ()) {
                if (o instanceof Property) {
                    pList.add((Property) o);
                }
            }

            ArrayList <String> colorSets = new ArrayList <> ();
            for (Property pr : pList) {
                int count = 3;
                if (two.contains(pr.getColor())) {
                    count--;
                }
            }
        }
    }

    public boolean doCardEffect(Card card, Player player, ArrayList<Space> spaces, Bank bank) {
        boolean playerBankrupt = false;
        String text = card.getText();
        if (card instanceof CardMoneyOnly) {
            System.out.println ("cmo");
            playerBankrupt = ((CardMoneyOnly) card).doCardEffect(player, bank);
            this.playing.setButtonsEnabled(JUST_FINISHED);
        } else if (card instanceof CardMovePlayer) {
            System.out.println ("cmp");
            boolean direct;
            playerBankrupt = ((CardMovePlayer) card).doCardEffect(player, spaces, bank);
            if (card instanceof CardARandomProperty || card instanceof CardCJail)
                direct = true;
            else
                direct = false;
            this.moveAndLand (players, spaces, player, bank, direct);
        } else if (card instanceof CardApplyOwnableSpace) {
            OwnableSpace owned = null;
            boolean noButtons[] = new boolean[]{false, false, false, false, false, false};
            System.out.println ("aos");
            ArrayList <Ownable> ownedList = player.getOwned ();
            int numOwned = 0;
            for (Ownable ownable : ownedList) {
                if (card instanceof CardDChangeNonProperty) {
                    if (ownable instanceof OwnableSpace && !(ownable instanceof Property)) {
                        playing.setBtnSpaceEnabled (((OwnableSpace) ownable).getLocation());
                        numOwned++;
                    }
                }
                else if (ownable instanceof Property) {
                    playing.setBtnSpaceEnabled (((Property) ownable).getLocation ());
                    numOwned++;
                }
            }
            if (numOwned == 0) {
                card.discard();
                playing.setButtonsEnabled(JUST_FINISHED);
            }
            else {
                playing.setButtonsEnabled(noButtons);
            }
        }
        this.drawnCard = card;
        return playerBankrupt;
    }

    private boolean doLandEffect(ArrayList<Player> players, ArrayList<Space> spaces, Space space, Player player,
                                 Bank bank) {
        boolean playerBankrupt = false;
        String text;
        String pName = player.getName();
        String sName = space.getName();
        this.playing.setIcon(space.getDisplayIcon());
        this.playing.setIconVisible(true);
        System.out.println ("Player landed on:" + space);
        Runnable x = new SafePause (5);
        Thread stop = new Thread (x);
        stop.start ();
        if (space instanceof Property) {
            Property property = (Property) space;
            Player owner = property.getOwner(players);
            if (owner == null && player.getCash() >= property.getPrice()) {
                text = String.format("No one owns %s. %s buys the property for %.1f", sName, pName, property.getPrice());
                this.playing.setButtonsEnabled(JUST_PURCHASE);
            } else if (owner == player && property.isAbleToDevelop(players)) {
                text = String.format("%s lands on their own property (%s) is able to develop the property for %.1f", pName, sName, property.getPrice());
                this.playing.setButtonsEnabled(JUST_DEVELOP);
            } else if (owner == player) {
                text = String.format("%s lands on their own property (%s), but is not able to develop their property.", pName, property.getName());
                this.playing.setButtonsEnabled(JUST_FINISHED);
            } else if (owner != null) {
                int numOwned = 0;
                ArrayList <Ownable> ownedList = player.getOwned ();
                for (Ownable ownable : ownedList) {
                    if (ownable instanceof OwnableSpace) {
                        playing.setBtnSpaceEnabled(((OwnableSpace) ownable).getLocation());
                        numOwned++;
                    }
                }
                if (player.getCash() >= property.getRent(players, spaces, player)) {
                    text = String.format("%s lands on %s's property (%s) and can either trade if they own anything or pay them %.1f as rent.", pName, owner.getName(), pName,
                            property.getRent(players, spaces, player));
                    if (numOwned > 0)
                        this.playing.setButtonsEnabled(PAY_OR_TRADE);
                    else
                        this.playing.setButtonsEnabled(JUST_PAY);
                } else {
                    text = String.format("%s lands on %s's property (%s) and can either trade if they own anything or declare bankruptcy.", pName, owner.getName(), pName);
                    if (numOwned > 0)
                        this.playing.setButtonsEnabled(TRADE_OR_FINISHED);
                    else
                        this.playing.setButtonsEnabled(JUST_FINISHED);
                }
            } else {
                text = String.format("No one owns %s. %s does not have enough money to buy it.", sName, pName);
                this.playing.setButtonsEnabled(JUST_FINISHED);
            }
            this.playing.appendMessage(text + " ");
            this.playing.setMessageVisible(true);

        } else if (space instanceof Utility || space instanceof Railroad) {
            OwnableSpace oSpace = (OwnableSpace) space;
            Player owner = oSpace.getOwner(players);
            String type = (oSpace instanceof Railroad) ? "railroad" : "utility";
            if (owner == null && player.getCash() >= oSpace.getPrice()) {
                text = String.format("No one owns %s. %s buys the %s for %.1f", sName, pName, type, oSpace.getPrice());
                this.playing.setButtonsEnabled(JUST_PURCHASE);
                oSpace.buySpace(players, spaces, player, bank);
            } else if (owner == player) {
                text = String.format("%s lands on their own %s with no effect.", pName, type);
                this.playing.setButtonsEnabled(JUST_FINISHED);
            } else if (owner != null) {
                if (player.getCash() >= oSpace.getRent(players, spaces, player)) {
                    text = String.format("%s lands on %s's %s (%s) and can either trade or pay them %.1f as rent.", pName, owner.getName(), type, pName,
                            oSpace.getRent(players, spaces, player));
                    this.playing.setButtonsEnabled(PAY_OR_TRADE);
                } else {
                    text = String.format("%s lands on %s's %s (%s) and can either trade or declare bankruptcy.", pName, owner.getName(), type, pName);
                    this.playing.setButtonsEnabled(TRADE_OR_FINISHED);
                }
            } else {
                text = String.format("No one owns %s. %s does not have enough money to buy it.", sName, pName);
                this.playing.setButtonsEnabled(JUST_FINISHED);
            }
            this.playing.appendMessage(text + " ");
            this.playing.setMessageVisible(true);
        } else if (space instanceof Corner) {
            switch (sName) {
                case "START":
                    text = String.format("%s lands on %s and gains %.1f.", pName, sName, (double) 200);
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    Transactions.cashToBank(player, bank, -200);
                    break;
                case "Community Service":
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    text = String.format("%s lands on %s and has to pay the bank %.1f for community service. ", pName, sName, (double) 50);
                    if (player.getCash() > 50) {
                        Transactions.cashToBank(player, bank, 50);
                    } else {
                        text += String.format("%s does not have enough money to pay the community service. Bankrupt!", pName);
                        playerBankrupt = true;
                    }
                    break;
                case "Free Parking":
                    text = String.format("%s lands on %s and has free parking for a turn.", pName, sName);
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    break;
                case "JAIL":
                    text = String.format("%s lands on %s and is arrested.", pName, sName);
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    player.arrest();
                    break;
                default:
                    text = "Error";
            }
            this.playing.appendMessage(text + " ");
            this.playing.setMessageVisible(true);
        } else if (space instanceof Chance) {
            text = String.format ("%s landed on Chance! ", pName);
            Card drawn = cards.pickRandom ();
            text += String.format ("%s drew %s! - \"%s\" ", pName, drawn.getName (), drawn.getText());
            this.playing.appendMessage(text);
            this.playing.setMessageVisible (true);
            this.doCardEffect (drawn, player, spaces, bank);
        } else if (space instanceof Tax) {
            switch (sName) {
                case "Luxury Tax":
                    text = String.format("%s lands on %s and has to pay the bank %.1f. ", pName, sName, (double) 75);
                    if (player.getCash() < 75) {
                        text += String.format("%s does not have enough money to pay the tax. Bankrupt!", pName);
                        playerBankrupt = true;
                    } else {
                        Transactions.cashToBank(player, bank, 75);
                    }
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    break;
                case "Income Tax":
                    double cash;
                    text = String.format("%s lands on %s and has to pay the bank %.1f or 10 percent of their money, whichever is higher. ", pName, sName, (double) 200);
                    cash = (player.getCash() * 0.1 > 200) ? player.getCash() * 0.1 : 200;

                    if (player.getCash() >= cash) {
                        Transactions.cashToBank(player, bank, cash);
                    } else {
                        text += String.format("%s does not have enough money to pay the tax. Bankrupt!", pName);
                        playerBankrupt = true;
                    }
                    this.playing.setButtonsEnabled(JUST_FINISHED);
                    break;
                default:
                    text = "Error";
            }
            this.playing.appendMessage(text + " ");
            this.playing.setMessageVisible(true);
        }

        return playerBankrupt;
    }

    public boolean moveAndLand(ArrayList<Player> players, ArrayList<Space> spaces, Player player, Bank bank,
                               boolean direct) {
        boolean playerBankrupt;
        int playerPos = player.getPosition();
        int diceRoll = player.getDiceRoll();

        if ((playerPos + diceRoll) % 32  < playerPos && !direct) {
            this.playing.appendMessage("Passed START! 200 awarded! ");
            Transactions.cashToBank(player, bank, -200);
            this.updateGUI();
        }

        playerPos = (playerPos + diceRoll) % 32;
        player.changePosition(playerPos);
        this.updateGUI();

        playerBankrupt = this.doLandEffect(players, spaces, spaces.get(playerPos), player, bank);

        return playerBankrupt;
    }


    public void updateGUI() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            ArrayList<Ownable> owned = player.getOwned();
            String ownedText = "";
            for (Ownable ownable : owned) {
                ownedText += String.format("\n%s ", ownable);
            }
            playing.setPlayerCash(i, player.getCash());
            playing.setPlayerText(i, ownedText);
            playing.refresh();
            int movement =  player.getPosition() - playing.getPlayerLocation(i);
            if (movement < 0)
                movement += 32;
            if (movement == 32)
                movement = 0;
            playing.movePlayer (i, movement);
        }

        playing.setBankCash(bank.getCash());
    }

    private class ButtonPressed implements ButtonListener {
        @Override
        public void buttonPressed(int index) {
            playing.setMessageVisible(false);
            playing.setIconVisible(false);

            Player player = players.get(currentPlayer);
            Space landedSpace = spaces.get(player.getPosition());
            Random rand = new Random();
            if (index < 100) {
                switch (index) {
                    case 0: // Roll Dice
                        for (Player p : players) {
                            p.setDiceRoll(0);
                        }
                        player.setDiceRoll(rand.nextInt(6) + 1);
                        System.out.println (player.getName () + " rolled a " + player.getDiceRoll());
                        playing.appendMessage(String.format("%s rolled a %d. ", player.getName(), player.getDiceRoll()));
                        doNextPartTurn();
                        break;
                    case 1: // Purchased
                        OwnableSpace oSpace = (OwnableSpace) landedSpace;
                        oSpace.buySpace(players, spaces, player, bank);
                        updateGUI();
                        playing.setButtonsEnabled(JUST_FINISHED);
                        break;
                    case 2: // Develop
                        Property property = (Property) landedSpace;
                        gameOver = Transactions.cashToBank(player, bank, property.getPrice());
                        updateGUI();
                        playing.setButtonsEnabled(JUST_FINISHED);
                        break;
                    case 3: // Pay Rent
                        oSpace = (OwnableSpace) landedSpace;
                        Player other = oSpace.getOwner(players);
                        gameOver = Transactions.cashToOtherPlayer(player, other, oSpace.getRent(players, spaces, player));
                        updateGUI();
                        playing.setButtonsEnabled(JUST_FINISHED);
                        break;
                    case 4: // Trade
                        trading = true;
                        int numOwned = 0;
                        ArrayList <Ownable> ownedList = player.getOwned ();
                        for (Ownable ownable : ownedList) {
                            if (ownable instanceof OwnableSpace) {
                                playing.setBtnSpaceEnabled(((OwnableSpace) ownable).getLocation());
                                numOwned++;
                                }
                        }
                        boolean[] nothing = new boolean[]{false, false, false, false, false, false};
                        playing.setButtonsEnabled (nothing);
                        playing.setMessage("Click one of your owned spaces to select it to trade");
                        playing.setMessageVisible(true);
                        updateGUI ();
                        break;
                    case 5: // Finished
                        currentPlayer = (++currentPlayer % players.size());
                        doTurn();
                        updateGUI();
                        break;
                }
            }
            else {
                OwnableSpace owned = (OwnableSpace) spaces.get (index - 100);
                if (!trading) {
                    playerBankrupt = ((CardApplyOwnableSpace) drawnCard).doCardEffect(player, spaces, owned, bank);
                }
                else {
                    OwnableSpace spaceTraded = (OwnableSpace) spaces.get(index);
                    OwnableSpace spaceLanded = (OwnableSpace) spaces.get(player.getPosition());
                    Player owner = spaceLanded.getOwner (players);
                    owner.getOwned().remove(spaceLanded);
                    player.getOwned().remove(spaceTraded);
                    owner.getOwned().add(spaceTraded);
                    owner.getOwned().add(spaceLanded);
                    trading = false;
                }
                updateGUI();
            }
        }
    }
}

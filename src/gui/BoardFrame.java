package gui;;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardFrame extends JFrame implements ActionListener {

    Timer timer;

    JLayeredPane pane;

    ArrayList <JButton> spaces;
    ArrayList <JLabel> playerIcons;

    JPanel messagePanel;
    JLabel messageText;

    JLabel landedOn;

    JLayeredPane info;

    JButton rollDice;
    JButton purchase;
    JButton develop;
    JButton payRent;
    JButton trade;
    JButton finished;

    JLabel bankAmount;
    ArrayList <JLabel> playerAmounts;

    int currentPlayer = 0;
    int[] playerIndices;

    int movement;

    int[][] xySpaceLocations;

    public BoardFrame (ArrayList <String> playerNames, double bankAmount, ArrayList <ImageIcon> spaces) {
        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (1080, 750);

        this.xySpaceLocations = new int[32][2];
        for (int i = 0; i < 32; i++) {
            int x = 0, y = 0;
            if (i < 9) {
                x = 80 * i;
                y = 0;
            }
            else if (9 <= i && i < 17) {
                x = 640;
                y = 80 * (i - 8);
            }
            else if (17 <= i && i < 25) {
                x = 80 * (24 - i);
                y = 640;
            }
            else if (25 <= i && i < 32) {
                x = 0;
                y = 80 * (32 - i);
            }
            this.xySpaceLocations[i][0] = x;
            this.xySpaceLocations[i][1] = y;
        }

        this.playerIndices = new int[4];

        this.initComponents (playerNames, bankAmount, spaces);

        this.setVisible (true);

        timer = new Timer (100, this);

        this.moveCurrentPlayer (10);

    }

    public void initComponents (ArrayList <String> playerNames, double bankAmount, ArrayList <ImageIcon> spaces) {
        ImageIcon icon;

        this.pane = new JLayeredPane ();
        this.pane.setBounds (0, 0, 720, 720);

        icon = new ImageIcon("src/resources/images/CenterBoard.png");
        JLabel bg = new JLabel (icon);
        bg.setBounds (0, 0, 720, 720);
        this.pane.add (bg, new Integer (1), 1);

        this.spaces = new ArrayList <> ();
        for (int i = 0; i < 32; i++) {
            JButton temp;
            icon = spaces.get (i);
            temp = new JButton (icon);

            temp.setDisabledIcon (icon);
            temp.setEnabled (false);

            int x = this.xySpaceLocations[i][0];
            int y = this.xySpaceLocations[i][1];
            temp.setBounds (x, y, 80, 80);
            this.spaces.add (temp);
        }
        for (JButton btn : this.spaces) {
            this.pane.add (btn, new Integer (2), 1);
        };

        this.playerIcons = new ArrayList <> ();
        for (int i = 0; i < playerNames.size (); i++) {
            icon = new ImageIcon ("src/resources/images/players/Player" + (i + 1) + ".png");
            JLabel temp = new JLabel (icon);
            int x = this.xySpaceLocations[0][0];
            int y = this.xySpaceLocations[0][0];
            this.currentPlayer = i;
            x += this.getXYMod ()[0];
            y += this.getXYMod ()[1];
            temp.setBounds (x, y, 40, 40);
            this.playerIcons.add (temp);
            this.pane.add (temp, new Integer (3), 1);
        }
        this.currentPlayer = 0;

        this.info = new JLayeredPane ();
        this.info.setBounds (720, 0, 360, 720);

        icon = new ImageIcon("src/resources/images/playingMenu/InfoBoard.png");
        bg = new JLabel (icon);
        bg.setBounds (0, 0, 360, 720);
        this.info.add (bg, new Integer (1), 1);

        JLabel bankLabel = new JLabel (/*Icon*/"Bank");
        bankLabel.setBounds (10, 50, 90, 30);
        this.bankAmount = new JLabel ();
        this.bankAmount.setBounds (110, 50, 90, 30);

        for (int i = 0; i < playerNames.size (); i++) {
            String name = playerNames.get (i);
            icon = new ImageIcon("src/resources/images/playingMenu/Player" + (i + 1) + ".png");
            JLabel playerLabel = new JLabel (icon);
            JLabel nameLabel = new JLabel (name);
            playerLabel.setBounds (20, 120 * i + 160, 80, 20);
            nameLabel.setBounds (120, 120 * i + 160, 80, 30);
            nameLabel.setForeground (Color.WHITE);
            this.info.add (playerLabel, new Integer (2), 1);
            this.info.add (nameLabel, new Integer (2), 1);
        }

        icon = new ImageIcon("src/resources/images/playingMenu/RollDice.png");
        this.rollDice = new JButton (icon);
        this.rollDice.setBounds (10, 620, 165, 30);
        this.rollDice.addActionListener (this);
        this.info.add (rollDice, new Integer (2), 1);

        icon = new ImageIcon("src/resources/images/playingMenu/Purchase.png");
        this.purchase = new JButton (icon);
        this.purchase.setBounds (175, 620, 165,30);
        this.purchase.setEnabled (false);
        this.info.add (purchase, new Integer (2), 1);

        icon = new ImageIcon("src/resources/images/playingMenu/Develop.png");
        this.develop = new JButton (icon);
        this.develop.setBounds (10, 651, 165, 30);
        this.develop.setEnabled (false);
        this.info.add (develop, new Integer (2), 1);

        icon = new ImageIcon("src/resources/images/playingMenu/PayRent.png");
        this.payRent = new JButton (icon);
        this.payRent.setBounds (175, 651, 165, 30);
        this.payRent.setEnabled (false);
        this.info.add (payRent, 2, 1);

        icon = new ImageIcon("src/resources/images/playingMenu/Trade.png");
        this.trade = new JButton (icon);
        this.trade.setBounds (10, 682, 165, 30);
        this.trade.setEnabled (false);
        this.info.add (trade, 2, 1);

        icon = new ImageIcon("src/resources/images/playingMenu/Finished.png");
        this.finished = new JButton (icon);
        this.finished.setBounds (175, 682, 165, 30);
        this.finished.setEnabled (false);
        this.info.add (finished, 2, 1);

        this.add (pane);
        this.add (info);


    }


    private int[] getXYMod () {
        int[] xyMod = new int[2];
        switch (currentPlayer) {
            case 1:
                xyMod[0] += 40;
                break;
            case 2:
                xyMod[1] += 40;
                break;
            case 3:
                xyMod[0] += 40;
                xyMod[1] += 40;
        }
        return xyMod;
    }
    public void moveCurrentPlayer (int movement) {
        this.movement = movement;
        this.timer.start ();
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource () instanceof Timer)
        {
            if (this.movement > 0) {
                int index = (++this.playerIndices[currentPlayer]) % 32;
                int x = this.xySpaceLocations[index][0];
                int y = this.xySpaceLocations[index][1];
                x += getXYMod ()[0];
                y += getXYMod ()[1];
                this.playerIcons.get (currentPlayer).setBounds (x, y, 40, 40);

                this.movement--;
                System.out.println ("yeet " + movement);
                this.repaint ();
                this.revalidate ();
            }
            else
                ((Timer) e.getSource()).stop ();
        }
        else if (e.getSource () instanceof JButton) {
            JButton pressed = (JButton) e.getSource ();
            if (!this.timer.isRunning () && pressed == this.rollDice) {
                System.out.println ("yeet");
                this.currentPlayer = 1;
                this.moveCurrentPlayer (10);
            }
            if (pressed == this.rollDice) {
                System.out.println ("yeet");
            }
        }
    }
}

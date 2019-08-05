package gui;;

import javax.swing.*;
import java.util.ArrayList;

public class BoardFrame extends JFrame {

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

        this.initComponents (playerNames, bankAmount, spaces);

        this.setVisible (true);
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
            switch (i) {
                case 1:
                    x += 40;
                    break;
                case 2:
                    y += 40;
                    break;
                case 3:
                    x += 40;
                    y += 40;
            }
            temp.setBounds (x, y, 40, 40);
            this.playerIcons.add (temp);
            this.pane.add (temp, new Integer (3), 1);
        }

        this.info = new JLayeredPane ();
        this.info.setBounds (720, 0, 360, 720);

        icon = new ImageIcon("src/resources/images/InfoBoard.png");
        bg = new JLabel (icon);
        bg.setBounds (0, 0, 360, 720);
        this.info.add (bg, new Integer (1), 1);

        JLabel bankLabel = new JLabel (/*Icon*/"Bank");
        bankLabel.setBounds (10, 50, 90, 30);
        this.bankAmount = new JLabel ();
        this.bankAmount.setBounds (110, 50, 90, 30);

        for (int i = 0; i < playerNames.size (); i++) {
            String name = playerNames.get (i);
            JLabel playerLabel = new JLabel (/*Icon*/ "Player" + (i + 1));
            JLabel nameLabel = new JLabel (name);
            playerLabel.setBounds (10, (120 * (i + 1)) + 30, 80, 30);
            nameLabel.setBounds (100, (120 * (i + 1)) + 30, 80, 30);
            this.info.add (playerLabel, new Integer (2), 1);
            this.info.add (nameLabel, new Integer (2), 1);
        }

        this.rollDice = new JButton ("Roll Dice");
        this.rollDice.setBounds (0, 0, 160, 30);
        this.purchase = new JButton ("Purchase");
        this.purchase.setBounds (0, 0, 160,30);
        this.develop = new JButton ("Develop");
        this.develop.setBounds (0, 0, 160, 30);
        this.payRent = new JButton ("Pay Rent");
        this.payRent.setBounds (0, 0, 160, 30);
        this.trade = new JButton ("Trade");
        this.trade.setBounds (0, 0, 160, 30);
        this.finished = new JButton ("Finished");
        this.finished.setBounds (0, 0, 160, 30);

        this.add (pane);
        this.add (info);


    }
}

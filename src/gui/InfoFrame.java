package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InfoFrame extends JFrame implements ActionListener {

    JLayeredPane pane;

    JButton rollDice;
    JButton purchase;
    JButton develop;
    JButton payRent;
    JButton trade;
    JButton finished;

    JLabel bankAmount;
    ArrayList <JLabel> playerAmounts;

    public InfoFrame (ArrayList <String> playerNames, double bankAmount) {
        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (360, 750);

        this.initComponents (playerNames, bankAmount);

        this.setVisible (true);
    }

    private void initComponents (ArrayList <String> playerNames, double bankAmount) {
//        ImageIcon icon;
//
//        this.pane = new JLayeredPane ();
//        this.pane.setBounds (0, 0, 360, 720);
//
//        icon = new ImageIcon("src/resources/images/InfoBoard.png");
//        JLabel bg = new JLabel (icon);
//        bg.setBounds (0, 0, 360, 720);
//        this.pane.add (bg, new Integer (1), 1);
//
//        JLabel bankLabel = new JLabel (/*Icon*/"Bank");
//        bankLabel.setBounds (10, 50, 90, 30);
//        this.bankAmount = new JLabel ();
//        this.bankAmount.setBounds (110, 50, 90, 30);
//
//        for (int i = 0; i < playerNames.size (); i++) {
//            String name = playerNames.get (i);
//            JLabel playerLabel = new JLabel (/*Icon*/ "Player" + (i + 1));
//            JLabel nameLabel = new JLabel (name);
//            playerLabel.setBounds (10, (120 * (i + 1)) + 30, 80, 30);
//            nameLabel.setBounds (100, (120 * (i + 1)) + 30, 80, 30);
//            this.add (playerLabel);
//            this.add (nameLabel);
//        }
//
//        this.rollDice = new JButton ("Roll Dice");
//        this.rollDice.setBounds (0, 0, 160, 30);
//        this.purchase = new JButton ("Purchase");
//        this.purchase.setBounds (0, 0, 160,30);
//        this.develop = new JButton ("Develop");
//        this.develop.setBounds (0, 0, 160, 30);
//        this.payRent = new JButton ("Pay Rent");
//        this.payRent.setBounds (0, 0, 160, 30);
//        this.trade = new JButton ("Trade");
//        this.trade.setBounds (0, 0, 160, 30);
//        this.finished = new JButton ("Finished");
//        this.finished.setBounds (0, 0, 160, 30);
//
//        this.add (pane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource ();

    }
}

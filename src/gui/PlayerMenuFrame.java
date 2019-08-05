package gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerMenuFrame extends JFrame implements ActionListener {

    private JLayeredPane pane;

    private JButton addPlayer;
    private JButton removePlayer;
    private JButton finished;

    private int numVisible;

    private ArrayList <JLabel> playerLabels;
    private ArrayList <JTextField> playerNameInputs;

    private PlayerListener playerListener;

    public PlayerMenuFrame (PlayerListener playerListener) {
        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (1080, 720);
        this.playerListener = playerListener;

        this.initComponents ();

        this.setVisible (true);
    }

    private void initComponents () {
        this.pane = new JLayeredPane ();

//        JLabel bg = new JLabel (/*Icon*/);
//        this.pane.add (bg, new Integer (1), 1);

        this.addPlayer = new JButton ("Add");
        this.addPlayer.addActionListener (this);
        this.addPlayer.setBounds (460, 50, 80, 80);
        this.add (addPlayer);

        this.removePlayer = new JButton ("Remove");
        this.removePlayer.addActionListener (this);
        this.removePlayer.setBounds (540, 50, 80, 80);
        this.add (removePlayer);

        this.finished = new JButton ("Finished");
        this.finished.addActionListener (this);
        this.finished.setBounds (50, 50, 100, 100);
        this.add (finished);

        this.playerLabels = new ArrayList <> ();
        for (int i = 0; i < 4; i++) {
            JLabel temp = new JLabel ("Player " + (i + 1));
            temp.setBounds (500, 170 + (i * 40), 80, 30);
            this.playerLabels.add (temp);
            this.add (temp);
        }

        this.playerNameInputs = new ArrayList <> ();
        for (int i = 0; i < 4; i++) {
            JTextField temp = new JTextField ("Player " + (i + 1), 10);
            temp.setBounds (600, 170 + (i * 40), 80, 30);
            this.playerNameInputs.add (temp);
            this.add (temp);
        }

        this.numVisible = 2;
        for (int i = 0; i < 4; i++) {
            if (i > 1) {
                this.playerLabels.get(i).setVisible (false);
                this.playerNameInputs.get(i).setVisible (false);
            }
        }
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        JButton clicked = (JButton) e.getSource ();
        if (clicked == this.addPlayer) {
            switch (this.numVisible) {
                case 2:
                case 3:
                    this.playerLabels.get (this.numVisible).setVisible (true);
                    this.playerNameInputs.get (this.numVisible).setVisible (true);
                    this.numVisible++;
                    break;
                case 4:
                    // warn (dialog)
            }
        }
        else if (clicked == this.removePlayer) {
            switch (this.numVisible) {
                case 2:
                    // warn (dialog)
                    break;
                case 3:
                case 4:
                    this.numVisible--;
                    this.playerLabels.get (this.numVisible).setVisible (false);
                    this.playerNameInputs.get (this.numVisible).setVisible (false);
            }
        }
        else if (clicked == this.finished) {
            if (this.playerListener != null) {
                ArrayList <String> names = new ArrayList <> ();
                for (int i = 0; i < this.numVisible; i++) {
                    String name = this.playerNameInputs.get (i).getText ();
                    if (name.length() > 10)
                        name = name.substring (0, 10);
                    names.add (name);
                }
                this.playerListener.playerNamesAdded (names);
                this.dispose ();
            }
        }

    }
}

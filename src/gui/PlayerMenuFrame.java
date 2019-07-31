package gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerMenuFrame extends JFrame implements ActionListener {

    private JButton addPlayer;
    private JButton removePlayer;
    private JButton finished;

    private PlayerListener playerListener;

    public PlayerMenuFrame (PlayerListener playerListener) {
        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (/* Size */720, 720);
        this.playerListener = playerListener;

        this.initComponents ();

        this.setVisible (true);
    }

    private void initComponents () {
        this.addPlayer = new JButton ();
        this.addPlayer.addActionListener (this);

        this.removePlayer = new JButton ();
        this.removePlayer.addActionListener (this);

        this.finished = new JButton ();
        this.finished.addActionListener (this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource ();
        if (clicked == this.addPlayer) {
            System.out.println ("Added player.");
        }
        else if (clicked == this.removePlayer) {
            System.out.println ("Removed player.");
        }
        else if (clicked == this.finished) {
            if (this.playerListener != null) {
                ArrayList <String> names = new ArrayList <> ();
                // get names and add them here
                this.playerListener.playerNamesAdded (names);
            }
        }

    }
}

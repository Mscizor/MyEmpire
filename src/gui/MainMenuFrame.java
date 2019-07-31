package gui;

import controller.PlayerMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame implements ActionListener {

    private JButton start;
    private JButton exit;
    private JSpinner spinnerTest;

    public MainMenuFrame () {
        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (/* Size */720, 720);

        this.initComponents ();

        this.setVisible (true);
    }

    private void initComponents () {
        this.start = new JButton ("Start");

        this.start.setPreferredSize (new Dimension (300, 300));
        this.start.setBounds (200, 200, 100, 100);
//        start.setBorderPainted (false);
        this.start.addActionListener (this);
        this.add (start);

        this.exit = new JButton ("Exit");

        this.exit.setPreferredSize (new Dimension (100, 100));
        this.exit.setBounds (200, 600, 100, 100);
//        exit.setBorderPainted (false);
        this.exit.addActionListener (this);

        this.add (exit);
}


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource ();

        if (clicked == this.start) {
            new PlayerMenuController();
            this.dispose ();
            System.out.println ("Start clicked.");
        }

        else if (clicked == this.exit) {
            this.dispose();
            System.out.println ("Exit was clicked.");
            System.exit(0);
        }
    }
}

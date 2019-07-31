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
        ImageIcon icon;

        icon = new ImageIcon ("./src/resources/squares/Chance.png");
        this.start = new JButton (icon);

        this.start.setPreferredSize (new Dimension (80, 80));
        this.start.setBounds (0, 0, 80, 80);
        this.start.addActionListener (this);
        this.add (start);

        icon = new ImageIcon ("./src/resources/squares/SqStart.png");
        this.exit = new JButton (icon);

        this.exit.setPreferredSize (new Dimension (80, 80));
        this.exit.setBounds (80, 0, 80, 80);
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
            System.out.println ("Start was clicked.");
        }

        else if (clicked == this.exit) {
            this.dispose();
            System.out.println ("Exit was clicked.");
            System.exit(0);
        }
    }
}

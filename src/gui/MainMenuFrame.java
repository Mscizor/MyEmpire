package gui;

import controller.PlayerMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame implements ActionListener {

    private JLayeredPane pane;

    private JButton start;
    private JButton exit;

    public MainMenuFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1080, 750);

        this.initComponents();

        this.setVisible(true);
    }
        private void initComponents () {
            ImageIcon icon;

            this.pane = new JLayeredPane ();
            this.pane.setBounds (0, 0, 1080, 720);

            icon = new ImageIcon("src/resources/images/StartScreen.png");
            JLabel bg = new JLabel (icon);
            bg.setBounds (0, 0, 1080, 720);
            this.pane.add (bg, new Integer (1), 0);

            icon = new ImageIcon("src/resources/images/squares/SqStart.png");
            this.start = new JButton(icon);

            this.start.setPreferredSize(new Dimension(120, 30));
            this.start.setBounds(480, 530, 120, 30);
            this.start.addActionListener(this);
            this.pane.add (start, new Integer (2), 0);

            icon = new ImageIcon("src/resources/images/squares/Chance.png");
            this.exit = new JButton(icon);

            this.exit.setPreferredSize(new Dimension(120, 30));
            this.exit.setBounds(480, 570, 120, 30);
//        exit.setBorderPainted (false);
            this.exit.addActionListener(this);
            this.pane.add (exit, new Integer (2), 0);

            this.add (pane);
        }


        @Override
        public void actionPerformed (ActionEvent e){
            JButton clicked = (JButton) e.getSource();

            if (clicked == this.start) {
                new PlayerMenuController();
                this.dispose();
                System.out.println("Start was clicked.");
            } else if (clicked == this.exit) {
                this.dispose();
                System.out.println("Exit was clicked.");
                System.exit(0);
            }
        }
    }

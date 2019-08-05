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
    private JLayeredPane pane;

    public MainMenuFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(/* Size */720, 720);

        this.initComponents();

        this.setVisible(true);
    }
        private void initComponents () {
            ImageIcon icon;

            icon = new ImageIcon("src/resources/images/squares/SqStart.png");
            this.start = new JButton(icon);

            this.start.setPreferredSize(new Dimension(80, 80));
            this.start.setBounds(0, 0, 80, 80);
            this.start.addActionListener(this);
            icon = new ImageIcon("src/resources/images/squares/Chance.png");
            this.exit = new JButton(icon);

            this.exit.setPreferredSize(new Dimension(80, 80));
            this.exit.setBounds(80, 0, 80, 80);
//        exit.setBorderPainted (false);
            this.exit.addActionListener(this);

            this.pane = new JLayeredPane();
            this.pane.setBounds(0, 0, 120, 120);

            this.exit.setBounds(40, 40, 80, 80);
            this.pane.add(start, new Integer(2), 0);
            this.pane.add(exit, new Integer(2), -1);
            this.add(pane);
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

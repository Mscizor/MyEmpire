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

    private ArrayList<JLabel> playerLabels;
    private ArrayList<JTextField> playerNameInputs;

    private PlayerListener playerListener;

    public PlayerMenuFrame(String title, PlayerListener playerListener) {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(560, 560);
        this.playerListener = playerListener;

        this.initComponents();

        this.setVisible(true);
    }

    private void initComponents() {
        ImageIcon icon;
        this.pane = new JLayeredPane();
        this.pane.setBounds(0, 0, 560, 560);

        icon = new ImageIcon("src/resources/images/EmptyBoard.png");
        JLabel bg = new JLabel(icon);
        bg.setBounds(0, 0, 560, 560);
        this.pane.add(bg, new Integer(1), 1);

        icon = new ImageIcon("src/resources/images/playerMenu/AddPlayer.png");
        this.addPlayer = new JButton(icon);
        this.addPlayer.addActionListener(this);
        this.addPlayer.setBounds(50, 150, 160, 30);
        this.pane.add(addPlayer, new Integer(2), 1);

        icon = new ImageIcon("src/resources/images/playerMenu/RemovePlayer.png");
        this.removePlayer = new JButton(icon);
        this.removePlayer.addActionListener(this);
        this.removePlayer.setBounds(80, 185, 160, 30);
        this.removePlayer.setEnabled(false);
        this.pane.add(removePlayer, new Integer(2), 2);

        icon = new ImageIcon("src/resources/images/playerMenu/Finished.png");
        this.finished = new JButton(icon);
        this.finished.addActionListener(this);
        this.finished.setBounds(250, 185, 30, 30);
        this.pane.add(finished, new Integer(2), 3);

        this.playerLabels = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            icon = new ImageIcon("src/resources/images/playerMenu/Player" + (i + 1) + ".png");
            JLabel temp = new JLabel(icon);
            temp.setBounds(80, 240 + (i * 30), 80, 20);
            this.playerLabels.add(temp);
            this.pane.add(temp, new Integer(2), 4);
        }

        this.playerNameInputs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            JTextField temp = new JTextField("Player " + (i + 1), 10);
            temp.setBounds(180, 240 + (i * 30), 80, 30);
            this.playerNameInputs.add(temp);
            this.pane.add(temp, new Integer(2), 5);
        }

        this.numVisible = 2;
        for (int i = 0; i < 4; i++) {
            if (i > 1) {
                this.playerLabels.get(i).setVisible(false);
                this.playerNameInputs.get(i).setVisible(false);
            }
        }

        this.add(pane);
        this.repaint();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == this.addPlayer) {
            switch (this.numVisible) {
                case 2:
                case 3:
                    this.removePlayer.setEnabled(true);
                    this.playerLabels.get(this.numVisible).setVisible(true);
                    this.playerNameInputs.get(this.numVisible).setVisible(true);
                    this.numVisible++;
                    if (this.numVisible >= 4)
                        clicked.setEnabled(false);
                    break;
            }
        } else if (clicked == this.removePlayer) {
            switch (this.numVisible) {
                case 3:
                case 4:
                    this.addPlayer.setEnabled(true);
                    this.numVisible--;
                    if (this.numVisible <= 2)
                        clicked.setEnabled(false);
                    this.playerLabels.get(this.numVisible).setVisible(false);
                    this.playerNameInputs.get(this.numVisible).setVisible(false);
            }
        } else if (clicked == this.finished) {
            if (this.playerListener != null) {
                ArrayList<String> names = new ArrayList<>();
                for (int i = 0; i < this.numVisible; i++) {
                    String name = this.playerNameInputs.get(i).getText();
                    if (name.length() > 10)
                        name = name.substring(0, 10);
                    names.add(name);
                }
                this.playerListener.playerNamesAdded(names);
            }
        }

    }
}

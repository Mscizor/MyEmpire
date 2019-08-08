package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class PlayingFrame extends JFrame implements ActionListener {

    /* Timers */
    private Timer moveTimer;
    private Timer messageTimer;
    private Timer iconTimer;
    private int seconds;

    /* Board GUI */
    private ArrayList <JButton> spaces;
    private ArrayList <JLabel> playerIcons;
    private ArrayList <JTextArea> playerOwned;

    private JPanel messagePanel;
    private JTextArea messageText;
    private JLabel centerImage;
    /* Board GUI */

    /* Info GUI */
    private ArrayList <JButton> buttons;
    private ButtonListener btnListener;

    private JLabel bankAmount;
    private ArrayList <JLabel> playerAmounts;
    /* Info GUI */

    /* Location variables and movement */
    private int currentPlayer = 0;
    private int movement;
    private int[] playerIndices;
    private int[][] xySpaceLocations;
    /* Location variables and movement */

    public PlayingFrame(String title, ArrayList <String> playerNames, double startingCash, double bankAmount,
                        ArrayList <ImageIcon> spaces, ButtonListener btnListener) {
        super (title);

        this.playerOwned = new ArrayList <> ();
        this.buttons = new ArrayList <> ();
        this.playerAmounts = new ArrayList <> ();
        this.btnListener = btnListener;

        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (1080, 750);

        this.xySpaceLocations = new int[32][2];
        for (int i = 0; i < 32; i++) {
            int x = 0, y = 0;
            if (i < 9) {
                x = 80 * i;
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
                y = 80 * (32 - i);
            }
            this.xySpaceLocations[i][0] = x;
            this.xySpaceLocations[i][1] = y;
        }

        this.playerIndices = new int[4];

        this.initComponents (playerNames, startingCash, bankAmount, spaces);

        this.setVisible (true);

        moveTimer = new Timer (100, this);
        messageTimer = new Timer (1000, this);
        iconTimer = new Timer (1000, this);

        this.moveCurrentPlayer (10);
    }

    private void initComponents (ArrayList <String> playerNames, double startingCash,
                                double bankAmount, ArrayList <ImageIcon> spaces) {
        ImageIcon icon;

        JLayeredPane pane;
        pane = new JLayeredPane ();
        pane.setBounds (0, 0, 720, 720);

        icon = new ImageIcon("src/resources/images/CenterBoard.png");
        JLabel bg = new JLabel (icon);
        bg.setBounds (0, 0, 720, 720);
        pane.add (bg, 1, 1);

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
            pane.add (btn, 2, 1);
        }

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
            pane.add (temp, 3, 1);
        }
        this.currentPlayer = 0;

        this.messagePanel = new JPanel ();
        this.messagePanel.setBounds (120, 260, 480, 200);
        this.messagePanel.setBorder (BorderFactory.createRaisedBevelBorder());
        pane.add (messagePanel, 6, 1);

        this.messageText = new JTextArea ();
        this.messageText.setBounds (125, 255, 470, 190);
        this.messageText.setOpaque (false);
        this.messageText.setEditable (false);
        this.messageText.setLineWrap (true);
        this.messageText.setWrapStyleWord (true);
        this.messagePanel.add (this.messageText);

        this.centerImage = new JLabel ();
        this.centerImage.setBounds (240, 140,240, 360);
        this.setVisible (false);
        pane.add (centerImage, 5, 1);

        JLayeredPane info = new JLayeredPane ();
        info.setBounds (720, 0, 360, 720);

        icon = new ImageIcon("src/resources/images/playingMenu/InfoBoard.png");
        bg = new JLabel (icon);
        bg.setBounds (0, 0, 360, 720);
        info.add (bg, 1, 1);

        icon = new ImageIcon("src/resources/images/playingMenu/Bank.png");
        JLabel bankLabel = new JLabel (icon);
        bankLabel.setBounds (20, 65, 90, 30);
        info.add (bankLabel, 2, 1);

        this.bankAmount = new JLabel (String.valueOf(bankAmount));
        this.bankAmount.setBounds (120, 65, 90, 30);
        this.bankAmount.setForeground (Color.WHITE);
        this.bankAmount.setFont (new Font ("Consolas", Font.PLAIN, 16));
        info.add (this.bankAmount, 2, 1);

        for (int i = 0; i < playerNames.size (); i++) {
            String name = playerNames.get (i);
            icon = new ImageIcon("src/resources/images/playingMenu/Player" + (i + 1) + ".png");
            JLabel playerLabel = new JLabel (icon);
            JLabel nameLabel = new JLabel (name);
            JLabel playerAmount = new JLabel (String.valueOf (startingCash));

            playerLabel.setBounds (20, 120 * i + 130, 80, 20);
            info.add (playerLabel, 2, 1);

            nameLabel.setBounds (120, 120 * i + 125, 80, 30);
            nameLabel.setForeground (Color.WHITE);
            nameLabel.setFont (new Font ("Consolas", Font.PLAIN, 12));
            info.add (nameLabel, 2, 1);

            playerAmount.setBounds (220, 120 * i + 125, 80, 30);
            playerAmount.setForeground (Color.WHITE);
            playerAmount.setFont (new Font ("Consolas", Font.PLAIN, 12));
            this.playerAmounts.add (playerAmount);
            info.add (playerAmount, 2, 1);

            JTextArea playerText = new JTextArea ();
            playerText.setEditable (false);
            this.playerOwned.add (playerText);
            JScrollPane playerOwned = new JScrollPane (playerText, VERTICAL_SCROLLBAR_AS_NEEDED,
                    HORIZONTAL_SCROLLBAR_AS_NEEDED);
            playerOwned.setBounds (20, 120 * i + 155, 320, 80);
            info.add (playerOwned, 2, 1);
        }

        try {
            File file = new File ("src/resources/Buttons.txt");
            Scanner sc = new Scanner (file, "UTF-8");
            for (int i = 0; i < 6; i++) {
                int x = 10, y = 620;
                if (i % 2 != 0)
                    x += 165;
                y += (i / 2) * 31;

                JButton temp;
                String name = sc.nextLine ();
                System.out.println (name);
                icon = new ImageIcon ("src/resources/images/playingMenu/" + name + ".png");
                temp = new JButton (icon);
                temp.setBounds (x, y, 165, 30);
                temp.addActionListener (this);
                this.buttons.add (temp);
                info.add (temp, 2, 1);
            }
        }
        catch (Exception e) {
            System.out.println("BoardFrame");
            System.out.println(e);
        }

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

    public void emitMessage (int seconds, String message) {
        this.seconds = seconds;
        this.messageText.setText (message);
        this.refresh ();
        this.messageTimer.start ();
    }

    public void emitImage (int seconds, ImageIcon icon) {
        this.seconds = seconds;
        this.centerImage.setIcon (icon);
        this.refresh ();
        this.iconTimer.start ();
    }

    public void setMessage (String message) {
        this.messageText.setText (message);
    }

    public void setIcon (ImageIcon icon) {
        this.centerImage.setIcon (icon);
    }

    public void setMessageVisible (boolean visible) {
        this.messagePanel.setVisible (visible);
    }

    public void setIconVisible (boolean visible) {
        this.centerImage.setVisible (visible);
    }

    public void setPlayerText (int player, String newText) {
        this.playerOwned.get (player).setText (newText);
    }

    public void setPlayerCash (int player, double newCash) {
        this.playerAmounts.get (player).setText (String.valueOf (newCash));
    }

    public void setBankCash (double newCash) {
        this.bankAmount.setText (String.valueOf (newCash));
    }

    public void disableButton (int button) {
        this.buttons.get (button).setEnabled (false);
    }

    public void enableButton (int button) {
        this.buttons.get (button).setEnabled (true);
    }

    public void setButtonsEnabled (boolean[] buttonsEnabled) {
        for (int i = 0; i < 6; i++) {
            this.buttons.get (i).setEnabled (buttonsEnabled[i]);
        }
    }
    public void setCurrentPlayer (int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void moveCurrentPlayer (int movement) {
        this.movement = movement;
        this.moveTimer.start ();
    }

    public int getPlayerLocation (int player) {
        int x, y;
        x = playerIcons.get (player).getX();
        y = playerIcons.get (player).getY();

        switch (player) {
            case 1:
                x -= 40;
                break;
            case 2:
                y -= 40;
                break;
            case 3:
                x -= 40;
                y -= 40;
                break;
        }

        for (int i = 0; i < this.xySpaceLocations.length; i++) {
            if (x == this.xySpaceLocations[i][0] && y == this.xySpaceLocations[i][1])
                return i;
        }

        return -1;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource () instanceof Timer)
        {
            Timer timer = (Timer) e.getSource ();
            if (timer == this.moveTimer) {
                if (this.movement > 0) {
                    this.messageText.setText ("Moving...");
                    this.messagePanel.setVisible(true);
                    int index = (++this.playerIndices[currentPlayer]) % 32;
                    int x = this.xySpaceLocations[index][0];
                    int y = this.xySpaceLocations[index][1];
                    x += getXYMod()[0];
                    y += getXYMod()[1];
                    this.playerIcons.get(currentPlayer).setBounds(x, y, 40, 40);
                    this.movement--;
                    System.out.println("yeet " + movement);
                    this.refresh ();
                } else {
                    timer.stop();
                    this.messagePanel.setVisible (false);
                }
            }
            else if (timer == this.messageTimer || timer == this.iconTimer) {
                if (timer == this.messageTimer) {
                    this.messagePanel.setVisible (true);
                }
                else {
                    this.centerImage.setVisible (true);
                }
                this.refresh ();

                if (this.seconds > 0) {
                    this.seconds--;
                }
                else {
                    timer.stop ();
                    if (timer == this.messageTimer) {
                        this.messagePanel.setVisible (false);
                    }
                    else {
                        this.centerImage.setVisible (false);
                    }
                }
            }
        }
        else if (e.getSource () instanceof JButton && !this.messageTimer.isRunning() && !this.iconTimer.isRunning ()
                && !this.moveTimer.isRunning ()) {
            JButton pressed = (JButton) e.getSource ();
            if (this.buttons.contains (pressed)) {
                this.btnListener.buttonPressed (this.buttons.indexOf (pressed));
            }
        }
    }

    public void refresh () {
        this.repaint ();
        this.revalidate ();
    }
}

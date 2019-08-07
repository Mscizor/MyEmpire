package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class SpaceSelectMenuFrame extends JFrame implements ActionListener {

    private JLayeredPane pane;

    private int[][] xySpaceLocations;
    private int currentImageIndex;
    private int[] finalLocations;

    private JLabel displayedImage;

    private ArrayList <JButton> spaces;
    private JButton finished;

    private ArrayList <ImageIcon> spaceImages;
    private ArrayList <ImageIcon> displayImages;

    private SpaceListener spaceListener;

    public SpaceSelectMenuFrame (SpaceListener spaceListener, ArrayList <ImageIcon> spaceImages,
                                 ArrayList <ImageIcon> displayImages) {
        this.spaceListener = spaceListener;
        this.spaceImages = spaceImages;
        this.displayImages = displayImages;

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
        this.finalLocations = new int[32];
        this.finalLocations[0] = 28;
        this.finalLocations[8] = 29;
        this.finalLocations[16] = 30;
        this.finalLocations[24] = 31;

        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (/* Size */725, 750);

        this.initComponents ();

        this.setVisible (true);
    }

    public void initComponents () {
        ImageIcon icon;

        this.pane = new JLayeredPane ();
        this.pane.setBounds (0, 0, 720, 720);

        icon = new ImageIcon ("src/resources/images/EmptyBoard.png");
        JLabel bg = new JLabel (icon);
        bg.setBounds (80, 80, 560, 560);
        this.pane.add (bg, new Integer (1), 1);

        this.displayedImage = new JLabel ();
        this.displayedImage.setBounds (240,180, 240, 360);
        this.displayedImage.setIcon (this.displayImages.get (0));
        this.pane.add (displayedImage, new Integer (2), 1);

        this.spaces = new ArrayList <> ();

        for (int i = 0; i < 32; i++) {
            JButton temp;
            if (i % 8 != 0) {
                icon = new ImageIcon ("src/resources/images/squares/numbers/" + i + ".png");
            }
            else {
                switch (i) {
                    case 0:
                        icon = new ImageIcon ("src/resources/images/squares/SqStart.png");
                        break;
                    case 8:
                        icon = new ImageIcon ("src/resources/images/squares/SqCommunityService.png");
                        break;
                    case 16:
                        icon = new ImageIcon ("src/resources/images/squares/SqJail.png");
                        break;
                    default:
                        icon = new ImageIcon ("src/resources/images/squares/SqFreeParking.png");
                }
            }
            temp = new JButton (icon);

            if (i % 8 == 0) {
                temp.setDisabledIcon (icon);
                temp.setEnabled (false);
            }

            int x = this.xySpaceLocations[i][0];
            int y = this.xySpaceLocations[i][1];
            temp.setBounds (x, y, 80, 80);
            temp.addActionListener (this);
            this.spaces.add (temp);
        }
        for (int i = 0; i < spaces.size (); i++) {
            this.pane.add (spaces.get (i), new Integer (2), 1);
        }

        icon = new ImageIcon ("src/resources/images/spaceMenu/Finished.png");
        this.finished = new JButton (icon);
        this.finished.setBounds (345, 110, 30, 30);
        this.finished.addActionListener (this);
        this.finished.setEnabled (false);
        this.pane.add (finished, new Integer (2), 1);

        this.add (pane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource ();
        if (pressed == this.finished) {
            boolean finishedPlacing = true;
            for (JButton btn : spaces) {
                if (btn.isEnabled ()) {
                    finishedPlacing = false;
                }
            }

            if (this.spaceListener != null && finishedPlacing) {
                ArrayList <ImageIcon> imagesAdded = new ArrayList <> ();
                int i = 0;
                for (JButton space : spaces) {
                    imagesAdded.add ((ImageIcon) space.getDisabledIcon());
                }
                this.spaceListener.spacesAdded (this.finalLocations);
            }
        }
        else if (this.spaces.contains (pressed)) {
            int index = this.spaces.indexOf (pressed);
            JButton spacePressed = this.spaces.get (index);
            this.finalLocations[index] = currentImageIndex;
            spacePressed.setDisabledIcon (spaceImages.get (this.currentImageIndex));
            spacePressed.setEnabled (false);
            this.currentImageIndex++;

            boolean finished = true;
            for (JButton btn : this.spaces) {
                if (btn.isEnabled ())
                    finished = false;
            }

            if (!finished) {
                this.displayedImage.setIcon(displayImages.get (this.currentImageIndex));
            }
            else {
                this.displayedImage.setVisible (false);
                this.finished.setEnabled (true);
            }
            this.update ();
        }
    }

    public void update () {
        this.revalidate ();
        this.repaint ();
    }
}

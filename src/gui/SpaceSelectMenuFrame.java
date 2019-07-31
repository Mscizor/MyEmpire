package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SpaceSelectMenuFrame extends JFrame implements ActionListener {

    JButton left;
    JButton right;
    JButton finished;
    JLabel image;


    ArrayList <ImageIcon> imagesToPlace;
    int[] imageIndexLocations;

    boolean finishedPlacing = false;


    SpaceListener spaceListener;

    public SpaceSelectMenuFrame(SpaceListener spaceListener, ArrayList <ImageIcon> images) {
        this.spaceListener = spaceListener;
        this.imagesToPlace = images;
        this.imageIndexLocations = new int[32];

        this.setDefaultCloseOperation (EXIT_ON_CLOSE);
        this.setLayout (null);
        this.setResizable (false);
        this.setSize (/* Size */720, 720);

        this.initComponents ();

        this.setVisible (true);
    }

    public void initComponents () {
        this.finished = new JButton ();
        this.finished.addActionListener (this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource ();

        if (pressed == this.finished) {
            if (this.spaceListener != null && finishedPlacing) {
                int[] indices = new int[32];
                // indices
                this.spaceListener.spacesAdded (indices);
            }
            else if (!finishedPlacing) {

            }
        }
    }
}

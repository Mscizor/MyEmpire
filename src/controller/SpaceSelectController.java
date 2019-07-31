package controller;

import gui.SpaceListener;
import gui.SpaceSelectMenuFrame;
import model.Bank;
import model.Player;
import model.spaces.Property;
import model.spaces.Railroad;
import model.spaces.Space;
import model.spaces.Utility;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SpaceSelectController {

    private ArrayList <Player> players;
    private Bank bank;
    private ArrayList <Space> spaces;

    ArrayList <ImageIcon> images;
    private SpaceSelectMenuFrame spaceSelectMenu;

    public SpaceSelectController (ArrayList <Player> players, Bank bank) {
        this.players = players;
        this.bank = bank;
        this.spaces = new ArrayList <> ();
        this.images = new ArrayList <> ();

        this.initSpaces ();
        this.spaceSelectMenu = new SpaceSelectMenuFrame (new PressedFinish (), images);
    }

    public void initSpaces () {
        try {
            File file = new File ("./src/resources/Properties.txt");
            Scanner sc = new Scanner (file);
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();
                String color = sc.nextLine ();
                double price = sc.nextDouble ();
                double priceBuilding = sc.nextDouble ();
                double[] rents = new double[6];
                for (int i = 0; i < 6; i++)
                    rents[i] = sc.nextDouble ();
                double multiplier = sc.nextDouble ();
                String iconName = sc.nextLine ();
                ImageIcon icon = new ImageIcon (".src/resources/images/" + iconName + ".png");
                images.add (icon);
                sc.nextLine ();
                sc.nextLine ();
                spaces.add (new Property (name, color, 0, price, priceBuilding, rents, multiplier, icon));
            }
        }
        catch (Exception e){
            // error
        }

        try {
            File file = new File ("./src/resources/Utilities.txt");
            Scanner sc = new Scanner (file);
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();
                String iconName = sc.nextLine ();
                ImageIcon icon = new ImageIcon (".src/resources/images/" + iconName + ".png");
                sc.nextLine ();
                sc.nextLine ();
                spaces.add (new Utility (name, 0, 150, icon));
            }
        }
        catch (Exception e){
            // error
        }

        try {
            File file = new File ("./src/resources/Railroads.txt");
            Scanner sc = new Scanner (file);
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();   
                String iconName = sc.nextLine ();
                ImageIcon icon = new ImageIcon (".src/resources/images/" + iconName + ".png");
                sc.nextLine ();
                sc.nextLine ();
                spaces.add (new Railroad (name, 0, 200, icon));
            }
        }
        catch (Exception e){
            // error
        }
    }
    private class PressedFinish implements SpaceListener {
        @Override
        public void spacesAdded (int[] spaceIndices) {

        }
    }

    public static void main (String[] args) {
//        new SpaceSelectController ();
    }
}

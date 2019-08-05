package controller;

import gui.SpaceListener;
import gui.SpaceSelectMenuFrame;
import model.Bank;
import model.Player;
import model.spaces.*;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SpaceSelectController {

    private ArrayList <Player> players;
    private Bank bank;
    private ArrayList <Space> spaces;

    ArrayList <ImageIcon> spaceImages;
    ArrayList <ImageIcon> displayImages;

    private SpaceSelectMenuFrame spaceSelectMenu;

    public SpaceSelectController (ArrayList <Player> players, Bank bank) {
        this.players = players;
        this.bank = bank;
        this.spaces = new ArrayList <> ();
        this.spaceImages = new ArrayList <> ();
        this.displayImages = new ArrayList <> ();

        this.initSpaces ();

        this.spaceSelectMenu = new SpaceSelectMenuFrame (new PressedFinish (), spaceImages, displayImages);
    }

    public void initSpaces () {
        try {
            File file = new File ("src/resources/Properties.txt");
            Scanner sc = new Scanner (file, "UTF-8");
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();
                String color = sc.nextLine ();
                double price = sc.nextDouble ();
                double priceBuilding = sc.nextDouble ();
                double[] rents = new double[6];
                for (int i = 0; i < 6; i++)
                    rents[i] = sc.nextDouble ();
                double multiplier = sc.nextDouble ();
                sc.nextLine ();
                ImageIcon[] icons = getImageIcons (sc);
                spaces.add (new Property (name, color, 0, price, priceBuilding, rents, multiplier, icons[0],
                        icons[1]));
            }
        }
        catch (Exception e){
            System.out.println ("SpaceSelectController - Properties");
            System.out.println (e);
        }

        try {
            File file = new File ("src/resources/Utilities.txt");
            Scanner sc = new Scanner (file, "UTF-8");
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();
                ImageIcon[] icons = getImageIcons (sc);
                spaces.add (new Utility (name, 0, 150, icons[0], icons[1]));
            }
        }
        catch (Exception e){
            System.out.println (e);
        }

        try {
            File file = new File ("src/resources/Railroads.txt");
            Scanner sc = new Scanner (file, "UTF-8");
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();
                ImageIcon[] icons = getImageIcons (sc);
                spaces.add (new Railroad (name, 0, 200, icons[0], icons[1]));
            }
        }
        catch (Exception e){
            System.out.println ("SpaceSelectController - Railroads");
            System.out.println (e);
        }

        try {
            File file = new File ("src/resources/Chances.txt");
            Scanner sc = new Scanner (file, "UTF-8");
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();
                ImageIcon[] icons = getImageIcons (sc);
                spaces.add(new Chance (name, 0, icons[0], icons[1]));
            }
        }
        catch (Exception e) {
            System.out.println ("SpaceSelectController - Chances");
            System.out.println (e);
        }

        try {
            File file = new File ("src/resources/Taxes.txt");
            Scanner sc = new Scanner (file, "UTF-8");
            while (sc.hasNextLine ()) {
                String name = sc.nextLine ();
                double tax = sc.nextDouble ();
                boolean isIncome = sc.nextBoolean ();
                sc.nextLine ();
                ImageIcon[] icons = getImageIcons (sc);
                spaces.add(new Tax (name, 0, tax, isIncome, icons[0], icons[1]));
            }
        }
        catch (Exception e) {
            System.out.println ("SpaceSelectController - Taxes");
            System.out.println (e);
        }

        try {
            File file = new File("src/resources/Corners.txt");
            Scanner sc = new Scanner(file, "UTF-8");
            while (sc.hasNextLine()) {
                String name = sc.nextLine();
                ImageIcon[] icons = getImageIcons (sc);
                spaces.add (new Corner (name, 0, icons[0], icons[1]));
            }
        }
        catch (Exception e) {
            System.out.println ("SpaceSelectController - Corners");
            System.out.println (e);
        }
    }

    private ImageIcon[] getImageIcons (Scanner sc) throws Exception {
        ImageIcon[] returning = new ImageIcon[2];
        try {
            String spaceIconName = sc.nextLine();
            ImageIcon spaceIcon = new ImageIcon("src/resources/images/squares/" + spaceIconName + ".png");
            this.spaceImages.add(spaceIcon);
            String displayIconName = sc.nextLine();
            ImageIcon displayIcon = new ImageIcon("src/resources/images/displayCards/" + displayIconName +
                    ".png");
            this.displayImages.add(displayIcon);
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            returning[0] = spaceIcon;
            returning[1] = displayIcon;
        }
        catch (Exception e) {
            System.out.println (e);
            throw new Exception ();
        }
        return returning;
    }
    private class PressedFinish implements SpaceListener {
        @Override
        public void spacesAdded (int[] finalLocations) {
            ArrayList <Space> orderedSpaces = new ArrayList <> ();
            for (int i = 0; i < finalLocations.length; i++) {
                int index = finalLocations[i];
                orderedSpaces.add (spaces.get (index));
                spaces.get (index).setLocation (i);
            }
            spaceSelectMenu.dispose ();
        }
    }
}

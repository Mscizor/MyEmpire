package controller;

import gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private MainMenuFrame mainMenu;

    public MainController () {
        mainMenu = new MainMenuFrame ("Main Menu", new StartClicked ());
    }

    private class StartClicked implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            mainMenu.dispose ();
            new PlayerMenuController ();
        }
    }
}

package model.spaces;

import javax.swing.*;

/**
 * This class inherits the attributes of the Space class.
 * It is a special type of space that allows a player to draw from the deck of
 * chance cards
 * when landed on.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Chance extends Space {
    /**
     * @param name          the name of the chance space
     * @param location      the location of the chance space on the array list of spaces
     * @param spaceIcon     the image of the chance space players can land on
     * @param displayIcon   the image of the chance space that holds its information
     * */
    public Chance(String name, int location, ImageIcon spaceIcon, ImageIcon displayIcon) {
        super(name, location, spaceIcon, displayIcon);
    }
}

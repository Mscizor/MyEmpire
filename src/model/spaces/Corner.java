package model.spaces;

import javax.swing.*;

/**
 * It is a special type of space that has different types of effects
 * if the Player if the player lands on it.
 * There are four types of corner spaces: start, jail, community service,
 * and free parking.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Corner extends Space {

    /**
     * This constructor accepts the type of Corner (as an integer)
     * and the location and initializes the Corner.
     *
     * @param name          the name of the corner space
     * @param location      the location of the corner space on the array list of spaces
     * @param spaceIcon     the image of the corner space players can land on
     * @param displayIcon   the image of the corner space that holds its information
     */
    public Corner(String name, int location, ImageIcon spaceIcon, ImageIcon displayIcon) {
        super(name, location, spaceIcon, displayIcon);
    }

}

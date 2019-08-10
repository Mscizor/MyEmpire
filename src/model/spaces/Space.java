package model.spaces;

import javax.swing.*;

/**
 * The Space class consists of 32 possible spaces the player can land on.
 * The space can be a property, utility, a corner space, a chance space, or a
 * railroad space.
 * The different kinds of spaces hold different attributes.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public abstract class Space {
    private String name;
    private int location;
    private ImageIcon spaceIcon;
    private ImageIcon displayIcon;

    /**
     * Constructor that initializes Space name, location,
     * and images associated with it.
     *
     * @param name          the name of the space
     * @param location      the location of the space
     * @param spaceIcon     the image of the space players can land on
     * @param displayIcon   the image of the space that holds its information
     */
    public Space(String name, int location, ImageIcon spaceIcon, ImageIcon displayIcon) {
        this.name = name;
        this.location = location;
        this.spaceIcon = spaceIcon;
        this.displayIcon = displayIcon;
    }

    /**
     * Method that gets the name of the space
     *
     * @return String specifying the name of the space.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method that sets the name of the space
     *
     * @param name  the name of the space
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method that gets the location of the space
     *
     * @return Integer specifying the position of the space in the array list of spaces.
     */
    public int getLocation() {
        return this.location;
    }

    /**
     * Method that sets the location of the space
     *
     * @param location the integer position of the space in the board
     */
    public void setLocation(int location) {
        this.location = location;
    }

    /**
     * Method that gets the image of the space players can land on
     *
     * @return ImageIcon specifying the spaceIcon
     */
    public ImageIcon getSpaceIcon() {
        return this.spaceIcon;
    }

    /**
     * Method that gets the image of the space that holds its information
     *
     * @return ImageIcon specifying the displayIcon
     */
    public ImageIcon getDisplayIcon() {
        return this.displayIcon;
    }
}

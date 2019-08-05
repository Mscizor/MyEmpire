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

    public Space(String name, int location, ImageIcon spaceIcon, ImageIcon displayIcon) {
        this.name = name;
        this.location = location;
        this.spaceIcon = spaceIcon;
        this.displayIcon = displayIcon;
    }

    /**
     * Gets the name of the space
     *
     * @return a <code> String </code>
     * specifying the name of the space.
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of the space
     *
     * @return a <code> int </code>
     * specifying the position of the space in the array list of spaces.
     */
    public int getLocation () {
        return this.location;
    }

    public void setLocation (int location) {
        this.location = location;
    }

    public ImageIcon getSpaceIcon () {
        return this.spaceIcon;
    }

    public ImageIcon getDisplayIcon () {
        return this.displayIcon;
    }
}

package model;

import java.util.ArrayList;

/**
 * @author janur
 */
public interface Ownable {
    default Player getOwner(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get((i)).getOwned().contains(this)) {
                return players.get(i);
            }
        }
        return null;
    }
}

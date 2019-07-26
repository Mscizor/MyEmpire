package model.cards;

import model.Bank;
import model.CardMovePlayer;
import model.Player;
import model.spaces.Property;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author janur
 */
public class CardARandomProperty extends Card implements CardMovePlayer {
    private final Property random;

    public CardARandomProperty(String name, String text, Property random) {
        super(name, text);
        this.random = random;
    }

    @Override
    public void doCardEffect(Player player, ArrayList<Space> spaces, Bank bank) {
        if (random == null) {
            player.changePosition(0);
        } else {
            player.changePosition(random.getLocation());
            // TODO: do land thing and start thing
        }
        this.discard();
    }
}

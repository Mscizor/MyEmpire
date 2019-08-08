package model.cards;

import model.Bank;
import model.CardMovePlayer;
import model.Player;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardCJail extends Card implements CardMovePlayer {
    public CardCJail(String name, String text) {
        super(name, text);
    }

    @Override
    public boolean doCardEffect(Player player, ArrayList<Space> spaces, Bank bank) {
        if (spaces.get(16).getName().equals("JAIL")) {
            player.changePosition(16);
            player.arrest();
        }
        this.discard();
        return false;
    }
}

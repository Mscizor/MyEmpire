package model;

import model.spaces.Space;

import java.util.ArrayList;

public interface CardMovePlayer {
    boolean doCardEffect(Player player, ArrayList<Space> spaces, Bank bank);
}

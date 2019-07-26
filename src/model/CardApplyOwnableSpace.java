package model;

import model.spaces.OwnableSpace;
import model.spaces.Space;

import java.util.ArrayList;

public interface CardApplyOwnableSpace {
    boolean isApplied();

    void doCardEffect(Player player, ArrayList<Space> spaces, OwnableSpace owned, Bank bank);

    double getChangeToRent();
}

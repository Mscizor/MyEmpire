package model;

import model.spaces.Space;
import java.util.ArrayList;

public interface CardMovePlayer {
	public void doCardEffect (Player player, ArrayList <Space> spaces, Bank bank);
}

package model.cards;

import model.Bank;
import model.CardMovePlayer;
import model.Player;
import model.spaces.Property;
import model.spaces.Space;

import java.util.ArrayList;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardCRandomProperty extends Card implements CardMovePlayer
{
	private final Property random;
	
	public CardCRandomProperty (String name, String text, Property random)
	{
		super (name, text);
		this.random = random;
	}
	
	@Override
	public void doCardEffect (Player player, ArrayList <Space> spaces, Bank bank)
	{
		if (spaces.get (random.getLocation ()) == random)
		{
			player.changePosition (random.getLocation ());
			// TODO : do land thing and start thing
		}
		
		this.discard ();
	}
}

package model.cards;

import java.util.ArrayList;

import model.*;
import model.spaces.*;

/**
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardDChangeNonProperty extends Card implements CardApplyOwnableSpace
{
	private final double changeToRent;
	private boolean applied = false;
	
	public CardDChangeNonProperty (String name, String text, double changeToRent)
	{
		super (name, text);
		this.changeToRent = changeToRent;
	}
	
	@Override
	public void doCardEffect (Player player, ArrayList <Space> spaces,
	                          OwnableSpace owned, Bank bank)
	{
		if (owned instanceof Railroad || owned instanceof Utility)
		{
			owned.addCard (this);
			this.applied = true;
		}
	}
	
	@Override
	public double getChangeToRent ()
	{
		return this.changeToRent;
	}
	
	@Override
	public boolean isApplied ()
	{
		return this.applied;
	}
}

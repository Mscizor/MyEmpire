package model.cards;

import controller.SpaceController;
import model.CardApplyOwnableSpace;
import model.spaces.Property;
import model.spaces.Space;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates a new set of cards at the start of the game. It acts as the deck in which the cards are picked
 * randomly.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class CardSet
{
	private final ArrayList <Card> cards;
	
	/**
	 * This constructor accepts a null parameter and initializes the values of the array list of cards to be used in
	 * gameplay.
	 *
	 * @param spaces The spaces to use for randomizing the cards
	 */
	public CardSet (ArrayList <Space> spaces)
	{
		cards = new ArrayList <> ();
		
		Random rand = new Random ();
		
		int randomCard;
		String name = null;
		String text = null;
		for (int i = 0; i < 6; i++)
		{
			randomCard = rand.nextInt (3);
			switch (randomCard)
			{
				case 0:
					name = "Go to Nearest Utility";
					text = "Land on the nearest utility, and if owned, roll dice " +
							"and pay the owner 10 times the value.";
					cards.add (new CardANearestNonProperty (name, text, false));
					break;
				case 1:
					name = "Go to Nearest Railroad";
					text = "Land on the nearest railroad.";
					cards.add (new CardANearestNonProperty (name, text, true));
					break;
				case 2:
					Property randProperty = SpaceController.getRandomProperty (spaces);
					if (randProperty != null)
					{
						name = "Proceed to " + randProperty.getName ();
						text = "Go directly to " + randProperty.getName () +
								" and land on it.";
					}
					cards.add (new CardARandomProperty (name, text, randProperty));
			}
		}
		for (int i = 0; i < 6; i++)
		{
			randomCard = rand.nextInt (5);
			double getCash;
			switch (randomCard)
			{
				case 0:
					name = "Bank Dividend";
					text = "Congratulations! Bank pays you dividend of $50.";
					getCash = 50;
					cards.add (new CardBGetCash (name, text, getCash));
					break;
				case 1:
					name = "Tax Refund";
					text = "Collect $100 from the bank.";
					getCash = 100;
					cards.add (new CardBGetCash (name, text, getCash));
					break;
				case 2:
					name = "Birthday";
					text = "It's your birthday! Collect $300 gift money.";
					getCash = 300;
					cards.add (new CardBGetCash (name, text, getCash));
					break;
				case 3:
					name = "Competition Winner";
					text = "Congratulations for winning ";
					getCash = 150;
					switch (rand.nextInt (5))
					{
						case 0:
							text += "2nd place on the beauty pageant! ";
							break;
						case 1:
							text += "1st place on the eating contest! ";
							break;
						case 2:
							text += "and getting through the local game show! ";
							break;
						case 3:
							text += "the annual baking contest! ";
							break;
						case 4:
							text += "the weight lifting competition! ";
					}
					text += "Collect $150 in prize money!";
					cards.add (new CardBGetCash (name, text, getCash));
					break;
				case 4:
					name = "Salary!";
					text = "Advance to START and collect your $200!";
					cards.add (new CardBStart (name, text));
					break;
			}
		}
		for (int i = 0; i < 4; i++)
		{
			randomCard = rand.nextInt (2);
			switch (randomCard)
			{
				case 0:
					name = "Arrested";
					text = "Go to JAIL directly. Do not pass START.";
					cards.add (new CardCJail (name, text));
					break;
				case 1:
					Property randProperty = SpaceController.getRandomProperty (spaces);
					if (randProperty != null)
					{
						name = "Go to " + randProperty.getName ();
						text = "Go to " + randProperty.getName () +
								" and land on it.";
					}
					cards.add (new CardCRandomProperty (name, text, randProperty));
			}
		}
		for (int i = 0; i < 7; i++)
		{
			randomCard = rand.nextInt (5);
			double changeToRent;
			switch (randomCard)
			{
				case 0:
					name = "Double Rent";
					text = "Apply to a property you own and collect double rent " +
							"from the next player. Discard if you don't have " +
							"property";
					changeToRent = 2.0;
					cards.add (new CardDDoubleRent (name, text, changeToRent));
					break;
				case 1:
					name = "Renovation";
					text = "Apply to a property you own and pay $25 per house " +
							"and $50 per hotel on it, which increases its rent by " +
							"50%. Discard if you don't have property.";
					changeToRent = 1.5;
					cards.add (new CardDRenovateProperty (name, text, changeToRent));
					break;
				case 2:
					name = "Dilapidated Houses";
					text = "Apply to a property you own, which decreases its rent " +
							"by 10%. Discard if you don't have property.";
					changeToRent = 0.9;
					cards.add (new CardDChangeProperty (name, text, changeToRent));
					break;
				case 3:
					name = "Price Hike";
					text = "Apply to a railroad/utility you own, which increases " +
							"rent by 10%. Discard if you don't have railroads or " +
							"utilities.";
					changeToRent = 1.1;
					cards.add (new CardDChangeNonProperty (name, text, changeToRent));
					break;
				case 4:
					name = "Price Drop";
					text = "Apply to a railroad/utility you own, which decreases " +
							"rent by 10%. Discard if you don't have railroads or " +
							"utilities.";
					changeToRent = 0.9;
					cards.add (new CardDChangeNonProperty (name, text, changeToRent));
			}
		}
		for (int i = 0; i < 3; i++)
		{
			randomCard = rand.nextInt (2);
			double paidCash = 0;
			switch (randomCard)
			{
				case 0:
					paidCash = rand.nextInt (100) + 100;
					name = "Donation to the Community";
					text = "You donate " + paidCash + " to the local community " +
							"for development.";
					break;
				case 1:
					paidCash = rand.nextInt (150) + 200;
					name = "Tax Payment";
					text = "Pay " + paidCash + " in taxes to the bank.";
					break;
			}
			cards.add (new CardERandomPay (name, text, paidCash));
		}
		for (int i = 0; i < 2; i++)
		{
			name = "Get out of Jail Free";
			text = "You can use this when you are in jail to avoid paying"
					+ " the bail cost.";
			cards.add (new CardFJailFree (name, text));
		}
	}
	
	/**
	 * This method accepts a null parameter and puts discarded cards back into the set of usable cards if certain
	 * conditions are met
	 */
	public void shuffleSet ()
	{
		int i;
		
		for (i = 0; i < cards.size (); i++)
		{
			if (cards.get (i).isDiscarded ())
			{
				cards.get (i).shuffleIn ();
			}
		}
	}
	
	/**
	 * This method accepts a null parameter and picks a random card. If no cards are left, discarded cards are shuffled
	 * back in.
	 *
	 * @return The randomly picked card.
	 */
	public Card pickRandom ()
	{
		Random rand = new Random ();
		int randomPick = rand.nextInt (cards.size ());
		boolean found = false;
		
		if (cards.isEmpty ())
		{
			this.shuffleSet ();
		}
		
		int i = 0;
		Card randomCard = null;
		while (i < cards.size () && !found && !cards.isEmpty ())
		{
			randomCard = cards.get ((randomPick + i) % cards.size ());
			if (!randomCard.isDiscarded ()
					&& !(randomCard instanceof CardApplyOwnableSpace)
					|| ((CardApplyOwnableSpace) randomCard).isApplied ())
			{
				found = true;
			}
		}
		
		this.cards.remove (randomCard);
		
		return randomCard;
	}
}

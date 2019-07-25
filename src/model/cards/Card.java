package model.cards;

/**
 * This object hold information and behavior of the possible cards in the game.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public abstract class Card
{
	private final String name;
	private final String text;
	private boolean discarded;
	
	public Card (String name, String text)
	{
		this.name = name;
		this.text = text;
	}
	
	/**
	 * Checks to see if the card is discarded.
	 *
	 * @return a <code> boolean </code> specifying the state of the card, if it is discarded or not.
	 */
	public boolean isDiscarded ()
	{
		return this.discarded;
	}
	
	/**
	 * Gets the name of the card.
	 *
	 * @return a <code> string </code> specifying the name of the card
	 */
	public String getName ()
	{
		return this.name;
	}
	
	/**
	 * Gets the text stored inside the card.
	 *
	 * @return a <code> String </code> specifying the text stored inside the card
	 */
	public String getText ()
	{
		return this.text;
	}
	
	/**
	 * Sets the boolean value of the card discard as true.
	 */
	public void discard ()
	{
		this.discarded = true;
	}
	
	/**
	 * Sets the boolean value of the card discard as false.
	 */
	public void shuffleIn ()
	{
		this.discarded = false;
	}
	
}

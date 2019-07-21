package model.cards;

/**
 * Frees the player from jail (if in) when player has this card.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class CardFJailFree extends Card
{
   /**
    * No-argument Constructor that picks a random card 
    * of this card type.
    */
   public CardFJailFree ()
   {
      super ("Get Out of Jail Free", "Keep this until you get jailed to"
              + "avoid the payment.");
   }
}

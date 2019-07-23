package model.cards;

import model.Ownable;

/**
 * Frees the player from jail (if in) when player has this card.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 */
public class CardFJailFree extends Card implements Ownable
{

   public CardFJailFree (String name, String text)
   {
      super (name, text);
   }
}

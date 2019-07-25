package model.spaces;

import controller.CardController;
import java.util.ArrayList;
import model.*;
import model.cards.Card;
import model.cards.CardSet;

/**
 * This class inherits the attributes of the Space class.
 * It is a special type of space that allows a player to draw from the deck of
 * chance cards
 * when landed on.
 *
 * @author Jan Uriel Marcelo
 * @author Thea Ellen Go
 * @version 1.0
 * @since 1.0
 */
public class Chance extends Space
{
   /**
    *
    * @param name the name of the space
    * @param location the location of the space on the array list of spaces
    */
   public Chance (String name, int location)
   {
      super (name, location);
   }
   
   public void doLandEffect (ArrayList <Player> players, ArrayList <Space> spaces, Player player, Bank bank,
                             CardSet cardSet)
   {
      Card card = cardSet.pickRandom ();
      CardController.doCardEffect (card, player, spaces, bank);
   }
}

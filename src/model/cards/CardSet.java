package model.cards;

import java.util.ArrayList;
import java.util.Random;
import model.spaces.Space;

/**
 * This class creates a new set of cards at the start of the game.
 * It acts as the deck in which the cards are picked randomly.
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
    * This constructor accepts a null parameter
    * and initializes the values of the array list of cards
    * to be used in gameplay.
    *
    * @param spaces The spaces to use for randomizing the cards
    */
   public CardSet (ArrayList <Space> spaces)
   {
      cards = new ArrayList <> ();

      int i;

      for (i = 0; i < 6; i++)
      {
//         random A
      }
      for (i = 0; i < 6; i++)
      {
//         random B
      }
      for (i = 0; i < 4; i++)
      {
//         random C
      }
      for (i = 0; i < 7; i++)
      {
//         random D
      }
      for (i = 0; i < 3; i++)
      {
//         random E
      }
      for (i = 0; i < 2; i++)
      {
//         random F
      }
   }

   /**
    * This method accepts a null parameter
    * and puts discarded cards back into the set of usable cards
    * if certain conditions are met
    */
   public void shuffleSet ()
   {
      int i;

      for (i = 0; i < cards.size (); i++)
      {
         if (cards.get (i).isDiscarded () == true)
         {
            cards.get (i).shuffleIn ();
         }
      }
   }

   /**
    * This method accepts a null parameter
    * and picks a random card.
    * If no cards are left, discarded cards are shuffled back in.
    *
    * @return The randomly picked card.
    */
   public Card pickRandom ()
   {
      int i = 0;
      Random rand = new Random ();
      int loc = rand.nextInt (32);
      Card hold;
      boolean found = false;

      while (true)
      {
         while (i <= cards.size () && found == false && !cards.isEmpty ())
         {
            hold = cards.get ((loc + i) % cards.size ());
//           if (hold.isDiscarded () == false)
//         {
//               if (hold instanceof D)
//               {
//                  if (((CardApply) (hold)).isApplied () == false)
//                  {
//                     return hold;
//                  }
//         }
//         else
//         {
//           return hold;
//         }
//               
//        
         }
         if (found == false)
         {
            shuffleSet ();
         }
      }
   }

}

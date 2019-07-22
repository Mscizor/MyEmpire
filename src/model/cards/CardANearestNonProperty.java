package model.cards;

import java.util.ArrayList;
import model.*;
import model.spaces.*;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class CardANearestNonProperty extends Card implements CardMovePlayer
{
   private final boolean isRailroad;
   
   public CardANearestNonProperty (String name, String text, boolean isRailroad)
   {
      super (name, text);
      this.isRailroad = isRailroad;
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces, Bank bank)
   {
      int playerPos = player.getPosition ();
      int highestDistance = 0;
      int currentDistance = 0;
      int destinationLoc;
      Space search;
      for (int i = 0; i < spaces.size (); i++)
      {
         search = spaces.get (i);
         if ((search instanceof Railroad && this.isRailroad) ||
                 (search instanceof Utility && !this.isRailroad))
         {
            destinationLoc = spaces.get (i).getLocation ();
         
            if (destinationLoc - playerPos >= 0)
               currentDistance = destinationLoc - playerPos;
            else
               currentDistance = destinationLoc - playerPos + 32;
         
            if (currentDistance > highestDistance)
               highestDistance = currentDistance;
         }
      }
//    TODO: do the landing thing and start thing
        
      this.discard ();
   }
}

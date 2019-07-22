/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cards;

import java.util.ArrayList;
import model.Player;
import model.spaces.*;  

/**
 *
 * @author janur
 */
public class CardANearestNonProperty extends Card
{
   private final boolean isRailroad;
   
   public CardANearestNonProperty (String name, String text, boolean isRailroad)
   {
      super (name, text);
      this.isRailroad = isRailroad;
   }
   
   public void doCardEffect (Player player, ArrayList <Space> spaces)
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

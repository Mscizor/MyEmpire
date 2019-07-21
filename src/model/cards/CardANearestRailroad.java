/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cards;

import java.util.ArrayList;
import model.Player;
import model.spaces.Railroad;

/**
 *
 * @author janur
 */
public class CardANearestRailroad extends Card
{
   public CardANearestRailroad (String name, String text)
   {
      super (name, text);
   }
   
   public void doCardEffect (Player player, ArrayList <Railroad> railroads)
   {
      int playerPos = player.getPosition ();
      int highestDistance = 0;
      int currentDistance = 0;
      int railroadLoc;
      for (int i = 0; i < railroads.size (); i++)
      {
         railroadLoc = railroads.get (i).getLocation ();
         
         if (railroadLoc - playerPos >= 0)
            currentDistance = railroadLoc - playerPos;
         else
            currentDistance = railroadLoc - playerPos + 32;
         
         if (currentDistance > highestDistance)
            highestDistance = currentDistance;
      }
//    TODO: do the landing thing
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cards;

import java.util.ArrayList;
import model.Player;
import model.spaces.Utility;
/**
 *
 * @author janur
 */
public class CardANearestUtility extends Card
{
   public CardANearestUtility (String name, String text)
   {
      super (name, text);
   }
   
   public void doCardEffect (Player player, ArrayList <Utility> utilities)
   {
      int playerPos = player.getPosition ();
      int highestDistance = 0;
      int currentDistance = 0;
      int utilityLoc;
      for (int i = 0; i < utilities.size (); i++)
      {
         utilityLoc = utilities.get (i).getLocation ();
         
         if (utilityLoc - playerPos >= 0)
            currentDistance = utilityLoc - playerPos;
         else
            currentDistance = utilityLoc - playerPos + 32;
         
         if (currentDistance > highestDistance)
            highestDistance = currentDistance;
      }
//    TODO: do the landing thing
   }
}

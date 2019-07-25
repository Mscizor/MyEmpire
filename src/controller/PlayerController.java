/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Bank;
import model.Player;
import model.spaces.Space;

/**
 *
 * @author janur
 */
public class PlayerController
{
   public static void moveAndLand (ArrayList <Player> players, ArrayList <Space> spaces, Player player, Bank bank,
                                   boolean direct)
   {
      int playerPos = player.getPosition ();
      int diceRoll = player.getDiceRoll ();
      
      if (playerPos + diceRoll < playerPos && !direct)
      {
         Transactions.cashToBank (player, bank, -200);
         // TODO: do START
      }
      
      playerPos = playerPos + diceRoll;
      player.changePosition (playerPos);
      
      SpaceController.doLandEffect (players, spaces, spaces.get (playerPos),
              player, bank);
   }
   
   public static void moveToAndLand (ArrayList <Player> players, ArrayList <Space> spaces, Player player, Bank bank,
                                     Space space, boolean direct)
   {
      int playerPos = player.getPosition ();
      int destinationLoc = space.getLocation ();
      
      if (destinationLoc < playerPos && !direct)
      {
         Transactions.cashToBank (player, bank, -200);
         // TODO: do START
      }
      
      player.changePosition (destinationLoc);
      
      SpaceController.doLandEffect (players, spaces, space, player, bank);
   }
}

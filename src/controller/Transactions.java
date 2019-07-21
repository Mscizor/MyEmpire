/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Bank;
import model.Player;
import model.spaces.OwnableSpace;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class Transactions
{
   public static void cashToBank (Player player, Bank bank, double cash)
   {
      player.changeCash (-cash);
      bank.changeCash (cash);
   }
   
   public static void cashToOtherPlayer (Player player, Player other, double cash)
   {
      player.changeCash (-cash);
      other.changeCash (cash);
   }
   
   public static void tradeWithOtherPlayer (Player player, Player other,
           ArrayList <OwnableSpace> give, ArrayList <OwnableSpace> take)
   {
      for (int i = 0; i < give.size (); i++)
         give.get (i).changeOwner (other);
      
      for (int i = 0; i < take.size (); i++)
         take.get (i).changeOwner (player);
   }
}

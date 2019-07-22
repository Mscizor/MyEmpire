package controller;

import java.util.ArrayList;
import model.Bank;
import model.Ownable;
import model.Player;

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
           ArrayList <Ownable> give, ArrayList <Ownable> take)
   {
//      for (int i = 0; i < give.size (); i++)
//         give.get (i).changeOwner (other);
//      
//      for (int i = 0; i < take.size (); i++)
//         take.get (i).changeOwner (player);
   }
}

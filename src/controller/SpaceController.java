package controller;

import java.util.ArrayList;
import model.Bank;
import model.Player;
import model.spaces.*;

/**
 *
 * @author Thea Go
 * @author Jan Uriel Marcelo
 */
public class SpaceController
{
   public static void doLandEffect (ArrayList<Space> spaces, Space space,
           Player player, Bank bank)
   {
      if (space instanceof Property)
      {

      }
      else if (space instanceof Utility || space instanceof Railroad)
      {

      }
      else if (space instanceof Corner)
      {
         String name = space.getName ();
         switch (name)
         {
            case "START":
               player.changeCash (200);
               bank.changeCash (-200);
               break;
            case "Community Service":
               player.changeCash (-50);
               bank.changeCash (200);
               break;
            case "Free Parking":
               ;
               break;
            case "JAIL":
               player.arrest ();
               break;
            default:
               break;
         }
      }
      else if (space instanceof Chance)
      {
//         player.changeCash ()
      }
      else if (space instanceof Tax)
      {
         String name = space.getName ();
         switch (name)
         {
            case "Luxury Tax":
               player.changeCash (-75);
               break;
            case "Income Tax":
               double cash;
               if (player.getCash () * 0.10 > 200)
               {
                  cash = player.getCash () * 0.10;
               }
               else
               {
                  cash = 200;
               }
               player.changeCash (-cash);
               break;
         }
      }
   }

}

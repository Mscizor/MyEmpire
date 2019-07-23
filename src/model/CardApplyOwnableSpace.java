package model;

import java.util.ArrayList;
import model.spaces.*;

public interface CardApplyOwnableSpace
{
   public boolean isApplied ();

   public void doCardEffect (Player player, ArrayList<Space> spaces,
           OwnableSpace owned, Bank bank);

   public double getChangeToRent ();
}

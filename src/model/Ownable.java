/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author janur
 */
public interface Ownable
{
   public Player getOwner (ArrayList <Player> players);
}

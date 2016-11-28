/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.creature.*;
import main.item.*;

/**
 *
 * @author nicol
 */
public interface GameInterface {
	
	
	public Room getCurrentRoom();
	public Player getPlayer();
	public Room getCurrentVendorRoom();
	public int getTurns();
	public void addTurn();
	public int getPoints();
	public String getDialogue();
	public void addDialogue();
	public void addDialogue(String string);
	public void createRooms();
	public void play();
	public void useItem(Item searchForItem);
	public void attack();
	public void flee();
	public void attack(boolean wantToStay);
	public void goRoom(int[] direction);
	public int[] getPlayerPosition();
	public void useTeleporter();
	
}

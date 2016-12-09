package main;

import main.creature.*;
import main.item.*;

public interface GameInterface {

	public Room getCurrentRoom();

	public Player getPlayer();
	
	public Vendor getVendor();
	
	public Room[][] getRooms();

	public Boolean[][] getRoomsSeen();
	
	public Room getCurrentVendorRoom();

	public int getTurns();

	public void addTurn();

	public int getPoints();

	public String getDialogue();

	public void addDialogue();

	public void addDialogue(String string);

	public void createRooms();

	public void play();

	public boolean useItem(Item searchForItem);

	public void attack();

	public void flee();

	public void attack(boolean wantToStay);

	public void goRoom(int[] direction);

	public int[] getPlayerPosition();

	public void useTeleporter();

	public boolean getLost();

}

package main;

import main.creature.*;
import main.item.*;

public interface GameInterface {

	public Room getCurrentRoom();

	public Player getPlayer();
	
	public int getTurnsLimit();
	
	public int[] getVendorPosition();
	
	public Vendor getVendor();
	
	public Room[][] getRooms();

	public Boolean[][] getRoomsSeen();
	
	public Room getCurrentVendorRoom();
	
	public void buyItem(Item itemToBuy);

	public int getTurns();

	public void addTurn();

	public int getPoints();

	public String getDialogue();

	public void addDialogue();

	public void addDialogue(String string);

	public void createRooms();

	public void play();

	public boolean useItem(Item searchForItem);

	public void takeItem(Item searchForItem);

	public void dropItem(Item searchForItem);

	public void attack();

	public void flee();

	public void attack(boolean wantToStay);

	public void goRoom(int[] direction);

	public int[] getPlayerPosition();

	public void useTeleporter();

	public boolean roomHasMonster();

	public boolean roomHasVendor();

	public boolean roomHasTeleporter();

	public boolean getLost();

	public void setScenario(int setTo);

}

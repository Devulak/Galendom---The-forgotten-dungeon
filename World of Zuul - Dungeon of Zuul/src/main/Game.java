package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.creature.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.item.*;

public class Game implements GameInterface {

	private int points;
	private int turns;
	private int turnsLimit;
	private String dialogue = "";
	private Player player = new Player(100);
	private Room currentRoom;
	private Room lastRoom;
	private Vendor vendor = new Vendor(0);
	private Room currentVendorRoom;
	private int score = 0;
	private Room[][] rooms = new Room[4][5];
	private Boolean[][] roomsSeen = new Boolean[4][5];

	public Game()
	{
		try {
			createRooms();
			serialization();
			currentRoom = rooms[1][2];
			currentVendorRoom = rooms[3][1];
			givePlayerItems();
		} catch (IOException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void serialization() throws IOException
	{
		try (FileOutputStream fout = new FileOutputStream("gamescenarios\\GameScenario2.ser")) {  
		ObjectOutputStream out = new ObjectOutputStream(fout);  

		out.writeObject(rooms);
		out.writeObject(roomsSeen);
		out.close();
		}
		System.out.println("Serialization succesful");
	}
	
	public void deSerialization() throws IOException, ClassNotFoundException
	{
		try (FileInputStream fileIn = new FileInputStream("gamescenarios\\GameScenario2.ser")) {
			ObjectInputStream in = new ObjectInputStream(fileIn);
			rooms = (Room[][]) in.readObject();
			roomsSeen = (Boolean[][]) in.readObject();
			in.close();
			fileIn.close();
		}
		System.out.println("Deserialization succesful");
	}
	
	private void givePlayerItems(){
		player.getCreaturesInventory().add(new Potion(5)); // x5 health potions
		player.getCreaturesInventory().add(new Weapon("Wooden Sword", 1)); //Wooden Sword, which the player has in the start of the game    
	}

	public int getTurns() 
	{
		return turns;
	}

	@Override
	public int getTurnsLimit() 
	{
		return turnsLimit;
	}

	@Override
	public Room getCurrentRoom() 
	{
		return currentRoom;
	}

	@Override
	public Player getPlayer() 
	{
		return player;
	}

	@Override
	public Vendor getVendor()
	{
		return vendor;
	}
	
	@Override
	public Room getCurrentVendorRoom() 
	{
		return currentVendorRoom;
	}

	@Override
	public Room[][] getRooms()
	{
		return rooms;
	}
	
	@Override
	public Boolean[][] getRoomsSeen()
	{
		return roomsSeen;
	}
	
	public void addTurn() 
	{
		turns++;
		if (turns == turnsLimit) //The Player will grow weaker for each turn he takes after max turns
		{
			addDialogue("You have been in the cave for too long, gas has been released and killing you");
		}
		if (turns > turnsLimit) 
		{
			player.takeDamageFromGas((turns - turnsLimit) * 2);
			if (player.getHealth() <= 0) 
			{
				lose();
			}
		}
	}

	@Override
	public int getPoints() 
	{
		return points;
	}

	@Override
	public String getDialogue() 
	{
		return dialogue;
	}

	@Override
	public void addDialogue() 
	{
		dialogue += "\n";
	}

	@Override
	public void addDialogue(String string) 
	{
		if (dialogue.length() == 0)
		{
			dialogue += string;
		} else 
		{
			dialogue += "\n" + string;
		}
	}

	@Override
	public void createRooms()
	{
		Room lvl_1, lvl_2a, lvl_2b, lvl_2c, lvl_3a, lvl_3b, lvl_3c, lvl_4c, lvl_4ba, lvl_4bb, lvl_5c, lvl_2Boss;
		// Darkens all the rooms
		for (int i = 0; i < roomsSeen.length; i++)
		{
			for (int j = 0; j < roomsSeen[i].length; j++)
			{
				roomsSeen[i][j] = false;
			}
		}
		rooms[0][2] = new Room("in the boss room");
		rooms[1][0] = new Room("in level 3a");
		rooms[1][1] = new Room("in level 2a");
		rooms[1][2] = new Room("in level 1");
		rooms[1][3] = new Room("in level 2c");
		rooms[1][4] = new Room("in level 3c");
		rooms[2][2] = new Room("in level 2b");
		rooms[2][4] = new Room("in level 4c");
		rooms[3][1] = new Room("in level 4ba");
		rooms[3][2] = new Room("in level 3b");
		rooms[3][3] = new Room("in level 4bb");
		rooms[3][4] = new Room("in level 5c");
		
		lvl_2Boss = rooms[0][2];
		lvl_3a = rooms[1][0];
		lvl_2a = rooms[1][1];
		lvl_1 = rooms[1][2];
		lvl_2c = rooms[1][3];
		lvl_3c = rooms[1][4];
		lvl_2b = rooms[2][2];
		lvl_4c = rooms[2][4];
		lvl_4ba = rooms[3][1];
		lvl_3b = rooms[3][2];
		lvl_4bb = rooms[3][3];
		lvl_5c = rooms[3][4];
		
		lvl_1.setExit(lvl_2a);
		lvl_1.setExit(lvl_2b);
		lvl_1.setExit(lvl_2c);
		lvl_1.setExit(lvl_2Boss);
		
		lvl_2Boss.setExit(lvl_1);

		lvl_2a.setExit(lvl_3a);
		lvl_2a.setExit(lvl_1);

		lvl_3a.setExit(lvl_2a);

		lvl_2b.setExit(lvl_3b);
		lvl_2b.setExit(lvl_1);

		lvl_3b.setExit(lvl_4ba);
		lvl_3b.setExit(lvl_4bb);
		lvl_3b.setExit(lvl_2b);

		lvl_4ba.setExit(lvl_3b);
		
		lvl_4bb.setExit(lvl_3b);

		lvl_2c.setExit(lvl_3c);
		lvl_2c.setExit(lvl_1);

		lvl_3c.setExit(lvl_4c);
		lvl_3c.setExit(lvl_2c);

		lvl_4c.setExit(lvl_5c);
		lvl_4c.setExit(lvl_3c);

		lvl_5c.setExit(lvl_4c);
		
		// Others
		lvl_2Boss.hasBoss(true);
		lvl_2Boss.locked(true);
		lvl_5c.setTeleporter(lvl_1);
		lvl_5c.addItemToMonster(new Key(1));
		
	}
	
	/**
	 * The play method will be called when the main class starts. The game will
	 * run until you write quit in the console.
	 */
	@Override
	public void play() {
		printWelcome();
	}

	/**
	 * This method will print out when you start the game
	 */
	private void printWelcome() {
		addDialogue("You awaken, hearing only silence. Without knowing where you are, you look to the right, and see a torch besides a skull and a Sign, which says:");
		addDialogue("You're lost in the Dungeon of Zuul. You have to navigate through the rooms to find the exits.''");
		addDialogue("You smell a disgusting stench, and look forward and see a big dark-green creature looking angry towards you.");
		addDialogue("Your goal is now to move to the end of the map. At the end of the map, you will meet the last boss, the Fallen Knight.");
		addDialogue("If you manage to defeat the boss, you will win, and escape this forgotten place.");
		addDialogue("But if your health reaches zero, you will lose and your corpse will forever be forgotten. Now, go!");
		printLook();
	}

	/* By writing "help" in console, this method will be called. */
	private void printLook() // Prints out what the character can see
	{
		addDialogue("You are " + currentRoom.getShortDescription());
		if (currentRoom.hasMonster()) {
			addDialogue("There's a monster level " + currentRoom.getMonster().getLevel() + " blocking your way");
		}
	}

	@Override
	public boolean useItem(Item searchForItem)
	{
		if(searchForItem instanceof Potion)
		{
			if(player.getCreaturesInventory().useItem(searchForItem))
			{
				addDialogue("You were healed for " + player.heal() + " HP (max 40% of your max health), and you lost " + player.getLevel()+ " points.");
				points -= player.getLevel()*2;
				return true;
			}
		}
		if(searchForItem instanceof GasMask)
		{
			if(player.getCreaturesInventory().useItem(searchForItem))
			{
				addDialogue("You prepared a mask and can now take on 5 more turns");
				turnsLimit += 5;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void buyItem(Item itemToBuy)
	{
		
		for (Item item : vendor.getCreaturesInventory().getContent())
		{
			if(item == itemToBuy)
			{
				if(player.getCreaturesInventory().useItem(player.getCreaturesInventory().searchItem(Coin.class), itemToBuy.getPrice()))
				{
					itemToBuy.clearPrice();
					currentRoom.getRoomsInventory().add(itemToBuy);
					vendor.getCreaturesInventory().remove(itemToBuy);
					addDialogue("Vendor> Splendid! I put it on the ground for you.");
					return;
				}
				else
				{
					addDialogue("Vendor> I'm sorry, but you don't seem to have the coin.");
					return;
				}
			}
		}
	}

	@Override
	public void attack()
	{
		attack(true);
	}

	@Override
	public void flee()
	{
		attack(false);
	}

	@Override
	public void attack(boolean wantToStay)
	{
		if (currentRoom.hasMonster())
		{
			if(!wantToStay)
			{
				if(Math.random() <= 0.5) // roll to check your flee success (0.5 = 50% chance)
				{
					addDialogue("Your attempt to flee was successful!");
					if(lastRoom == null)
					{
						addDialogue("... although, you have no place you run, you find a shiny new stick laying around");
						currentRoom.getRoomsInventory().add(new Weapon("GASPOWERED STICK", 1));
					}
					else
					{
						currentRoom = lastRoom;
					}
					lastRoom = currentRoom;
					return;
				}
				else
				{
					addDialogue("The monster caught you fleeing");
				}
			}
			else
			{
				int playerRolled = player.rollDamage(currentRoom.getMonster());
				if (playerRolled == 0)
				{
					addDialogue("Monster blocked your attack");
				}
				else
				{
					addDialogue("You rolled " + playerRolled + " dmg");
				}
			}

			if (currentRoom.getMonster().getHealth() > 0)
			{
				int monsterRolled = currentRoom.getMonster().rollDamage(player);
				if (monsterRolled == 0)
				{
					addDialogue("You blocked the monsters attack");
				}
				else
				{
					addDialogue("The monster rolled " + monsterRolled + " dmg");
				}

				if (player.getHealth() <= 0)
				{
					lose();
				}
			}
			else
			{
				if(currentRoom.getBoss())
				{
					player.gainExperience(currentRoom.getMonster());
					for (Item item : currentRoom.getMonster().getCreaturesInventory().getContent())
					{
						currentRoom.getRoomsInventory().add(item);
					}
					currentRoom.setMonsterToNull();
					points += player.getLevel()*5;
					addDialogue("You have killed the last boss, and escaped the Dungeon of Zuul, thanks for playing.\nPlease exit the game.");
				}				
				else
				{
					player.gainExperience(currentRoom.getMonster());
					for (Item item : currentRoom.getMonster().getCreaturesInventory().getContent())
					{
						currentRoom.getRoomsInventory().add(item);
					}
					currentRoom.setMonsterToNull();
					points += player.getLevel()*5;
					addDialogue("You have slain the monster!");
				}
				
				// Certain special events after slaying a monster

			} 
		}
		else
		{
			addDialogue("There's no monster to attack");
		}
	}
	
	@Override
	public void goRoom(int[] direction)
	{
		if (currentRoom.hasMonster())
		{
			addDialogue("The monster (level " + currentRoom.getMonster().getLevel() + "): is blocking your way");
		}
		else
		{
			// get the room the directions are pointing at if any
			Room nextRoom = null;
			int[] newPosition = {getPlayerPosition()[0] + direction[0], getPlayerPosition()[1] + direction[1]};
			
			if(newPosition[0] >= 0 && newPosition[0] < rooms.length && newPosition[1] >= 0 && newPosition[1] < rooms[0].length) // make sure not to exceed limits
			{
				nextRoom = rooms[getPlayerPosition()[0] + direction[0]][getPlayerPosition()[1] + direction[1]];
			}
			
			if (currentRoom.hasExit(nextRoom)) // It will first check if the door exists
			{
				// See if the door is locked or not
				if(nextRoom.getLocked())
				{
					// Searches for items that have key as class and then uses it if possible, this also checks to see if it was successful to use the item
					if(player.getCreaturesInventory().useItem(player.getCreaturesInventory().searchItem(Key.class)))
					{
						nextRoom.locked(false);
						addDialogue("You've unlocked the door with your key"); // You can now enter the room!
					}
					else
					{
						addDialogue("The door seem to be locked, obtain a key to open it"); // If the door is locked
					}
				}
				else
				{
					lastRoom = currentRoom;
					currentRoom = nextRoom;
					goVendor();
					printLook(); // It will give you a description of what's in the room
					addTurn();
				}
			}
			else
			{
				addDialogue("There is no door!"); // If there is no path to the next room, the game will tell you that you can't go that way.
			}
                        
		}
	}
        
	
	/**
	 * Makes the vendor walk "again", vendor cannot move outside a locked
	 * room, nor can he move into locked rooms.
	 * The vendor travels at random exits, so he might go forward and back
	 * between two of the same rooms, but that's okay,
	 * he may be a bit silly but he's our silly!
	 */
	private void goVendor()
	{
		if (currentVendorRoom != currentRoom && !currentVendorRoom.getLocked()) // make sure the player isn't in the same room and the room itself is unlocked
		{
			int index = new Random().nextInt(currentVendorRoom.getExits().size()); // randomizes which exit to use
			Room roomToGo = currentVendorRoom.getExits().get(index); // selects the room from the exit
			if (!roomToGo.getLocked()) // Make sure the vendor can't go in locked rooms
			{
				currentVendorRoom = roomToGo; // goto that room
			}
		}
	}
	
	@Override
	public int[] getPlayerPosition()
	{
		// Make predictions and find the current room's position
		for (int i = 0; i < rooms.length; i++)
		{
			for (int j = 0; j < rooms[i].length; j++)
			{
				if(rooms[i][j] == currentRoom)
				{
					// Set the room as visible
					roomsSeen[i][j] = true;
					
					// Set position on the map
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
	
	@Override
	public int[] getVendorPosition()
	{
		// Make predictions and find the current room's position
		for (int i = 0; i < rooms.length; i++)
		{
			for (int j = 0; j < rooms[i].length; j++)
			{
				if(rooms[i][j] == currentVendorRoom)
				{
					// Set position on the map
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
	
	@Override
	public void useTeleporter()
	{
		if(currentRoom.getTeleporter() != null && currentRoom.getMonster() == null) // is there even a teleporter and isthere a monster blocking?
		{
			addTurn();
			lastRoom = currentRoom;
			currentRoom = currentRoom.useTeleporter(); // teleport and destroy teleporter
		}
	}
	
	public void lose()
	{
		// What to do if you lose
		addDialogue("You died, thanks for playing.");
	}
}

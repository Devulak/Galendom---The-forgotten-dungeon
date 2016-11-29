package main;

import main.creature.*;
import java.util.*;
import main.item.*;

public class Game implements GameInterface {

	private int points;
	private int turns;
	private int turnsLimit;
	private String dialogue = "";
	private Player player;
	private Room currentRoom;
	private Room lastRoom;
	private Vendor vendor = new Vendor(0);
	private Room currentVendorRoom;
	private int score = 0;
	private Room lvl_1, lvl_2, lvl_2a, lvl_3, lvl_3a, lvl_4, lvl_4a, lvl_5, lvl_5a, lvl_6, lvl_7, lvl_8;
	private Room[][] rooms = new Room[3][4];
	private Boolean[][] roomsSeen = new Boolean[3][4];

	public Game() 
	{
		createRooms();
		turnsLimit = 20;
		currentRoom = lvl_1; //The player will start in this room
		currentVendorRoom = lvl_5a; //The vendor will start in this room
	}

	public int getTurns() 
	{
		return turns;
	}

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
			player.takeDamage((turns - turnsLimit) ^ 2);
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
		// Darkens all the rooms
		for (int i = 0; i < roomsSeen.length; i++)
		{
			for (int j = 0; j < roomsSeen[i].length; j++)
			{
				roomsSeen[i][j] = false;
			}
		}
		
		/* Adds rooms to the game, also gives them descriptions */
		rooms[1][0] = new Room("in level 1");
		rooms[2][0] = new Room("in level 2");
		rooms[0][0] = new Room("in level 2a");
		rooms[2][1] = new Room("in level 3");
		rooms[0][1] = new Room("in level 3a");
		rooms[2][2] = new Room("in level 4");
		rooms[1][1] = new Room("in level 4a");
		rooms[1][2] = new Room("in level 5");
		rooms[2][3] = new Room("in level 5a");
		rooms[0][2] = new Room("in level 6");
		rooms[0][3] = new Room("in level 7");
		rooms[1][3] = new Room("in level 8");
		
		lvl_1 = rooms[1][0];
		lvl_2 = rooms[2][0];
		lvl_2a = rooms[0][0];
		lvl_3 = rooms[2][1];
		lvl_3a = rooms[0][1];
		lvl_4 = rooms[2][2];
		lvl_4a = rooms[1][1];
		lvl_5 = rooms[1][2];
		lvl_5a = rooms[2][3];
		lvl_6 = rooms[0][2];
		lvl_7 = rooms[0][3];
		lvl_8 = rooms[1][3];

		// Adds creatures to the game, number tells what level they should start at
		player = new Player(1);
		
		// Give creatures some items that they drop
		// Monsters
        lvl_1.setMonster(new Monster(1));
        lvl_2.setMonster(new Monster(2));
        lvl_2a.setMonster(new Monster(2));
        lvl_3.setMonster(new Monster(3));
        lvl_3a.setMonster(new Monster(3));
        lvl_4.setMonster(new Monster(4));
        lvl_4a.setMonster(new Monster(4));
        lvl_5.setMonster(new Monster(5));
        lvl_5a.setMonster(new Monster(5));
        lvl_6.setMonster(new Monster(7));
        lvl_7.setMonster(new Monster(7));
        lvl_8.setMonster(new Monster(11));
        	
        // Give creatures some items that they drop

		//For testing the game and skipping the start fast, this will give you strength like room 5 (the middle of the game). Remember to change start level to 6.
		//hero.inventory.add(new Weapon("iron_sword", 5, 7)); // Iron Sword
		//hero.inventory.add(new Shield("steel_shield", 4,0)); // Steel Shield
		//hero.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
		//hero.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
		//hero.inventory.add(new Legging("steel_leggings", 3)); // Steel Legging
		//hero.inventory.add(new Boot("steel_boots", 2)); // Steel Boot
                
		// Others
		lvl_5a.locked(true);
		lvl_4a.setTeleporter(lvl_1);
		lvl_4a.addItemToMonster(new Key(1));

		// This gives the player the option to move between the rooms
		lvl_1.setExit(lvl_2);
		lvl_1.setExit(lvl_2a);

		lvl_2.setExit(lvl_3);
		lvl_2.setExit(lvl_1);

		lvl_2a.setExit(lvl_3a);
		lvl_2a.setExit(lvl_1);

		lvl_3.setExit(lvl_4);
		lvl_3.setExit(lvl_2);

		lvl_3a.setExit(lvl_4a);
		lvl_3a.setExit(lvl_2a);

		lvl_4.setExit(lvl_5);
		lvl_4.setExit(lvl_5a);
		lvl_4.setExit(lvl_3);

		lvl_4a.setExit(lvl_3a);

		lvl_5.setExit(lvl_6);
		lvl_5.setExit(lvl_4);

		lvl_5a.setExit(lvl_4);

		lvl_6.setExit(lvl_7);
		lvl_6.setExit(lvl_5);

		lvl_7.setExit(lvl_8);
		lvl_7.setExit(lvl_6);
		
		lvl_8.setExit(lvl_7);
		
		// Potions
		player.getCreaturesInventory().add(new Potion(5)); // x5 health potions
		lvl_3a.addItemToMonster(new Potion(5)); // x5 health potions
		lvl_2.addItemToMonster(new Potion(2)); // x2 health potions
		lvl_3.addItemToMonster(new Potion(2)); // x2 health potions
		lvl_4.addItemToMonster(new Potion(2)); // x2 health potions
		lvl_5a.addItemToMonster(new Potion(3)); // x3 health potions
		lvl_7.addItemToMonster(new Potion(5)); // x5 health potions
		
		// Coins
		lvl_2a.addItemToMonster(new Coin(2)); // 2 coins
		lvl_3a.addItemToMonster(new Coin(3)); // 3 coins
		lvl_4a.addItemToMonster(new Coin(4)); // 4 coins
		lvl_2.addItemToMonster(new Coin(5)); // 5 coins
		lvl_3.addItemToMonster(new Coin(6)); // 6 coins
		lvl_4.addItemToMonster(new Coin(7)); // 7 coins
		lvl_5a.addItemToMonster(new Coin(8)); // 8 coins
		
		//Vendor
		vendor.getCreaturesInventory().add(new Shield("Steel Shield", 6, 15)); //Steel Shield from Vendor, 15 coins
		vendor.getCreaturesInventory().add(new Helmet("Steel Helmet", 7, 5)); //Steel Helmet from Vendor, 5 coins
		vendor.getCreaturesInventory().add(new Chestplate("Steel Armour", 8, 20)); //Steel Chestplate from Vendor, 20 coins
		vendor.getCreaturesInventory().add(new Legging("Steel Leggings", 7, 10)); //Steel Leggings from Vendor, 10 coins
		vendor.getCreaturesInventory().add(new Boot("Steel Boots", 5, 5)); //Steel Boots from Vendor, 5 coins
		vendor.getCreaturesInventory().add(new GasMask(5, 5)); //Gassmask
		
		// Weapons
		player.getCreaturesInventory().add(new Weapon("Wooden Sword", 1)); //Wooden Sword, which the player has in the start of the game                
		lvl_4.addItemToMonster(new Weapon("Iron Sword", 3)); //Iron Sword
		lvl_5a.addItemToMonster(new Weapon("Iron Sword", 3)); //Iron Sword
		lvl_5.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_6.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_7.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_8.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword
                
		
		// Shields
		lvl_1.addItemToMonster(new Shield("Wooden Shield", 2)); //Wooden Shield
		lvl_4.addItemToMonster(new Shield("Iron Shield", 4)); //Iron Shield
		lvl_8.addItemToMonster(new Shield("Steel Shield", 6)); //Steel Shield
		
		// Helmets
		lvl_2a.addItemToMonster(new Helmet("Leather Helmet", 2)); //Leather Helmet
		lvl_3a.addItemToMonster(new Helmet("Iron Helmet", 5 )); //Iron Helmet
		lvl_5a.addItemToMonster(new Helmet("Iron Helmet", 5 )); //Iron Helmet
		lvl_6.addItemToMonster(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		lvl_7.addItemToMonster(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		lvl_8.addItemToMonster(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		
		// Chestplates
		lvl_2a.addItemToMonster(new Chestplate("Leather Armour", 4)); //Leather Chestplate
		lvl_4.addItemToMonster(new Chestplate("Iron Armour", 6)); //Iron Chestplate
		lvl_5a.addItemToMonster(new Chestplate("Iron Armour", 6)); //Iron Chestplate                               
		lvl_8.addItemToMonster(new Chestplate("Steel Armour", 8)); //Steel Chestplate
		
		// Leggings
		lvl_2.addItemToMonster(new Legging("Leather Leggings", 3)); //Leather Leggings
		lvl_4a.addItemToMonster(new Legging("Iron Leggings", 5)); //Iron Leggings
		lvl_5a.addItemToMonster(new Legging("Iron Leggings", 5)); //Iron Leggings                             
		lvl_7.addItemToMonster(new Legging("Steel Leggings", 7)); //Steel Leggings
		lvl_8.addItemToMonster(new Legging("Steel Leggings", 7)); //Steel Leggings
		
		// Boots          
		lvl_2.addItemToMonster(new Boot("Leather Boots", 1)); //Leather Boots
		lvl_3.addItemToMonster(new Boot("Iron Boots", 3)); //Iron Boots
		lvl_5a.addItemToMonster(new Boot("Iron Boots", 3)); //Iron Boots
		lvl_5.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_6.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_7.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_8.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		/* placeholder to outcomment all above in 1 line */
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
	
	protected void buyItem(Item itemToBuy)
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

	public void attack()
	{
		attack(true);
	}

	public void flee()
	{
		attack(false);
	}

	public void attack(boolean wantToStay)
	{
		if (currentRoom.hasMonster())
		{
			if(!wantToStay)
			{
				if(Math.random() <= 0.5) // roll to check your flee success (0.5 = 50% chance)
				{
					addDialogue("Your atempt to flee was successful!");
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
				player.gainExperience(currentRoom.getMonster());
				for (Item item : currentRoom.getMonster().getCreaturesInventory().getContent())
				{
					currentRoom.getRoomsInventory().add(item);
				}
				currentRoom.setMonsterToNull();
				points += player.getLevel()*5;
				
				// Certain special events after slaying a monster
				if(currentRoom == lvl_8)
				{
					addDialogue("You have killed the last boss, and escaped the Dungeon of Zuul, thanks for playing.\nPlease exit the game.");
				}
				else
				{
					addDialogue("You have slain the monster!");
				}
				if (currentRoom == lvl_7) // Boss ahead
				{
					addDialogue("You see something big moving in the shadows ahead.");
				}
			}
		}
		else
		{
			addDialogue("There's no monster to attack");
		}
	}
	
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

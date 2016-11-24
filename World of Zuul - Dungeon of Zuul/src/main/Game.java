package main;

import main.creature.*;
import java.util.*;
import main.item.*;

public class Game implements GameInterface
{
	private int points;
	private int turns;
	private String dialogue = "";
	private Player player;
	private Room currentRoom;
	private Room lastRoom;
	private Creature vendor;
	private Room currentVendorRoom;
	private int score = 0;
	private Room lvl_1, lvl_2, lvl_2a, lvl_3, lvl_3a, lvl_4, lvl_4a, lvl_5, lvl_5a, lvl_6, lvl_7, lvl_8;
	private Room[][] rooms = new Room[3][4];
	
	public Game()
	{
		createRooms();
		currentRoom = lvl_1; //The player will start in this room
		currentVendorRoom = lvl_5a; //The vendor will start in this room
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
	public Room getCurrentVendorRoom()
	{
		return currentVendorRoom;
	}
	
	public int getTurns()
	{
		return turns;
	}

	@Override
	public void addTurn()
	{
		turns++;
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
		if(dialogue.length() == 0)
		{
			dialogue += string;
		}
		else
		{
			dialogue += "\n" + string;
		}
	}

	@Override
	public void createRooms()
	{
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
		
		/* Adds creatures to the game, number tells what level they should start at  */
		player = new Player(100);
		
		// Give creatures some items that they drop
		// Potions
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
        lvl_8.setMonster(new Boss(11));
        	
        // Give creatures some items that they drop
		// Others
		lvl_5a.locked(true);
		lvl_4a.setTeleporter(lvl_1);
		lvl_4a.addItemToMonster(new Key("Key", 1));

		/* This gives the player the option to move between the rooms */
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

		player.getCreaturesInventory().add(new Potion(5)); // x5 health potions
		lvl_3a.addItemToMonster(new Potion(5)); // x5 health potions
		lvl_3.addItemToMonster(new Potion(4)); // x4 health potions
		lvl_4.addItemToMonster(new Potion(5)); // x5 health potions
		lvl_5a.addItemToMonster(new Potion(5)); // x5 health potions
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
		lvl_5a.getRoomsInventory().add(new Shield("Steel Shield", 6)); //Steel Shield from Vendor
		lvl_5a.getRoomsInventory().add(new Helmet("Steel Helmet", 7)); //Steel Helmet from Vendor
		lvl_5a.getRoomsInventory().add(new Armour("Steel Armour", 8)); //Steel Chestplate from Vendor
		lvl_5a.getRoomsInventory().add(new Legging("Steel Leggings", 7)); //Steel Leggings from Vendor
		lvl_5a.getRoomsInventory().add(new Boot("Steel Boots", 5)); //Steel Boots from Vendor                
                                                              
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
		lvl_2a.addItemToMonster(new Armour("Leather Armour", 4)); //Leather Chestplate
		lvl_4.addItemToMonster(new Armour("Iron Armour", 6)); //Iron Chestplate
		lvl_5a.addItemToMonster(new Armour("Iron Armour", 6)); //Iron Chestplate                               
		lvl_8.addItemToMonster(new Armour("Steel Armour", 8)); //Steel Chestplate
                
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
                
		//Skip to lvl_6, which is mid of the game(set start level to 6 and player level to level 5)
		//player.inventory.add(new Weapon("Steel Sword", 5));
		//player.inventory.add(new Shield("Steel Shield", 6));
		//player.inventory.add(new Helmet("Iron Helmet", 5 ));
		//player.inventory.add(new Armour("Steel Armour", 8));
		//player.inventory.add(new Legging("Iron Leggings", 5));
		//player.inventory.add(new Boot("Iron Boots", 3));
		//player.inventory.add(new Boot("Steel Boots", 5));
		//player.inventory.add(new Weapon("Steel Sword", 5));
	}
	
	/**
	 * The play method will be called when the main class starts. The game will
	 * run until you write quit in the console.
	 */
	@Override
	public void play()
	{
		printWelcome();
	}

	/**
	 * This method will print out when you start the game
	 */
	private void printWelcome()
	{
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
		if (currentRoom.hasMonster())
		{
			addDialogue("There's a monster level " + currentRoom.getMonster().getLevel() + " blocking your way");
		}
	}
	public void useItem(Item searchForItem)
	{
		for(Iterator<Item> it = player.getCreaturesInventory().getContent().iterator(); it.hasNext();)
		{
			Item item = it.next();
			if (item.equals(searchForItem) && item.getAmount() > 0 && item instanceof Potion)
			{
				if (item.getAmount() == 1)
				{
					it.remove();
				}
				else
				{
					item.use();
				}
				addDialogue("You were healed for " + player.heal() + " HP (max 40% of your max health)");
				return;
			}
			else if (item.equals(searchForItem) && item instanceof Key)
			{
				for(Iterator<Room> rooms = currentRoom.getExits().iterator(); rooms.hasNext();)
				{
					Room room = rooms.next(); // It's like a copy of the element
					if(room.getLocked())
					{
						if (item.getAmount() == 1)
						{
							it.remove();
						}
						else
						{
							item.use();
						}
						room.locked(false);
						addDialogue("You've unlocked the door with your key"); // If the door is locked
					}
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
					player = null;
				}
			}
			else
			{
				player.gainExperience(currentRoom.getMonster());
				for (Item item : currentRoom.getMonster().getCreaturesInventory().getContent())
				{
					currentRoom.getRoomsInventory().add(item);
				}
				score += currentRoom.getMonster().givePlayerPoints();
				currentRoom.setMonsterToNull();
				addDialogue("You have slain the monster!");
				System.out.println(score);
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
					addDialogue("The door seem to be locked, obtain a key to open it"); // If the door is locked
				}
				else
				{
					addTurn();
					lastRoom = currentRoom;
					currentRoom = nextRoom;
					printLook(); // It will give you a description of what's in the room
				}
			}
			else
			{
				addDialogue("There is no door!"); // If there is no path to the next room, the game will tell you that you can't go that way.
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
}
package main;

import java.util.*;
import main.item.*;

public class Game
{
	private int points =0;
	private int turns;
	private String dialogue = "";
	protected Creature player;
	protected Room currentRoom;
	protected Room lastRoom;
	protected Creature vendor = new Creature(0);
	protected Room currentVendorRoom;	
	protected Room lvl_1, lvl_2, lvl_2a, lvl_3, lvl_3a, lvl_4, lvl_4a, lvl_5, lvl_5a, lvl_6, lvl_7, lvl_8;
	protected Room[][] rooms = new Room[3][4];
	
	public Game()
	{
		createRooms();
		currentRoom = lvl_1; //The player will start in this room
		currentVendorRoom = lvl_5a; //The vendor will start in this room
	}

	public int getTurns()
	{
		return turns;
	}

	public void addTurn()
	{
		turns++;
	}

	public int getPoints()
	{
              
              return points;
        }
        
        

	public String getDialogue()
	{
		return dialogue;
	}

	public void addDialogue()
	{
		dialogue += "\n";
	}

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
		player = new Creature(1);
		
		// Give creatures some items that they drop
		// Potions
        lvl_1.setMonster(new Creature(1));
        lvl_2.setMonster(new Creature(2));
        lvl_2a.setMonster(new Creature(2));
        lvl_3.setMonster(new Creature(3));
        lvl_3a.setMonster(new Creature(3));
        lvl_4.setMonster(new Creature(4));
        lvl_4a.setMonster(new Creature(4));
        lvl_5.setMonster(new Creature(5));
        lvl_5a.setMonster(new Creature(5));
        lvl_6.setMonster(new Creature(7));
        lvl_7.setMonster(new Creature(7));
        lvl_8.setMonster(new Creature(11));
        	
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
		lvl_4a.monster.inventory.add(new Key("Key", 1));

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
		
                // Potions
		player.inventory.add(new Potion(5)); // x5 health potions
		lvl_3a.monster.inventory.add(new Potion(5)); // x5 health potions
                lvl_2.monster.inventory.add(new Potion(2)); // x2 health potions
		lvl_3.monster.inventory.add(new Potion(2)); // x2 health potions
		lvl_4.monster.inventory.add(new Potion(2)); // x2 health potions
		lvl_5a.monster.inventory.add(new Potion(3)); // x3 health potions
		lvl_7.monster.inventory.add(new Potion(5)); // x5 health potions
		
		// Coins
		lvl_2a.monster.inventory.add(new Coin(2)); // 2 coins
		lvl_3a.monster.inventory.add(new Coin(3)); // 3 coins
		lvl_4a.monster.inventory.add(new Coin(4)); // 4 coins
		lvl_2.monster.inventory.add(new Coin(5)); // 5 coins
		lvl_3.monster.inventory.add(new Coin(6)); // 6 coins
		lvl_4.monster.inventory.add(new Coin(7)); // 7 coins
		lvl_5a.monster.inventory.add(new Coin(8)); // 8 coins
		
		//Vendor
		vendor.inventory.add(new Shield("Steel Shield", 6)); //Steel Shield from Vendor, 15 coins
		vendor.inventory.add(new Helmet("Steel Helmet", 7)); //Steel Helmet from Vendor, 5 coins
		vendor.inventory.add(new Armour("Steel Armour", 8)); //Steel Chestplate from Vendor, 20 coins
		vendor.inventory.add(new Legging("Steel Leggings", 7)); //Steel Leggings from Vendor, 10 coins
		vendor.inventory.add(new Boot("Steel Boots", 5)); //Steel Boots from Vendor, 5 coins     
		
		// Weapons
		player.inventory.add(new Weapon("Wooden Sword", 1)); //Wooden Sword, which the player has in the start of the game
		lvl_4.monster.inventory.add(new Weapon("Iron Sword", 3)); //Iron Sword
		lvl_5a.monster.inventory.add(new Weapon("Iron Sword", 3)); //Iron Sword
		lvl_5.monster.inventory.add(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_6.monster.inventory.add(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_7.monster.inventory.add(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_8.monster.inventory.add(new Weapon("Steel Sword", 5)); //Steel Sword
		
		// Shields
		lvl_1.monster.inventory.add(new Shield("Wooden Shield", 2)); //Wooden Shield
		lvl_4.monster.inventory.add(new Shield("Iron Shield", 4)); //Iron Shield
		lvl_8.monster.inventory.add(new Shield("Steel Shield", 6)); //Steel Shield
		
		// Helmets
		lvl_2a.monster.inventory.add(new Helmet("Leather Helmet", 2)); //Leather Helmet
		lvl_3a.monster.inventory.add(new Helmet("Iron Helmet", 5 )); //Iron Helmet
		lvl_5a.monster.inventory.add(new Helmet("Iron Helmet", 5 )); //Iron Helmet
		lvl_6.monster.inventory.add(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		lvl_7.monster.inventory.add(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		lvl_8.monster.inventory.add(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		
		// Chestplates
		lvl_2a.monster.inventory.add(new Armour("Leather Armour", 4)); //Leather Chestplate
		lvl_4.monster.inventory.add(new Armour("Iron Armour", 6)); //Iron Chestplate
		lvl_5a.monster.inventory.add(new Armour("Iron Armour", 6)); //Iron Chestplate                               
		lvl_8.monster.inventory.add(new Armour("Steel Armour", 8)); //Steel Chestplate
		
		// Leggings
		lvl_2.monster.inventory.add(new Legging("Leather Leggings", 3)); //Leather Leggings
		lvl_4a.monster.inventory.add(new Legging("Iron Leggings", 5)); //Iron Leggings
		lvl_5a.monster.inventory.add(new Legging("Iron Leggings", 5)); //Iron Leggings                             
		lvl_7.monster.inventory.add(new Legging("Steel Leggings", 7)); //Steel Leggings
		lvl_8.monster.inventory.add(new Legging("Steel Leggings", 7)); //Steel Leggings
		
		// Boots          
		lvl_2.monster.inventory.add(new Boot("Leather Boots", 1)); //Leather Boots
		lvl_3.monster.inventory.add(new Boot("Iron Boots", 3)); //Iron Boots
		lvl_5a.monster.inventory.add(new Boot("Iron Boots", 3)); //Iron Boots
		lvl_5.monster.inventory.add(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_6.monster.inventory.add(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_7.monster.inventory.add(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_8.monster.inventory.add(new Boot("Steel Boots", 5)); //Steel Boots
		
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
	public void play()
	{
		printWelcome();
	}

	/**
	 * This method will print out when you start the game
	 */
	private void printWelcome()
	{
		addDialogue("You're lost in the Dungeon of Zuul.");
		addDialogue("Your goal is now to move to the end of the map. At the end of the map, you will meet the last boss.");
                addDialogue("If you dwell too long in the cave and exceed 25 turns, you will grow weak by the toxic gass and take more damage.");
		addDialogue("If you manage to defeat the boss, you will win.");
		addDialogue("But if your health reaches zero, you will lose!\n");
		printLook();
	}

	/* By writing "help" in console, this method will be called. */
	private void printHelp()
	{
		addDialogue();
		addDialogue("Your command words are:");
		addDialogue("go: This gives you the option to move around. The command is: go \"direction\".");
		addDialogue("map: The console will print out a map and your current whereabouts.");
		addDialogue("quit: The game will quit.");
		addDialogue("look: Look around in the room for items.");
		addDialogue("Status: Your healthpoints and XP");
		addDialogue("Inventory: Look through your inventory");
		addDialogue("Use: type use health_potion to use a potion from your inventory.");

		// parser.showCommands(); // This line prints out the command words.
	}

	private void printLook() // Prints out what the character can see
	{
		addDialogue("You are " + currentRoom.getShortDescription());
		if (currentRoom.hasMonster())
		{
			addDialogue("There's a monster level " + currentRoom.monster.getLevel() + " blocking your way");
		}
	}
	protected boolean useItem(Item searchForItem)
	{
		if(searchForItem instanceof Potion)
		{
			if(player.inventory.useItem(searchForItem))
			{
				addDialogue("You were healed for " + player.heal() + " HP (max 40% of your max health), and you lost " + player.level + " points." + " You score is now: " + points + " points.");
                                points -= player.level*2;
				return true;
			}
		}
		return false;
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
						currentRoom.inventory.add(new Weapon("GASPOWERED STICK", 1));
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
				int playerRolled = player.rollDamage(currentRoom.monster);
				if (playerRolled == 0)
				{
					addDialogue("Monster blocked your attack");
				}
				else
				{
					addDialogue("You rolled " + playerRolled + " dmg");
				}
			}

			if (currentRoom.monster.getHealth() > 0)
			{
				int monsterRolled = currentRoom.monster.rollDamage(player);
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
				player.gainExperience(currentRoom.monster);
				for (Item item : currentRoom.monster.inventory.getContent())
				{
					currentRoom.inventory.add(item);
				}
				currentRoom.monster = null;
				addDialogue("You have slain the monster!");
			}
		}
		else
		{
			addDialogue("There's no monster to attack");
		}   
                if(currentRoom.monster==null)
                {
                    points+=player.level*5;
                    addDialogue("You gained 10 points, your score is now: " + points + " points!");
                }
                         
                if(currentRoom.monster==null && currentRoom == lvl_8){
                    addDialogue("You have killed the last boss, and escaped the Dungeon of Zuul, thanks for playing.\nPlease exit the game.");
                }    
                if(player==null){
                    addDialogue("You died, thanks for playing.");
                    Runtime.getRuntime().exit(0);                     
                }
                 if (currentRoom==lvl_7 && currentRoom.monster==null){
                    addDialogue("You see something big moving in the shadows ahead.");
                }
                 if(turns>=1)//The Player will grow weaker for each turn he takes after max turns
                 {
                            player.health-=player.level;                            
                            addDialogue("You have been in the cave for too long, you have grown weaker by the toxic gass and take more damage.");
                 }                
        }
	
	public void goRoom(int[] direction)
	{
		if (currentRoom.hasMonster())
		{
			addDialogue("The monster (level " + currentRoom.monster.getLevel() + "): is blocking your way");
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
					if(player.inventory.useItem(player.inventory.searchItem(Key.class)))
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
					addTurn();
					lastRoom = currentRoom;
					currentRoom = nextRoom;
					goVendor();
					printLook(); // It will give you a description of what's in the room
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
        public void event()
        {
                   
        }
}
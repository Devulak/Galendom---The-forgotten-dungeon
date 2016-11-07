package main;

import java.util.Iterator;
import main.item.*;

public class Game {

    private Parser parser;
    protected Room currentRoom;
	private Creature hero;
       
        

    public Game() {
        createRooms();
        parser = new Parser();
    }

    public void createRooms() {
        /* Creating rooms */
        Room lvl_1, lvl_2, lvl_2a, lvl_3, lvl_3a, lvl_4, lvl_4a, lvl_5, lvl_5a, lvl_6, lvl_7, lvl_8;

        /* Adds rooms to the game, also gives them descriptions */
        lvl_1 = new Room("in level 1");
        lvl_2 = new Room("in level 2");
        lvl_2a = new Room("in level 2a");
        lvl_3 = new Room("in level 3");
        lvl_3a = new Room("in level 3a");
        lvl_4 = new Room("in level 4");
        lvl_4a = new Room("in level 4a");
        lvl_5 = new Room("in level 5");
        lvl_5a = new Room("in level 5a");
        lvl_6 = new Room("in level 6");
        lvl_7 = new Room("in level 7");
        lvl_8 = new Room("in level 8");

        /* Adds creatures to the game, number tells what level they should start at  */
		hero = new Creature(1);
        lvl_1.setMonster(new Creature(1));
        lvl_2.setMonster(new Creature(2));
        lvl_2a.setMonster(new Creature(2));
        lvl_3.setMonster(new Creature(3));
        lvl_3a.setMonster(new Creature(3));
        lvl_4.setMonster(new Creature(4));
        lvl_4a.setMonster(new Creature(4));
        lvl_5.setMonster(new Creature(5));
        lvl_5a.setMonster(new Creature(5));
        lvl_6.setMonster(new Creature(6));
        lvl_7.setMonster(new Creature(7));
        lvl_8.setMonster(new Creature(8));
        	
        // Give creatures some items that they drop
		
		// Potions
		hero.inventory.add(new Potion("health_potion", 3)); // x3 health potions
                lvl_3a.monster.inventory.add(new Potion("health_potion", 8)); // x6 health potions
		lvl_4.monster.inventory.add(new Potion("health_potion", 5)); // x5 health potions
                lvl_4a.monster.inventory.add(new Potion("health_potion", 3)); // x3 health potions
		lvl_5a.monster.inventory.add(new Potion("health_potion", 14)); // x14 health potions
                lvl_7.monster.inventory.add(new Potion("health_potion", 8)); // x8 health potions
                
		// Coins
		lvl_1.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                lvl_2a.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                lvl_3a.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                lvl_4a.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                lvl_2.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                lvl_3.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                lvl_4.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                lvl_5a.monster.inventory.add(new Coin("coins", 10)); // 10 coins
                
                //Vendor
                lvl_5a.inventory.add(new Shield("steel_shield", 4,0)); // Steel Shield
                lvl_5a.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
                lvl_5a.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
                lvl_5a.inventory.add(new Legging("steel_leggings", 3)); // Steel Legging
                lvl_5a.inventory.add(new Boot("steel_boots", 2)); // Steel Boot
                                              
		// Weapons
		hero.inventory.add(new Weapon("wooden_sword", 3, 7)); // Wooden Sword
                lvl_1.monster.inventory.add(new Weapon("broken_wooden_sword", 1, 2)); // Wooden Sword
                lvl_2.monster.inventory.add(new Weapon("broken_wooden_sword", 2, 4)); // Wooden Sword
                lvl_3.monster.inventory.add(new Weapon("broken_wooden_sword", 4, 6)); // Wooden Sword
                lvl_4.monster.inventory.add(new Weapon("broken_wooden_sword", 5, 7)); // Wooden Shield
                lvl_4a.monster.inventory.add(new Weapon("iron_sword", 5, 7)); // Iron Sword
                lvl_5a.monster.inventory.add(new Weapon("iron_sword", 5, 7)); // Iron Sword
                lvl_5.monster.inventory.add(new Weapon("demonic_steel_sword", 16, 18)); // Demonic Steel Sword
                lvl_6.monster.inventory.add(new Weapon("demonic_steel_sword", 16, 18)); // Demonic Steel Sword
                lvl_7.monster.inventory.add(new Weapon("holy_steel_sword", 18, 20)); // Holy Steel Sword
                lvl_8.monster.inventory.add(new Weapon("fallen_knight_sword", 20, 22)); // Holy Steel Sword
                
		// Shields
                lvl_2a.monster.inventory.add(new Shield("wooden_shield", 1,0)); // Wooden Shield
                lvl_4a.monster.inventory.add(new Shield("wooden_shield", 1,0)); // Wooden Shield
                lvl_5a.monster.inventory.add(new Shield("broken_steel_shield", 3,0)); // Steel Shield 
                lvl_8.monster.inventory.add(new Shield("steel_shield", 4,0)); // Steel Shield
                
                // Helmets
		lvl_3.monster.inventory.add(new Helmet("iron_helmet", 2)); // Iron Helmet
                lvl_6.monster.inventory.add(new Helmet("broken_steel_helmet", 2)); // Steel Helmet
                lvl_7.monster.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
                lvl_8.monster.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
                
		// Chestplates
		lvl_4a.monster.inventory.add(new Chestplate("iron_chestplate", 4)); // Iron Chestplate
                lvl_5a.monster.inventory.add(new Chestplate("broken_steel_chestplate", 4)); // Steel Chestplate
                lvl_5.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
                lvl_6.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
                lvl_7.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
                lvl_8.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
                
                // Leggings
		lvl_3a.monster.inventory.add(new Legging("iron_leggings", 2)); //Iron Leggings
                lvl_5a.monster.inventory.add(new Legging("broken_steel_leggings", 2)); //Steel Leggings
                lvl_5.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
		lvl_6.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
                lvl_7.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
                lvl_8.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
                
		// Boots                
                lvl_6.monster.inventory.add(new Boot("steel_boots", 2)); // Steel Boots
                lvl_7.monster.inventory.add(new Boot("steel_boots", 2)); // Steel Boots
                lvl_8.monster.inventory.add(new Boot("steel_boots", 2)); // Steel Boots
                
                //For testing the game and skipping the start fast, this will give you strength like room 5 (the middle of the game). Remember to change start level to 6.
                //hero.inventory.add(new Weapon("iron_sword", 5, 7)); // Iron Sword
		//hero.inventory.add(new Shield("steel_shield", 4,0)); // Steel Shield
                //hero.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
                //hero.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
                //hero.inventory.add(new Legging("steel_leggings", 3)); // Steel Legging
                //hero.inventory.add(new Boot("steel_boots", 2)); // Steel Boot                             
                 
        // This gives the player the option to move between the rooms
        lvl_1.setExit("left", lvl_2);
        lvl_1.setExit("right", lvl_2a);

        lvl_2.setExit("forward", lvl_3);
        lvl_2.setExit("back", lvl_1);

        lvl_2a.setExit("forward", lvl_3a);
        lvl_2a.setExit("back", lvl_1);

        lvl_3.setExit("forward", lvl_4);
        lvl_3.setExit("back", lvl_2);

        lvl_3a.setExit("forward", lvl_4a);
        lvl_3a.setExit("back", lvl_2a);

        lvl_4.setExit("right", lvl_5);
        lvl_4.setExit("left", lvl_5a);
        lvl_4.setExit("back", lvl_3);

        lvl_4a.setExit("back", lvl_3a);

        lvl_5.setExit("forward", lvl_6);
        lvl_5.setExit("back", lvl_4);

        lvl_5a.setExit("back", lvl_4);

        lvl_6.setExit("forward", lvl_7);
        lvl_6.setExit("back", lvl_5);

        lvl_7.setExit("forward", lvl_8);
        lvl_7.setExit("back", lvl_6);

        lvl_8.setExit("back", lvl_7);

        currentRoom = lvl_1; //The player will start in this room
        
        
    }
	
	/**
	 * The play method will be called when the main class starts. The game will
	 * run until you write quit in the console.
	 */
    public void play() {
        printWelcome();

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
	}

	/**
	 * This method will print out when you start the game 
	 */
    private void printWelcome() {
        System.out.println();
        System.out.println("You're lost in the dungeon of Zuul. You have to navigate through the dark rooms to find the exits.");
        System.out.println("Your goal is to move to the end of the map. At the end of the map, you will meet the last boss, the Fallen Knight.");
        System.out.println("If you manage to defeat the boss, you will win.");
        System.out.println("But if your health reaches zero, you will lose and your corpse will forever be forgotten. Now, go!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
		printLook();
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP)
		{
            printHelp();
        }
		else if (commandWord == CommandWord.MAP)
		{
            printMap();
        }
		else if (commandWord == CommandWord.GO)
		{
            goRoom(command);
        }
		else if (commandWord == CommandWord.QUIT)
		{
            wantToQuit = quit(command);
        }
		else if (commandWord == CommandWord.LOOK)
		{
			printLook();
        }
		else if (commandWord == CommandWord.STATUS)
		{
            printStatus();
        }
		else if (commandWord == CommandWord.INVENTORY)
		{
            printInventory();
        }
		else if (commandWord == CommandWord.USE)
		{
            useItem(command);
        }
		else if (commandWord == CommandWord.TAKE)
		{
            takeItem(command);
        }
		else if (commandWord == CommandWord.ATTACK)
		{
            combatAttack();
        }
        else if (commandWord == CommandWord.FLEE)
		{
            
        }
		if(hero == null)
		{
			System.out.println("You've been killed...");
                        System.out.println("Your corpse will now be used to guard the rooms against others, who may fall into the cave of Zuul...");
			wantToQuit = true;
		}
        return wantToQuit;
    }

    /* By writing "help" in console, this method will be called. */
    private void printHelp()
	{
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("go: This gives you the option to move around. The command is: go \"direction\".");
        System.out.println("map: The console will print out a map and your current whereabouts.");
        System.out.println("quit: The game will quit.");
        System.out.println("look: Look around in the room for items.");
        System.out.println("Status: Your healthpoints and XP");
        System.out.println("Inventory: Look through your inventory");
        System.out.println("Use: type use health_potion to use a potion from your inventory.");

        // parser.showCommands(); // This line prints out the command words.
    }
	
    private void printStatus() // Prints out the character specific things to check
	{
        System.out.println("Hero (" + hero.printLevel() + "):    " + hero.printHealth()); // Prints out the hero's health
        System.out.println("Experience points: " + hero.getExperienceBar()); // Prints out the hero's experience
    }
	
	private void printLook() // Prints out what the character can see
	{
		System.out.println("You are " + currentRoom.getShortDescription());
		if(currentRoom.hasMonster())
		{
			System.out.println("There's a monster " + currentRoom.monster.printLevel() + " blocking your way");
            System.out.println("Monster (" + currentRoom.monster.printLevel() + "): " + currentRoom.monster.printHealth());
		}
		else
		{
			System.out.println(currentRoom.getExitString());
		}
		currentRoom.inventory.printContent("Room items");
	}
	
    private void printInventory() // Prints out the hero's current inventory
	{
		hero.inventory.printContent("Your inventory");
    }
	
	/**
	 * This method will be called when you write "map" in console. /* It will
	 * print out the whole map and it will also include your current position.
	 */
	private void printMap()
	{
		System.out.println("/----------------------------------------------------------------------------------------\\");
		System.out.println("|                                              level 5a                                  |");
		System.out.println("|            level 2  -- level 3 -- level 4 -<                                           |");
		System.out.println("| level 1 -<                                   level 5 -- level 6 -- level 7 -- level 8  |");
		System.out.println("|            level 2a -- level 3a -- level 4a                                            |");
		System.out.println("\\----------------------------------------------------------------------------------------/");
	}
	
	private void useItem(Command command)
	{
        if (!command.hasSecondWord())
		{
            System.out.println("Use what?");
            return;
        }
		
		String searchName = command.getSecondWord();

		for (Iterator<Item> it = hero.inventory.getContent().iterator(); it.hasNext();)
		{
			Item item = it.next();
			if(item.getName().equals(searchName) && item.getAmount() > 0 && item instanceof Potion)
			{
				System.out.println("You used a " + item.getName());
				if(item.getAmount() == 1)
				{
					it.remove();
				}
				else
				{
					item.use();
				}
				hero.heal();
				return;
			}
		}
		
		System.out.println("That doesn't seem to be a usable item");
	}
	
	private void takeItem(Command command)
	{
        if (!command.hasSecondWord())
		{
            System.out.println("Take what?");
            return;
        }
		
		String searchName = command.getSecondWord();

		for (Iterator<Item> roomInventory = currentRoom.inventory.getContent().iterator(); roomInventory.hasNext();)
		{
			Item item = roomInventory.next();
			if(item.getName().equals(searchName))
			{
				System.out.println(item.getName() + " has been added to your inventory");
				
				// Make sure it's able to stack and throw stuff that can't stack
				// TO-DO: Rebuild with the new .add for Inventory objects
				for (Iterator<Item> heroInventory = hero.inventory.getContent().iterator(); heroInventory.hasNext();)
				{
					Item inventoryItem = heroInventory.next();
					if(inventoryItem.getClass() == item.getClass()) // Do the item taken match anything in the hero's inventory?
					{
						if(item.getAmount() > 0 && inventoryItem.getAmount() > 0) // Is the items stackable with eachother?
						{
							inventoryItem.addAmount(item.getAmount()); // transfer the current amount to the hero inventory
							roomInventory.remove(); // Remove the room item
							return; // stop looking
						}
						else // What do we do if it's not stackable? (Works as if the things got swapped or switched)
						{
							System.out.println(inventoryItem.getName() + " has been dropped, you don't have room to carry both");
							
							// Clone the items to the correct inventories
							hero.inventory.add(item);
							currentRoom.inventory.add(inventoryItem);
							
							// Remove the items from the old inventories
							heroInventory.remove();
							roomInventory.remove();
						}
					}
				}
				// END
				
				hero.inventory.add(item);
				roomInventory.remove();
				return;
			}
		}
		
		System.out.println("Can't find that in the room"); // These aren't the droids you're looking for
	}
	
    private void combatAttack()
	{
		if(currentRoom.hasMonster())
		{
			hero.attack(currentRoom.monster);
			
			System.out.println("Monster (" + currentRoom.monster.printLevel() + "): " + currentRoom.monster.printHealth());
			
			if(currentRoom.monster.health > 0)
			{
				currentRoom.monster.attack(hero);
				
				System.out.println("Hero (" + hero.printLevel() + "):    " + hero.printHealth());
				
				if(hero.health > 0)
				{
					System.out.println("Your turn!");
				}
				else
				{
					hero = null;
				}
			}
			else
			{
				System.out.println("You have slain the monster (" + currentRoom.monster.printLevel() + ")!");
				hero.gainExperience(currentRoom.monster);
				for (Item item : currentRoom.monster.inventory.getContent())
				{
					currentRoom.inventory.add(item);
				}
				currentRoom.monster = null;
			}
		}
		else
			System.out.println("There's no monster to attack");
    }

    /*
    private void dropWeapon(Command command) {
        String weapon = currentWeapon.getWeaponName();
        if (!hasWeapon) {
            System.out.println("You don't have any weapon to drop.");
        } else if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
        } else if (command.getSecondWord() == weapon) {
            currentWeapon = null;
            hasWeapon = false;
            System.out.println("Successfully dropped");
        } else {
            System.out.println("I'm not sure what you mean with " + command.getSecondWord() + ("."));
        }

    } */
    
	/* When you write "go" in console, this method will be called. */
    private void goRoom(Command command)
	{
        if (!command.hasSecondWord())
		{
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if(currentRoom.hasMonster())
		{
            System.out.println("The monster (" + currentRoom.monster.printLevel() + "): is blocking your way");
		}
		else if (nextRoom == null) // It will first check if there is a path to the next room.
		{
            System.out.println("There is no door!"); // If there is no path to the next room, the game will tell you that you can't go that way.
        }
		else
		{
            currentRoom = nextRoom;
			printLook(); // It will give you a description of what's in the room
        }
    }

/**
 * This method will run when you write quit in the console. It will first check
 * if you add a second word. Like "quit game", the game will ask you to quit what?
 * Otherwise the while loop in play() method will stop and the game will quit.
 */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}

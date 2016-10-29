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

    private void createRooms() {
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
		
        /* Give creatures some items that they may drop */
		
		// Potions
		lvl_1.monster.inventory.add(new Potion("health_potion", 4)); // 4x health potions
		
		// Coins
		hero.inventory.add(new Coin("coin", 20)); // 20 coins
		
		// Weapons
		hero.inventory.add(new Weapon("wooden_sword", 1, 2)); // Wooden Sword
		// lvl_2.Monster.inventory.add(new Weapon("iron_sword", 2, 4)); // Iron Sword
		// lvl_2a.Monster.inventory.add(new Weapon("steel_sword", 4, 8)); // Steel Sword
		
		// Shields
		hero.inventory.add(new Shield("wooden_shield", 1, 20)); // Wooden Shield
		
		// Helmets
		
		// Chestplates
		
		// Leggings
		
		// Boots

        /* This gives the player the option to move between the rooms */
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
        System.out.println("You're lost in a cave. You have to find the exit to win.");
        System.out.println("Your goal is to move to the end of the map. At the end of the map, you");
        System.out.println("will face the boss that you have to defeat. If you defeat the boss, you will");
        System.out.println("win, but if your health reaches zero, you will lose.");
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
		if(hero == null)
		{
			System.out.println("You've been killed and lost the game!");
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
			System.out.println("You these things layong on the ground");
			for (Item item : currentRoom.inventory)
			{
				if(item.getAmount() > 1)
				{
					System.out.println(item.getAmount() + "x " + item.getName() + "s");
				}
				else
				{
					System.out.println(item.getName());
				}
			}
			System.out.println(currentRoom.getExitString());
		}
	}
	
    private void printInventory() // Prints out the hero's current inventory
	{
		for (Item Item : hero.inventory)
		{
			if(Item.getAmount() > 1)
			{
				System.out.println(Item.getAmount() + "x " + Item.getName() + "s");
			}
			else
			{
				System.out.println(Item.getName());
			}
		}
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

		for (Iterator<Item> it = hero.inventory.iterator(); it.hasNext();)
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

		for (Iterator<Item> it = currentRoom.inventory.iterator(); it.hasNext();)
		{
			Item item = it.next();
			if(item.getName().equals(searchName))
			{
				System.out.println("You picked up " + item.getName());
				// Make sure it's able to stack and throw stuff that can't stack
				hero.inventory.add(item);
				it.remove();
				return;
			}
		}
		
		System.out.println("Can't find that in the room");
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
				for (Item item : currentRoom.monster.inventory)
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

package main;

import java.util.Iterator;
import main.item.*;

public class Game {

    protected Room currentRoom;
	protected Creature hero;

    public Game() {
        createRooms();
    }

    private void createRooms() {
        /* Creating rooms */
        Room lvl_1, lvl_2, lvl_2a, lvl_3, lvl_3a, lvl_4, lvl_4a, lvl_5, lvl_5a, lvl_6, lvl_7, lvl_8;

        /* Adds rooms to the game, also gives them descriptions */
        lvl_1 = new Room("in level 1", 1, 0);
        lvl_2 = new Room("in level 2", 2, 0);
        lvl_2a = new Room("in level 2a", 0, 0);
        lvl_3 = new Room("in level 3", 2, 1);
        lvl_3a = new Room("in level 3a", 0, 1);
        lvl_4 = new Room("in level 4", 2, 2);
        lvl_4a = new Room("in level 4a", 1, 1);
        lvl_5 = new Room("in level 5", 1, 2);
        lvl_5a = new Room("in level 5a", 2, 3);
        lvl_6 = new Room("in level 6", 0, 2);
        lvl_7 = new Room("in level 7", 0, 3);
        lvl_8 = new Room("in level 8", 1, 3);

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
		hero.inventory.add(new Potion("Health Potions", 4)); // 4x health potions
		
		// Coins
		hero.inventory.add(new Coin("Coins", 20)); // 20 coins
		
		// Weapons
		hero.inventory.add(new Weapon("Wooden Sword", 1, 2)); // Wooden Sword
		// lvl_2.Monster.inventory.add(new Weapon("iron_sword", 2, 4)); // Iron Sword
		// lvl_2a.Monster.inventory.add(new Weapon("steel_sword", 4, 8)); // Steel Sword
		
		// Shields
		lvl_1.monster.inventory.add(new Shield("Wooden Shield", 1, 20)); // Wooden Shield
		
		// Helmets
		
		// Chestplates
		
		// Leggings
		
		// Boots

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

        currentRoom = lvl_1; //The player will start in this room
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
    private void printWelcome() {
        System.out.println();
        System.out.println("You're lost in a cave. You have to find the exit to win.");
        System.out.println("Your goal is to move to the end of the map. At the end of the map, you");
        System.out.println("will face the boss that you have to defeat. If you defeat the boss, you will");
        System.out.println("win, but if your health reaches zero, you will lose.");
        System.out.println("Click on '?' if you need help.");
        System.out.println();
		printLook();
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
			System.out.println(currentRoom.getExitString());
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
	
	private void useItem(Item searchForItem)
	{
		for (Iterator<Item> it = hero.inventory.getContent().iterator(); it.hasNext();)
		{
			Item item = it.next();
			if(item.equals(searchForItem) && item.getAmount() > 0 && item instanceof Potion)
			{
				System.out.println("You used a " + item.toString());
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
		
		System.out.println("useItem didn't find '" + searchForItem + "'");
	}
	
    public void attack()
	{
		if(currentRoom.hasMonster())
		{
			hero.attack(currentRoom.monster);
			
			System.out.println("Monster (" + currentRoom.monster.printLevel() + "): " + currentRoom.monster.printHealth());
			
			if(currentRoom.monster.getHealth() > 0)
			{
				currentRoom.monster.attack(hero);
				
				System.out.println("Hero (" + hero.printLevel() + "):    " + hero.printHealth());
				
				if(hero.getHealth() > 0)
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
}
package main;

import java.util.Iterator;
import main.item.*;

public class Game {

<<<<<<< HEAD
	protected Room currentRoom;
	protected Creature hero;
=======
    protected Room currentRoom;
	protected Creature player;
>>>>>>> refs/remotes/origin/master
	protected String dialogue = "";
	protected int score = 0;

	public Game() {
		createRooms();
	}

	public void addDialogue() {
		dialogue += "\n";
	}

	public void addDialogue(String string) {
		dialogue += string + "\n";
	}

<<<<<<< HEAD
	public void createRooms() {
		boolean wantToQuit = false;
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
		hero = new Creature(20);
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

		lvl_5a.setDoor(new Door("Door"));

		// Give creatures some items that they drop
		// Potions
		hero.inventory.add(new Key("Key", 1));
		lvl_1.inventory.add(new Potion(3)); // x3 health potions
=======
    public void createRooms()
	{
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
		player = new Creature(1);
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
		player.inventory.add(new Potion(3)); // x3 health potions
>>>>>>> refs/remotes/origin/master
		lvl_3a.monster.inventory.add(new Potion(8)); // x6 health potions
		lvl_4.monster.inventory.add(new Potion(5)); // x5 health potions
		lvl_4a.monster.inventory.add(new Potion(3)); // x3 health potions
		lvl_5a.monster.inventory.add(new Potion(14)); // x14 health potions
		lvl_7.monster.inventory.add(new Potion(8)); // x8 health potions
<<<<<<< HEAD

=======
                
>>>>>>> refs/remotes/origin/master
		// Coins
		lvl_1.monster.inventory.add(new Coin(10)); // 10 coins
		lvl_2a.monster.inventory.add(new Coin(10)); // 10 coins
		lvl_3a.monster.inventory.add(new Coin(10)); // 10 coins
		lvl_4a.monster.inventory.add(new Coin(10)); // 10 coins
		lvl_2.monster.inventory.add(new Coin(10)); // 10 coins
		lvl_3.monster.inventory.add(new Coin(10)); // 10 coins
		lvl_4.monster.inventory.add(new Coin(10)); // 10 coins
		lvl_5a.monster.inventory.add(new Coin(10)); // 10 coins

		//Vendor
		lvl_5a.inventory.add(new Shield("steel_shield", 4, 0)); // Steel Shield
		lvl_5a.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
		lvl_5a.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
		lvl_5a.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
		lvl_5a.inventory.add(new Boot("steel_boots", 2)); // Steel Boots

		// Weapons
<<<<<<< HEAD
		lvl_1.inventory.add(new Weapon("Wooden Sword", 2)); // Wooden Sword
=======
		player.inventory.add(new Weapon("Wooden Sword", 2)); // Wooden Sword
>>>>>>> refs/remotes/origin/master
		lvl_1.monster.inventory.add(new Weapon("Broken Wooden Sword", 1)); // Wooden Sword
		lvl_2.monster.inventory.add(new Weapon("Broken Wooden Sword", 3)); // Wooden Sword
		lvl_3.monster.inventory.add(new Weapon("Broken Wooden Sword", 5)); // Wooden Sword
		lvl_4.monster.inventory.add(new Weapon("Broken Wooden Sword", 6)); // Wooden Shield
		lvl_4a.monster.inventory.add(new Weapon("iron_sword", 6)); // Iron Sword
		lvl_5a.monster.inventory.add(new Weapon("iron_sword", 6)); // Iron Sword
		lvl_5.monster.inventory.add(new Weapon("demonic_steel_sword", 17)); // Demonic Steel Sword
		lvl_6.monster.inventory.add(new Weapon("demonic_steel_sword", 17)); // Demonic Steel Sword
		lvl_7.monster.inventory.add(new Weapon("holy_steel_sword", 19)); // Holy Steel Sword
		lvl_8.monster.inventory.add(new Weapon("fallen_knight_sword", 21)); // Fallen Knight Sword
<<<<<<< HEAD

		// Shields
		lvl_2a.monster.inventory.add(new Shield("wooden_shield", 1, 0)); // Wooden Shield
		lvl_4a.monster.inventory.add(new Shield("wooden_shield", 1, 0)); // Wooden Shield
		lvl_5a.monster.inventory.add(new Shield("broken_steel_shield", 3, 0)); // Broken Steel Shield
		lvl_8.monster.inventory.add(new Shield("steel_shield", 4, 0)); // Steel Shield

		// Helmets
=======
                
		// Shields
		lvl_2a.monster.inventory.add(new Shield("wooden_shield", 1,0)); // Wooden Shield
		lvl_4a.monster.inventory.add(new Shield("wooden_shield", 1,0)); // Wooden Shield
		lvl_5a.monster.inventory.add(new Shield("broken_steel_shield", 3,0)); // Broken Steel Shield
		lvl_8.monster.inventory.add(new Shield("steel_shield", 4,0)); // Steel Shield
                
                // Helmets
>>>>>>> refs/remotes/origin/master
		lvl_3.monster.inventory.add(new Helmet("iron_helmet", 2)); // Iron Helmet
		lvl_6.monster.inventory.add(new Helmet("broken_steel_helmet", 2)); // Broken Steel Helmet
		lvl_7.monster.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
		lvl_8.monster.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
<<<<<<< HEAD

=======
                
>>>>>>> refs/remotes/origin/master
		// Chestplates
		lvl_4a.monster.inventory.add(new Chestplate("iron_chestplate", 4)); // Iron Chestplate
		lvl_5a.monster.inventory.add(new Chestplate("broken_steel_chestplate", 4)); // Broken Steel Chestplate
		lvl_5.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
		lvl_6.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
		lvl_7.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
		lvl_8.monster.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
<<<<<<< HEAD

=======
                
>>>>>>> refs/remotes/origin/master
		// Leggings
		lvl_3a.monster.inventory.add(new Legging("iron_leggings", 2)); // Iron Leggings
		lvl_5a.monster.inventory.add(new Legging("broken_steel_leggings", 2)); // Broken Steel Leggings
		lvl_5.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
		lvl_6.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
		lvl_7.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
		lvl_8.monster.inventory.add(new Legging("steel_leggings", 3)); // Steel Leggings
<<<<<<< HEAD

=======
                
>>>>>>> refs/remotes/origin/master
		// Boots                
		lvl_6.monster.inventory.add(new Boot("steel_boots", 2)); // Steel Boots
		lvl_7.monster.inventory.add(new Boot("steel_boots", 2)); // Steel Boots
		lvl_8.monster.inventory.add(new Boot("steel_boots", 2)); // Steel Boots
<<<<<<< HEAD

		//For testing the game and skipping the start fast, this will give you strength like room 5 (the middle of the game). Remember to change start level to 6.
		//hero.inventory.add(new Weapon("iron_sword", 5, 7)); // Iron Sword
		//hero.inventory.add(new Shield("steel_shield", 4,0)); // Steel Shield
		//hero.inventory.add(new Helmet("steel_helmet", 3)); // Steel Helmet
		//hero.inventory.add(new Chestplate("steel_chestplate", 5)); // Steel Chestplate
		//hero.inventory.add(new Legging("steel_leggings", 3)); // Steel Legging
		//hero.inventory.add(new Boot("steel_boots", 2)); // Steel Boot
=======
>>>>>>> refs/remotes/origin/master

		/* This gives the player the option to move between the rooms */
		lvl_1.setExit("right", lvl_2);
		lvl_1.setExit("left", lvl_2a);

		lvl_2.setExit("down", lvl_3);
		lvl_2.setExit("left", lvl_1);

		lvl_2a.setExit("down", lvl_3a);
		lvl_2a.setExit("right", lvl_1);

		lvl_3.setExit("down", lvl_4);
		lvl_3.setExit("up", lvl_2);

		lvl_3a.setExit("right", lvl_4a);
		lvl_3a.setExit("up", lvl_2a);

		lvl_4.setExit("left", lvl_5);
		lvl_4.setExit("down", lvl_5a);
		lvl_4.setExit("up", lvl_3);

		lvl_4a.setExit("left", lvl_3a);

		lvl_5.setExit("left", lvl_6);
		lvl_5.setExit("right", lvl_4);

		lvl_5a.setExit("up", lvl_4);

		lvl_6.setExit("down", lvl_7);
		lvl_6.setExit("right", lvl_5);

		lvl_7.setExit("right", lvl_8);
		lvl_7.setExit("up", lvl_6);

		lvl_8.setExit("left", lvl_7);

		currentRoom = lvl_1; //The player will start in this room

	}

	/**
	 * The play method will be called when the main class starts. The game will
	 * run until you write quit in the console.
	 */
	public void play() {
		printWelcome();
	}

	/**
	 * This method will print out when you start the game
	 */
	private void printWelcome() {
		addDialogue();
		addDialogue("You awaken, hearing only silence. Without knowing where you are, you look to the right, and see a torch besides a skull and a Sign, which says:");
		addDialogue("''You're lost in the Dungeon of Zuul. You have to navigate through the dark rooms to find the exits.''");
		addDialogue("You smell a disgusting stench, and look forward and see a big dark-green creature looking angry towards you.");
		addDialogue("Your goal is now to move to the end of the map. At the end of the map, you will meet the last boss, the Fallen Knight.");
		addDialogue("If you manage to defeat the boss, you will win, and escape this forgotten place.");
		addDialogue("But if your health reaches zero, you will lose and your corpse will forever be forgotten. Now, go!");
		addDialogue("You're lost in a cave. You have to find the exit to win.");
		addDialogue("Your goal is to move to the end of the map. At the end of the map, you");
		addDialogue("will face the boss that you have to defeat. If you defeat the boss, you will");
		addDialogue("win, but if your health reaches zero, you will lose.");
		addDialogue("Click on '?' if you need help.");
		addDialogue();
		printLook();
<<<<<<< HEAD
	}

	/* By writing "help" in console, this method will be called. */
	private void printHelp() {
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

=======
    }
	
>>>>>>> refs/remotes/origin/master
	private void printLook() // Prints out what the character can see
	{
		addDialogue("You are " + currentRoom.getShortDescription());
		if (currentRoom.hasMonster()) 
		{
			addDialogue("There's a monster level " + currentRoom.monster.getLevel() + " blocking your way");
		} 
		else 
		{
			addDialogue(currentRoom.getExitString());
		}
	}
<<<<<<< HEAD

	protected void useItem(Item searchForItem) {
		for (Iterator<Item> it = hero.inventory.getContent().iterator(); it.hasNext();) {
=======
	
	protected void useItem(Item searchForItem)
	{
		for (Iterator<Item> it = player.inventory.getContent().iterator(); it.hasNext();)
		{
>>>>>>> refs/remotes/origin/master
			Item item = it.next();
			if (item.equals(searchForItem) && item.getAmount() > 0 && item instanceof Potion) {
				if (item.getAmount() == 1) {
					it.remove();
				} else {
					item.use();
				}
				addDialogue("You were healed for " + hero.heal() + " HP (max 40% of your max health)");
				return;
			}
<<<<<<< HEAD
			else if(item.equals(searchForItem) && item instanceof Key)
			{
				if(currentRoom.getExit("down").hasLockedDoor())
				{
					currentRoom.getExit("down").unlockDoor();
					addDialogue("The door has been unlocked!");
					it.remove();
				}
				else
				{
					addDialogue("There is no door to unlock!");
				}
<<<<<<< HEAD
=======
				addDialogue("You were healed for " + player.heal() + " HP (max 40% of your max health)");
				return;
>>>>>>> refs/remotes/origin/master
			}
		
=======
>>>>>>> parent of 5bb50b7... Locked door V.0.2
		}
	}
<<<<<<< HEAD

	public void attack() {
		if (currentRoom.hasMonster()) {
			addDialogue("You rolled " + hero.rollDamage(currentRoom.monster) + "dmg");

			if (currentRoom.monster.getHealth() > 0) {
				addDialogue("Monster rolled " + currentRoom.monster.rollDamage(hero) + "dmg");

				if (hero.getHealth() <= 0) {
					hero = null;
				}
			} else {
				addDialogue("You have slain the monster (level " + currentRoom.monster.getLevel() + ")!");
				hero.gainExperience(currentRoom.monster);
				for (Item item : currentRoom.monster.inventory.getContent()) {
=======
	
    public void attack()
	{
		if(currentRoom.hasMonster())
		{
			addDialogue("You rolled " + player.rollDamage(currentRoom.monster) + "dmg");
			
			if(currentRoom.monster.getHealth() > 0)
			{
				addDialogue("Monster rolled " + currentRoom.monster.rollDamage(player) + "dmg");
				
				if(player.getHealth() <= 0)
				{
					player = null;
				}
			}
			else
			{
				player.gainExperience(currentRoom.monster);
				for (Item item : currentRoom.monster.inventory.getContent())
				{
>>>>>>> refs/remotes/origin/master
					currentRoom.inventory.add(item);
				}
				currentRoom.monster = null;
				addDialogue("You have slain the monster!");
			}
		} else {
			addDialogue("There's no monster to attack");
		}

	}

	public void goRoom(String direction) {
		Room nextRoom = currentRoom.getExit(direction);
		if (currentRoom.hasMonster()) 
		{
			addDialogue("The monster (level " + currentRoom.monster.getLevel() + "): is blocking your way");
		}
			else if(nextRoom.hasLockedDoor())
			{
			if(hero.hasKey()){
				addDialogue("Door unlocked!");
				nextRoom.unlockDoor();
			}
			else
			{
			addDialogue("There is a locked door blocking your way");
			}
		}
		else if (nextRoom == null) // It will first check if there is a path to the next room.
		{
			addDialogue("There is no door!"); // If there is no path to the next room, the game will tell you that you can't go that way.
		}
		else
		{
			currentRoom = nextRoom;
			printLook(); // It will give you a description of what's in the room
		}
	}
}

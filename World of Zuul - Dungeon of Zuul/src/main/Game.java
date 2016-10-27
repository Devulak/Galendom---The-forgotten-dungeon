package main;

import main.item.Helmets;
import main.item.Shields;
import main.item.Weapons;
import main.item.Armor;
import main.item.Leggings;
import main.item.Boots;

public class Game {

    private Parser parser;
    protected Room currentRoom;
    private Weapons currentWeapon;
    private Helmets currentHelmet;
    private Armor currentArmor;
    private Leggings currentLeggings;
    private Shields currentShield;
    private Boots currentBoots;
    private boolean hasWeapon = true;
    private boolean hasShield = true;
	private Creature Hero = new Creature(1);

    public Game() {
        createRooms();
        generateWeapons();
        generateArmor();
        parser = new Parser();
    }

    private void generateWeapons() {
        Weapons woodenSword = new Weapons("It's a wooden sword.",1,"wooden sword", 1, 3);
        currentWeapon = woodenSword;
    }

    private void generateArmor() {
        Shields woodenShield = new Shields("It's a wooden shield",2,"wooden shield", 1, 5);
        currentShield = woodenShield;
    }

    private void createRooms() {
        /* Creating rooms */
        Room lvl_1, lvl_2, lvl_2a, lvl_3, lvl_3a, lvl_4, lvl_4a, lvl_5, lvl_5a, lvl_6, lvl_7, lvl_8;

        /* Give rooms descriptions. If you write "in a cave" then the game will say something like "You are in a cave" */
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

        /* Adds monsters to a game  */
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

    public void play() {
        printWelcome();
        test();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

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

    private void test() {
        System.out.println();
        System.out.println("You have a " + currentWeapon.getWeaponName() + " and a " + currentShield.getShieldName() + ".");
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.MAP) {
            printMap();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.LOOK) {
			printLook();
        } else if (commandWord == CommandWord.STATUS) {
            printStatus();
        } else if (commandWord == CommandWord.STATUS) {
            
        }
        return wantToQuit;
    }

    /* By writing "help" in console, this method will be called. */
    private void printHelp() {
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("go: This gives you the option to move around. The command is: go \"direction\".");
        System.out.println("map: The console will print out a map and your current whereabouts.");
        System.out.println("quit: The game will quit.");
        System.out.println("look: Look around in the room for items.");
        System.out.println("Status: Your healthpoints and XP");
        System.out.println("Inventory: Look through your inventory");

        /* parser.showCommands(); */ // This line prints out the command words.
    }
	
    private void printStatus() // Prints out the character specific things to check
	{
        System.out.println(Hero.getHealth()); // Prints out the hero's health
        System.out.println(Hero.getExperience()); // Prints out the hero's experience
    }
	
	private void printLook() // Prints out what the character can see
	{
		System.out.println("You are " + currentRoom.getShortDescription());
		if(currentRoom.hasMonster())
			System.out.println("There's a monster " + currentRoom.Monster.getLevel() + " blocking your way");
		else
			System.out.println(currentRoom.getExitString());
	}

    private void printMap()
	{
        System.out.println("/----------------------------------------------------------------------------------------\\");
        System.out.println("|                                              level 5a                                  |");
        System.out.println("|            level 2  -- level 3 -- level 4 -<                                           |");
        System.out.println("| level 1 -<                                   level 5 -- level 6 -- level 7 -- level 8  |");
        System.out.println("|            level 2a -- level 3a -- level 4a                                            |");
        System.out.println("\\----------------------------------------------------------------------------------------/");
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
    
    
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if(currentRoom.hasMonster())
		{
            System.out.println("The monster is blocking your way");
		}
		else if (nextRoom == null)
		{
            System.out.println("There is no door!");
        }
		else
		{
            currentRoom = nextRoom;
			printLook();
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}

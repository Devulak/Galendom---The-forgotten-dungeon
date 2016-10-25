package main;

public class Game {

    private Parser parser;
    private Room currentRoom;
    private Weapons currentWeapon;
    private Armor currentArmor;

    public Game() {
	createRooms();
	generateWeapons();
	generateArmor();
	parser = new Parser();
    }

    private void generateWeapons() {
	Weapons woodenSword = new Weapons("wooden sword", 1, 3);
	Weapons ironSword = new Weapons("iron sword", 2, 4);
	Weapons steelSword = new Weapons("steel sword", 3, 5);
	currentWeapon = woodenSword;
    }

    private void generateArmor() {
	Armor woodenChestplate = new Armor("wooden chestplate", 5);
	Leggings woodenLeggings = new Leggings("wooden leggings", 2);
	Boots woodenBoots = new Boots("wooden boots", 2);
	
	Shields woodenShield = new Shields("wooden shield", 1, 5);
	currentArmor = woodenShield;
    }

    private void createRooms() {
	/* Creating rooms */
	Room lvl_1, lvl_2, lvl_2a, lvl_3, lvl_3a, lvl_4, lvl_4a, lvl_5, lvl_5a, lvl_6, lvl_7, lvl_8;

	/* Give rooms descriptions. If you write "in a cave" then the game will say something like "You are in a cave" */
	lvl_1 = new Room("in level 1", "1 ");
	lvl_2 = new Room("in level 2", "2 ");
	lvl_2a = new Room("in level 2a", "2a");
	lvl_3 = new Room("in level 3", "3 ");
	lvl_3a = new Room("in level 3a", "3a");
	lvl_4 = new Room("in level 4", "4 ");
	lvl_4a = new Room("in level 4a", "4a");
	lvl_5 = new Room("in level 5", "5 ");
	lvl_5a = new Room("in level 5a", "5a");
	lvl_6 = new Room("in level 6", "6 ");
	lvl_7 = new Room("in level 7", "7 ");
	lvl_8 = new Room("in level 8", "8 ");

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
	System.out.println(currentRoom.getLongDescription());
    }

    private void test() {
	System.out.println();
	System.out.println("You have a " + currentWeapon.getWeaponName() + " and a " + currentArmor.getArmorName() + ".");
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
	}
	return wantToQuit;
    }

    /* By writing "help" in console, this method will be called. */
    private void printHelp() {
	System.out.println();
	System.out.println("You're lost in a cave. You have to find the exit to win.");
	System.out.println("Your goal is to move to the end of the map. At the end of the map, you");
	System.out.println("will face the boss that you have to defeat. If you defeat the boss, you will");
	System.out.println("win, but if your health reaches zero, you will lose.");
	System.out.println();
	System.out.println("There will be monsters in every room and to move to be able to move to the next room,");
	System.out.println("you have to defeat all the monsters.");
	System.out.println();
	System.out.println("Your command words are:");
	System.out.println("help: Self-explanatory.");
	System.out.println();
	System.out.println("go: This gives you the option to move around. The command is: go \"direction\".");
	System.out.println("The game will tell you the paths. For example, if you can and want to go right");
	System.out.println("you write \"go right\" to go right.");
	System.out.println();
	System.out.println("map: The console will print out a map and your current whereabouts.");
	System.out.println();
	System.out.println("quit: The game will quit.");

	/* parser.showCommands(); */ // This line prints out the command words.
    }

    private void printMap() {
	System.out.println();
	System.out.println("/--------------------------------------------------------------\\");
	System.out.println("|                                 " + currentRoom.isInRoom("5a") + "                         |");
	System.out.println("|         " + currentRoom.isInRoom("2 ") + " -- " + currentRoom.isInRoom("3 ") + " -- " + currentRoom.isInRoom("4 ") + " -<                              |");
	System.out.println("| " + currentRoom.isInRoom("1 ") + " -<                         " + currentRoom.isInRoom("5 ") + " -- " + currentRoom.isInRoom("6 ") + " -- " + currentRoom.isInRoom("7 ") + " -- " + currentRoom.isInRoom("8 ") + " |");
	System.out.println("|         " + currentRoom.isInRoom("2a") + " -- " + currentRoom.isInRoom("3a") + " -- " + currentRoom.isInRoom("4a") + "                                 |");
	System.out.println("\\--------------------------------------------------------------/");
	System.out.println();
    }

    private void goRoom(Command command) {
	if (!command.hasSecondWord()) {
	    System.out.println("Go where?");
	    return;
	}

	String direction = command.getSecondWord();

	Room nextRoom = currentRoom.getExit(direction);

	if (nextRoom == null) {
	    System.out.println("There is no door!");
	} else {
	    currentRoom = nextRoom;
	    System.out.println(currentRoom.getLongDescription());
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

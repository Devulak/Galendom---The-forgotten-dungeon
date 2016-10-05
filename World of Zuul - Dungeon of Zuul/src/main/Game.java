package main;

public class Game {

    private Parser parser;
    private Room currentRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        /* Creating rooms */
        Room lvl_1, lvl_2, lvl_2b, lvl_3, lvl_3b, lvl_4, lvl_4b, lvl_5, lvl_5b, lvl_6, lvl_7, lvl_8;

        /* Give rooms descriptions. If you write "in a cave" then the game will say something like "You are in a cave" */
        lvl_1 = new Room("lvl_1");
        lvl_2 = new Room("lvl_2");
        lvl_2b = new Room("lvl_2b");
        lvl_3 = new Room("lvl_3");
        lvl_3b = new Room("lvl_3b");
        lvl_4 = new Room("lvl_4");
        lvl_4b = new Room("lvl_4b");
        lvl_5 = new Room("lvl_5");
        lvl_5b = new Room("lvl_5b");
        lvl_6 = new Room("lvl_6");
        lvl_7 = new Room("lvl_7");
        lvl_8 = new Room("lvl_8");

        /* This gives the player the option to move between the rooms */
        lvl_1.setExit("east", lvl_2);
        lvl_1.setExit("west", lvl_2b);

        lvl_2.setExit("south", lvl_3);
        lvl_2.setExit("west", lvl_1);

        lvl_2b.setExit("south", lvl_3b);
        lvl_2b.setExit("east", lvl_1);

        lvl_3.setExit("south", lvl_4);
        lvl_3.setExit("north", lvl_2);

        lvl_3b.setExit("east", lvl_4b);
        lvl_3b.setExit("north", lvl_2b);

        lvl_4.setExit("west", lvl_5);
        lvl_4.setExit("south", lvl_5b);

        lvl_4b.setExit("west", lvl_3b);

        lvl_5.setExit("north", lvl_3);
        lvl_5.setExit("west", lvl_6);
        lvl_5.setExit("east", lvl_4);

        lvl_5b.setExit("north", lvl_5);

        lvl_6.setExit("south", lvl_7);
        lvl_6.setExit("east", lvl_5);
        
        lvl_7.setExit("east", lvl_8);
        lvl_7.setExit("north", lvl_6);
        
        lvl_8.setExit("west", lvl_7);
        
        currentRoom = lvl_1; //The player will start in this room
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
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
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
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

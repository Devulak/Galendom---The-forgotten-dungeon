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

        lvl_4.setExit("forward", lvl_5);
        lvl_4.setExit("left", lvl_5a);
        lvl_4.setExit("right", lvl_3);

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

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("You're lost in a dark cave.");
        System.out.println("Your mission is to go out of the cave.");
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
        System.out.println("You're lost in a cave. You have to ");
        System.out.println("find the exit to win.");
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

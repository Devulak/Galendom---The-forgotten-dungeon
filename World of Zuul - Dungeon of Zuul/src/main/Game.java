package main;

import java.util.Iterator;
import main.item.*;

public class Game {

    protected Room currentRoom;
	protected Creature player;
	protected String dialogue = "";
	protected int score = 0;

    public Game()
	{
        createRooms();
    }

    public void addDialogue()
	{
		dialogue += "\n";
    }

    public void addDialogue(String string)
	{
		dialogue += string + "\n";
    }

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
		player.inventory.add(new Potion(5)); // x5 health potions		
                
		// Coins
		lvl_1.monster.inventory.add(new Coin(1)); // 10 coins
		lvl_2a.monster.inventory.add(new Coin(2)); // 10 coins
		lvl_3a.monster.inventory.add(new Coin(3)); // 10 coins
		lvl_4a.monster.inventory.add(new Coin(5)); // 10 coins
		lvl_2.monster.inventory.add(new Coin(7)); // 10 coins
		lvl_3.monster.inventory.add(new Coin(8)); // 10 coins
		lvl_4.monster.inventory.add(new Coin(9)); // 10 coins
		lvl_5a.monster.inventory.add(new Coin(10)); // 10 coins
                lvl_5a.monster.inventory.add(new Weapon("iron_sword", 6)); // Steel Sword

		//Vendor
		lvl_5a.inventory.add(new Shield("Steel Shield", 6));
		lvl_5a.inventory.add(new Helmet("Steel Helmet", 7));
		lvl_5a.inventory.add(new Chestplate("Steel Chestplate", 8));
		lvl_5a.inventory.add(new Legging("Steel Leggings", 7));
		lvl_5a.inventory.add(new Boot("Steel Boots", 5));
                lvl_5a.inventory.add(new Weapon("Steel Sword", 5));
                lvl_5a.inventory.add(new Shield("Steel Shield", 6));
                                              
		// Weapons
		player.inventory.add(new Weapon("Wooden Sword", 1));
                lvl_4.monster.inventory.add(new Weapon("Iron Sword", 3));
                
		// Shields
		lvl_1.monster.inventory.add(new Shield("Wooden Shield", 2));
                lvl_4.monster.inventory.add(new Shield("Iron Shield", 4));
                
                // Helmets
		lvl_2a.monster.inventory.add(new Helmet("Leather Helmet", 2));
                lvl_3a.monster.inventory.add(new Helmet("Iron Helmet", 5 ));
                
		// Chestplates
                lvl_2a.monster.inventory.add(new Chestplate("Leather Chestplate", 4));
                lvl_4.monster.inventory.add(new Chestplate("Iron Chestplate", 6));
	                
		// Leggings
		lvl_2.monster.inventory.add(new Legging("Leather Leggings", 3));
                lvl_4a.monster.inventory.add(new Legging("Iron Leggings", 5));
                
		// Boots          
                lvl_2.monster.inventory.add(new Boot("Leather Boots", 1));
                lvl_3.monster.inventory.add(new Boot("Iron Boots", 3));

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
    public void play()
	{
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
    }
	
	private void printLook() // Prints out what the character can see
	{
		addDialogue("You are " + currentRoom.getShortDescription());
		if(currentRoom.hasMonster())
		{
			addDialogue("There's a monster level " + currentRoom.monster.getLevel() + " blocking your way");
		}
		else
		{
			addDialogue(currentRoom.getExitString());
		}
	}
	
	protected void useItem(Item searchForItem)
	{
		for (Iterator<Item> it = player.inventory.getContent().iterator(); it.hasNext();)
		{
			Item item = it.next();
			if(item.equals(searchForItem) && item.getAmount() > 0 && item instanceof Potion)
			{
				if(item.getAmount() == 1)
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
		}
	}
	
    public void attack()
	{
		if(currentRoom.hasMonster())
		{
			addDialogue("You rolled " + player.rollDamage(currentRoom.monster) + " dmg");
                        
                        if(player.rollDamage(currentRoom.monster) == 0){
                            addDialogue ("The Monster Blocked your attack.");
                        }
                        if (currentRoom.monster.rollDamage(player) == 0){
                            addDialogue("You blocked the Monsters attack.");
                        }
			
			if(currentRoom.monster.getHealth() > 0)
			{
				addDialogue("Monster rolled " + currentRoom.monster.rollDamage(player) + " dmg");
				
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
					currentRoom.inventory.add(item);
				}
				currentRoom.monster = null;
				addDialogue("You have slain the monster!");
			}
		}
		else
			addDialogue("There's no monster to attack");
                
    }
   
	
    public void goRoom(String direction)
	{
        Room nextRoom = currentRoom.getExit(direction);

        if(currentRoom.hasMonster())
		{
            addDialogue("The monster (level " + currentRoom.monster.getLevel() + "): is blocking your way");
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

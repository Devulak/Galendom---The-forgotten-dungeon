package main;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.creature.*;
import main.item.*;
import main.item.armour.*;

public class Game implements GameInterface
{
	private static Game instance = null;
	private int scenario;
	private int points;
	private int turns;
	private boolean won;
	private int turnsLimit = 20;
	private String dialogue = "";
	private Player player = new Player(1);
	private Room currentRoom;
	private Room lastRoom;
	private Vendor vendor = new Vendor(0);
	private Room currentVendorRoom;
	private Room[][] rooms = new Room[4][5];
	private Boolean[][] roomsSeen = new Boolean[4][5];
	private Scoreboard scoreboard = new Scoreboard();
	
	@Override
	public void play()
	{
		// Reset values
		this.points = 0;
		this.turns = 0;
		this.won = false;
		this.turnsLimit = 20;
		this.dialogue = "";
		this.player = new Player(1);
		this.currentRoom = null;
		this.lastRoom = null;
		this.vendor = new Vendor(0);
		this.currentVendorRoom = null;
		this.rooms = null;
		this.roomsSeen = null;
		
		// load scenario
		try {
			deSerialization();
		} catch (IOException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// Welcome the player and do default stuff
		printWelcome();
		printLook();
	}
	
	public static Game getInstance()
	{
		if(instance == null)
		{
			instance = new Game();
		}
		return instance;
	}
	
	@Override
	public void setScenario(int setTo)
	{
		scenario = setTo;
	}
	
	/**
	 * This method will save every single object that are required
	 * @throws IOException 
	 */
	public void serialization() throws IOException
	{
		try (FileOutputStream fout = new FileOutputStream("src/gamescenarios/GameScenario" + scenario + ".ser"))
		{  
			ObjectOutputStream out = new ObjectOutputStream(fout);  

			out.writeObject(rooms);
			out.writeObject(roomsSeen);
			out.writeObject(currentRoom);
			out.writeObject(currentVendorRoom);
			out.writeObject(player);
			out.writeObject(vendor);
			out.close();
			fout.close();
		}
		System.out.println("Serialization succesful");
	}
	
	/**
	 * This method will load a file that contains the rooms
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void deSerialization() throws IOException, ClassNotFoundException
	{
		try (FileInputStream fileIn = new FileInputStream("src/gamescenarios/GameScenario" + scenario + ".ser"))
		{
			ObjectInputStream in = new ObjectInputStream(fileIn);
			rooms = (Room[][]) in.readObject();
			roomsSeen = (Boolean[][]) in.readObject();
			currentRoom = (Room) in.readObject();
			currentVendorRoom = (Room) in.readObject();
			player = (Player) in.readObject();
			vendor = (Vendor) in.readObject();
			in.close();
			fileIn.close();
		}
		System.out.println("Deserialization succesful");
	}

	/**
	 * Returns the number of turns the player has made
	 * @return turns
	 */
	@Override
	public int getTurns() 
	{
		return turns;
	}

	/**
	 * Returns the turn limit
	 * @return turnsLimit
	 */
	@Override
	public int getTurnsLimit() 
	{
		return turnsLimit;
	}

	/**
	 * Returns the player's current position
	 * @return currentRoom
	 */
	@Override
	public Room getCurrentRoom() 
	{
		return currentRoom;
	}

	/**
	 * Returns the player
	 * @return player
	 */
	@Override
	public Player getPlayer() 
	{
		return player;
	}

	/**
	 * Returns the vendor
	 * @return vendor
	 */
	@Override
	public Vendor getVendor()
	{
		return vendor;
	}
	
	/**
	 * Returns the vendors current position
	 * @return vendor
	 */
	@Override
	public Room getCurrentVendorRoom() 
	{
		return currentVendorRoom;
	}

	/**
	 * Returns the array containing rooms
	 * @return rooms
	 */
	@Override
	public Room[][] getRooms()
	{
		return rooms;
	}
	
	/**
	 * Returns the rooms that the player has visited previously
	 * @return roomsSeen
	 */
	@Override
	public Boolean[][] getRoomsSeen()
	{
		return roomsSeen;
	}
	
	/**
	 * Increments the number of turns by one everytime the player moves around the map.
	 * The player will be warned that he will lose health if the amount of turns equals the turn limit.
	 * The player will gradually lose health if he stays in the dungeon for long.
	 */
	@Override
	public void addTurn() 
	{
		turns++;
		if (turns == turnsLimit) //The Player will grow weaker for each turn he takes after max turns
		{
			addDialogue("You have been in the cave for too long, gas has been released and killing you");
		}
		if (turns > turnsLimit) 
		{
			player.takeDamageFromGas((turns - turnsLimit) * 2);
		}
	}

	/**
	 * Returns points
	 * @return points
	 */
	@Override
	public int getPoints() 
	{
		return points;
	}

	/**
	 * Returns the dialogue to the GUI
	 * @return dialouge
	 */
	@Override
	public String getDialogue() 
	{
		return dialogue;
	}

	/**
	 * Adds a new line everytime the game wants to print something out
	 */
	@Override
	public void addDialogue() 
	{
		dialogue += "\n";
	}

	/**
	 * 
	 * @param string 
	 */
	@Override
	public void addDialogue(String string) 
	{
		if (dialogue.length() == 0)
		{
			dialogue += string;
		} else 
		{
			dialogue += "\n" + string;
		}
	}

	@Override
	public void createRooms()
	{
		Room lvl_1, lvl_2a, lvl_2b, lvl_2c, lvl_3a, lvl_3b, lvl_3c, lvl_4c, lvl_4ba, lvl_4bb, lvl_5c, lvl_2Boss;
		// Darkens all the rooms
		for (int i = 0; i < roomsSeen.length; i++)
		{
			for (int j = 0; j < roomsSeen[i].length; j++)
			{
				roomsSeen[i][j] = false;
			}
		}
		rooms[0][2] = new Room("in the boss room");
		rooms[1][0] = new Room("in level 3a");
		rooms[1][1] = new Room("in level 2a");
		rooms[1][2] = new Room("in level 1");
		rooms[1][3] = new Room("in level 2c");
		rooms[1][4] = new Room("in level 3c");
		rooms[2][2] = new Room("in level 2b");
		rooms[2][4] = new Room("in level 4c");
		rooms[3][1] = new Room("in level 4ba");
		rooms[3][2] = new Room("in level 3b");
		rooms[3][3] = new Room("in level 4bb");
		rooms[3][4] = new Room("in level 5c");
		
		lvl_2Boss = rooms[0][2];
		lvl_3a = rooms[1][0];
		lvl_2a = rooms[1][1];
		lvl_1 = rooms[1][2];
		lvl_2c = rooms[1][3];
		lvl_3c = rooms[1][4];
		lvl_2b = rooms[2][2];
		lvl_4c = rooms[2][4];
		lvl_4ba = rooms[3][1];
		lvl_3b = rooms[3][2];
		lvl_4bb = rooms[3][3];
		lvl_5c = rooms[3][4];
		
		lvl_1.setExit(lvl_2a);
		lvl_1.setExit(lvl_2b);
		lvl_1.setExit(lvl_2c);
		lvl_1.setExit(lvl_2Boss);
		
		lvl_2Boss.setExit(lvl_1);

		lvl_2a.setExit(lvl_3a);
		lvl_2a.setExit(lvl_1);

		lvl_3a.setExit(lvl_2a);

		lvl_2b.setExit(lvl_3b);
		lvl_2b.setExit(lvl_1);

		lvl_3b.setExit(lvl_4ba);
		lvl_3b.setExit(lvl_4bb);
		lvl_3b.setExit(lvl_2b);

		lvl_4ba.setExit(lvl_3b);
		
		lvl_4bb.setExit(lvl_3b);

		lvl_2c.setExit(lvl_3c);
		lvl_2c.setExit(lvl_1);

		lvl_3c.setExit(lvl_4c);
		lvl_3c.setExit(lvl_2c);

		lvl_4c.setExit(lvl_5c);
		lvl_4c.setExit(lvl_3c);

		lvl_5c.setExit(lvl_4c);
		
		player = new Player(1);
		vendor = new Vendor(0);
		
		// Monsters
        lvl_1.setMonster(new Monster(1));
        lvl_2a.setMonster(new Monster(2));
        lvl_2b.setMonster(new Monster(2));
        lvl_2c.setMonster(new Monster(2));
        lvl_3a.setMonster(new Monster(3));
        lvl_3b.setMonster(new Monster(3));
        lvl_4ba.setMonster(new Monster(4));
        lvl_4bb.setMonster(new Monster(4));
        lvl_3c.setMonster(new Monster(3));
        lvl_4c.setMonster(new Monster(4));
        lvl_5c.setMonster(new Monster(5));
        lvl_2Boss.setMonster(new Boss(8));
		
		// Others
		lvl_2Boss.hasBoss(true);
		lvl_2Boss.locked(true);
		lvl_5c.setTeleporter(lvl_1);
		lvl_4bb.addItemToMonster(new Key(1));
                
               // Potions
		player.getCreaturesInventory().add(new Potion(5)); // x5 health potions
		lvl_3a.addItemToMonster(new Potion(5)); // x5 health potions
		lvl_2c.addItemToMonster(new Potion(2)); // x2 health potions
		lvl_3c.addItemToMonster(new Potion(2)); // x2 health potions
		lvl_4c.addItemToMonster(new Potion(2)); // x2 health potions
		
		// Coins
		lvl_1.addItemToMonster(new Coin(2)); // 2 coins
        lvl_2a.addItemToMonster(new Coin(3)); // 2 coins
		lvl_3a.addItemToMonster(new Coin(4)); // 3 coins		
		lvl_2c.addItemToMonster(new Coin(5)); // 5 coins
		lvl_3c.addItemToMonster(new Coin(6)); // 6 coins
        lvl_4c.addItemToMonster(new Coin(7)); // 4 coins
		lvl_5c.addItemToMonster(new Coin(8)); // 8 coins
		
		//Vendor
		vendor.getCreaturesInventory().add(new GasMask(5, 10)); //Gassmask
        vendor.getCreaturesInventory().add(new Potion(5, 5)); //Potion
        vendor.getCreaturesInventory().add(new Key(1, 20)); //Key
		
		// Weapons
		player.getCreaturesInventory().add(new Weapon("Wooden Sword", 1)); //Wooden Sword, which the player has in the start of the game                
		lvl_4c.addItemToMonster(new Weapon("Iron Sword", 3)); //Iron Sword
		lvl_5c.addItemToMonster(new Weapon("Iron Sword", 3)); //Iron Sword
		lvl_2b.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_3b.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_4ba.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword
		lvl_4bb.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword 
        lvl_2Boss.addItemToMonster(new Weapon("Steel Sword", 5)); //Steel Sword 
		
		// Shields
		lvl_1.addItemToMonster(new Shield("Wooden Shield", 2)); //Wooden Shield
		lvl_5c.addItemToMonster(new Shield("Iron Shield", 4)); //Iron Shield
		lvl_4bb.addItemToMonster(new Shield("Steel Shield", 6)); //Steel Shield
        lvl_2Boss.addItemToMonster(new Shield("Steel Shield", 6)); //Steel Shield
		
		// Helmets
		lvl_2a.addItemToMonster(new Helmet("Leather Helmet", 2)); //Leather Helmet
		lvl_3a.addItemToMonster(new Helmet("Iron Helmet", 5 )); //Iron Helmet
		lvl_5c.addItemToMonster(new Helmet("Iron Helmet", 5 )); //Iron Helmet
		lvl_3b.addItemToMonster(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		lvl_4ba.addItemToMonster(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		lvl_4bb.addItemToMonster(new Helmet("Steel Helmet", 7 )); //Steel Helmet
        lvl_2Boss.addItemToMonster(new Helmet("Steel Helmet", 7 )); //Steel Helmet
		
		// Chestplates
		lvl_2a.addItemToMonster(new Chestplate("Leather Armour", 4)); //Leather Chestplate
		lvl_4c.addItemToMonster(new Chestplate("Iron Armour", 6)); //Iron Chestplate
		lvl_5c.addItemToMonster(new Chestplate("Iron Armour", 6)); //Iron Chestplate
        lvl_4ba.addItemToMonster(new Chestplate("Steel Armour", 8)); //Steel Chestplate
		lvl_4bb.addItemToMonster(new Chestplate("Steel Armour", 8)); //Steel Chestplate
        lvl_2Boss.addItemToMonster(new Chestplate("Steel Armour", 8)); //Steel Chestplate
		
		// Leggings
		lvl_2c.addItemToMonster(new Legging("Leather Leggings", 3)); //Leather Leggings
		lvl_4c.addItemToMonster(new Legging("Iron Leggings", 5)); //Iron Leggings
		lvl_5c.addItemToMonster(new Legging("Iron Leggings", 5)); //Iron Leggings                             
		lvl_4ba.addItemToMonster(new Legging("Steel Leggings", 7)); //Steel Leggings
		lvl_4bb.addItemToMonster(new Legging("Steel Leggings", 7)); //Steel Leggings
        lvl_2Boss.addItemToMonster(new Legging("Steel Leggings", 7)); //Steel Leggings
		
		// Boots          
		lvl_2c.addItemToMonster(new Boot("Leather Boots", 1)); //Leather Boots
		lvl_3c.addItemToMonster(new Boot("Iron Boots", 3)); //Iron Boots
		lvl_5c.addItemToMonster(new Boot("Iron Boots", 3)); //Iron Boots
		lvl_2b.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_3b.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_4ba.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		lvl_4bb.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
        lvl_2Boss.addItemToMonster(new Boot("Steel Boots", 5)); //Steel Boots
		/* placeholder to outcomment all above in 1 line */ 
		
	}

	/**
	 * This method will print out when you start the game
	 */
	private void printWelcome() {
		addDialogue("Your goal is to move to the end of the map.");
		addDialogue("If you manage to defeat the boss at the end, you will win.");
		addDialogue("But if your health reaches zero, you will lose!");
	}

	/**
	 * Informs you your current whereabouts. If there is a monster in your room,
	 * it will inform you its level.
	 */
	private void printLook()
	{
		addDialogue("You are " + currentRoom.getShortDescription());
		if (currentRoom.hasMonster()) {
			addDialogue("There's a monster level " + currentRoom.getMonster().getLevel() + " blocking your way");
		}
	}

	/**
	 * Will search through your inventory. If you wanted to use potion,
	 * this method will find a class called Potion. Otherwise it will return false.
	 * @param searchForItem
	 * @return true, false
	 */
	@Override
	public boolean useItem(Item searchForItem)
	{
		// Have the player already lost
		if(getLost())
		{
			return false;
		}
		
		if(searchForItem instanceof Potion)
		{
			if(player.getCreaturesInventory().useItem(searchForItem))
			{
				addDialogue("You were healed for " + player.heal() + " HP (max 40% of your max health), and you lost " + player.getLevel()+ " points.");
				points -= player.getLevel()*2;
				return true;
			}
		}
		if(searchForItem instanceof GasMask)
		{
			if(player.getCreaturesInventory().useItem(searchForItem))
			{
				addDialogue("You prepared a mask and can now take on 5 more turns");
				turnsLimit += 5;
				return true;
			}
		}
		return false;
	}

	@Override
	public void takeItem(Item searchForItem)
	{
		// Have the player already lost
		if(getLost())
		{
			return;
		}
		
		Item tempItem = getPlayer().getCreaturesInventory().add(searchForItem);
		getCurrentRoom().getRoomsInventory().swap(searchForItem, tempItem);
	}

	@Override
	public void dropItem(Item searchForItem)
	{
		// Have the player already lost
		if(getLost())
		{
			return;
		}
		
		if(!useItem(searchForItem)) // checks to see if it's an item that's suppose to be used
		{
			Item droppedItem = getCurrentRoom().getRoomsInventory().add(searchForItem);
			getPlayer().getCreaturesInventory().swap(searchForItem, droppedItem);
		}
	}
	
	/**
	 * Gives the player the item, he wants to buy if he has enough money
	 * @param itemToBuy 
	 */
	@Override
	public void buyItem(Item itemToBuy)
	{
		// Have the player lost?
		if(getLost())
		{
			return;
		}
		
		// Is the player and the vendor not in the same room or is there a monster?
		if(!roomHasVendor() || roomHasMonster())
		{
			return;
		}
		
		for (Item item : vendor.getCreaturesInventory().getContent())
		{
			if(item == itemToBuy)
			{
				if(player.getCreaturesInventory().useItem(player.getCreaturesInventory().searchItem(Coin.class), itemToBuy.getPrice()))
				{
					itemToBuy.clearPrice();
					currentRoom.getRoomsInventory().add(itemToBuy);
					vendor.getCreaturesInventory().remove(itemToBuy);
					addDialogue("Vendor> Splendid! I put it on the ground for you.");
					return;
				}
				else
				{
					addDialogue("Vendor> I'm sorry, but you don't seem to have the coin.");
					return;
				}
			}
		}
	}

	/**
	 * Will make the player to attack a monster
	 */
	@Override
	public void attack()
	{
		attack(true);
	}

	/**
	 * Will make the player flee to his previous room
	 */
	@Override
	public void flee()
	{
		attack(false);
	}

	/**
	 * 
	 * @param wantToStay 
	 */
	@Override
	public void attack(boolean wantToStay)
	{
		// Have the player already lost
		if(getLost())
		{
			return;
		}
		
		if (currentRoom.hasMonster())
		{
			if(!wantToStay)
			{
				if(Math.random() <= 0.5) // roll to check your flee success (0.5 = 50% chance)
				{
					addDialogue("Your attempt to flee was successful!");
					if(lastRoom == null)
					{
						addDialogue("... although, you have no place you run, you find a shiny new stick laying around");
						currentRoom.getRoomsInventory().add(new Weapon("GASPOWERED STICK", 1));
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
				int playerRolled = player.rollDamage(currentRoom.getMonster());
				if (playerRolled == 0)
				{
					addDialogue("Monster blocked your attack");
				}
				else
				{
					addDialogue("You rolled " + playerRolled + " dmg");
				}
			}
			if (currentRoom.getMonster().getHealth() > 0)
			{
				int monsterRolled = currentRoom.getMonster().rollDamage(player);
				if (monsterRolled == 0)
				{
					addDialogue("You blocked the monsters attack");
				}
				else
				{
					addDialogue("The monster rolled " + monsterRolled + " dmg");
				}
			}
			else
			{
				if(currentRoom.getBoss())
				{
					player.gainExperience(currentRoom.getMonster());
					for (Item item : currentRoom.getMonster().getCreaturesInventory().getContent())
					{
						currentRoom.getRoomsInventory().add(item);
					}
					currentRoom.setMonsterToNull();
					points += player.getLevel()*5;
					this.won = true;
				}				
				else
				{
					player.gainExperience(currentRoom.getMonster());
					for (Item item : currentRoom.getMonster().getCreaturesInventory().getContent())
					{
						currentRoom.getRoomsInventory().add(item);
					}
					currentRoom.setMonsterToNull();
					points += player.getLevel()*5;
					addDialogue("You have slain the monster!");
				}
			} 
		}
		else
		{
			addDialogue("There's no monster to attack");
		}
	}
	
	@Override
	public void goRoom(int[] direction)
	{
		// Have the player already lost
		if(getLost())
		{
			return;
		}
		
		if (currentRoom.hasMonster())
		{
			addDialogue("The monster (level " + currentRoom.getMonster().getLevel() + "): is blocking your way");
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
					if(player.getCreaturesInventory().useItem(player.getCreaturesInventory().searchItem(Key.class)))
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
					lastRoom = currentRoom;
					currentRoom = nextRoom;
					goVendor();
					printLook(); // It will give you a description of what's in the room
					addTurn();
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
		if (currentVendorRoom != currentRoom && !currentVendorRoom.getLocked()) // make sure the player isn't in the same room and the room itself is unlocked
		{
			int index = new Random().nextInt(currentVendorRoom.getExits().size()); // randomizes which exit to use
			Room roomToGo = currentVendorRoom.getExits().get(index); // selects the room from the exit
			if (!roomToGo.getLocked()) // Make sure the vendor can't go in locked rooms
			{
				currentVendorRoom = roomToGo; // goto that room
			}
		}
	}
	
	@Override
	public int[] getPlayerPosition()
	{
		// Make predictions and find the current room's position
		for (int i = 0; i < rooms.length; i++)
		{
			for (int j = 0; j < rooms[i].length; j++)
			{
				if(rooms[i][j] == currentRoom)
				{
					// Set the room as visible
					roomsSeen[i][j] = true;
					
					// Set position on the map
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
	
	@Override
	public int[] getVendorPosition()
	{
		// Make predictions and find the current room's position
		for (int i = 0; i < rooms.length; i++)
		{
			for (int j = 0; j < rooms[i].length; j++)
			{
				if(rooms[i][j] == currentVendorRoom)
				{
					// Set position on the map
					return new int[]{i, j};
				}
			}
		}
		return null;
	}
	
	@Override
	public void useTeleporter()
	{
		// Have the player lost?
		if(getLost())
		{
			return;
		}
		
		if(currentRoom.getTeleporter() != null && currentRoom.getMonster() == null) // is there even a teleporter and is there a monster blocking?
		{
			addTurn();
			lastRoom = currentRoom;
			currentRoom = currentRoom.useTeleporter(); // teleport and destroy teleporter
		}
	}
	
	@Override
	public boolean roomHasMonster()
	{
		return currentRoom.hasMonster();
	}
	
	@Override
	public boolean roomHasVendor()
	{
		if(currentRoom == currentVendorRoom)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean roomHasTeleporter()
	{
		if(currentRoom.getTeleporter() != null)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean getLost()
	{
		if(player.getHealth() <= 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Score> getScoreboard()
	{
		return this.scoreboard.getScoreboard();
	}

	@Override
	public void addScore(String name)
	{
		if(this.getWon())
		{
			this.points = (int) (this.points*1.2);
		}
		this.scoreboard.add(name, points);
		this.points = 0;
	}

	@Override
	public boolean getWon()
	{
		return this.won;
	}
}

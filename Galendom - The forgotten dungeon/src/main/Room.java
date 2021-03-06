package main;

import java.io.Serializable;
import main.creature.*;
import java.util.*;
import main.item.*;

public class Room implements Serializable
{
    private final String description;
	private List<Room> exits = new ArrayList<>();
    private LevelAbleCreature monster; // The monster in the room, if there's any!
	private boolean hasBoss = false;
    private Inventory inventory = new Inventory(true); // Inventory full of stuff (or not)!
    private boolean locked = false;
	private Room teleporter;

    public Room(String description)
    {
        this.description = description;
    }

    public void setMonster(LevelAbleCreature creature) 
    {
        monster = creature;
    }

    public boolean hasMonster() 
    {
        if(monster == null)
		{
			return false;
		}
		else
		{
			return true;
		}
    }
	
	public void setMonsterToNull() 
	{
		monster = null;
	}
	
    public LevelAbleCreature getMonster() 
    {
		return monster;
	}
	
	public void hasBoss(boolean hasBoss)
	{
		this.hasBoss = hasBoss;
	}
	
	public boolean getBoss()
	{
		return hasBoss;
	}
	
	
	/**
	* Returns an item to the monsters inventory in the specific room if there is any.
	* @param item	the item you want to add into the monsters inventory
	* @return		if there is a monster in the room, the item will be added
	*/
	public Item addItemToMonster(Item item) 
	{
		if(hasMonster())
		{
			return getMonster().getCreaturesInventory().add(item);
		}
		else
		{
			System.out.println("Error: The game cannot give a monster an item. The monster does not exist");
			return null;
		}
	}
	
	public Inventory getRoomsInventory()
	{
		return inventory;
	}
	
	public void locked(boolean lock)
	{
		locked = lock;
	}
	
	public boolean getLocked()
	{
		return locked;
	}
    
	public void setTeleporter(Room teleportTo)
	{
		teleporter = teleportTo;
	}
	
    public Room getTeleporter() 
    {
		return teleporter;
    }
	
    public Room useTeleporter() 
    {
		Room tempRoom = teleporter; // Keep the teleporter point in a seperate variable while deleting the teleporter
		teleporter = null;
		return tempRoom;
    }
	
    public void setExit(Room exit) 
    {
        exits.add(exit);
    }

    public String getShortDescription()
    {
        return description;
    }

	// find out if the exit pointing at can be accessed
    public boolean hasExit(Room exitLookingFor)
    {
		for (Room exit : exits)
		{
			if(exitLookingFor == exit)
			{
				return true;
			}
		}
		return false;
    }
	
    public List<Room> getExits() 
    {
        return exits;
    }

}


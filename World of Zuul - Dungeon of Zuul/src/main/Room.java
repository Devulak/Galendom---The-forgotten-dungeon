package main;

import java.util.*;


public class Room
{
    private String description;
	private List<Room> exits = new ArrayList<>();
    protected Creature monster; // The monster in the room, if there's any!
    protected Inventory inventory = new Inventory(true); // Inventory full of stuff (or not)!
    private boolean locked = false;
	protected Room teleporter;

    public Room(String description)
    {
        this.description = description;
    }

    public void setMonster(Creature creature) 
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
	
	protected void locked(boolean lock)
	{
		locked = lock;
	}
	
	public boolean getLocked()
	{
		return locked;
	}
    
	protected void setTeleporter(Room teleportTo)
	{
		teleporter = teleportTo;
	}
	
    public Room getTeleporter() 
    {
		return teleporter;
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
	
    protected List<Room> getExits() 
    {
        return exits;
    }
}


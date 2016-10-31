package main;

import java.util.*;
import main.item.*;


public class Room
{
    private String description;
    private HashMap<String, Room> exits;
	protected Creature monster; // The monster in the room, if there's any!
	protected Inventory inventory = new Inventory(true); // Inventory full of stuff (or not)!

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
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

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    protected String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys)
		{
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}


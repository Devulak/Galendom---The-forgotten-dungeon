package main;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import main.item.*;


public class Room
{
    private String description;
    private HashMap<String, Room> exits;
	protected Creature Monster; // The monster in the room, if there's any!
	private List<Object> inventory = new ArrayList<>(); // Inventory full of stuff (or not)!

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setMonster(Creature Creature) 
    {
        Monster = Creature;
    }

    public boolean hasMonster() 
    {
        if(Monster == null)
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
        String returnString = "Paths:";
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


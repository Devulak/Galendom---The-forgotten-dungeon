package main;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private String uniqueId;
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description, String uniqueId) 
    {
        this.description = description;
        this.uniqueId = uniqueId;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String isInRoom(String CheckId)
    {
		String RoomBlock = "*";
		if (CheckId == this.uniqueId)
			RoomBlock = "█";
		return RoomBlock + "." + CheckId;
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Paths:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}


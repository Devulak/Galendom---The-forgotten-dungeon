package main.creature;

import java.io.Serializable;
import main.Inventory;
import java.util.*;
import main.item.*;

public abstract class Creature implements Serializable
{
	
	private Inventory inventory = new Inventory(); // Inventory full of stuff (or not)!

    public Creature(int lvl) 
	{ //this is a constructor for the creatures
	}
	
	public Inventory getCreaturesInventory()
	{
		return inventory;
	}
	
}
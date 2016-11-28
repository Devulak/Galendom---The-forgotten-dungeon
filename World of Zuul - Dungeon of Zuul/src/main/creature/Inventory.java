package main.creature;

import java.util.*;
import main.item.*;

public class Inventory
{
	private List<Item> inventory = new ArrayList<>(); // Inventory full of stuff!
	private boolean nolimit = false;
	
	public Inventory() // Constructor
	{
		
	}
	
	public Inventory(boolean nolimit) // Constructor
	{
		this.nolimit = nolimit;
	}
	
	public Item add(Item item)
	{
		if(item != null)
		{
			// Check if there's something alike already in the inventory
			for (Iterator<Item> currentInventory = inventory.iterator(); currentInventory.hasNext();)
			{
				Item inventoryItem = currentInventory.next();
				if(inventoryItem.getClass() == item.getClass()) // Do the item adding match anything in the inventory?
				{
					if(item.getAmount() > 0 && inventoryItem.getAmount() > 0) // Is the items stackable with eachother?
					{
						inventoryItem.addAmount(item.getAmount()); // Transfer the current amount to the inventory's item
						return null; // Return null since it doesn't give anything back
					}
					else if(!nolimit) // What do we do if it's not stackable? (Works as if the things got swapped or switched)
					{
						currentInventory.remove(); // Remove the old item
						inventory.add(item); // Add the new item
						return inventoryItem; // Return old item, since thew inventoryItem is still specified we can still use that to return
					}
				}
			}
			inventory.add(item); // Add the new item
		}
		return null; // Return null since it doesn't give anything back
	}
	
	public void remove(Item searchForItem)
	{
		if(searchForItem != null)
		{
			for (Iterator<Item> itInventory = inventory.iterator(); itInventory.hasNext();)
			{
				Item item = itInventory.next();
				if(item.equals(searchForItem))
				{
					itInventory.remove();
					return;
				}
			}
		}
	}
	
	public void swap(Item itemToRemove, Item itemToAdd)
	{
		remove(itemToRemove);
		add(itemToAdd);
	}
	
	protected List<Item> getContent()
	{
		return inventory;
	}
	
	/**
	 * Returns an Item object that is found in the inventory with the given
	 * class to search, remember to put ".class" after naming what class you
	 * would like to search for
	 * 
	 * @param searchClass
	 * @return the Item found in the inventory or null if nothing found
	 */
	protected Item searchItem(Class searchClass)
	{
		for (Item item : inventory)
		{
			if(searchClass.isInstance(item))
			{
				return item;
			}
		}
		return null;
	}
	
	/**
	 * Uses an usable item in the inventory that's equal to it's parameter.
	 * Returns true or false depending on if using the item was successful
	 * 
	 * @param searchForItem
	 * @return true if the item provided can and has been used, false otherwise
	 */
	protected boolean useItem(Item searchForItem)
	{
		return useItem(searchForItem, 1);
	}
	
	/**
	 * @param searchForItem
	 * @param amount
	 */
	protected boolean useItem(Item searchForItem, int amount)
	{
		if (searchForItem != null)
		{
			for (Iterator<Item> itInventory = inventory.iterator(); itInventory.hasNext();)
			{
				Item item = itInventory.next();
				if (item.equals(searchForItem) && item.getAmount() >= amount) // Check to see if the item match and if it's usable, by how many
				{
					if (item.getAmount() > amount)
					{
						item.use(amount);
					}
					else
					{
						itInventory.remove();
					}
					return true;
				}
			}
		}
		return false;
	}
}
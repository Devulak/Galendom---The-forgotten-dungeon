package main;

import java.util.*;
import main.item.Item;

public class Inventory
{
	protected List<Item> inventory = new ArrayList<>(); // Inventory full of stuff!
	private boolean nolimit = false;
	
	public Inventory() // Constructor
	{
		
	}
	
	public Inventory(boolean nolimit) // Constructor
	{
		this.nolimit = nolimit;
	}
	
	protected Item add(Item item)
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
	
	protected void remove(Item searchForItem)
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
	
	protected void swap(Item itemToRemove, Item itemToAdd)
	{
		remove(itemToRemove);
		add(itemToAdd);
	}
	
	public List<Item> getContent()
	{
		return inventory;
	}
	
	public void printContent(String inventoryName)
	{
		String horizontalPrefix = "-";
		String verticalPrefix = "|";
		int length = 30;
		String topLine = "/";
		int topOffset = 5;
		String bottomLine = "\\";
		
		for (int i = 0; i < (length - inventoryName.length()); i++)
		{
			if(i == topOffset)
			{
				topLine += inventoryName.toUpperCase();
			}
			topLine += horizontalPrefix;
		}
		System.out.println(topLine);
		if(!inventory.isEmpty())
		{
			for (Item Item : inventory)
			{
				if(Item.getAmount() > 1)
				{
					System.out.println(verticalPrefix + " " + Item.getAmount() + "x " + Item.toString() + "s");
				}
				else
				{
					System.out.println(verticalPrefix + " " + Item.toString());
				}
			}
		}
		else
		{
			System.out.println(verticalPrefix + "          .' '.            ");
			System.out.println(verticalPrefix + " .        .   .            ");
			System.out.println(verticalPrefix + "  .         .         . <.>");
			System.out.println(verticalPrefix + "    ' .  . ' ' .  . '      ");
		}
		for (int i = 0; i < length; i++)
		{
			bottomLine += horizontalPrefix;
		}
		System.out.println(bottomLine);
	}
}
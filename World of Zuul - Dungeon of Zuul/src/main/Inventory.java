package main;

import java.util.*;
import main.item.Item;

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
		// Check if there's something alike already in the inventory
		for (Iterator<Item> it = inventory.iterator(); it.hasNext();)
		{
			Item inventoryItem = it.next();
			if(inventoryItem.getClass() == item.getClass()) // Do the item adding match anything in the inventory?
			{
				if(item.getAmount() > 0 && inventoryItem.getAmount() > 0) // Is the items stackable with eachother?
				{
					inventoryItem.addAmount(item.getAmount()); // Transfer the current amount to the inventory's item
					return null; // Return null since it doesn't give anything back
				}
				else if(!nolimit) // What do we do if it's not stackable? (Works as if the things got swapped or switched)
				{
					inventory.add(item); // Add the item
					it.remove(); // Remove the old item
					return inventoryItem; // Return old item, since thew inventoryItem is still specified we can still use that to return
				}
			}
		}
		inventory.add(item); // Add the item
		return null; // Return null since it doesn't give anything back
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
					System.out.println(verticalPrefix + " " + Item.getAmount() + "x " + Item.getName() + "s");
				}
				else
				{
					System.out.println(verticalPrefix + " " + Item.getName());
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
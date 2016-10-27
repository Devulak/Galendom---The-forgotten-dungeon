package main.item;


public class Item
{
	private String name;
	private boolean stackable;
	private String slot;

	public Item(String name, boolean stackable, String slot) // Constructor
	{
		this.name = name;
		this.stackable = stackable;
		this.slot = slot;
	}

	public String getName() // Get the name of the item
	{
		return name;
	}
}

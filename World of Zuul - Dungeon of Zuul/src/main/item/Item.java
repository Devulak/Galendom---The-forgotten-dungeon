package main.item;


public class Item
{
	private String name;
	private int amount;
	private String slot;

	public Item(String name, int amount, String slot) // Constructor
	{
		this.name = name;
		this.amount = amount;
		this.slot = slot;
	}

	public String getName() // Get the name of the item
	{
		return name;
	}

	public int getAmount() // Get the amount of the item, if zero, means it's not stackable
	{
		return amount;
	}
}

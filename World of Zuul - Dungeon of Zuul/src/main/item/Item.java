package main.item;


public class Item
{
	private String name;
	private int amount;
	private String category;

	public Item(String name, int amount, String category) // Constructor
	{
		this.name = name;
		this.amount = amount;
		this.category = category;
	}

	public String getName() // Get the name of the item
	{
		return name;
	}

	public int getAmount() // Get the amount of the item, if zero, means it's not stackable
	{
		return amount;
	}

	public String getCategory() // Get the category of an item, the category makes sure you can't pick up more than one of each
	{
		return category;
	}
}

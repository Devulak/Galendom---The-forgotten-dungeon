package main.item;

public class Item
{
	private String name;
	private int amount;

	public Item(String name, int amount) // Constructor
	{
		this.name = name;
		this.amount = amount;
	}

	public String toString() // Return value on object
	{
		if(amount > 0)
		{
			return name + " (" + amount + ")";
		}
		return name;
	}

	public int getAmount() // Get the amount of the item, if zero, means it's not stackable
	{
		return amount;
	}

	public void addAmount(int add) // Get the amount of the item, if zero, means it's not stackable
	{
		amount += add;
	}

	public void use() // Get the amount of the item, if zero, means it's not stackable
	{
		amount--;
	}
}

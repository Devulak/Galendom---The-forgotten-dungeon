package main.item;

public class Potion extends Item
{
	private final boolean usable = true;
	
	public Potion(int amount)
	{
		super("Health Potions", amount);
	}

	public boolean getUsable() // Get the category of an item, the category makes sure you can't pick up more than one of each
	{
		return usable;
	}
}
package main.item;

public class Armour extends Item
{
	private int amour;
	
	public Armour(String name, int armor) // Constructor
	{
		super(name, 0);
		this.amour = armor;
	}
	
	public String toString() // Return value on object
	{
		return name + " (" + amour + " arm)";
	}

	public int getArmour() // Get the category of an item, the category makes sure you can't pick up more than one of each
	{
		return amour;
	}
}
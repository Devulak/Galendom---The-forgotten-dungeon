package main.item;

public class Armour extends Item
{
	private int armour;
	
	public Armour(String name, int armour) // Constructor
	{
		super(name, 0);
		this.armour = armour;
	}
	
	public String toString() // Return value on object
	{
		return name + " (" + armour + " def)";
	}

	public int getArmour() // Get the category of an item, the category makes sure you can't pick up more than one of each
	{
		return armour;
	}
}
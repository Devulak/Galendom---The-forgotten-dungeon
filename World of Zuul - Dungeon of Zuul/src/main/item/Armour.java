package main.item;

public class Armour extends Item
{
	private int armour;
	
	public Armour(String name, int armour, int price) // Constructor
	{
		super(name, 0, price);
		this.armour = armour;
		this.extra = armour + " def";
	}
	
	public Armour(String name, int armour) // Constructor
	{
		this(name, armour, 0);
	}

	public int getArmour() // Get the category of an item, the category makes sure you can't pick up more than one of each
	{
		return armour;
	}
}
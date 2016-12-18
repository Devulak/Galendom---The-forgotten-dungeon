package main.item.armour;

public class Chestplate extends Armour
{
	public Chestplate(String name, int armour, int price) // Constructor
	{
		super(name, armour, price);
	}
	
	public Chestplate(String name, int armour) // Constructor
	{
		this(name, armour, 0);
	}
}
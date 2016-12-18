package main.item.armour;

public class Legging extends Armour
{
	public Legging(String name, int armour, int price) // Constructor
	{
		super(name, armour, price);
	}
	
	public Legging(String name, int armour) // Constructor
	{
		this(name, armour, 0);
	}
}
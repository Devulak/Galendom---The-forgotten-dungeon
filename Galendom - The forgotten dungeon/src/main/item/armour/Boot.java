package main.item.armour;

public class Boot extends Armour
{
	public Boot(String name, int armour, int price) // Constructor
	{
		super(name, armour, price);
	}
	
	public Boot(String name, int armour) // Constructor
	{
		this(name, armour, 0);
	}
}
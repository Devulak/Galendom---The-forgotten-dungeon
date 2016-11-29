package main.item;

public class Shield extends Armour
{
	public Shield(String name, int armour, int price) // Constructor
	{
		super(name, armour, price);
	}
	
	public Shield(String name, int armour) // Constructor
	{
		this(name, armour, 0);
	}
}
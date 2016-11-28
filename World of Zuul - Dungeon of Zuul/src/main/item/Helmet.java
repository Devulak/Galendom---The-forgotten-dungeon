package main.item;

public class Helmet extends Armour
{
	public Helmet(String name, int armour, int price) // Constructor
	{
		super(name, armour, price);
	}
	
	public Helmet(String name, int armour) // Constructor
	{
		this(name, armour, 0);
	}
}
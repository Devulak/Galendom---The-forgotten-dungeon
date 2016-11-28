package main.item;

public class Gassmask extends Item
{
	public Gassmask(int amount, int price)
	{
		super("Gasmask", amount, price);
	}
	
	public Gassmask(int amount)
	{
		this(amount, 0);
	}
}

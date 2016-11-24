package main.item;

public class Gasmask extends Item
{
	public Gasmask(int amount, int price)
	{
		super("Gasmask", amount, price);
	}
	
	public Gasmask(int amount)
	{
		this(amount, 0);
	}
}

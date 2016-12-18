package main.item;

public class Potion extends Item
{
	public Potion(int amount, int price)
	{
		super("Health Potions", amount, price);
	}
	
	public Potion(int amount)
	{
		this(amount, 0);
	}
}
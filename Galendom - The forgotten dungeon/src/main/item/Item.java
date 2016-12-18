package main.item;

import java.io.Serializable;

public abstract class Item implements Serializable
{
	private String name;
	private int amount;
	protected String extra;
	private int price;

	public Item(String name, int amount, int price) // Constructor
	{
        this.name = name;
		this.amount = amount;
		this.price = price;
	}

	public Item(String name, int amount) // Constructor
	{
        this(name, amount, 0);
	}

	@Override
	public String toString() // Return value on object
	{
		String amountStr = this.amount > 0 ? " (" + this.amount + ")" : "";
		String extraStr = this.extra != null ? " (" + this.extra + ")" : "";
		String priceStr = this.price > 0 ? " (" + this.price + ")" : "";
		return this.name + amountStr + extraStr + priceStr;
	}

	public int getPrice() // Get the amount of the item, if zero, means it's not stackable
	{
		return price;
	}

	public void clearPrice() // Get the amount of the item, if zero, means it's not stackable
	{
		price = 0;
	}

	public int getAmount() // Get the amount of the item, if zero, means it's not stackable
	{
		return amount;
	}

	public void addAmount(int add) // Get the amount of the item, if zero, means it's not stackable
	{
		amount += add;
	}

	public void use()
	{
		use(1);
	}

	public void use(int amount)
	{
		this.amount -= amount;
	}
}

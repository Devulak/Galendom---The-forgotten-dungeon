package main.item;

public class Weapon extends Item
{
	private int damage;
	
	public Weapon(String name, int damage, int price)
	{
		super(name, 0, price);
		this.damage = damage;
		this.extra = damage + " dmg";
	}
	
	public Weapon(String name, int damage)
	{
		this(name, damage, 0);
	}

	public int getDamage()
	{
		return damage;
	}
}
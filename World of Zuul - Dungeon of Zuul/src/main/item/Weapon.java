package main.item;

public class Weapon extends Item
{
	private int damage;
	
	public Weapon(String name, int damage)
	{
		super(name, 0);
		this.damage = damage;
	}
	
	public String toString() // Return value on object
	{
		return name + " (" + damage + " dmg)";
	}

	public int getDamage()
	{
		return damage;
	}
}
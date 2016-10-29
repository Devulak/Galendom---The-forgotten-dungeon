package main.item;

public class Weapon extends Item
{
	private int damageMin;
	private int damageMax;
	
	public Weapon(String name, int damageMin, int damageMax)
	{
		super(name, 0);
		this.damageMin = damageMin;
		this.damageMax = damageMax;
	}

	public int getDamageMin()
	{
		return damageMin;
	}

	public int getDamageMax()
	{
		return damageMax;
	}
}
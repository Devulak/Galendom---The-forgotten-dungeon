package main.item;

public class Weapon extends Item
{
	private int damageMin;
	private int damageMax;
	
	public Weapon(String name, int damageMin, int damageMax)
	{
		super(name, 0, "weapon");
		this.damageMin = damageMin;
		this.damageMax = damageMax;
	}
}
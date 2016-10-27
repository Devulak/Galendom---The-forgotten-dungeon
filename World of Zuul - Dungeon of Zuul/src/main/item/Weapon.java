package main.item;

public class Weapon extends Item
{
	private int damageMin;
	private int damageMax;
	
	public Weapon(String name, boolean stackable, String slot, int damageMin, int damageMax)
	{
		super(name, stackable, slot);
		this.damageMin = damageMin;
		this.damageMax = damageMax;
	}
}

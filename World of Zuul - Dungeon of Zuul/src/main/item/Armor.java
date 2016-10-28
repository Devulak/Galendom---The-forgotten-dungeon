package main.item;

public class Armor extends Item
{
	private int armor;
	
	public Armor(String name, String slot, int armor) // Constructor
	{
		super(name, 0, slot);
		this.armor = armor;
	}
}
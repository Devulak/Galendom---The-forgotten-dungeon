package main.item;

public class Shield extends Armor
{
	private int reflect;
	
	public Shield(String name, int armor, int reflect) // Constructor
	{
		super(name, armor);
		this.reflect = reflect;
	}
}
package main.item;

public class Shield extends Armour
{
	private int reflect;
	
	public Shield(String name, int armour, int reflect) // Constructor
	{
		super(name, armour);
		this.reflect = reflect;
	}
}
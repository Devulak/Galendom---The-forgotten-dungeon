package main.item;

public class GasMask extends Item
{
	public GasMask(int amount, int price)
	{
		super("Gasmask", amount, price);
	}
	
	public GasMask(int amount)
	{
		this(amount, 0);
	}
}

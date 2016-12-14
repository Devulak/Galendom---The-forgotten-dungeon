package main;

public class singleton
{
	private static singleton instance = null;
	private static int scenario = 0;
	
	private singleton()
	{
		
	}
	
	/*public static singleton getInstance()
	{
		if(instance == null)
		{
			instance = new singleton();
		}
		return instance;
	}*/
	
	public static void setScenario(int value)
	{
		scenario = value;
	}
	
	public static int getScenario()
	{
		return scenario;
	}
}

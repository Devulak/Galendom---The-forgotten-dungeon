package main.creature;

public class Monster extends LevelAbleCreature {

	private int maxPoints;

	public Monster(int lvl) 
	{
		super(lvl);
		level = lvl;
		experienceMax = EXP_BASE + level * EXP_REQUIRED_RATIO;
		maxHealth = HEALTH_BASE_AMOUNT + level * HEALTH_GAIN_AMOUNT;
		health = maxHealth;
		maxPoints = POINTS * level;
	}

	@Override
	public int givePlayerPoints() 
	{
		return maxPoints;
	}
	
	@Override
	public int getHealth()
	{
		return health;
	}
	
	@Override
	public int getMaxHealth()
	{
		return maxHealth;
	}

}

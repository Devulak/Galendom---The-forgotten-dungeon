package main.creature;

public class Monster extends LevelAbleCreature {

	public Monster(int lvl) 
	{
		super(lvl);
		level = lvl;
		experienceMax = EXP_BASE + level * EXP_REQUIRED_RATIO;
		maxHealth = HEALTH_BASE_AMOUNT + level * HEALTH_GAIN_AMOUNT;
		health = maxHealth;
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

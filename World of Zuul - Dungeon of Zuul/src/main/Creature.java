package main;

import java.util.*;
import main.item.*;

public class Creature {
	
    protected int level; // The creature level, this is for scaling when created or gaining a level
    protected int experience = 0; // The amount of experience the creature has (this always starts with "0")
    protected int experienceMax; // The max amount of experience the creature needs for a level up
	
	private static final int experienceRequiredBase = 400; // The amount of experience you need atleast per level
	private static final int experienceRequiredRatio = 200; // The amount of experience you need further more per level
	private static final int experienceGainRatio = 200; // The amount of experience you get per level of the monster
	
    private int health; // Current health
    private int maxHealth; // Maximum amount of health
	private static final int healthBaseAmount = 8; // The base amount of health you have
	private static final int healthGainAmount = 4; // The amount of health you gain each level
	
	protected Inventory inventory = new Inventory(); // Inventory full of stuff (or not)!

    public Creature(int lvl) { //this is a constructor for the creatures
		level = lvl;
		experienceMax = experienceRequiredBase + level*experienceRequiredRatio;
		maxHealth = healthBaseAmount + level*healthGainAmount;
		health = maxHealth;
	}
	
	protected int getHealth()
	{
		return health;
	}
	
	protected int getMaxHealth()
	{
		return maxHealth;
	}
	
	protected int getExperience()
	{
		return experience;
	}
	
	protected int getMaxExperience()
	{
		return experienceMax;
	}
	
	protected String printLevel()
	{
		return "level " + level;
	}
	
	protected int getLevel()
	{
		return level;
	}
	
	protected void gainExperience(Creature Enemy)
	{
		experience += experienceGainRatio*Enemy.getLevel();
		System.out.println("You've gained " + experienceGainRatio*Enemy.getLevel() + " xp");
		while(experience >= experienceMax)
		{
			experience -= experienceMax;
			level++;
			experienceMax = experienceRequiredBase + level*experienceRequiredRatio;
			
			maxHealth = healthBaseAmount + level*healthGainAmount;
			health += healthGainAmount;
			
			System.out.println("You've level up to " + printLevel() + "!");
		}
	}
	
	public int getArmour()
	{
		int armour = 0;
		for (Item item : inventory.getContent())
		{
			if(item instanceof Armour)
			{
				armour += ((Armour)item).getArmour();
			}
		}
		return armour;
	}
	
	public int getStrength()
	{
		int strength = 0;
		int damageMinBoost = 0;
		int damageMaxBoost = 0;
		for (Item item : inventory.getContent())
		{
			if(item instanceof Weapon)
			{
				damageMinBoost += ((Weapon)item).getDamageMin();
				damageMaxBoost += ((Weapon)item).getDamageMax();
			}
		}
		return strength;
	}
	
	protected int attack(Creature enemy)
	{
		int damageMinBoost = 0;
		int damageMaxBoost = 0;
		int damageReduce = getArmour();
		for (Item item : inventory.getContent())
		{
			if(item instanceof Weapon)
			{
				damageMinBoost += ((Weapon)item).getDamageMin();
				damageMaxBoost += ((Weapon)item).getDamageMax();
			}
		}
		damageMaxBoost -= damageMinBoost; // Remove min damage, min damage defined outside random
		int roll = (int) Math.round(Math.random()*(level + damageMaxBoost)) + level + damageMinBoost - damageReduce; // Calculation for a roll
		if(roll < 0)
		{
			roll = 0;
		}
		enemy.health -= roll;
		return roll;
	}
	
	protected int takeDamage()
	{
		getArmour();
		return 0;
	}
	
	protected int heal()
	{
		int healingAmount = (int) (maxHealth * 0.4); // 40% of the max health restored, if amount is with digits, disregard those.
		if(health+healingAmount > maxHealth)
		{
			healingAmount = maxHealth-health;
		}
		health += healingAmount;
		return healingAmount;
	}
}
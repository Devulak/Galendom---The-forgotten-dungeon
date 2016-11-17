package main;

import java.util.*;
import main.item.*;

public class Creature {
	
    protected int level; // The creature level, this is for scaling when created or gaining a level
    protected int experience = 0; // The amount of experience the creature has (this always starts with "0")
    protected int experienceMax; // The max amount of experience the creature needs for a level up
	
	private static final int experienceRequiredBase = 4; // The amount of experience you need atleast per level
	private static final int experienceRequiredRatio = 2; // The amount of experience you need further more per level
	private static final int experienceGainRatio = 2; // The amount of experience you get per level of the monster
	
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
	
	protected int getLevel()
	{
		return level;
	}
	
	protected void gainExperience(Creature Enemy)
	{
		experience += experienceGainRatio*Enemy.getLevel();
		while(experience >= experienceMax)
		{
			experience -= experienceMax;
			level++;
			experienceMax = experienceRequiredBase + level*experienceRequiredRatio;
			
			maxHealth = healthBaseAmount + level*healthGainAmount;
			health += healthGainAmount;
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
	
	public boolean hasShield()
	{
		for (Item item : inventory.getContent())
		{
			if(item instanceof Shield)
			{
				return true;
			}
		}
		return false;
	}
	
	public int getStrength()
	{
		int strength = level;
		for (Item item : inventory.getContent())
		{
			if(item instanceof Weapon)
			{
				strength += ((Weapon)item).getDamage();
			}
		}
		return strength;
	}
	public boolean hasKey()
	{
		for (Item item : inventory.getContent())
		{
			if(item instanceof Key)
			{
				return true;
			}
		}
		return false;
	}
	
	protected int rollDamage(Creature enemy)
	{
		double hitPoints = getStrength();
		// This will roll the amount of damage given that the creature attacks with no resistance, aka. no armor against him
		if(Math.random() <= 0.1) // did he roll a crit (10%)
		{
			hitPoints = hitPoints * 1.5; // 150% damage on crit, if uneven, calculate extra damage point
		}
		return enemy.takeDamage(hitPoints);
	}
	
	protected int takeDamage(double hitPoints)
	{
		hitPoints = hitPoints * (1 - getArmour() / 50); // Each point of armour reduces damage by 2% (max 40 armour (80% reduction) may be possible)
		int hitPointsTaken = (int) Math.round(hitPoints); // Rounds to neearest point
		if(hasShield() && Math.random() <= 0.1) // Did he roll a block (10%)
		{
			hitPointsTaken = 0;
		}
		health -= hitPointsTaken;
		return hitPointsTaken;
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
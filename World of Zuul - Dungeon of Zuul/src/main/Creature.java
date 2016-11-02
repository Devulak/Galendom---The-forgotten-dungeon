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
	
    protected int health; // Current health
    protected int healthMax; // Maximum amount of health
	private static final int healthBaseAmount = 8; // The base amount of health you have
	private static final int healthGainAmount = 4; // The amount of health you gain each level
	
	protected Inventory inventory = new Inventory(); // Inventory full of stuff (or not)!

    public Creature(int lvl) { //this is a constructor for the creatures
		level = lvl;
		experienceMax = experienceRequiredBase + level*experienceRequiredRatio;
		healthMax = healthBaseAmount + level*healthGainAmount;
		health = healthMax;
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
			
			healthMax = healthBaseAmount + level*healthGainAmount;
			health += healthGainAmount;
			
			System.out.println("You've level up to " + printLevel() + "!");
		}
	}
	
	protected String printHealth()
	{
		int blockTotal = 20;
		int blockHealth = (int) Math.ceil((double) health/healthMax*blockTotal);
		String blocks = "";
		if (this.health < 0)
			health = 0;
		for (int i = 1; i <= blockTotal; i++)
		{
			if(blockHealth >= i)
				blocks += "■";
			else
				blocks += "□";
		}
		return blocks + " " + health + "/" + healthMax + " hp";
	}
	
	protected String getExperienceBar()
	{
		int blockTotal = 10;
		int blockHealth = (int) Math.ceil((double) experience/experienceMax*blockTotal);
		String blocks = "";
		for (int i = 1; i <= blockTotal; i++)
		{
			if(blockHealth >= i)
				blocks += "■";
			else
				blocks += "□";
		}
		return blocks + " " + experience + "/" + experienceMax + " xp";
	}
	
	protected void attack(Creature enemy)
	{
		int damageMinBoost = 0;
		int damageMaxBoost = 0;
		int damageReduce = 0;
		for (Item item : inventory.getContent())
		{
			if(item instanceof Weapon)
			{
				damageMinBoost += ((Weapon)item).getDamageMin();
				damageMaxBoost += ((Weapon)item).getDamageMax();
			}
		}
		for (Item item : enemy.inventory.getContent())
		{
			if(item instanceof Armour)
			{
				damageReduce += ((Armour)item).getArmour();
			}
		}
		damageMaxBoost -= damageMinBoost; // Remove min damage, min damage defined outside random
		int roll = (int) Math.round(Math.random()*(level + damageMaxBoost)) + level + damageMinBoost - damageReduce; // Calculation for a roll
		if(roll < 0)
		{
			roll = 0;
		}
		System.out.println("Rolled " + roll + " dmg!");
		enemy.health -= roll;
	}
	
	protected void heal()
	{
		health += healthGainAmount;
		if(health > healthMax)
		{
			health = healthMax;
		}
	}
}
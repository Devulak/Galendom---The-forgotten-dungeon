package main;

import java.util.ArrayList;
import java.util.List;
import main.item.*;

public class Creature {
	
    private int level; // The creature level, this is for scaling when created or gaining a level
    private int experience = 0; // The amount of experience the creature has (this always starts with "0")
    private int experienceMax; // The max amount of experience the creature needs for a level up
	
	private static final int experienceRequiredBase = 400; // The amount of experience you need atleast per level
	private static final int experienceRequiredRatio = 200; // The amount of experience you need further more per level
	private static final int experienceGainRatio = 200; // The amount of experience you get per level of the monster
	
    protected int health; // Current health
    private int healthMax; // Maximum amount of health
	private static final int healthBaseAmount = 8; // The base amount of health you have
	private static final int healthGainAmount = 4; // The amount of health you gain each level
	
	protected List<Item> inventory = new ArrayList<>(); // Inventory full of stuff!

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
	
	protected void attack(Creature Enemy)
	{
		int damageMinBoost = 0;
		int damageMaxBoost = 0;
		for (Item Item : inventory)
		{
			if("weapon".equals(Item.getCategory()))
			{
				damageMinBoost += ((Weapon)Item).getDamageMin();
				damageMaxBoost += ((Weapon)Item).getDamageMax();
			}
		}
		damageMaxBoost -= damageMinBoost; // Remove min damage, min damage defined outside random
		int roll = (int) Math.round(Math.random()*(level + damageMaxBoost)) + level + damageMinBoost; // Calculation for a roll
		System.out.println("Rolled " + roll + " dmg!");
		Enemy.health -= roll;
	}
}
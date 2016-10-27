package main;

import java.util.ArrayList;
import java.util.List;

public class Creature {
	
    private int level; // The creature level, this is for scaling when created or gaining a level
    private int experience = 0; // The amount of experience the creature has (this always starts with "0")
    private int experienceMax; // The max amount of experience the creature needs for a level up
	
    protected int health; // Current health
    private int healthMax; // Maximum amount of health
	
    private int damageMin; // Maximum amount of damage
    private int damageMax; // Minimum amount of damage

    public Creature(int lvl) { //this is a constructor for the creatures
		level = lvl;
		experienceMax = 400 + level*200;
		healthMax = 8 + level*4;
		health = healthMax;
		damageMin = level;
		damageMax = level*2;
	}
	
	protected String printLevel()
	{
		return "level " + level;
	}
	
	protected String printHealth()
	{
		int blockTotal = 20;
		int blockHealth = (int) Math.ceil((double) health/healthMax*blockTotal);
		String blocks = "";
		for (int i = 1; i <= blockTotal; i++)
		{
			if(blockHealth >= i)
				blocks += "■";
			else
				blocks += "□";
		}
		return blocks + " " + health + "/" + healthMax + " hp";
	}
	
	protected String getExperience()
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
		Enemy.health -= (int) Math.round(Math.random()*(damageMax - damageMin)) + damageMin;
	}
}
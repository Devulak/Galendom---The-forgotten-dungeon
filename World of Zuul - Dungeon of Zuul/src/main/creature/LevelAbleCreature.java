/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.creature;

import main.item.Armour;
import main.item.Item;
import main.item.Shield;
import main.item.Weapon;

/**
 *
 * @author Nicolai
 */
public abstract class LevelAbleCreature extends Creature implements HasLevel, CanGivePoints {

	private int maxPoints;
	
	public LevelAbleCreature(int lvl) {
		super(lvl);
	}

	public int getHealth()
	{
		return health;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public int getExperience()
	{
		return experience;
	}
	
	public int getMaxExperience()
	{
		return experienceMax;
	}
	
	@Override
	public int getLevel()
	{
		return level;
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
	
	public int rollDamage(LevelAbleCreature enemy)
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
		hitPoints = hitPoints * (1 - (double) getArmour() / 50); // Each point of armour reduces damage by 2% (max 40 armour (80% reduction) may be possible)
		int hitPointsTaken = (int) Math.round(hitPoints); // Rounds to neearest point
		if(hasShield() && Math.random() <= 0.1) // Did he roll a block (10%)
		{
			hitPointsTaken = 0;
		}
		health -= hitPointsTaken;
		return hitPointsTaken;
	}

	@Override
	public int givePlayerPoints() 
	{
		return maxPoints;
	}
	
}
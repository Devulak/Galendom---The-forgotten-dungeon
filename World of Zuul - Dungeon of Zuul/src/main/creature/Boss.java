/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.creature;

/**
 *
 * @author Nicolai
 */
public class Boss extends LevelAbleCreature {

	private int maxPoints;
	private final int EXTRAPOINTS = 50; //How many points will the player be awarded if he defeats the boss?
	
	public Boss(int lvl) 
	{
		super(lvl);
		level = lvl;
		experienceMax = EXP_BASE + level * EXP_REQUIRED_RATIO;
		maxHealth = HEALTH_BASE_AMOUNT + level * HEALTH_GAIN_AMOUNT;
		health = maxHealth;
		maxPoints = POINTS * level + EXTRAPOINTS;
	}

	@Override
	public int givePlayerPoints() 
	{
		return maxPoints;
	}

}

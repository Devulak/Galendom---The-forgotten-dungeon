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
public class Player extends LevelAbleCreature {

	public Player(int lvl) {
		super(lvl);
		level = lvl;
		experienceMax = EXP_BASE + level * EXP_REQUIRED_RATIO;
		maxHealth = HEALTH_BASE_AMOUNT + level * HEALTH_GAIN_AMOUNT;
		health = maxHealth;
	}

	public void gainExperience(LevelAbleCreature enemy)
	{
		experience += EXPERIENCE_GAIN_RATIO*enemy.getLevel();
		if(experience >= experienceMax)
		{
			experience -= experienceMax;
			level++;
			experienceMax = EXP_BASE + level*EXP_REQUIRED_RATIO;
			
			maxHealth = HEALTH_BASE_AMOUNT + level*HEALTH_GAIN_AMOUNT;
			health += HEALTH_GAIN_AMOUNT;
		}
	}
	
	public int heal()
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

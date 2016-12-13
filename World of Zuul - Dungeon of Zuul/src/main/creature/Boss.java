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

	public Boss(int lvl) 
	{
		super(lvl);
		level = lvl;
		experienceMax = EXP_BASE + level * EXP_REQUIRED_RATIO;
		maxHealth = HEALTH_BASE_AMOUNT + level * HEALTH_GAIN_AMOUNT;
		health = maxHealth;
	}

}

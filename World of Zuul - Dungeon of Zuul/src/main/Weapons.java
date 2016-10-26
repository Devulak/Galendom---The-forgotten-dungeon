/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Nicolai
 */
public class Weapons {

    private int maxDamage;
    private int minDamage;
    private final String weaponName;

    public Weapons(String n, int minD, int maxD) {
	weaponName = n;
	minDamage = minD;
	maxDamage = maxD;
    }

    public String getWeaponName(){
	return weaponName;
    }
    
    public int getWeaponMinDamage(){
        return minDamage;
    }
}

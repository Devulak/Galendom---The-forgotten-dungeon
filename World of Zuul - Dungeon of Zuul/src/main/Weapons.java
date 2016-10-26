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
public class Weapons extends Items {

    private int maxDamage;
    private int minDamage;
    private final String weaponName;

    public Weapons(String description, int uniqueID, String n, int minD, int maxD) {
        super(description, uniqueID);
	this.weaponName = n;
	this.minDamage = minD;
	this.maxDamage = maxD;
    }

    public String getWeaponName(){
	return weaponName;
    }
    
    public int getWeaponMinDamage(){
        return minDamage;
    }
}

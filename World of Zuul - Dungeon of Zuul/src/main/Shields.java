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
public class Shields extends Armor {

    private String shieldName;
    private int reflectChance;

    public Shields(String description, int uniqueID, String n, int a, int r) {
	super(description, uniqueID, a);
	shieldName = n;
	reflectChance = r;
    }

    public String getShieldName() {
	return shieldName;
    }
}

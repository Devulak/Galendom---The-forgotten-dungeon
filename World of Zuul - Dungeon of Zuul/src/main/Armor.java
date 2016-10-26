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
public class Armor {

    private int armorPoints;
    private String armorName;

    public Armor(String n, int a) {
	armorName = n;
	armorPoints = a;
    }

    public Armor(int a) {
	armorPoints = a;
    }

    public String getArmorName() {
	return armorName;
    }
}

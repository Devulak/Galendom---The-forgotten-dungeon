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
public class Boots extends Armor {

    private String bootsName;

    public Boots(String description, int uniqueID, String n, int a) {
	super(description, uniqueID, a);
	bootsName = n;
    }

    public String getBootsName() {
	return bootsName;
    }
}

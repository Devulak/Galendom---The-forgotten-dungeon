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
public class Leggings extends Armor {

    private String leggingsName;

    public Leggings(String n, int a) {
	super(a);
	leggingsName = n;
    }

    public String getLeggingsName() {
	return leggingsName;
    }
}
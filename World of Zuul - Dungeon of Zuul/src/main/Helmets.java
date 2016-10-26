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
public class Helmets extends Armor {

    private String helmetName;

    public Helmets(String n, int a) {
	super(a);
	helmetName = n;
    }

    public String getHelmetName() {
	return helmetName;
    }
}

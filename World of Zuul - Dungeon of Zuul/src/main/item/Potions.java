/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.item;

/**
 *
 * @author nicol
 */
public class Potions extends Item {

    private int healthReceived;

    public Potions(String description, int uniqueID, int h) {
        super(description, uniqueID);
        healthReceived = h;
    }

    public int getHealth() {
        return healthReceived;
    }

}

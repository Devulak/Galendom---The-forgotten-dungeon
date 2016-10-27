/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.item;

/**
 *
 * @author Nicolai
 */
public class Chestplates extends Armor {

    private String chestplateName;

    /* Constructor for chestplate */
    public Chestplates(String description, int uniqueID, String n, int a) {
        super(description, uniqueID, a);
        chestplateName = n;

    }

}

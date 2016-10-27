/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.item;

import main.item.Item;

/**
 *
 * @author Nicolai
 */
public class Armor extends Item {

    private int armorPoints;
    private String armorName;

    /* Constructor for armor in general */
    public Armor(String description, int uniqueID, int a) {
        super(description, uniqueID);
        armorPoints = a;
    }

    public String getArmorName() {
        return armorName;
    }
}

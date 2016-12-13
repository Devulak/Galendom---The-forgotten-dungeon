/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.item.armour;

/**
 *
 * @author Nicolai
 */
public class Chestplate extends Armour
{
	public Chestplate(String name, int armour, int price) // Constructor
	{
		super(name, armour, price);
	}
	
	public Chestplate(String name, int armour) // Constructor
	{
		this(name, armour, 0);
	}
}
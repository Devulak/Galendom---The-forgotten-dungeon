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
public class Key extends Item {
    
    public Key(String name, int amount) 
	{
		super("Key", amount);
    }
    
	public boolean hasKey(){
		return true;
	}
    
}

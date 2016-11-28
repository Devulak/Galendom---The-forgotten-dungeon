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
public class Key extends Item
{
	public Key(int amount, int price)
	{
		super("Keys", amount, price);
	}
	
	public Key(int amount)
	{
		this(amount, 0);
	}
}
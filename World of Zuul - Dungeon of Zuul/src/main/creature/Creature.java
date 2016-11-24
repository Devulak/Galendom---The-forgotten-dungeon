package main.creature;

import java.util.*;
import main.item.*;

public abstract class Creature 
{
	
	protected int level; // The creature level, this is for scaling when created or gaining a level
    protected int experience = 0; // The amount of experience the creature has (this always starts with "0")
    protected int experienceMax; // The max amount of experience the creature needs for a level up
	
    protected int health; // Current health
    protected int maxHealth; // Maximum amount of health
	
	
	public Inventory inventory = new Inventory(); // Inventory full of stuff (or not)!

    public Creature(int lvl) 
	{ //this is a constructor for the creatures
	}
	
}
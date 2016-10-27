package main;

import java.util.HashMap;

public class Creatures {

    protected int health; //the health
    private int level; //the level
    protected int damage; //the damage
    private int givesXP; //the amount of XP the monster gives when it dies
    private int XP; //the XP the hero has

    public Creatures(int l) { //this is a constructor for the creatures
		health = 8 + l*4;
		level = l;
		damage = l*2;
		givesXP = l*2;
	}
	
    protected Creatures(){ //this is a constructor for the hero
		String[] inventoryHero = new String[8]; //making inventory
		health = 12;
		level = 1;
		damage = 5;
		XP = 0;
	}
}
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
    
    Creatures monster1 = new Creatures(1);
    Creatures monster2 = new Creatures(2);
    Creatures monster2a = new Creatures(3);
    Creatures monster3 = new Creatures(4);
    Creatures monster3a = new Creatures(5); 
    Creatures monster4 = new Creatures(6); 
    Creatures monster4a = new Creatures(7); 
    Creatures monster5 = new Creatures(8); 
    Creatures monster5a = new Creatures(9); 
    Creatures monster6 = new Creatures(10); 
    Creatures monster7 = new Creatures(11); 
    Creatures monster8 = new Creatures(12); 
    
    protected Creatures(){ //this is a constructor for the hero
        String[] inventoryHero = new String[8]; //making inventory
        health = 12;
        level = 1;
        damage = 5;
        XP = 0;
        }
}
class vendor {
    String[] inventoryVendor = new String[8]; //mkaing inventory
}
 
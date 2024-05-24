package Creature.Plant;
import Creature.Zombie.*;

public class Squash extends Plant{
    private int idx = 6;
    public static int cooldownplant = 20;

    public Squash(){
        super("Squash", 5000, 5000, 1, false, 50, 1, 20, false);
        this.idx = 6;
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        super.setHealth(0);
    }
    
    public int idx() {return this.idx;}
}

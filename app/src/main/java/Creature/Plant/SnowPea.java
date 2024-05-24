package Creature.Plant;
import Creature.Zombie.*;

public class SnowPea extends Plant{
    private int idx = 5;
    public SnowPea(){
        super("SnowPea", 100, 25, 4, false, 175, -1, 10, true);
        this.idx = 5;
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        zombie.Slowed();
    }
    
    public int idx() {return this.idx;}
}

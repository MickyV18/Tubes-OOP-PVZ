package Creature.Plant;
import Creature.Zombie.*;

public class TangleKelp extends Plant{
    private int idx = 8;
    public TangleKelp(){
        super("Tangle Kelp", 5000, 5000, 1, true, 50, 1, 20, false);
        this.idx = 8;
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        super.setHealth(0);
    }
    
    public int idx() {return this.idx;}
}

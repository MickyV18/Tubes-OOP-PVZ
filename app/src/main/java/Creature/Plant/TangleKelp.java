package Creature.Plant;
import Creature.Zombie.*;
import Creature.*;

public class TangleKelp extends Plant{
    public TangleKelp(){
        super("Tangle Kelp", 5000, 5000, 1, true, 50, 1, 10);
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        super.setHealth(0);
    }
}

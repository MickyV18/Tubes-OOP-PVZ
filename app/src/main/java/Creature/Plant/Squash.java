package Creature.Plant;
import Creature.Zombie.*;

public class Squash extends Plant{
    public Squash(){
        super("Squash", 5000, 5000, 1, false, 50, 1, 20, false);
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        super.setHealth(0);
    }
}

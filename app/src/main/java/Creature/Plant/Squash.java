package Creature.Plant;
import Creature.Zombie.*;
import Creature.*;

public class Squash extends Plant{
    public Squash(){
        super("Squash", 100, 5000, 0, false, 50, 1, 20);
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        super.setHealth(0);
    }
}

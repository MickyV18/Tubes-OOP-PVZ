package Creature.Plant;
import Creature.Zombie.*;

public class SnowPea extends Plant{
    public SnowPea(){
        super("SnowPea", 100, 25, 4, false, 175, -1, 10, true);
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        zombie.Slowed();
    }
}

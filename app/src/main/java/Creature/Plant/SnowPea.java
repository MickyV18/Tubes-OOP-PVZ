package Creature.Plant;
import Creature.Zombie.*;
import Creature.*;

public class SnowPea extends Plant{
    public SnowPea(){
        super("SnowPea", 100, 25, 4, false, 175, -1, 10);
    }

    public void attack (Zombie zombie){
        super.attack(zombie);
        System.out.println("KEATTACK");
        zombie.Slowed();
    }
}

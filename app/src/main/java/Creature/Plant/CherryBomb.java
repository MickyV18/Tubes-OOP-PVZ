package Creature.Plant;
import Creature.Zombie.*;

public class CherryBomb extends Plant{
    public CherryBomb(){
        super("Cherry Bomb", 5000, 5000, 0, false, 50, 1, 10);
    }
    
    public void attack (Zombie zombie){
        super.attack(zombie);
        super.setHealth(0);
    }
}

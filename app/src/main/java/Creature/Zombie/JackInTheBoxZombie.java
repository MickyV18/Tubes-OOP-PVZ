package Creature.Zombie;
import Creature.Plant.*;

public class JackInTheBoxZombie extends Zombie {
    public JackInTheBoxZombie(){
        super("JackInTheBox Zombie", 100, 1000, 1, false);
    }

    public void attack(Plant plant) {
        plant.setHealth(0);
        super.setHealth(0);
    }
}
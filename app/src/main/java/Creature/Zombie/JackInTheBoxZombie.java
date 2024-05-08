package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;

public class JackInTheBoxZombie extends Zombie {
    public JackInTheBoxZombie(){
        super("JackInTheBox Zombie", 100, 1000, 1, false);
    }

    public void attack(Zombie zombie, Plant plant) {
        plant.setHealth(0);
        zombie.setHealth(0);
    }
}
package app.src.main.java.Creature.Zombie;
import app.src.main.java.Creature.*;
import app.src.main.java.Creature.Plant.*;

public class JackInTheBoxZombie extends Zombie {
    public JackInTheBoxZombie(){
        super("JackInTheBox Zombie", 100, 1000, 1, false);
    }

    public void attack(Zombie zombie, Plant plant) {
        if (plant.getHealth() <= zombie.getAtkDmg()){
            plant.setHealth(0);
        }else{
            plant.setHealth(zombie.getHealth() - plant.getAtkDmg());
        }
        zombie.setHealth(0);
    }
}
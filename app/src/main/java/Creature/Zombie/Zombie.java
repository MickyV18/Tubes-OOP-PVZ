package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;

public abstract class Zombie extends Creature{
    private int slowed = 0;
    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic){
        super(name, health, attack_damage, attack_speed, is_aquatic);
    }

    public void attack(Zombie zombie, Plant plant) {
        if (plant.getHealth() <= zombie.getAtkDmg()){
            plant.setHealth(0);
        }else{
            plant.setHealth(plant.getHealth() - zombie.getAtkDmg());
        }
    }

    public void Slowed(){
        slowed = 3;
    }

    public int getSlowed(){
        return slowed;
    }

    public void SlowedTimeDecrease(){
        slowed -= 1;
    }
}

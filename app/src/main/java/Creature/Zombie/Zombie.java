package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;
import Tiles.*;

public abstract class Zombie extends Creature{
    private int slowed = 0;
    public Zombie(String name, int health, int attack_damage, float attack_speed, boolean is_aquatic){
        super(name, health, attack_damage, attack_speed, is_aquatic);
    }

    public void attack(Plant plant) {
        if (plant.getHealth() <= super.getAtkDmg()){
            plant.setHealth(0);
        }else{
            plant.setHealth(plant.getHealth() - super.getAtkDmg());
        }
    }

    public void Slowed(){
        slowed = 3;
        setAtkSpd(super.getAtkSpd() + (super.getAtkSpd() / 2));
    }

    public void unSlowed(){
        setAtkSpd(2 / 3 * super.getAtkSpd());

    }

    public int getSlowed(){
        return slowed;
    }

    public void SlowedTimeDecrease(){
        slowed -= 1;
    }
}

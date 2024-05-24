package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;

public abstract class Zombie extends Creature{
    private int slowed = -1;
    private boolean normal = true;
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
        normal = false;
        if (slowed == -1){
            setAtkSpd(super.getAtkSpd() + (super.getAtkSpd() / 2));
        }
        slowed = 3;
    }

    public void unSlowed(){
        if (!normal){
            System.out.println(super.getAtkSpd());
            setAtkSpd((2 * super.getAtkSpd()) / 3);
            System.out.println(super.getAtkSpd());
            normal = true;
        }
        slowed = -1;

    }

    public int getSlowed(){
        return slowed;
    }

    public void SlowedTimeDecrease(){
        slowed -= 1;
    }
}

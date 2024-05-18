package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;
import Tiles.*;

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
        System.out.println("Keslowed");
        normal = false;
        slowed = 3;
        System.out.println(super.getAtkSpd() + (super.getAtkSpd() / 2));
        setAtkSpd(super.getAtkSpd() + (super.getAtkSpd() / 2));
        System.out.println(super.getAtkSpd());
    }

    public void unSlowed(){
        if (!normal){
            System.out.println("UNSLOWED");
            System.out.println(super.getAtkSpd());
            setAtkSpd((2 * super.getAtkSpd()) / 3);
            System.out.println(super.getAtkSpd());
            normal = true;
        }

    }

    public int getSlowed(){
        return slowed;
    }

    public void SlowedTimeDecrease(){
        slowed -= 1;
    }
}

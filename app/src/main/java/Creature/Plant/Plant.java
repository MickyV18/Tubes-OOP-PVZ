package Creature.Plant;
import Creature.Zombie.*;
import Creature.*;

public abstract class Plant extends Creature {
    private int cost;
    private int range;
    private int cooldown;

    public Plant(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic, int cost, int range, int cooldown){
        super(name, health, attack_damage, attack_speed, is_aquatic);

        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    public void attack(Zombie zombie) {
        if (zombie.getHealth() <= super.getAtkDmg()){
            zombie.setHealth(0);
        }else{
            zombie.setHealth(zombie.getHealth() - super.getAtkDmg());
        }
    }

    public int getCost() {
        return cost;
    }

    public int getRange() {
        return range;
    }

    public int getCoolDown() {
        return cooldown;
    }
}

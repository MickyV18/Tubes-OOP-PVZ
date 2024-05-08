package app.src.main.java.Creature.Plant;
import app.src.main.java.Creature.Zombie.*;
import app.src.main.java.Creature.*;

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

    public void attack(Zombie zombie, Plant plant) {
        if (zombie.getHealth() <= plant.getAtkDmg()){
            zombie.setHealth(0);
        }else{
            zombie.setHealth(zombie.getHealth() - plant.getAtkDmg());
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

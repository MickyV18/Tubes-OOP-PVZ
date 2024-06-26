package Creature.Plant;
import Creature.Zombie.*;
import Creature.*;

public abstract class Plant extends Creature {
    private int cost;
    private int range;
    private int cooldown;
    private boolean firstattack;
    private int idx;
    public static int cooldownplant;

    public Plant(String name, int health, int attack_damage, float attack_speed, boolean is_aquatic, int cost, int range, int cooldown, boolean firstattack) {
        super(name, health, attack_damage, attack_speed, is_aquatic);

        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.firstattack = firstattack;
    }

    public boolean getFirstAttack(){
        return firstattack;
    }

    public void setFirstAttack(){
        firstattack = false;
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

    public boolean isSunEnough(Plant plant, int sun) {
        if (sun >= plant.getCost()) {
            sun -= plant.getCost();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isCooldown(long currentTime, long time_stamp){
        long time = currentTime - time_stamp;
        int duration = (int) time;
        duration = duration/1000;

        if (duration%cooldown == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public abstract int idx();
}

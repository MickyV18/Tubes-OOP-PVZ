package Creature;

import Creature.Zombie.Zombie;
import Creature.Plant.Plant;

public abstract class Creature {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;
    private boolean is_aquatic;
    private long time_stamp = System.currentTimeMillis();

    public Creature(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.is_aquatic = is_aquatic;
    }

    public long getTimeCreated() {
        return time_stamp;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAtkDmg() {
        return attack_damage;
    }

    public int getAtkSpd() {
        return attack_speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return health == 0;
    }

    public boolean isAquatic() {
        return is_aquatic;
    }

    public void increase_time_stamp() {
        time_stamp++;
    }
    public void reset_time_stamp() {
        time_stamp = 0;
    }

    public abstract void attack(Zombie zombie, Plant plant);
}

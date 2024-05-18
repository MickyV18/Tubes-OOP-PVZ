package Tiles;

import Creature.Plant.Plant;
import Creature.Zombie.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile {
    private List<Zombie> zombies;
    private Plant plant = null;

    public Tile() {
        this.zombies = new ArrayList<Zombie>();
    }

    public void addZombie(Zombie zombie) {
        this.zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        this.zombies.remove(zombie);
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public boolean hasZombie() {
        return !zombies.isEmpty();
    }

    public Plant getPlant() {
        // System.out.println(plant);
        return plant;
    }

    public void addPlant(Plant plant) {
        // System.out.println(plant);
        if (this.plant == null) {
            this.plant = plant;
        }
        // System.out.println(plant);
    }

    public void removePlant() {
        
        // System.out.println(plant);
        this.plant = null;
        // System.out.println(plant);
    }
}

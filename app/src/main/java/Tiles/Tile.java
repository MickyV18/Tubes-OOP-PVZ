package Tiles;

import Creature.*;
import Creature.Plant.Plant;
import Creature.Zombie.*;
import java.util.ArrayList;
import java.util.List;
import Sun.*;

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

    public void removeAllZombie() {        
        zombies.clear();
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public boolean hasZombie() {
        return !zombies.isEmpty();
    }

    public Plant getPlant() {
        return plant;
    }

    public void addPlant(Plant plant) {
        if (this.plant == null) {
            this.plant = plant;
            Sun.decreaseSun(plant.getCost());
        }
    }

    public <T extends Tile> void removePlant() {
        this.plant = null;
    }

    public <T extends Creature> void removeCreature(T entity, int row, int col){
        if (entity.isDead()){
            if (entity instanceof Zombie){
                removeZombie((Zombie) entity);
            } else if (entity instanceof Plant){
                removePlant();
            }
        }
    }
}

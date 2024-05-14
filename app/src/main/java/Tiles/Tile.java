package Tiles;

import Creature.Zombie.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile {
    private List<Zombie> zombies;

    public Tile(){
        this.zombies = new ArrayList<Zombie>();
    }

    public void addZombie(Zombie zombie) { this.zombies.addLast(zombie); }
    public void removeZombie(Zombie zombie) { this.zombies.remove(zombie); }
}  

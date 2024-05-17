package Tiles;

import java.util.List;

import Creature.Plant.*;
import Creature.Zombie.Zombie;

public class WaterTile extends Tile {
    private Plant plant;
    private boolean lilyPlanted;
    private int lilyBoost;

    public WaterTile() {
        super();
        this.plant = null;
        this.lilyPlanted = false;
    }

    public void addPlant(Plant plant) {
        if (this.plant == null && plant instanceof Lilypad || plant instanceof TangleKelp) {
            lilyPlanted = true;
            this.lilyBoost = plant.getHealth();
        } else if (lilyPlanted) {
            this.plant = plant;
            this.plant.setHealth(plant.getHealth() + this.lilyBoost);
        }
    }

    public boolean isLilyPlanted() {
        return lilyPlanted;
    }
}

package Tiles;

import Creature.Plant.*;

public class WaterTile extends Tile {
    // private Plant plant;
    private boolean lilyPlanted;
    private int lilyBoost;

    public WaterTile() {
        super();
        // super.getPlant() = null;
        this.lilyPlanted = false;
    }

    public void addPlant(Plant plant) {
        if ((super.getPlant() == null && plant instanceof Lilypad) || plant instanceof TangleKelp) {
            lilyPlanted = true;
            this.lilyBoost = plant.getHealth();
            super.addPlant(plant);
        } else if (lilyPlanted) {
            super.removePlant();
            super.addPlant(plant);
            super.getPlant().setHealth(plant.getHealth() + this.lilyBoost);
        }
    }

    public boolean isLilyPlanted() {
        return lilyPlanted;
    }

    public void removePlant() {
        super.removePlant();
        this.lilyPlanted = false;
    }   
}

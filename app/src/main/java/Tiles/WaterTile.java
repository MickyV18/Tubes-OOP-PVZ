package Tiles;

import Creature.Plant.*;

public class WaterTile extends Tile{
    private Plant plant;
    private boolean lilyPlanted;
    private int lilyBoost;

    public WaterTile() {
        super();
        this.plant = null;
        this.lilyPlanted = false;
    }
    
    public Plant getPlant() { return plant; }
    public void addPlant(Plant plant) {
        if (this.plant == null && plant instanceof Lilypad) {
            lilyPlanted = true;
            this.lilyBoost = plant.getHealth();
        }
        else if (lilyPlanted) {
            this.plant = plant;
            this.plant.setHealth(plant.getHealth() + this.lilyBoost);
        }
    }
    public void removePlant(Plant plant) { this.plant = null; }
    public boolean isLilyPlanted() { return lilyPlanted; }
}

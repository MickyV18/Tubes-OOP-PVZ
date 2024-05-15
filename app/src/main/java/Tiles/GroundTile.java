package Tiles;

import Creature.Plant.*;

public class GroundTile extends Tile {
    private Plant plant;

    public GroundTile() {
        super();
        this.plant = null;
    }

    public Plant getPlant() { return plant; }

    public void addPlant(Plant plant) {
        if (this.plant == null) {
            this.plant = plant;
        }
    }
    
    public void removePlant(Plant plant) { this.plant = null; }
}
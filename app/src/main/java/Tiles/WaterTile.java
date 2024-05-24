package Tiles;

import Creature.Plant.*;
import Sun.Sun;

public class WaterTile extends Tile {
    private boolean lilyPlanted;
    private int lilyBoost;

    public WaterTile() {
        super();
        this.lilyPlanted = false;
    }

    @Override
    public void addPlant(Plant plant) throws InvalidSpawn{
        if (Sun.getSun() >= plant.getCost())
        {
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
    }

    public boolean isLilyPlanted() {
        return lilyPlanted;
    }

    public void removePlant() {
        super.removePlant();
        this.lilyPlanted = false;
        this.lilyBoost = 0;
    }
    
    public void removeSCPlant(Tile tile) {
        super.removePlant();
        Lilypad lilypad = new Lilypad();
        lilypad.setHealth(lilyBoost);
        try {
            tile.addPlant(lilypad);
        } catch (InvalidSpawn e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

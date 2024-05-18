package Creature.Plant;

public class Lilypad extends Plant {
    private Plant plant;

    public Lilypad(){
        super("Lilypad", 100, 0, 0, true, 25, 0, 10);
        this.plant = null;
    }

    public void addPlant(Plant plant){
        this.plant = plant;
    }

    public boolean hasPlanted(){
        if (plant != null) {
            return true;
        }
        else{
            return false;
        }
    }

    public void setHealth(boolean hasPlanted){
        if (hasPlanted) {
            plant.setHealth(this.getHealth() + plant.getHealth());
        }
    }
}

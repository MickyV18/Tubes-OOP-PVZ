package Creature.Plant;

public class Sunflower extends Plant {
    private int idx = 7;
    public Sunflower(){
        super("Sunflower", 100, 0, 0, false, 50, 0, 10, false);
        this.idx = 7;
    }
    
    public int idx() {return this.idx;}
}

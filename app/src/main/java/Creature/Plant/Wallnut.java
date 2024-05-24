package Creature.Plant;

public class Wallnut extends Plant {
    private int idx = 9;
    public Wallnut(){
        super("Wallnut", 1000, 0, 0, false, 50, 0, 20, false);
        this.idx = 9;
    }
    
    public int idx() {return this.idx;}
}

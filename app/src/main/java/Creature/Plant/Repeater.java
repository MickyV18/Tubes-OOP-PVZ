package Creature.Plant;

public class Repeater extends Plant{
    private int idx = 4;
    public Repeater(){
        super("Repeater", 100, 25, 2, false, 150, -1, 15, true);
        this.idx = 4;
    }
    
    public int idx() {return this.idx;}
}
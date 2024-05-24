package Creature.Plant;

public class CherryBomb extends Plant{
    private int idx = 1;
    public CherryBomb(){
        super("Cherry Bomb", 5000, 5000, 1, false, 50, 1, 20, true);
        this.idx = 1;
    }
    
    public int idx() {return this.idx;}
}

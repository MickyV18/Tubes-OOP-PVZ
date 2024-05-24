package Creature.Plant;

public class Peashooter extends Plant {
    private int idx = 3;
    public static int cooldownplant = 10;

    public Peashooter(){
        super("Peashooter", 100, 25, 4, false, 100, -1, 10, true);
        this.idx = 3;
    }
    
    public int idx() {return this.idx;}
}

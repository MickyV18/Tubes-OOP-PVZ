package Creature.Plant;

public class CabbagePult extends Plant{
    private int idx = 0;
    public static int cooldownplant = 10;

    public CabbagePult(){
        super("Cabbagepult", 100, 25, 4, false, 100, -1, 10, true);
        this.idx = 0;
    }
    
    public int idx() {return this.idx;}
}

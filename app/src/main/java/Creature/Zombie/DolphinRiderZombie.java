package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;

public class DolphinRiderZombie extends Zombie implements Jump {
    private boolean jumped = true;
    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175,  100, 1, true);
    }

    public void jump(){
        jumped = false;
        System.out.println("coba gradle");
    }
    public boolean hasJumped(){
        return jumped;
    }
}

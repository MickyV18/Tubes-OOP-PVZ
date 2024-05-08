package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;

public class PoleVaultingZombie extends Zombie{
    private boolean jumped = false;
    public PoleVaultingZombie(){
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public void attack(Zombie zombie, Plant plant){
        
    }

    public void jump(){
        jumped = false;
    }
    public boolean hasJumped(){
        return jumped;
    }
}

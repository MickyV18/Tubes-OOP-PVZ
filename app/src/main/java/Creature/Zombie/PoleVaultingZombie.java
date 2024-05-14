package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;

public class PoleVaultingZombie extends Zombie{
    private boolean jumped = true;
    public PoleVaultingZombie(){
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public void jump(){
        // posisi zombie jadi pindah 2 tile dan plant yg dilompatin mati
        jumped = false;
    }
    public boolean hasJumped(){
        return jumped;
    }
}

package app.src.main.java.Creature.Zombie;
import app.src.main.java.Creature.*;
import app.src.main.java.Creature.Plant.*;

public class PoleVaultingZombie extends Zombie{
    private boolean jumped = false;
    public PoleVaultingZombie(){
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public void jump(){
        jumped = false;
    }
    public boolean hasJumped(){
        return jumped;
    }
}

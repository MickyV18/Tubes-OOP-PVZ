package app.src.main.java.Zombie;
import app.src.main.java.Creature.*;
import app.src.main.java.Plant.*;

public class PoleVaultingZombie extends Zombie{
    private boolean jumped = false;
    public PoleVaultingZombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic){
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public void jump(){
        jumped = false;
    }
    public boolean hasJumped(){
        return jumped;
    }
}

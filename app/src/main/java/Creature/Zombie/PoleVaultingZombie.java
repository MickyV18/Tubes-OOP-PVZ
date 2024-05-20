package Creature.Zombie;

import java.util.ArrayList;
import java.util.List;
import Tiles.*;

public class PoleVaultingZombie extends Zombie implements Jump {

    private boolean jumped = false;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public void jump(Tile tileawal, Tile tileakhir, int x, int y) {
        List<Zombie> zombies = new ArrayList<>(tileawal.getZombies());
        for (Zombie zombie : zombies) {
            if (zombie instanceof PoleVaultingZombie) {
                tileakhir.addZombie(zombie);
                tileawal.removeZombie(zombie);
            }
        }
        tileakhir.removePlant();
        jumped = true;
    }

    public boolean hasJumped() {
        return jumped;
    }
}

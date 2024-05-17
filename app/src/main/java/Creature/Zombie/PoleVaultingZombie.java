package Creature.Zombie;

import java.util.ArrayList;
import java.util.List;
import Creature.*;
import Creature.Plant.*;
import Tiles.*;

public class PoleVaultingZombie extends Zombie implements Jump {

    private boolean jumped = false;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public void jump(Tile tileawal, Tile tileplant, Tile tileakhir, int x, int y) {
        // posisi zombie jadi pindah 2 tile dan plant yg dilompatin mati
        List<Zombie> zombies = new ArrayList<>(tileawal.getZombies());
        for (Zombie zombie : zombies) {
            if (zombie instanceof PoleVaultingZombie) {
                // System.out.println(x + " " + y + " ");
                tileakhir.addZombie(zombie);
                tileawal.removeZombie(zombie);
            }
        }
        // System.out.println(tileplant.getPlant());
        tileplant.removePlant();
        // System.out.println(tileplant.getPlant());
        jumped = true;
    }

    public boolean hasJumped() {
        return jumped;
    }
}

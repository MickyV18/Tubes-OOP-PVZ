package Creature.Zombie;

import java.util.ArrayList;
import java.util.List;

import Tiles.Tile;

public class DolphinRiderZombie extends Zombie implements Jump {
    private boolean jumped = false;

    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, true);
    }

    public void jump(Tile tileawal, Tile tileplant, Tile tileakhir, int x, int y) {
        // posisi zombie jadi pindah 2 tile dan plant yg dilompatin mati
        List<Zombie> zombies = new ArrayList<>(tileawal.getZombies());
        for (Zombie zombie : zombies) {
            if (zombie instanceof DolphinRiderZombie) {
                System.out.println(x + " " + y + " ");
                tileakhir.addZombie(zombie);
                tileawal.removeZombie(zombie);
            }
        }
        tileplant.removePlant();
        jumped = true;
    }

    public boolean hasJumped() {
        return jumped;
    }
}

package Creature.Zombie;

import java.util.ArrayList;
import java.util.List;
import Creature.*;
import Creature.Plant.*;
import Tiles.*;

public class PoleVaultingZombie extends Zombie implements Jump{

    private boolean jumped = false;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false);
    }

    public void jump(Tile[][] tiles, int x, int y) {
        // posisi zombie jadi pindah 2 tile dan plant yg dilompatin mati
        for (Zombie zombie : tiles[x][y].getZombies()) {
            if (zombie.getName() == "Pole Vaulting Zombie") {
            tiles[x - 2][y].addZombie(zombie);
            tiles[x][y].removeZombie(zombie);
            }
        }
        if (tiles[x - 1][y].getPlant() != null) {
            tiles[x - 1][y].removePlant();
        }
        jumped = true;
    }

    public boolean hasJumped() {
        return jumped;
    }
}

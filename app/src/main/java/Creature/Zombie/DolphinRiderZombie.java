package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;
import Tiles.Tile;

public class DolphinRiderZombie extends Zombie implements Jump {
    private boolean jumped = false;
    public DolphinRiderZombie(){
        super("Dolphin Rider Zombie", 175,  100, 1, true);
    }

    public void jump(Tile[][] tiles, int x, int y) {
        // posisi zombie jadi pindah 2 tile dan plant yg dilompatin mati
        for (Zombie zombie : tiles[x][y].getZombies()) {
            if (zombie.getName() == "Dolphin Rider Zombie") {
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

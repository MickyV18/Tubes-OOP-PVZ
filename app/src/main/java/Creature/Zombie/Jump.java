package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;
import Tiles.Tile;

public interface Jump {
    public void jump(Tile[][] tiles, int x, int y);
    public boolean hasJumped();
}

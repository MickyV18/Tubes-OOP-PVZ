package Creature.Zombie;
import Tiles.Tile;

public interface Jump {
    public void jump(Tile tileawal, Tile tileakhir, int x, int y);
    public boolean hasJumped();
}

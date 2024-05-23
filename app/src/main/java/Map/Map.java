package Map;

import Tiles.*;

public class Map {
    private static Tile[][] tiles = new Tile[6][11]; 

    private Map(){};

    public static Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    public static void setTile(int i, int j, Tile tile) {
        tiles[i][j] = tile;
    }
}

import Creature.*;
import Creature.Plant.*;
import Creature.Zombie.*;
import java.util.*;

public class Game {
    // private List<List<Tile>> tiles;
    private List<Plant> deckPlants;
    private int sun;
    private boolean gameover = false;
    
    public Game(List<Plant> deckPlants, int sun) {
        this.deckPlants = deckPlants;
        this.sun = sun;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public int getSun() {
        return sun;
    }

    public void addPlant(Plant plant) {
        deckPlants.add(plant);
    }

    public void produceSun() {
        sun += 25;
    }

    public void decreaseSun(int amount) {
        sun -= amount;
    }
}

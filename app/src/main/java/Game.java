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

    public void gameloop() {
    }

    public void openfile(){
        // open file
    }

    public void spawnPlant(){

    }
    
    public void spawnZombie(){

    }

    public void digplant(){

    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover() {
        gameover = true;
    }

    public int getSun() {
        return sun;
    }

    public void addPlant(Plant plant) {
        
    }

    public void produceSun() {
        sun += 25;
    }

    public void decreaseSun(int amount) {
        sun -= amount;
    }
}

import Creature.*;
import Creature.Plant.*;
import Creature.Zombie.*;
import java.util.*;

public class Game {
    // private List<List<Tile>> tiles;
    private List<Plant> deckPlants;
    private int sun;
    private boolean gameover = false;
    private int CountZombie = 0;
    private final String[] zombie_name = { "Bucket", "Dolphin", "Ducky", "Football", "Gargantuar", "Jack", "Newspaper",
            "Normal", "Pole", "Cone" };
    private static long previousTime = System.currentTimeMillis();

    public Game() {
        // this.deckPlants = deckPlants;
        sun = 25;
    }

    public void gameloop() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameover()) {
            // NYOBA PLANT BELOM SELESAI
            // String name = scanner.next();
            // String[] stats = name.split(" ");
            // try {
            // if (stats[0] == "PLANT"){
            // String type = stats[1];
            // int x_position = Integer.parseInt(stats[2]);
            // int y_position = Integer.parseInt(stats[3]);

            // }
            // } catch (Exception e) {
            // // TODO: handle exception
            // }

            long currentTime = System.currentTimeMillis();
            if (currentTime - previousTime >= 1000 && CountZombie < 10) {
                Random random = new Random();
                double probability = 0.3;
                if (random.nextDouble(0, 1) < probability) {
                    Zombie zombie = spawnZombie();
                }   
            }
            try {
                // harus dimasukin ke atribut di tile
                System.out.println(zombie.getName());
            } catch (Exception e) {
                // TODO: handle exception
            }

            previousTime = currentTime;
        }
    }

    public void openfile() {
        // open file
    }

    public void spawnPlant() {

    }

    public Zombie spawnZombie() {
        Random random = new Random();
        int zombieInt = random.nextInt(0, zombie_name.length);
        if (zombieInt == 1) {
            BucketheadZombie zombie = new BucketheadZombie();
            CountZombie++;
            return zombie;
        } else if ( zombieInt == 2) {
            DolphinRiderZombie zombie = new DolphinRiderZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 3) {
            DuckyTubeZombie zombie = new DuckyTubeZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 4) {
            FootballZombie zombie = new FootballZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 5) {
            Gargantuar zombie = new Gargantuar();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 6) {
            JackInTheBoxZombie zombie = new JackInTheBoxZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 7) {
            NewspaperZombie zombie = new NewspaperZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 8) {
            NormalZombie zombie = new NormalZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 9) {
            PoleVaultingZombie zombie = new PoleVaultingZombie();
            CountZombie++;
            return zombie;
        } else {
            ConeheadZombie zombie = new ConeheadZombie();
            CountZombie++;
            return zombie;
        }
    }

    public void digplant() {

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

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
    private int gametimestamp = 0;

    public Game() {
        // this.deckPlants = deckPlants;
        sun = 25;
    }

    public void gameloop() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameover()) {
            long currentTime = System.currentTimeMillis();
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

            Random random = new Random();
            int produceSun_time = random.nextInt(5, 10);

            if ((currentTime - produceSun_time) >= 1000){
                gametimestamp++;
                if (gametimestamp > 200){
                    gametimestamp = 0;
                }
            }
            
            for (int i = 0; i < 6; i++) {    
                if ((currentTime - previousTime) >= 1000 && CountZombie < 10 && gametimestamp >= 20 && gametimestamp <= 160) {
                    double probability = 0.3;
                    if (random.nextDouble(0, 1) < probability) {
                        Zombie zombie = spawnZombie();
                        if (zombie.isAquatic() && tile aquatic){
                        }
                    }
                    try {
                        // harus dimasukin ke atribut di tile
                        System.out.println(zombie.getName());
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                for (int j = 0; j < panjang tile; j++) {                            
                    if (tile.getzombie() != null) {
                        for (int j = 0; j < jumlah zombie; j++) {
                            if (zombie ke j . timestamp - previousTime >= zombie.attaack_speed && tile next to zombie ada plant){
                                zombie attack si plant
                            }
                            if (zombie ke j . timestamp - previousTime >= 5000 && tile next to zombie ga ada plant){
                                move();
                                if (tile next to zombie ada plant){
                                    zombie attack si plant
                                }
                            }
                        }
                    }
                    
                    if (tile. isplanted()){
                        if (plant.name = sunflower && (currentTime - previousTime) >= 3000){
                            produceSun();
                        }
                        if (plant . timestamp - previousTime >= plant.attack_speed){
                            plant attack si zombie
                            
                        }
                    }

                    if ((currentTime - previousTime) <= produceSun_time && gametimestamp < 100){
                        produceSun();
                        produceSun_time = random.nextInt(5, 10);
                    }
                }

            }
            previousTime = currentTime;
        }
    }

    public void openfile() {
        // open file
    }

    public Zombie spawnZombie() {
        Random random = new Random();
        int zombieInt = random.nextInt(0, zombie_name.length);
        if (zombieInt == 1) {
            BucketheadZombie zombie = new BucketheadZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 2) {
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

    public void removePlant(Plant plant) {

    }

    public void produceSun() {
        sun += 25;
    }

    public void decreaseSun(int amount) {
        sun -= amount;
    }
}

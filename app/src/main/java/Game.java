import Creature.*;
import Creature.Plant.*;
import Creature.Zombie.*;
import java.util.*;
import Tiles.*;

public class Game {
    private Tile[][] tiles;
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
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0) {
                    tiles[i][j] = new EndTile();
                } else if (i == 10) {
                    tiles[i][j] = new SpawnTile();
                } else if (j == 2 || j == 3) {
                    tiles[i][j] = new WaterTile();
                } else {
                    tiles[i][j] = new GroundTile();
                }
            }
        }
    }

    public void gameloop() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameover()) {
            long currentTime = System.currentTimeMillis();

            // NYOBA PLANT BELOM SELESAI
            String name = scanner.next();
            String[] stats = name.split(" ");
            try {
                if ("PLANT".equals(stats[0])){
                    String type = stats[1];
                    int x_position = Integer.parseInt(stats[2]);
                    int y_position = Integer.parseInt(stats[3]);
                    Plant tanaman = PlantFactory.createPlant(type);

                    if (x_position == 2 || y_position == 3){
                        WaterTile water = (WaterTile) tiles[x_position][y_position];
                        if (water.getPlant() == null && tanaman.isSunEnough(tanaman, sun) && tanaman.isCooldown(currentTime, tanaman.getTimeCreated())){
                            if (tanaman instanceof Lilypad || water.isLilyPlanted()){
                                water.addPlant(tanaman);
                            }   
                        }
                    }
                    else {
                        GroundTile ground = (GroundTile) tiles[x_position][y_position];
                        if (ground.getPlant() == null && tanaman.isSunEnough(tanaman, sun) && tanaman.isCooldown(currentTime, tanaman.getTimeCreated())){
                            if (tanaman instanceof Lilypad){
                                ground.addPlant(tanaman);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            // TODO: handle exception (invalidplantexception)
            }

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
                        tiles[10][i].addZombie(zombie);
                        if (zombie.isAquatic() && (i == 2 || i == 3)){
                            zombie.move();
                        } else if (!zombie.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5)){
                            zombie.move();
                        } else{
                            tiles[10][i].removeZombie(zombie);
                        }
                    }
                    try {
                        // harus dimasukin ke atribut di tile
                        System.out.println(tiles[10][i]);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                for (int j = 10; j < 0; j--) {
                    // getzombie belom ada                            
                    if (tiles[j][i].getZombies() != null) {
                        for (Zombie zombie : tiles[j][i].getZombies()) {
                            if (i == 2 || i == 3){
                                WaterTile nextTile = (WaterTile) tiles[j-1][i];
                                if (zombie.getTimeCreated() - previousTime >= zombie.getAtkSpd() && nextTile.getPlant() != null){
                                    zombie.attack(nextTile.getPlant());
                                }
                                if (zombie.getTimeCreated() - previousTime >= 5000 && nextTile.getPlant() == null){
                                    zombie.move();
                                    nextTile = (WaterTile) tiles[j-2][i];
                                    if (nextTile.getPlant() != null){
                                        zombie.attack(nextTile.getPlant());
                                    }
                                }
                            } else{
                                GroundTile nextTile = (GroundTile) tiles[j-1][i];
                                if (zombie.getTimeCreated() - previousTime >= zombie.getAtkSpd() && nextTile.getPlant() != null){
                                    zombie.attack(nextTile.getPlant());
                                }
                                if (zombie.getTimeCreated() - previousTime >= 5000 && nextTile.getPlant() == null){
                                    zombie.move();
                                    nextTile = (GroundTile) tiles[j-2][i];
                                    if (nextTile.getPlant() != null){
                                        zombie.attack(nextTile.getPlant());
                                    }
                                }
                            }
                        }
                    }
                    
                    GroundTile ground = (GroundTile) tiles[j][i];
                    WaterTile water = (WaterTile) tiles[j][i];
                    if (ground.getPlant() != null){
                        if (ground.getPlant() instanceof Sunflower || water.getPlant() instanceof Sunflower && (int) ((ground.getPlant().getTimeCreated() - currentTime) / 1000) >= 3){
                            produceSun();
                        }
                    }



                        // if ((int) ((ground.getPlant().getTimeCreated() - currentTime) / 1000) % ground.getPlant().getAtkSpd() == 0){
                        //     ground.getPlant().attack();
                        // }
                    

                    if ((currentTime - previousTime) <= produceSun_time && gametimestamp < 100){
                        produceSun();
                        produceSun_time = random.nextInt(5, 10);
                    }
                }

            }
            previousTime = currentTime;
        }
    }

    // class InvalidPlantException extends Exception{
    //     public InvalidPlantException(String message){
    //         super("Plant not in deck");
    //     }
    // }

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

    public void produceSun() {
        sun += 25;
    }

    public void decreaseSun(int amount) {
        sun -= amount;
    }
}

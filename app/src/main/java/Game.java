import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Creature.Plant.Lilypad;
import Creature.Plant.*;
import Creature.Plant.PlantFactory;
import Creature.Plant.Sunflower;
import Creature.Zombie.BucketheadZombie;
import Creature.Zombie.ConeheadZombie;
import Creature.Zombie.DolphinRiderZombie;
import Creature.Zombie.DuckyTubeZombie;
import Creature.Zombie.FootballZombie;
import Creature.Zombie.Gargantuar;
import Creature.Zombie.JackInTheBoxZombie;
import Creature.Zombie.NewspaperZombie;
import Creature.Zombie.NormalZombie;
import Creature.Zombie.PoleVaultingZombie;
import Creature.Zombie.Zombie;
import Tiles.EndTile;
import Tiles.GroundTile;
import Tiles.SpawnTile;
import Tiles.Tile;
import Tiles.WaterTile;

public class Game {
    private Tile[][] tiles = new Tile[11][6];
    private List<Plant> deckPlants;
    private int sun;
    private boolean gameover = false;
    private int CountZombie = 0;
    private final String[] zombie_name = { "Bucket", "Dolphin", "Ducky", "Football", "Gargantuar", "Jack", "Newspaper",
            "Normal", "Pole", "Cone" };
    private int gametimestamp = -1;
    long currentTime;

    public Game() {
        // this.deckPlants = deckPlants;
        currentTime = System.currentTimeMillis();
        sun = 25;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == 0) {
                    tiles[i][j] = new EndTile();
                } else if (i == 10) {
                    tiles[i][j] = new SpawnTile();
                } else if (j == 2 || j == 3) {
                    if (i != 0 || i != 10) {
                        tiles[i][j] = new WaterTile();
                    }
                } else {
                    if (i != 0 || i != 10) {
                        tiles[i][j] = new GroundTile();
                    }
                }
            }
        }
        // System.err.println(tiles[0][0].getClass().getName());
    }

    public void gameloop() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int produceSun_time = random.nextInt(5, 10);

        while (!isGameover()) {
            System.out.println(sun);
            // System.err.println(tiles[0][0].getClass().getName());
            produceSun_time--;
            gametimestamp++;
            // System.err.println(currentTime);
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
                        if (water.getPlant() == null && tanaman.isSunEnough(tanaman, sun) && tanaman.isCooldown(currentTime, tanaman.getTimeStamp())){
                            if (tanaman instanceof Lilypad || water.isLilyPlanted()){
                                water.addPlant(tanaman);
                            }   
                        }
                    }
                    else {
                        GroundTile ground = (GroundTile) tiles[x_position][y_position];
                        if (ground.getPlant() == null && tanaman.isSunEnough(tanaman, sun) && tanaman.isCooldown(currentTime, tanaman.getTimeStamp())){
                            if (tanaman instanceof Lilypad){
                                ground.addPlant(tanaman);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            // TODO: handle exception (invalidplantexception)
            }
            if (gametimestamp > 200) {
                gametimestamp = 0;
            }
            System.out.println();
            System.out.println();
            for (int i = 0; i < 6; i++) {
                for (int j = 1; j < 11; j++) {
                    if (tiles[j][i].hasZombie()) {
                        System.out.print("Z");
                        // System.err.println(tiles[j][i].getClass().getName());
                        // for (Zombie zombie : tiles[j][i].getZombies()) {
                        // System.out.println(zombie.getName());
                        // }
                    } else {
                        System.out.print("-");
                    }
                }
                System.out.println();
            }
            System.out.println();
            // GroundTile ground = (GroundTile) tiles[4][4];
            // Peashooter peashooter = new Peashooter();
            // ground.addPlant(peashooter);
            // if (ground.getPlant() != null) {
            // System.out.println(ground.getPlant().getName());
            // }

            for (int i = 0; i < 6; i++) {
                spawnZombieActivity(i);
                // System.out.print(CountZombie);
                for (int j = 1; j < 11; j++) {
                // j=0 cek ada zombie atau gak
                // cek di tile zombie ada plant atau gak
                // pake instance of
                // benerin zombie move
                // plant hp 0 tapi mau nyerang (sinkron)
                    if (tiles[j][i].hasZombie()) {
                        System.out.println("MASUK LOOP");
                        for (Zombie zombie : tiles[j][i].getZombies()) {
                            if (i == 2 || i == 3) {
                                WaterTile nextTile = (WaterTile) tiles[j - 1][i];
                                waterZombieActivity(zombie, nextTile, j, i);
                            } else {
                                GroundTile nextTile = (GroundTile) tiles[j - 1][i];
                                groundZombieActivity(zombie, nextTile, j, i);
                            }
                        }
                    }

                    if (i==2 || i==3){
                        WaterTile water = (WaterTile) tiles[j][i];
                        if (water.getPlant() != null){
                            if (water.getPlant() instanceof Sunflower && (int) ((water.getPlant().getTimeStamp() - currentTime) / 1000) >= 3){
                                produceSun();
                            }
                            if ((int) ((water.getPlant().getTimeStamp() - currentTime) / 1000) % water.getPlant().getAtkSpd() == 0){
                                if (water.getPlant() instanceof CherryBomb){
                                    if (getTiles(water.getPlant(), j, i) != null){
                                        for (Zombie zombie : getTiles(water.getPlant(), j, i).getZombies()) {
                                            water.getPlant().attack(zombie);
                                        }
                                    }
                                    else if (getTiles(water.getPlant(), j+1, i) != null){
                                        for (Zombie zombie : getTiles(water.getPlant(), j+1, i).getZombies()) {
                                            water.getPlant().attack(zombie);
                                        }
                                    }
                                    else {
                                        for (Zombie zombie : getTiles(water.getPlant(), j-1, i).getZombies()) {
                                            water.getPlant().attack(zombie);
                                        }
                                    }
                                }
                                else {
                                    if (getTiles(water.getPlant(), j, i) != null){
                                        for (Zombie zombie : getTiles(water.getPlant(), j, i).getZombies()) {
                                            water.getPlant().attack(zombie);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        GroundTile ground = (GroundTile) tiles[j][i];
                        if (ground.getPlant() != null){
                            if (ground.getPlant() instanceof Sunflower && (int) ((ground.getPlant().getTimeStamp() - currentTime) / 1000) >= 3){
                                produceSun();
                            }
                            if ((int) ((ground.getPlant().getTimeStamp() - currentTime) / 1000) % ground.getPlant().getAtkSpd() == 0){
                                if (ground.getPlant() instanceof CherryBomb){
                                    if (getTiles(ground.getPlant(), j, i) != null){
                                        for (Zombie zombie : getTiles(ground.getPlant(), j, i).getZombies()) {
                                            ground.getPlant().attack(zombie);
                                        }
                                    }
                                    else if (getTiles(ground.getPlant(), j+1, i) != null){
                                        for (Zombie zombie : getTiles(ground.getPlant(), j+1, i).getZombies()) {
                                            ground.getPlant().attack(zombie);
                                        }
                                    }
                                    else {
                                        for (Zombie zombie : getTiles(ground.getPlant(), j-1, i).getZombies()) {
                                            ground.getPlant().attack(zombie);
                                        }
                                    }
                                }
                                else {
                                    if (getTiles(ground.getPlant(), j, i) != null){
                                        for (Zombie zombie : getTiles(ground.getPlant(), j, i).getZombies()) {
                                            ground.getPlant().attack(zombie);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (produceSun_time == 0 && gametimestamp < 100) {
                produceSun();
                produceSun_time = random.nextInt(5, 10);
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
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

    public void spawnZombieActivity(int i) {
        Random random = new Random();
        if (CountZombie < 10 && gametimestamp >= 0 && gametimestamp <= 160) {
            double probability = 0.3;
            if (random.nextDouble(0, 1) < probability) {
                Zombie zombie = spawnZombie();
                tiles[10][i].addZombie(zombie);
                if ((!zombie.isAquatic() && (i == 2 || i == 3))
                        || (zombie.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5))) {
                    tiles[10][i].removeZombie(zombie);
                }
                // System.out.print(tiles[10][i].hasZombie());
            }
        }
    }

    public void waterZombieActivity(Zombie zombie, WaterTile nextTile, int x_position, int y_position) {
        if ((int) ((zombie.getTimeStamp() - currentTime) / 1000) % zombie.getAtkSpd() == 0
                && nextTile.getPlant() != null) {
            System.out.println(zombie.getName() + " NYERANG");
            if (zombie.getName() == "Dolphin Rider Zombie") {
                DolphinRiderZombie dolphinRiderZombie = (DolphinRiderZombie) zombie;
                if (!dolphinRiderZombie.hasJumped()) {
                    dolphinRiderZombie.jump();
                } else {
                    zombie.attack(nextTile.getPlant());
                }
            } else {
                zombie.attack(nextTile.getPlant());
            }
            zombie.setTimeStamp(currentTime);
        } else if ((int) (zombie.getTimeStamp() - currentTime) / 1000 % 5 == 0 &&
                nextTile.getPlant() == null) {
            System.out.println(zombie.getName() + " JALAN");

            move(zombie, x_position, y_position);
            System.out.println("ZOMBIE MAJU!!!");
            // nextTile = (WaterTile) tiles[j - 2][i];
            // if (nextTile.getPlant() != null) {
            // zombie.attack(nextTile.getPlant());
            // }
            // zombie.setTimeStamp(currentTime);
        }
    }

    public void groundZombieActivity(Zombie zombie, GroundTile nextTile, int x_position, int y_position) {
        if ((int) (zombie.getTimeStamp() - currentTime) / 1000 % zombie.getAtkSpd() == 0
                && nextTile.getPlant() != null) {
            System.out.println(zombie.getName() + " NYERANG");
            if (zombie.getName() == "Pole Vaulting Zombie") {
                PoleVaultingZombie polevaultingZombie = (PoleVaultingZombie) zombie;
                if (!polevaultingZombie.hasJumped()) {
                    polevaultingZombie.jump();
                } else {
                    zombie.attack(nextTile.getPlant());
                }
            } else {
                zombie.attack(nextTile.getPlant());
            }
            zombie.setTimeStamp(currentTime);
        } else if ((int) (zombie.getTimeStamp() - currentTime) / 1000 % 5 == 0
                && nextTile.getPlant() == null) {
            System.out.println(zombie.getName() + " JALAN");
            move(zombie, x_position, y_position);
            System.out.println("ZOMBIE MAJU!!!");
            // move();
            // nextTile = (GroundTile) tiles[j - 2][i];
            // if (nextTile.getPlant() != null) {
            // zombie.attack(nextTile.getPlant());
            // }
            // zombie.setTimeStamp(currentTime);
        }
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

    public void move(Zombie zombie, int x_position, int y_position) {

        tiles[x_position - 1][y_position].addZombie(zombie);
        tiles[x_position][y_position].removeZombie(zombie);
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

    public Tile getTiles(Plant plant, int col, int row){
        if (plant.getRange() == -1){
            for (int j = 1; j < col; j++){
                if (tiles[j][row].hasZombie()){
                    return tiles[j][row];
                }
            }
        }
        else {
            if (tiles[col+1][row].hasZombie()){
                return tiles[col+1][row];
            }
        }
        return null;
    }
}

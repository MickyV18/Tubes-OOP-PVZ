import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.System;

import Creature.Plant.*;
import Creature.Zombie.*;
import Tiles.EndTile;
import Tiles.GroundTile;
import Tiles.SpawnTile;
import Tiles.Tile;
import Tiles.WaterTile;

public class Game {
    private Tile[][] tiles = new Tile[6][11];
    private List<Plant> deckPlants;
    private int sun;
    private boolean gameover = false;
    private int CountZombie = 0;
    private int gametimestamp = -1;
    long currentTime;
    private int flag = 0;

    public Game() {
        // this.deckPlants = deckPlants;
        currentTime = System.currentTimeMillis();
        sun = 25;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) {
                    tiles[i][j] = new EndTile();
                } else if (j == 10) {
                    tiles[i][j] = new SpawnTile();
                } else if (i == 2 || i == 3) {
                    if (j != 0 || j != 10) {
                        tiles[i][j] = new WaterTile();
                    }
                } else {
                    if (j != 0 || j != 10) {
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
            // System.out.println(sun);
            produceSun_time--;
            gametimestamp++;
            currentTime = System.currentTimeMillis();
            // String name = scanner.next();
            // String[] stats = name.split(" ");
            // try {
            // if ("PLANT".equals(stats[0])) {
            // String type = stats[1];
            // int x_position = Integer.parseInt(stats[2]);
            // int y_position = Integer.parseInt(stats[3]);
            // Plant tanaman = PlantFactory.createPlant(type);

            // if (x_position == 2 || y_position == 3) {
            // WaterTile water = (WaterTile) tiles[x_position][y_position];
            // if (tiles[i][j].getPlant() == null && tanaman.isSunEnough(tanaman, sun)
            // && tanaman.isCooldown(currentTime, tanaman.getTimeStamp())) {
            // if (tanaman instanceof Lilypad || water.isLilyPlanted()) {
            // water.addPlant(tanaman);
            // }
            // }
            // } else {
            // GroundTile ground = (GroundTile) tiles[x_position][y_position];
            // if (tiles[i][j].getPlant() == null && tanaman.isSunEnough(tanaman, sun)
            // && tanaman.isCooldown(currentTime, tanaman.getTimeStamp())) {
            // if (tanaman instanceof Lilypad) {
            // ground.addPlant(tanaman);
            // }
            // }
            // }
            // }
            // } catch (Exception e) {
            // // TODO: handle exception (invalidplantexception)
            // }
            if (gametimestamp > 200) {
                gametimestamp = 0;
                flag = 2;
            }
            System.out.println();
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 11; j++) {
                    if (tiles[i][j].hasZombie() && tiles[i][j].getPlant() != null) {
                        System.out.print("A");
                    } else if (tiles[i][j].hasZombie()) {
                        System.out.print("Z");
                    } else if (tiles[i][j].getPlant() != null) {
                        System.out.print("P");
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
            // if (tiles[i][j].getPlant() != null) {
            // System.out.println(tiles[i][j].getPlant().getName());
            // }

            for (int i = 0; i < 6; i++) {
                spawnZombieActivity(i);
                for (int j = 1; j < 11; j++) {
                    // j=0 cek ada zombie atau gak
                    // cek di tile zombie ada plant atau gak
                    // pake instance of
                    // benerin zombie move
                    // plant hp 0 tapi mau nyerang (sinkron)

                    if (tiles[i][j].getPlant() != null) {
                        if (tiles[i][j].getPlant().getHealth() != 0) {
                            if (tiles[i][j].getPlant() instanceof Sunflower
                                    && (int) ((tiles[i][j].getPlant().getTimeStamp() - currentTime) / 1000) >= 3) {
                                produceSun();
                            }
                            if ((int) ((tiles[i][j].getPlant().getTimeStamp() - currentTime) / 1000)
                                    % tiles[i][j].getPlant().getAtkSpd() == 0) {
                                if (tiles[i][j].getPlant() instanceof CherryBomb) {
                                    if (getTiles(tiles[i][j].getPlant(), i, j) != null) {
                                        for (Zombie zombie : getTiles(tiles[i][j].getPlant(), i, j).getZombies()) {
                                            tiles[i][j].getPlant().attack(zombie);
                                        }
                                    } else if (getTiles(tiles[i][j].getPlant(), i, j + 1) != null) {
                                        for (Zombie zombie : getTiles(tiles[i][j].getPlant(), i, j + 1).getZombies()) {
                                            tiles[i][j].getPlant().attack(zombie);
                                        }
                                    } else {
                                        for (Zombie zombie : getTiles(tiles[i][j].getPlant(), i, j - 1).getZombies()) {
                                            tiles[i][j].getPlant().attack(zombie);
                                        }
                                    }
                                } else {
                                    if (getTiles(tiles[i][j].getPlant(), i, j) != null) {
                                        for (Zombie zombie : getTiles(tiles[i][j].getPlant(), i, j).getZombies()) {
                                            tiles[i][j].getPlant().attack(zombie);
                                        }
                                    }
                                }
                            }
                        } else {
                            tiles[i][j].removePlant();
                        }
                    }

                    if (tiles[i][j].hasZombie()) {
                        // System.out.println("MASUK LOOP");
                        List<Zombie> zombies = new ArrayList<>(tiles[i][j].getZombies());
                        for (Zombie zombie : zombies) {
                            if (zombie.getHealth() != 0) {
                                if ((currentTime - zombie.getTimeStamp()) / 1000 != 0) {
                                    if ((currentTime - zombie.getTimeStamp()) / 1000 >= zombie.getAtkSpd()) {
                                        System.out.println("MASUK 1");
                                        if ((tiles[i][j - 1].getPlant() != null)) {
                                            System.out.println("MASUK 2");
                                            ZombieActivity(zombie, i, j);
                                        } else if ((tiles[i][j].getPlant() != null)) {
                                            System.out.println("MASUK 3");
                                            ZombieActivity(zombie, i, j + 1);
                                        }
                                    }
                                    if ((currentTime - zombie.getTimeStamp()) / 1000 >= 5 &&
                                            tiles[i][j - 1].getPlant() == null) {
                                        move(zombie, i, j);
                                        // System.out.println("ZOMBIE MAJU!!!");
                                        zombie.setTimeStamp(currentTime);
                                        // nextTile = (WaterTile) tiles[j - 2][i];
                                        // if (nextTile.getPlant() != null) {
                                        // zombie.attack(nextTile.getPlant());
                                        // }
                                        // zombie.setTimeStamp(currentTime);
                                    }
                                }
                            } else {
                                tiles[i][j].removeZombie(zombie);
                            }
                        }
                    }
                }
            }
            if (produceSun_time == 0 && gametimestamp < 100) {
                produceSun();
                produceSun_time = random.nextInt(5, 10);
            }
            isZombieInEndTile();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    // class InvalidPlantException extends Exception{
    // public InvalidPlantException(String message){
    // super("Plant not in deck");
    // }
    // }

    public void openfile() {
        // open file
    }

    public void spawnZombieActivity(int i) {
        Random random = new Random();
        if (CountZombie < 1 && gametimestamp >= 0 && gametimestamp <= 160) {
            double probability = 0.3;
            if (random.nextDouble(0, 1) < probability) {
                Zombie zombie = spawnZombie();
                tiles[i][10].addZombie(zombie);
                if (flag != 0) {
                    Zombie zombie2 = spawnZombie();
                    Zombie zombie3 = spawnZombie();
                    tiles[i][10].addZombie(zombie2);
                    tiles[i][10].addZombie(zombie3);
                    flag--;
                }
                Peashooter peashooter = new Peashooter();
                tiles[i][8].addPlant(peashooter);
                Peashooter peashooter2 = new Peashooter();
                tiles[i][7].addPlant(peashooter2);
                // System.out.println( tiles[i][8].getPlant());

                List<Zombie> zombies = new ArrayList<>(tiles[i][10].getZombies());
                for (Zombie zombiecheck : zombies) {
                    // Check conditions and remove elements from tiles[10][i] based on zombiecheck
                    if ((!zombiecheck.isAquatic() && (i == 2 || i == 3))
                            || (zombiecheck.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5))) {
                        tiles[i][10].removeZombie(zombiecheck);
                        CountZombie--;
                    }
                }
                // System.out.println(CountZombie);
                // for (Iterator<Zombie> iterator = tiles[10][i].getZombies().iterator();
                // iterator.hasNext();) {
                // Zombie zombiecheck = iterator.next();
                // if ((!zombiecheck.isAquatic() && (i == 2 || i == 3))
                // || (zombiecheck.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5))) {
                // tiles[10][i].removeZombie(zombiecheck);
                // }
                // }
                // for (Zombie zombiecheck : tiles[10][i].getZombies()) {
                // System.out.println(zombiecheck.getName());
                // if ((!zombiecheck.isAquatic() && (i == 2 || i == 3))
                // || (zombiecheck.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5))) {
                // tiles[10][i].removeZombie(zombiecheck);
                // }
                // }
                // System.out.print(tiles[10][i].hasZombie());
            }
        }
    }

    public void ZombieActivity(Zombie zombie, int x_position, int y_position) {
        // System.out.println("Waktu Zombie dibuat: " + (currentTime -
        // zombie.getTimeStamp()) / 1000);
        if (zombie instanceof DolphinRiderZombie) {
            DolphinRiderZombie dolphinRiderZombie = (DolphinRiderZombie) zombie;
            if (!dolphinRiderZombie.hasJumped()) {
                // System.out.println("Posisi Zombie: " + x_position + y_position);
                dolphinRiderZombie.jump(tiles[x_position][y_position], tiles[x_position][y_position - 1],
                        tiles[x_position][y_position - 2], x_position, y_position);
            } else {
                zombie.attack(tiles[x_position][y_position - 1].getPlant());
            }
        } else if (zombie instanceof PoleVaultingZombie) {
            PoleVaultingZombie poleVaultingZombie = (PoleVaultingZombie) zombie;
            if (!poleVaultingZombie.hasJumped()) {
                // System.out.println("Posisi Zombie: " + x_position + y_position);
                // System.out.println(tiles[x_position][y_position-1].getPlant());
                poleVaultingZombie.jump(tiles[x_position][y_position], tiles[x_position][y_position - 1],
                        tiles[x_position][y_position - 2], x_position, y_position);
            } else {
                System.out.println(tiles[x_position][y_position - 1].getPlant().getHealth());
                // System.out.println(tiles[x_position][y_position - 1].getPlant());
                zombie.attack(tiles[x_position][y_position - 1].getPlant());
                System.out.println(tiles[x_position][y_position - 1].getPlant().getHealth());
            }
            // System.out.println(tiles[x_position][y_position - 1].getPlant());
        } else {
            zombie.attack(tiles[x_position][y_position - 1].getPlant());
        }
        zombie.setTimeStamp(currentTime);
    }

    public Zombie spawnZombie() {
        Random random = new Random();
        int zombieInt = 9;
        // int zombieInt = random.nextInt(1, 10);
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
        tiles[x_position][y_position - 1].addZombie(zombie);
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

    public Tile getTiles(Plant plant, int row, int col) {
        // System.out.println(row);
        // System.out.println(col);
        if (plant.getRange() == -1) {
            for (int j = 1; j < col; j++) {
                if (tiles[row][j].hasZombie()) {
                    return tiles[row][j];
                }
            }
        } else {
            if (tiles[col + 1][row].hasZombie()) {
                return tiles[row][col + 1];
            }
        }
        return null;
    }

    public void isZombieInEndTile() {
        for (int x_position = 0; x_position < 6; x_position++) {
            if (tiles[x_position][0].hasZombie()) {
                setGameover();
                break;
            }
        }
    }
}

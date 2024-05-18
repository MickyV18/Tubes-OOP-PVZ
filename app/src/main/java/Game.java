// urusin masalah design pattern dan exception
// threading TT

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
    private int sun;
    long currentTime;
    private int flag = 0;
    private int setter = -1;
    private int limitZombie = 0;
    private int CountZombie = 0;
    private int gametimestamp = -1;
    private List<Plant> deckPlants;
    private boolean gameover = false;
    private boolean firstround = false;
    private Tile[][] tiles = new Tile[6][11];

    public Game() {
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
            setter++;
            if (setter % 2 == 0) {
                produceSun_time--;
                gametimestamp++;
                // System.out.println(sun);
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
            } else if (setter == 400) {
                firstround = true;
            }
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
            // if (tiles[i][j].getPlant(2) == null && tanaman.isSunEnough(tanaman, sun)
            // && tanaman.isCooldown(currentTime, tanaman.getTimeStamp())) {
            // if (tanaman instanceof Lilypad || water.isLilyPlanted()) {
            // water.addPlant(tanaman);
            // }
            // }
            // } else {
            // GroundTile ground = (GroundTile) tiles[x_position][y_position];
            // if (tiles[i][j].getPlant(w) == null && tanaman.isSunEnough(tanaman, sun)
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
            }
            if (gametimestamp == 80 && gametimestamp == 160) {
                flag = 2;
            }

            for (int i = 0; i < 6; i++) {
                if (setter % 2 == 0) {
                    spawnZombieActivity(i);
                }
                for (int j = 1; j < 11; j++) {
                    // plant hp 0 tapi mau nyerang (sinkron)
                    // slowed zombie
                    if (tiles[i][j].getPlant() != null) {
                        Plant inTilePlant = tiles[i][j].getPlant();
                        // System.out.println(inTilePlant);
                        if (inTilePlant.getHealth() != 0) {
                            if (inTilePlant instanceof Sunflower
                                    && ((currentTime - (inTilePlant.getTimeStamp())) / 1000) >= 3) {
                                produceSun();
                                inTilePlant.setTimeStamp(currentTime);
                            }
                            System.out.println(inTilePlant.getAtkSpd());
                            System.out.println("Waktu Nyerang: "
                                    + ((currentTime - inTilePlant.getTimeStamp()) / 1000) % inTilePlant.getAtkSpd());
                            if (((currentTime - (inTilePlant.getTimeStamp())) / 1000)
                                    % inTilePlant.getAtkSpd() == 0
                                    && (currentTime - inTilePlant.getTimeStamp()) / 1000 != 0) {
                                System.out.println("NOUMI");
                                if (inTilePlant instanceof Squash || inTilePlant instanceof CherryBomb
                                        || inTilePlant instanceof TangleKelp) {
                                    if (getTiles(inTilePlant, i, j) != null) {
                                        CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                        getTiles(inTilePlant, i, j).removeAllZombie();
                                        tiles[i][j].removePlant();
                                        if (inTilePlant instanceof CherryBomb) {
                                            if (i == 0) {
                                                if (getTiles(inTilePlant, i + 1, j) != null) {
                                                    CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                    getTiles(inTilePlant, i + 1, j).removeAllZombie();
                                                    tiles[i][j].removePlant();
                                                }
                                            } else if (i == 5) {
                                                if (getTiles(inTilePlant, i - 1, j) != null) {
                                                    CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                    getTiles(inTilePlant, i - 1, j).removeAllZombie();
                                                    tiles[i][j].removePlant();
                                                }
                                            } else {
                                                if (getTiles(inTilePlant, i + 1, j) != null) {
                                                    CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                    getTiles(inTilePlant, i, j).removeAllZombie();
                                                    tiles[i][j].removePlant();
                                                } else if (getTiles(inTilePlant, i - 1, j) != null) {
                                                    CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                    getTiles(inTilePlant, i - 1, j).removeAllZombie();
                                                    tiles[i][j].removePlant();
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (getTiles(inTilePlant, i, j) != null) {
                                        List<Zombie> zombies = new ArrayList<>(
                                                getTiles(inTilePlant, i, j).getZombies());
                                        for (Zombie zombie : zombies) {
                                            inTilePlant.attack(zombie);
                                        }
                                        inTilePlant.setTimeStamp(currentTime);

                                    }
                                }
                                System.out.println(CountZombie);

                            }
                        } else {
                            if (i == 2 || i == 3) {
                                WaterTile watertile = (WaterTile) tiles[i][j];
                                watertile.removePlant();
                            } else {
                                tiles[i][j].removePlant();
                            }
                        }
                    }

                    if (tiles[i][j].hasZombie()) {
                        List<Zombie> zombies = new ArrayList<>(tiles[i][j].getZombies());
                        for (Zombie zombie : zombies) {
                            if (zombie instanceof NewspaperZombie) {
                                NewspaperZombie newspaperZombie = (NewspaperZombie) zombie;
                                if (newspaperZombie.isAngry()) {
                                    newspaperZombie.angry();
                                }
                            }
                            if (zombie.getSlowed() == 0) {
                                zombie.unSlowed();
                            } else if (setter % 2 == 0) {
                                zombie.SlowedTimeDecrease();
                            }
                            if (zombie.getHealth() != 0) {
                                if ((currentTime - zombie.getTimeStamp()) / 1000 != 0) {
                                    // System.out.println(
                                    // "Waktu Zombie Nyerang: " + (currentTime - zombie.getTimeStamp()) / 100);
                                    // System.out.println(setter);
                                    if ((currentTime - zombie.getTimeStamp()) / 100 >= zombie.getAtkSpd() * 10) {
                                        if ((tiles[i][j - 1].getPlant() != null)) {
                                            System.out.println("Health Plant sebelum kena damage: "
                                                    + tiles[i][j - 1].getPlant().getHealth());
                                            ZombieAttack(zombie, i, j);
                                            System.out.println("Health Plant setelah kena damage: "
                                                    + tiles[i][j - 1].getPlant().getHealth());
                                        } else if ((tiles[i][j].getPlant() != null)) {
                                            ZombieAttack(zombie, i, j + 1);
                                        }
                                    }
                                    if (tiles[i][j - 1].getPlant() == null) {
                                        // System.out.println((currentTime - zombie.getTimeStamp()) / 1000);
                                        if ((currentTime - zombie.getTimeStamp()) / 1000 >= 10
                                                && (zombie.getSlowed() == 0 || zombie.getSlowed() == -1)) {
                                            // System.out.println(zombie.getHealth());
                                            move(zombie, i, j);
                                            zombie.setTimeStamp(currentTime);
                                        } else if ((currentTime - zombie.getTimeStamp()) / 1000 >= 15) {
                                            // System.out.println(zombie.getHealth());
                                            move(zombie, i, j);
                                            zombie.setTimeStamp(currentTime);
                                        }
                                    }
                                }
                            } else {
                                tiles[i][j].removeZombie(zombie);
                                CountZombie--;
                            }
                        }
                    }
                }
            }
            if (produceSun_time == 0 && gametimestamp < 100) {
                produceSun();
                produceSun_time = random.nextInt(5, 10);
            }

            // isZeroZombie();
            isZombieInEndTile();

            try {
                Thread.sleep(500);
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

    public void isZeroZombie() {
        if (CountZombie == 0) {
            System.out.println("EZ WINNN ");
            setGameover();
        }
    }

    public void spawnZombieActivity(int i) {
        Random random = new Random();
        if (flag != 0) {
            limitZombie = 25;
        } else if (flag == 0 && CountZombie <= 10) {
            limitZombie = 10;
        }
        if (CountZombie < 1 && gametimestamp >= 0 && gametimestamp <= 160 && !firstround) {
            double probability = 0.3;
            if (random.nextDouble(0, 1) < probability) {
                Zombie zombie = spawnZombie();
                tiles[i][9].addZombie(zombie);
                if (flag != 0) {
                    Zombie zombie2 = spawnZombie();
                    Zombie zombie3 = spawnZombie();
                    tiles[i][9].addZombie(zombie2);
                    tiles[i][9].addZombie(zombie3);
                    flag--;
                }
                // Lilypad lilypad = new Lilypad();
                // tiles[i][8].addPlant(lilypad);
                // Lilypad lilypad2 = new Lilypad();
                // tiles[i][1].addPlant(lilypad2);
                // Wallnut wallnut = new Wallnut();
                // tiles[i][8].addPlant(wallnut);
                // Wallnut wallnut2 = new Wallnut();
                // tiles[i][7].addPlant(wallnut2);
                // Wallnut wallnut3 = new Wallnut();
                // tiles[i][6].addPlant(wallnut3);
                TangleKelp cherrybomb = new TangleKelp();
                tiles[i][7].addPlant(cherrybomb);
                // Sunflower sunflower = new Sunflower();
                // tiles[i][9].addPlant(sunflower);
                // System.out.println(zombie.getName());

                // Peashooter peashooter = new Peashooter();
                // tiles[i][7].addPlant(peashooter);
                // Peashooter peashooter3 = new Peashooter();
                // tiles[i][2].addPlant(peashooter3);
                // Peashooter peashooter2 = new Peashooter();
                // tiles[i][4].addPlant(peashooter2);

                List<Zombie> zombies = new ArrayList<>(tiles[i][9].getZombies());
                for (Zombie zombiecheck : zombies) {
                    // Check conditions and remove elements from tiles[10][i] based on zombiecheck
                    if ((!zombiecheck.isAquatic() && (i == 2 || i == 3))
                            || (zombiecheck.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5))) {
                        tiles[i][9].removeZombie(zombiecheck);
                        CountZombie--;
                        tiles[i][7].removePlant();
                    }
                }
            }
        }
    }

    public void ZombieAttack(Zombie zombie, int x_position, int y_position) {
        if (zombie instanceof DolphinRiderZombie) {
            DolphinRiderZombie dolphinRiderZombie = (DolphinRiderZombie) zombie;
            if (!dolphinRiderZombie.hasJumped()) {
                System.out.println("MASUK ATTACK 2");
                dolphinRiderZombie.jump(tiles[x_position][y_position], tiles[x_position][y_position - 1],
                        tiles[x_position][y_position - 2], x_position, y_position);
            } else {
                zombie.attack(tiles[x_position][y_position - 1].getPlant());
            }
        } else if (zombie instanceof PoleVaultingZombie) {
            PoleVaultingZombie poleVaultingZombie = (PoleVaultingZombie) zombie;
            if (!poleVaultingZombie.hasJumped()) {
                poleVaultingZombie.jump(tiles[x_position][y_position], tiles[x_position][y_position - 1],
                        tiles[x_position][y_position - 2], x_position, y_position);
            } else {
                zombie.attack(tiles[x_position][y_position - 1].getPlant());
            }
        } else {
            System.out.println("Health Zombie yang nyerang: " + zombie.getHealth());
            zombie.attack(tiles[x_position][y_position - 1].getPlant());
            System.out.println("Attack Speed: " + zombie.getAtkSpd());
        }
        zombie.setTimeStamp(currentTime);
    }

    public Zombie spawnZombie() {
        Random random = new Random();
        int zombieInt = 1;
        // int zombieInt = random.nextInt(2, 2);
        if (zombieInt == 1) {
            DolphinRiderZombie zombie = new DolphinRiderZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 2) {
            DuckyTubeZombie zombie = new DuckyTubeZombie();
            CountZombie++;
            return zombie;
        } else if (zombieInt == 3) {
            BucketheadZombie zombie = new BucketheadZombie();
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

    public Tile getTiles(Plant plant, int col, int row) {
        if (plant.getRange() == -1) {
            for (int j = row; j < 10; j++) {
                if (tiles[col][j].hasZombie()) {
                    return tiles[col][j];
                }
            }
        } else {
            System.out.println("Zombie: " + tiles[col][row + 1].hasZombie());
            if (tiles[col][row + 1].hasZombie()) {
                return tiles[col][row + 1];
            }
        }
        return null;
    }

    public void isZombieInEndTile() {
        for (int x_position = 0; x_position < 6; x_position++) {
            if (tiles[x_position][0].hasZombie()) {
                setGameover();
                System.out.println("Zombie winnn");
                break;
            }
        }
    }
}

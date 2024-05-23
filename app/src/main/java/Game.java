package Game;
// urusin masalah design pattern dan exception
// threading TT

// package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.System;

import Creature.CreatureFactory;
import Creature.Plant.*;
import Creature.Zombie.*;
import Tiles.EndTile;
import Tiles.GroundTile;
import Tiles.SpawnTile;
import Tiles.Tile;
import Tiles.WaterTile;
import Sun.Sun;
import Map.*;

public class Game {
    private static Game game = new Game();
    private static long currentTime;
    private static int flag = 0;
    private static int setter = -1;
    private static int limitZombie = 10;
    private static int CountZombie = 0;
    private static int gametimestamp = -1;
    private static List<Plant> deckPlants;
    private static boolean gameover = false;
    private static boolean firstround = false;
    // private Tile[][] tiles = new Tile[6][11];

    public static synchronized Game getGame() {
        if (game == null) {
            synchronized (Game.class) {
                if (game == null) {
                    game = new Game();
                }
            }
        }
        return game;
    }

    private Game() {
        currentTime = System.currentTimeMillis();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) {
                    // Map.getTile(i, j) = new EndTile();
                    Map.setTile(i, j, new EndTile());
                } else if (j == 10) {
                    // Map.getTile(i, j) = new SpawnTile();
                    Map.setTile(i, j, new SpawnTile());

                } else if (i == 2 || i == 3) {
                    if (j != 0 || j != 10) {
                        // Map.getTile(i, j) = new WaterTile();
                        Map.setTile(i, j, new WaterTile());

                    }
                } else {
                    if (j != 0 || j != 10) {
                        // Map.getTile(i, j) = new GroundTile();
                        Map.setTile(i, j, new GroundTile());
                    }
                }
            }
        }
        // System.err.println(tiles[0][0].getClass().getName());
    }

    public void gameloop() {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int produceSun_time = random.nextInt(5, 10);

        while (!isGameover()) {
            // System.out.println("sun = " + Sun.getSun());
            setter++;
            if (setter % 2 == 0) {

                // Produce Sun dari game
                produceSun_time--;
                if (produceSun_time == 0 && gametimestamp < 100) {
                    Sun.produceSun();
                    produceSun_time = random.nextInt(5, 10);
                }

                // nambah waktu permainan
                gametimestamp++;

                // mencetak map
                System.out.println();
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 11; j++) {
                        if (Map.getTile(i, j).hasZombie() && Map.getTile(i, j).getPlant() != null) {
                            System.out.print("A");
                        } else if (Map.getTile(i, j).hasZombie()) {
                            System.out.print("Z");
                        } else if (Map.getTile(i, j).getPlant() != null) {
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
            // WaterTile water = (WaterTile) Map.getTile(x_position, y_position);
            // if (Map.getTile(i, j).getPlant(2) == null && tanaman.isSunEnough(tanaman, sun)
            // && tanaman.isCooldown(currentTime, tanaman.getTimeStamp())) {
            // if (tanaman instanceof Lilypad || water.isLilyPlanted()) {
            // water.addPlant(tanaman);
            // }
            // }
            // } else {
            // GroundTile ground = (GroundTile) Map.getTile(x_position, y_position);
            // if (Map.getTile(i, j).getPlant(w) == null && tanaman.isSunEnough(tanaman, sun)
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
                if (setter % 6 == 0) {
                    spawnZombieActivity(i);
                }
                for (int j = 1; j < 11; j++) {
                    // plant hp 0 tapi mau nyerang (sinkron)
                    // slowed zombie
                    if (Map.getTile(i, j).getPlant() != null) {
                        Plant inTilePlant = Map.getTile(i, j).getPlant();
                        if (inTilePlant.getHealth() != 0) {
                            if (inTilePlant instanceof Sunflower
                                    && ((currentTime - (inTilePlant.getTimeStamp())) / 1000) >= 3) {
                                Sun.produceSun();
                                inTilePlant.setTimeStamp(currentTime);
                            } else if (((currentTime - (inTilePlant.getTimeStamp())) / 1000)
                                    % inTilePlant.getAtkSpd() == 0
                                    && (currentTime - inTilePlant.getTimeStamp()) / 1000 != 0) {
                                if (inTilePlant instanceof Squash || inTilePlant instanceof CherryBomb
                                        || inTilePlant instanceof TangleKelp) {
                                    boolean explode = false;
                                    if (getTiles(inTilePlant, i, j) != null) {
                                        CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                        getTiles(inTilePlant, i, j).removeAllZombie();
                                        explode = true;
                                    } else if (getTiles(inTilePlant, i, j - 1) != null) {
                                        CountZombie -= getTiles(inTilePlant, i, j - 1).getZombies().size();
                                        getTiles(inTilePlant, i, j - 1).removeAllZombie();
                                        explode = true;
                                    }
                                    if (explode) {
                                        if (i == 2 || i == 3) {
                                            WaterTile waterTile = (WaterTile) Map.getTile(i, j);
                                            waterTile.removeSCPlant(Map.getTile(i, j));
                                        } else {
                                            Map.getTile(i, j).removePlant();

                                        }
                                    }
                                    if (inTilePlant instanceof CherryBomb) {
                                        if (i == 0) {
                                            if (getTiles(inTilePlant, i + 1, j) != null) {
                                                CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                getTiles(inTilePlant, i + 1, j).removeAllZombie();
                                                Map.getTile(i, j).removePlant();
                                            }
                                        } else if (i == 5) {
                                            if (getTiles(inTilePlant, i - 1, j) != null) {
                                                CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                getTiles(inTilePlant, i - 1, j).removeAllZombie();
                                                Map.getTile(i, j).removePlant();
                                            }
                                        } else {
                                            if (getTiles(inTilePlant, i + 1, j) != null) {
                                                CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                getTiles(inTilePlant, i, j).removeAllZombie();
                                                Map.getTile(i, j).removePlant();
                                            } else if (getTiles(inTilePlant, i - 1, j) != null) {
                                                CountZombie -= getTiles(inTilePlant, i, j).getZombies().size();
                                                getTiles(inTilePlant, i - 1, j).removeAllZombie();
                                                Map.getTile(i, j).removePlant();
                                            }
                                        }
                                        Map.getTile(i, j).removePlant();
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
                                // System.out.println(CountZombie);

                            }
                        } else {
                            if (i == 2 || i == 3) {
                                WaterTile watertile = (WaterTile) Map.getTile(i, j);
                                watertile.removePlant();
                            } else {
                                Map.getTile(i, j).removePlant();
                            }
                        }
                    }

                    if (Map.getTile(i, j).hasZombie()) {
                        List<Zombie> zombies = new ArrayList<>(Map.getTile(i, j).getZombies());

                        for (Zombie zombie : zombies) {
                            if (zombie instanceof NewspaperZombie) {
                                NewspaperZombie newspaperZombie = (NewspaperZombie) zombie;
                                if (newspaperZombie.isAngry()) {
                                    newspaperZombie.angry();
                                }
                            }
                            if (zombie.getSlowed() == 0 && setter % 2 == 0) {
                                zombie.unSlowed();
                            } else if (setter % 2 == 0 && zombie.getSlowed() > 0) {
                                zombie.SlowedTimeDecrease();
                            }
                            if (zombie.getHealth() != 0) {
                                if ((currentTime - zombie.getTimeStamp()) / 1000 != 0) {
                                    // System.out.println(
                                    // "Waktu Zombie Nyerang: " + (currentTime - zombie.getTimeStamp()) / 100);
                                    // System.out.println(setter);
                                    if ((currentTime - zombie.getTimeStamp()) / 100 >= zombie.getAtkSpd() * 10) {
                                        if ((Map.getTile(i, j-1).getPlant() != null)) {
                                            System.out.println("Health Plant sebelum kena damage: "
                                                    + Map.getTile(i, j-1).getPlant().getHealth());
                                            ZombieAttack(zombie, i, j);
                                            System.out.println("Health Plant setelah kena damage: "
                                                    + Map.getTile(i, j-1).getPlant().getHealth());
                                        } else if ((Map.getTile(i, j).getPlant() != null)) {
                                            ZombieAttack(zombie, i, j + 1);
                                        }
                                    }
                                    if (Map.getTile(i, j-1).getPlant() == null) {
                                        System.out.println(zombie.getSlowed());
                                        if ((currentTime - zombie.getTimeStamp()) / 1000 >= 10
                                                && zombie.getSlowed() == -1) {
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
                                Map.getTile(i, j).removeZombie(zombie);
                                CountZombie--;
                            }
                        }
                    }
                }
            }

            if (produceSun_time == 0 && gametimestamp < 100) {
                Sun.produceSun();
                produceSun_time = random.nextInt(5, 10);
            }

            if (gametimestamp >= 160) {
                isZeroZombie();
            }
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

    public static void openfile() {
        // open file
    }

    public static void isZeroZombie() {
        if (CountZombie == 0) {
            System.out.println("EZ WINNN ");
            setGameover();
        }
    }

    public static void spawnZombieActivity(int i) {
        Random random = new Random();
        double probability = 0.3;
        if (flag != 0) {
            probability = 0.5;
            limitZombie = 25;
        } else if (flag == 0 && CountZombie <= 10) {
            limitZombie = 10;
        }
        // harusnya CountZombie < limitzombie && gametimestamp >= 20
        if (CountZombie < limitZombie && gametimestamp >= 0 && gametimestamp <= 160 && !firstround) {
            if (random.nextDouble(0, 1) < probability) {
                Zombie zombie = CreatureFactory.createZombie();
                Map.getTile(i, 9).addZombie(zombie);
                if (flag != 0) {
                    Zombie zombie2 = CreatureFactory.createZombie();
                    Zombie zombie3 = CreatureFactory.createZombie();
                    Map.getTile(i, 9).addZombie(zombie2);
                    Map.getTile(i, 9).addZombie(zombie3);
                    flag--;
                }
                // Lilypad lilypad = new Lilypad();
                // Map.getTile(i, 9).addPlant(lilypad);
                // Lilypad lilypad2 = new Lilypad();
                // tiles[i][1].addPlant(lilypad2);
                // Wallnut wallnut = new Wallnut();
                // Map.getTile(i, 9).addPlant(wallnut);
                // Wallnut wallnut2 = new Wallnut();
                // Map.getTile(i, 7).addPlant(wallnut2);
                // Wallnut wallnut3 = new Wallnut();
                // tiles[i][6].addPlant(wallnut3);
                // TangleKelp cherrybomb = new TangleKelp();
                // Map.getTile(i, 7).addPlant(cherrybomb);
                // Sunflower sunflower = new Sunflower();
                // Map.getTile(i, 9).addPlant(sunflower);
                // System.out.println(zombie.getName());
                // Squash squash = new Squash();
                // Map.getTile(i, 9).addPlant(squash);

                Peashooter peashooter = new Peashooter();
                Map.getTile(i, 5).addPlant(peashooter);
                Peashooter peashooter3 = new Peashooter();
                Map.getTile(i, 4).addPlant(peashooter3);
                // CherryBomb cher = new CherryBomb();
                // Map.getTile(i, 9).addPlant(cher);
                // System.out.println( Map.getTile(i, 9).getPlant());

                List<Zombie> zombies = new ArrayList<>(Map.getTile(i, 9).getZombies());
                for (Zombie zombiecheck : zombies) {
                    // Check conditions and remove elements from tiles[10][i] based on zombiecheck
                    if ((!zombiecheck.isAquatic() && (i == 2 || i == 3))
                            || (zombiecheck.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5))) {
                        Map.getTile(i, 9).removeZombie(zombiecheck);
                        Map.getTile(i, 5).removePlant();
                        Map.getTile(i, 4).removePlant();
                    } else {
                        CountZombie++;
                    }
                }
                System.out.println(Map.getTile(i, 9).getZombies());
            }
        }
    }

    public static void ZombieAttack(Zombie zombie, int x_position, int y_position) {
        if (zombie instanceof DolphinRiderZombie) {
            DolphinRiderZombie dolphinRiderZombie = (DolphinRiderZombie) zombie;
            if (!dolphinRiderZombie.hasJumped()) {
                System.out.println("MASUK ATTACK 2");
                dolphinRiderZombie.jump(Map.getTile(x_position, y_position), Map.getTile(x_position, y_position-2), x_position,
                        y_position);
            } else {
                zombie.attack(Map.getTile(x_position, y_position-1).getPlant());
            }
        } else if (zombie instanceof PoleVaultingZombie) {
            PoleVaultingZombie poleVaultingZombie = (PoleVaultingZombie) zombie;
            if (!poleVaultingZombie.hasJumped()) {
                poleVaultingZombie.jump(Map.getTile(x_position, y_position), Map.getTile(x_position, y_position-2), x_position,
                        y_position);
            } else {
                zombie.attack(Map.getTile(x_position, y_position-1).getPlant());
            }
        } else {
            System.out.println("Health Zombie yang nyerang: " + zombie.getHealth());
            zombie.attack(Map.getTile(x_position, y_position-1).getPlant());
            System.out.println("Attack Speed: " + zombie.getAtkSpd());
        }
        zombie.setTimeStamp(currentTime);
    }

    public static void move(Zombie zombie, int x_position, int y_position) {
        Map.getTile(x_position, y_position-1).addZombie(zombie);
        Map.getTile(x_position, y_position).removeZombie(zombie);
    }

    public static void digplant() {

    }

    public static boolean isGameover() {
        return gameover;
    }

    public static void setGameover() {
        gameover = true;
    }

    public static void addPlant(Plant plant) {

    }

    public static Tile getTiles(Plant plant, int col, int row) {
        if (plant.getRange() == -1) {
            for (int j = row + 1; j < 10; j++) {
                if (Map.getTile(col, j).hasZombie()) {
                    return Map.getTile(col, j);
                }
            }
        } else {
            System.out.println("Zombie: " + Map.getTile(col, row+1).hasZombie());
            if (Map.getTile(col, row+1).hasZombie()) {
                return Map.getTile(col, row+1);
            }
        }
        return null;
    }

    public static void isZombieInEndTile() {
        for (int x_position = 0; x_position < 6; x_position++) {
            if (Map.getTile(x_position, 0).hasZombie()) {
                setGameover();
                System.out.println("Zombie winnn");
                break;
            }
        }
    }
}

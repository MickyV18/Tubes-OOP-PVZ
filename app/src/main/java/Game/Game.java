package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
import ThreadManager.ThreadManager;
import Map.*;

public class Game implements Runnable {
    private static Game game = new Game();
    private static long currentTime;
    private static int flag = 0;
    private static int setter = -1;
    private static int limitZombie = 10;
    private static int CountZombie = 0;
    private static int gametimestamp = -1;
    private static boolean gameover = false;
    private static boolean firstround = false;

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
                    Map.setTile(i, j, new EndTile());
                } else if (j == 10) {
                    Map.setTile(i, j, new SpawnTile());

                } else if (i == 2 || i == 3) {
                    if (j != 0 || j != 10) {
                        Map.setTile(i, j, new WaterTile());

                    }
                } else {
                    if (j != 0 || j != 10) {
                        Map.setTile(i, j, new GroundTile());
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        int produceSun_time = random.nextInt(5, 10);

        while (!isGameover()) {
            setter++;
            if (setter % 2 == 0) {

                produceSun_time--;
                if (produceSun_time == 0 && gametimestamp < 100) {
                    Sun.produceSun();
                    produceSun_time = random.nextInt(5, 10);
                }

                gametimestamp++;

                if (setter % 20 == 0) {

                    System.out.println();
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 11; j++) {
                            System.out.print("[");
                            if (Map.getTile(i, j).hasZombie() && Map.getTile(i, j).getPlant() != null) {
                                for (Zombie zombie : Map.getTile(i, j).getZombies()) {
                                    System.out.print(zombie.getName() + ", ");
                                }
                                System.out.print(Map.getTile(i, j).getPlant().getName());
                            } else if (Map.getTile(i, j).hasZombie()) {
                                for (Zombie zombie : Map.getTile(i, j).getZombies()) {
                                    System.out.print(zombie.getName() + ", ");
                                }
                            } else if (Map.getTile(i, j).getPlant() != null) {
                                System.out.print(Map.getTile(i, j).getPlant().getName());
                            }
                            System.out.print("]");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
            } else if (setter == 400) {
                firstround = true;
            }
            currentTime = System.currentTimeMillis();

            if (gametimestamp > 200) {
                gametimestamp = 0;
            }
            if (gametimestamp == 80 || gametimestamp == 155) {
                flag = 4;
            }

            for (int i = 0; i < 6; i++) {
                if (setter % 6 == 0) {
                    spawnZombieActivity(i);
                }
                for (int j = 1; j < 11; j++) {
                    if (Map.getTile(i, j).getPlant() != null) {
                        Plant inTilePlant = Map.getTile(i, j).getPlant();
                        if (inTilePlant.getHealth() != 0) {
                            if (inTilePlant instanceof Sunflower
                                    && ((currentTime - (inTilePlant.getTimeStamp())) / 1000) >= 3) {
                                Sun.produceSun();
                                inTilePlant.setTimeStamp(currentTime);
                            } else if ((((currentTime - (inTilePlant.getTimeStamp())) / 1000)
                                    % inTilePlant.getAtkSpd() == 0
                                    && (currentTime - inTilePlant.getTimeStamp()) / 1000 != 0)
                                    || inTilePlant.getFirstAttack()) {
                                if (inTilePlant.getFirstAttack()) {
                                    inTilePlant.setFirstAttack();
                                }
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
                                    if ((currentTime - zombie.getTimeStamp()) / 100 >= zombie.getAtkSpd() * 10) {
                                        if ((Map.getTile(i, j - 1).getPlant() != null)) {
                                            ZombieAttack(zombie, i, j);
                                        } else if ((Map.getTile(i, j).getPlant() != null)) {
                                            ZombieAttack(zombie, i, j + 1);
                                        }
                                    }
                                    if (Map.getTile(i, j - 1).getPlant() == null) {
                                        if ((currentTime - zombie.getTimeStamp()) / 1000 >= 10
                                                && zombie.getSlowed() == -1) {
                                            move(zombie, i, j);
                                            zombie.setTimeStamp(currentTime);
                                        } else if ((currentTime - zombie.getTimeStamp()) / 1000 >= 15) {
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

            if (gametimestamp > 160) {
                isZeroZombie();
            }

            isZombieInEndTile();

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        ThreadManager.getInstance().stopThread();
    }

    public void endGame() {
        flag = 0;
        setter = -1;
        limitZombie = 10;
        CountZombie = 0;
        gametimestamp = -1;
        gameover = false;
        firstround = false;

    }

    public static void isZeroZombie() {
        if (CountZombie == 0) {
            System.out.println("PLANT WIN, TURU DEK");
            setGameover();
        }
    }

    public static void spawnZombieActivity(int i) {
        Random random = new Random();
        double probability = 0.3;
        if (flag != 0) {
            limitZombie = 25;
        } else if (flag == 0) {
            limitZombie = 10;
        }
        if (CountZombie < limitZombie && gametimestamp >= 20 && gametimestamp <= 160 && !firstround) {
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

                List<Zombie> zombies = new ArrayList<>(Map.getTile(i, 9).getZombies());
                for (Zombie zombiecheck : zombies) {
                    if ((!zombiecheck.isAquatic() && (i == 2 || i == 3))
                            || (zombiecheck.isAquatic() && (i == 0 || i == 1 || i == 4 || i == 5))) {
                        Map.getTile(i, 9).removeZombie(zombiecheck);
                    } else {
                        CountZombie++;
                        if ((Map.getTile(i, 8).getPlant() != null)) {
                            ZombieAttack(zombie, i, 9);
                        } else if ((Map.getTile(i, 9).getPlant() != null)) {
                            ZombieAttack(zombie, i, 10);
                        }
                    }
                }
            }
        }
    }

    public static void ZombieAttack(Zombie zombie, int x_position, int y_position) {
        if (zombie instanceof DolphinRiderZombie) {
            DolphinRiderZombie dolphinRiderZombie = (DolphinRiderZombie) zombie;
            if (!dolphinRiderZombie.hasJumped()) {
                dolphinRiderZombie.jump(Map.getTile(x_position, y_position), Map.getTile(x_position, y_position - 2),
                        x_position,
                        y_position);
            } else {
                zombie.attack(Map.getTile(x_position, y_position - 1).getPlant());
            }
        } else if (zombie instanceof PoleVaultingZombie) {
            PoleVaultingZombie poleVaultingZombie = (PoleVaultingZombie) zombie;
            if (!poleVaultingZombie.hasJumped()) {
                poleVaultingZombie.jump(Map.getTile(x_position, y_position), Map.getTile(x_position, y_position - 2),
                        x_position,
                        y_position);
            } else {
                zombie.attack(Map.getTile(x_position, y_position - 1).getPlant());
            }
        } else {
            zombie.attack(Map.getTile(x_position, y_position - 1).getPlant());
        }
        zombie.setTimeStamp(currentTime);

    }

    public static void move(Zombie zombie, int x_position, int y_position) {
        Map.getTile(x_position, y_position - 1).addZombie(zombie);
        Map.getTile(x_position, y_position).removeZombie(zombie);
    }

    public static boolean isGameover() {
        return gameover;
    }

    public static void setGameover() {
        gameover = true;
    }

    public static Tile getTiles(Plant plant, int col, int row) {
        if (plant.getRange() == -1) {
            for (int j = row + 1; j < 10; j++) {
                if (Map.getTile(col, j).hasZombie()) {
                    return Map.getTile(col, j);
                }
            }
        } else {
            if (Map.getTile(col, row + 1).hasZombie()) {
                return Map.getTile(col, row + 1);
            }
        }
        return null;
    }

    public static void isZombieInEndTile() {
        for (int x_position = 0; x_position < 6; x_position++) {
            if (Map.getTile(x_position, 0).hasZombie()) {
                setGameover();
                System.out.println("ZOMBIE WINN, AKU MAKAN OTAK KAMU KALO PUNYA");
                break;
            }
        }
    }

}

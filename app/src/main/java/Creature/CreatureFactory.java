package Creature;

import java.util.Random;

import Creature.Plant.*;
import Creature.Zombie.*;

public class CreatureFactory {
    public static Plant createPlant(String plantType) {
        if (plantType.equalsIgnoreCase("cabbagepult")) {
            return new CabbagePult();
        } else if (plantType.equalsIgnoreCase("cherry bomb")) {
            return new CherryBomb();
        } else if (plantType.equalsIgnoreCase("lilypad")) {
            return new Lilypad();
        } else if (plantType.equalsIgnoreCase("peashooter")) {
            return new Peashooter();
        } else if (plantType.equalsIgnoreCase("repeater")) {
            return new Repeater();
        } else if (plantType.equalsIgnoreCase("snowpea")) {
            return new SnowPea();
        } else if (plantType.equalsIgnoreCase("squash")) {
            return new Squash();
        } else if (plantType.equalsIgnoreCase("sunflower")) {
            return new Sunflower();
        } else if (plantType.equalsIgnoreCase("tangle kelp")) {
            return new TangleKelp();
        } else if (plantType.equalsIgnoreCase("wallnut")) {
            return new Wallnut();
        } else {
            throw new IllegalArgumentException("Unknown plant type: " + plantType);
        }
    }

    public static Zombie createZombie() {
        Random random = new Random();
        int zombieInt = random.nextInt(1, 10);
        if (zombieInt == 6) {
            DolphinRiderZombie zombie = new DolphinRiderZombie();
            return zombie;
        } else if (zombieInt == 2) {
            DuckyTubeZombie zombie = new DuckyTubeZombie();
            return zombie;
        } else if (zombieInt == 3) {
            BucketheadZombie zombie = new BucketheadZombie();
            return zombie;
        } else if (zombieInt == 4) {
            FootballZombie zombie = new FootballZombie();
            return zombie;
        } else if (zombieInt == 5) {
            Gargantuar zombie = new Gargantuar();
            return zombie;
        } else if (zombieInt == 6) {
            JackInTheBoxZombie zombie = new JackInTheBoxZombie();
            return zombie;
        } else if (zombieInt == 7) {
            NewspaperZombie zombie = new NewspaperZombie();
            return zombie;
        } else if (zombieInt == 8) {
            NormalZombie zombie = new NormalZombie();
            return zombie;
        } else if (zombieInt == 9) {
            PoleVaultingZombie zombie = new PoleVaultingZombie();
            return zombie;
        } else {
            ConeheadZombie zombie = new ConeheadZombie();
            return zombie;
        }
    }
}
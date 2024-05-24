package Creature;

import java.util.Random;

import Creature.Plant.*;
import Creature.Zombie.*;

public class CreatureFactory {
    public static Plant createPlant(int plantType) {
        if (plantType == 0) {
            return new CabbagePult();
        } else if (plantType == 1) {
            return new CherryBomb();
        } else if (plantType == 2) {
            return new Lilypad();
        } else if (plantType == 3) {
            return new Peashooter();
        } else if (plantType == 4) {
            return new Repeater();
        } else if (plantType == 5) {
            return new SnowPea();
        } else if (plantType == 6) {
            return new Squash();
        } else if (plantType == 7) {
            return new Sunflower();
        } else if (plantType == 8) {
            return new TangleKelp();
        } else if (plantType == 9) {
            return new Wallnut();
        } else {
            throw new IllegalArgumentException("Unknown plant type: " + plantType);
        }
    }

    public static Zombie createZombie() {
        Random random = new Random();
        int zombieInt = random.nextInt(0, 9);
        if (zombieInt == 0) {
            DolphinRiderZombie zombie = new DolphinRiderZombie();
            return zombie;
        } else if (zombieInt == 1) {
            DuckyTubeZombie zombie = new DuckyTubeZombie();
            return zombie;
        } else if (zombieInt == 2) {
            BucketheadZombie zombie = new BucketheadZombie();
            return zombie;
        } else if (zombieInt == 3) {
            FootballZombie zombie = new FootballZombie();
            return zombie;
        } else if (zombieInt == 4) {
            Gargantuar zombie = new Gargantuar();
            return zombie;
        } else if (zombieInt == 5) {
            JackInTheBoxZombie zombie = new JackInTheBoxZombie();
            return zombie;
        } else if (zombieInt == 6) {
            NewspaperZombie zombie = new NewspaperZombie();
            return zombie;
        } else if (zombieInt == 7) {
            NormalZombie zombie = new NormalZombie();
            return zombie;
        } else if (zombieInt == 8) {
            PoleVaultingZombie zombie = new PoleVaultingZombie();
            return zombie;
        } else {
            ConeheadZombie zombie = new ConeheadZombie();
            return zombie;
        }
    }
}
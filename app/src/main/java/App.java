
import Creature.Plant.*;
import Creature.Zombie.*;
import Creature.*;

public class App {
    public static void main(String[] args){
        System.out.println("hello world!");
        PoleVaultingZombie zombie = new PoleVaultingZombie();
        zombie.Slowed();
        System.out.println(zombie.getSlowed());
    }
}
package app.src.main.java.Zombie;
import app.src.main.java.Creature.*;
import app.src.main.java.Plant.*;

public class Gargantuar extends Zombie {
    public Gargantuar(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic){
        super("Gargantuar", 500, 1000, 10, false);
    }
}

package app.src.main.java.Zombie;
import app.src.main.java.Creature.*;
import app.src.main.java.Plant.*;

public class NewspaperZombie extends Zombie {
    public NewspaperZombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic){
        super("Newspaper Zombie", 225, 100, 1, false);
    }
}

package Creature.Zombie;
import Creature.*;
import Creature.Plant.*;

public class NewspaperZombie extends Zombie {
    public NewspaperZombie(){
        super("Newspaper Zombie", 225, 100, 1, false);
    }

    public void angry() {
        super.setAtkDmg(200);
    }

    public boolean isAngry() {
        return super.getHealth() <= 100;
    }
}

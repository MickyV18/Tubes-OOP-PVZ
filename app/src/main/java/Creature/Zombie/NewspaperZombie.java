package Creature.Zombie;

public class NewspaperZombie extends Zombie {
    public NewspaperZombie(){
        super("Newspaper Zombie", 225, 100, 1, false);
    }

    public void angry() {
        super.setAtkSpd(50);
    }
}

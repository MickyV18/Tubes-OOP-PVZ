public class DolphinRiderZombie extends Zombie implements Jump {
    private boolean jumped = false;
    public DolphinRiderZombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic){
        super("Dolphin Rider Zombie", 175,  100, 1, true);
    }

    public void jump(){
        jumped = false;
    }
    public boolean hasJumped(){
        return jumped;
    }
}

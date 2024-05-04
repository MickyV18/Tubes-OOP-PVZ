public abstract class Plant extends Creature {
    public Plant(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic){
        super(name, health, attack_damage, attack_speed, is_aquatic);
    }

    public void attack(Zombie zombie, Plant plant) {
        if (zombie.getHealth() <= plant.getAtkDmg()){
            zombie.setHealth(0);
        }else{
            zombie.setHealth(zombie.getHealth() - plant.getAtkDmg());
        }
    }
}

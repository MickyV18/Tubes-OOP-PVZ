public abstract class Creature {
    private String name;
    private int health;
    private int attack_damage;
    private int attack_speed;
    private boolean is_aquatic;
 
    public Creature(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic){
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
    }

    public String getName() {
        return name;
      }
    
      public int getHealth() {
        return health;
      }
    
      public int getAtkDmg() {
        return attack_damage;
      }
    
      public int getAtkSpd() {
        return attack_speed;
      }
    
      public void takeDmg(int dmg) {
        if (health < dmg){
            health = 0;
        }else{
            health -= dmg;
        }
      }
    
      public boolean isDead() {
        return health == 0;
      }

      public boolean isAquatic() {
        return is_aquatic;
      }
}

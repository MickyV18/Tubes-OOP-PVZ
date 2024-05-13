
import Creature.Plant.*;
import Creature.Zombie.*;
import Creature.*;
import java.time.*;

public class App<T> {

    private String state;
    // private List<List<T>> Sessions;

    public static void main(String[] args){
        System.out.println("Hello");
        PoleVaultingZombie zombie = new PoleVaultingZombie();
        zombie.Slowed();
        System.out.println(zombie.getSlowed());
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public void startGame() {

    }

    public void exit() {
    
    }

    public void help(){

    }

    public void plantList(){

    }

    public void zombieList(){

    }
}
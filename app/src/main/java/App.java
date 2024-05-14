
import Creature.Plant.*;
import Creature.Zombie.*;
import Creature.*;
import java.lang.System;
import java.util.Scanner;

public class App<T> {

    private static String state;
    private static boolean over = false;
    // private List<List<T>> Sessions;

    public static String getState() {
        return state;
    }

    public static void setState(String newstate) {
        state = newstate;
    }

    public static void startGame() {
        // Deck deckPlants = new Deck()
        // Game game = new Game()
        // gameloop();
    }

    public static void exit() {
        over = true;
    }

    public static void help() {

    }

    public static void plantList() {

    }

    public static void zombieList() {

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long previousTime = System.currentTimeMillis();
        while (!over) {
            String name = scanner.next();
            if (name.equals("")) {
                exit();
            }
        }
        scanner.close();
    }

}
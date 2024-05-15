
import Creature.Plant.*;
import Creature.Zombie.*;
import Creature.*;
import java.lang.System;
import java.util.Scanner;

public class App<T> {

    private static String state;
    private static boolean over = false;
    private static InventoryDeck<Plant> inventorydeck;
    // private List<List<T>> Sessions;

    public static String getState() {
        return state;
    }

    public static void setState(String newstate) {
        state = newstate;
    }

    public static void startGame() {
        // dia ngebuat deck baru trus milih 6 plant, baru mulai gamenya
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();

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

        // Scanner scanner = new Scanner(System.in);
        // inventorydeck = new InventoryDeck<>();
        // while (!over) {
        //     String name = scanner.next();
        //     if (name.equals("Start Game")) {
        //         startGame();
        //     } else if (name.equals("Help")) {
        //         help();
        //     } else if (name.equals("List Plant")){
        //         plantList();
        //     } else if (name.equals("List Zombie")){
        //         zombieList();
        //     } else if (name.equals("Exit")){
        //         exit();
        //     } else{
        //         System.out.println("Command not found");
        //     }
        // }
        // scanner.close();
        Game game = new Game();
        game.gameloop();
    }

}
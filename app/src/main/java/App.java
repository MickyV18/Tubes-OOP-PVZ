
import java.util.Scanner;
import java.lang.System;

import Creature.Plant.Plant;

public class App<T> {

    private static String state;
    private static boolean over = false;
    private static InventoryDeck inventorydeck;
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
        // String name = scanner.next();

        // Game game = new Game();
        inventorydeck = new InventoryDeck();
        boolean stateDeck = false;
        int command;
        // command = scanner.nextInt();

        while (!stateDeck) {
            inventorydeck.printInventory();
            inventorydeck.printDeck();

            System.out.println("\nChoose your deck:");
            System.out.println("1. Random deck");
            System.out.println("2. Add plant");
            System.out.println("3. Remove plant");
            System.out.println("4. Replace plant");
            System.out.println("5. Swap plant deck");
            System.out.println("6. Swap plant inventory");
            System.out.println("7. Clear deck");
            System.out.println("8. Ready!");
            System.out.println("\nInput command index: ");

            command = scanner.nextInt();
            int idx1, idx2;
            switch (command) {
                case 1:
                    inventorydeck.randomDeck();
                    break;
                case 2:
                    System.out.println("Input inventory plant index: ");
                    idx1 = scanner.nextInt();
                    inventorydeck.addPlantToDeck(idx1 - 1);
                    break;
                case 3:
                    System.out.println("Input deck plant index: ");
                    idx1 = scanner.nextInt();
                    inventorydeck.removePlantFromDeck(idx1 - 1);
                    break;
                case 4:
                    System.out.println("Input inventory plant index: ");
                    idx1 = scanner.nextInt();
                    System.out.println("Input deck plant index: ");
                    idx2 = scanner.nextInt();
                    inventorydeck.replaceDeckWInventory(idx1 - 1, idx2 - 1);
                    break;
                case 5:
                    System.out.println("Input deck plant index: ");
                    idx1 = scanner.nextInt();
                    System.out.println("Input deck plant index: ");
                    idx2 = scanner.nextInt();
                    inventorydeck.swapPlantDeck(idx1 - 1, idx2 - 1);
                    break;
                case 6:
                    System.out.println("Input inventory plant index: ");
                    idx1 = scanner.nextInt();
                    System.out.println("Input inventory plant index: ");
                    idx2 = scanner.nextInt();
                    inventorydeck.swapPlantInventory(idx1 - 1, idx2 - 1);
                    break;
                case 7:
                    inventorydeck.clearDeck();
                    break;
                case 8:
                    if (inventorydeck.isDeckFull()) {
                        stateDeck = true;
                    } else {
                        System.out.println("Deck is not ready");
                    }
                    Game game = new Game();
                    game.gameloop();
                    break;
                default:
                    System.out.println("Wrong index, input again!");
                    break;
            }
        }
        scanner.close();
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
        // String name = scanner.next();
        // if (name.equals("Start Game")) {
        // startGame();
        // } else if (name.equals("Help")) {
        // help();
        // } else if (name.equals("List Plant")){
        // plantList();
        // } else if (name.equals("List Zombie")){
        // zombieList();
        // } else if (name.equals("Exit")){
        // exit();
        // } else{
        // System.out.println("Command not found");
        // }
        // }
        // scanner.close();
        Game game = new Game();
        game.gameloop();
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("KETIK DISINI: ");
        // String command = scanner.nextLine();
        // System.out.println(command);
        // scanner.close();
        // startGame();
    }

}
import GUI.*;

import java.util.Scanner;

import Inventory_Deck.InventoryDeck;

public class App<T> {

    private static String state;
    private static boolean over = false;
    private static InventoryDeck inventorydeck;
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
                    if (inventorydeck.isDeckFull()) {
                        System.out.println("Deck full!");
                    } else if (inventorydeck.isInDeck(idx1-1)) {
                        System.out.println("Plant already in deck");
                    } else {
                        inventorydeck.addPlantToDeck(idx1 - 1);
                    }
                    break;
                case 3:
                    if (inventorydeck.isDeckEmpty()) {
                        System.out.println("Deck is empty!");
                    } else {
                        System.out.println("Input deck plant index: ");
                        idx1 = scanner.nextInt();
                        inventorydeck.removePlantFromDeck(idx1 - 1);
                    }
                    break;
                case 4:
                    if (inventorydeck.isDeckEmpty()) {
                        System.out.println("Deck is empty!");
                    } else {
                        System.out.println("Input inventory plant index: ");
                        idx1 = scanner.nextInt();
                        if (!inventorydeck.isInDeck(idx1-1)) {
                            System.out.println("Input deck plant index: ");
                            idx2 = scanner.nextInt();
                            inventorydeck.replaceDeckWInventory(idx1 - 1, idx2 - 1);
                        } else {
                            System.out.println("Plant already in deck");
                        }
                        
                    }
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
                        Game game = new Game();
                        game.gameloop();
                        break;
                    } else {
                        System.out.println("Deck is not ready");
                    }
                    break;
                default:
                    System.out.println("Wrong index, input again!");
                    break;
            }
            if (stateDeck) {
                Game game = new Game();
                game.gameloop();
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
        System.out.println("\n================================| HELP |================================\n");
        System.out.println("Start Game  : Start the game");
        System.out.println("Plant List  : Show all plants");
        System.out.println("Zombie List : Show all zombies");
        System.out.println("Help        : Show all commands");
        System.out.println("Exit        : Exit the game");
    }

    public static void plantList() {
        System.out.println("\n=============================| PLANT LIST |=============================\n");
        System.out.println("1. Cabbagepult");
        System.out.println("2. Cherry Bomb");
        System.out.println("3. Lilypad");
        System.out.println("4. Peashooter");
        System.out.println("5. Reapeter");
        System.out.println("6. Snowpea");
        System.out.println("7. Squash");
        System.out.println("8. Sunflower");
        System.out.println("9. Tangle Kelp");
        System.out.println("10. Wallnut");
    }

    public static void zombieList() {
        System.out.println("\n============================| ZOMBIE LIST |=============================\n");
        System.out.println("1. Buckethead Zombie");
        System.out.println("2. Conehead Zombie");
        System.out.println("3. Dolphin Rider Zombie");
        System.out.println("4. Ducky Tube Zombie");
        System.out.println("5. Football Zombie");
        System.out.println("6. Gargantuar");
        System.out.println("7. JackInTheBox Zombie");
        System.out.println("8. Newspaper Zombie");
        System.out.println("9. Normal Zombie");
        System.out.println("10. Pole Vaulting Zombie");
    }

    public static void main(String[] args) {
        new MenuUI();
        // Scanner scanner = new Scanner(System.in);
        // // inventorydeck = new InventoryDeck<>();
        // // while (!over) {
        // System.out.println("try Help to print out available commands");
        // while(true){
        //     String name = scanner.nextLine();
        //     if (name.equals("Start Game")) {
        //         Game game = new Game();
        //         game.gameloop();
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
        // }
        // scanner.close();
        
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("KETIK DISINI: ");
        // String command = scanner.nextLine();
        // System.out.println(command);
        // scanner.close();
        // help();
        // plantList();
        // zombieList();
        
    }

}

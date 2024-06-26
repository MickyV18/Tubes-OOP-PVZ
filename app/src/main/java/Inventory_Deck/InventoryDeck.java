package Inventory_Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Creature.Plant.CabbagePult;
import Creature.Plant.CherryBomb;
import Creature.Plant.Lilypad;
import Creature.Plant.Peashooter;
import Creature.Plant.Plant;
import Creature.Plant.Repeater;
import Creature.Plant.SnowPea;
import Creature.Plant.Squash;
import Creature.Plant.Sunflower;
import Creature.Plant.TangleKelp;
import Creature.Plant.Wallnut;

public class InventoryDeck<T extends Plant> {
    private List<T> deck;
    private List<T> inventory;

    @SuppressWarnings("unchecked")
    public InventoryDeck() {
        deck = new ArrayList<>();
        inventory = new ArrayList<>();
        inventory.add((T) new CabbagePult());
        inventory.add((T) new CherryBomb());
        inventory.add((T) new Lilypad());
        inventory.add((T) new Peashooter());
        inventory.add((T) new Repeater());
        inventory.add((T) new SnowPea());
        inventory.add((T) new Squash());
        inventory.add((T) new Sunflower());
        inventory.add((T) new TangleKelp());
        inventory.add((T) new Wallnut());
    }

    public void addPlantToDeck(int idx) {
        if (deck.size() < 6) {
            T plant = inventory.get(idx);
            deck.add(plant);
        }
    }

    public void removePlantFromDeck(int idx) {
        deck.remove(idx);
    }

    public void replaceDeckWInventory(int old, int newP) {
        if (newP >= 0 && newP < deck.size()) {
            T plant = inventory.get(old);
            deck.set(newP, plant);
        }

    }

    public void swapPlantDeck(int old, int newP) {
        if (old >= 0 && old < deck.size() && newP >= 0 && newP < deck.size()) {
            T temp = deck.get(old);
            deck.set(old, deck.get(newP));
            deck.set(newP, temp);
        }
    }

    public void swapPlantInventory(int old, int newP) {
        if (old >= 0 && old < inventory.size() && newP >= 0 && newP < inventory.size()) {
            T temp = inventory.get(old);
            inventory.set(old, inventory.get(newP));
            inventory.set(newP, temp);
        }
    }

    public void randomDeck() {
        deck.clear();
        Random random = new Random();
        while (deck.size() < 6) {
            int idx = random.nextInt(inventory.size());
            T plant = inventory.get(idx);
            if (!deck.contains(plant)) {
                deck.add(plant);
            }
        }
    }

    public void clearDeck() {
        deck.clear();
    }

    public boolean isDeckFull() {
        return deck.size() == 6;
    }

    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }

    public boolean isInDeck(int idx) {
        return deck.contains(inventory.get(idx));
    }

    public T getPlantFromInventory(int idx) {
        return inventory.get(idx);
    }

    public void printDeck() {
        int count = 1;
        System.out.println("\nDeck:");
        for (T plant : deck) {
            if (plant != null) {
                System.out.println(count + ". " + plant.getName());
            } else {
                System.out.println(count + ". (Empty Slot)");
            }
            count++;
        }
    }

    public void printInventory() {
        int count = 1;
        System.out.println("\nInventory:");
        for (T plant : inventory) {
            System.out.println(count + ". " + plant.getName());
            count++;
        }
    }
}
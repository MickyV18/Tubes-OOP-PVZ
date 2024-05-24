package ThreadManager;

import java.util.ArrayList;
import java.util.List;

import GUI.GameUI;
import Game.*;
import Map.Map;
import Creature.Zombie.Zombie;
import GUI.*;

public class ThreadManager {
    private static ThreadManager instance;
    private ArrayList<Runnable> threadList;

    private ThreadManager() {
        threadList = new ArrayList<>();
    }

    public static synchronized ThreadManager getInstance() {
        if (instance == null) {
            instance = new ThreadManager();
        }
        return instance;
    }

    public synchronized void startThread() {
        for (Runnable thread : threadList) {
            new Thread(thread).start();
        }
    }

    public synchronized void stopThread() {
        for (Runnable thread : threadList) {
            if (thread instanceof Game) {
                ((Game) thread).endGame();
            } else if (thread instanceof GameUI) {
                ((GameUI) thread).endGUIGame();
            }
        }
        GUI.loadState("menu");
        threadList.clear();
    }

    public synchronized void removeThread(Runnable thread) {
        threadList.remove(thread);
    }

    public synchronized ArrayList<Runnable> getList() {
        return threadList;
    }

    public synchronized void addThread(Runnable thread) {
        threadList.add(thread);
    }

    public synchronized void getZombie() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 11; j++) {
                List<Zombie> zombies = new ArrayList<>(Map.getTile(i, 9).getZombies());
                for (Zombie zombie : zombies) {
                    // GUI
                }
            }
        }
    }
}
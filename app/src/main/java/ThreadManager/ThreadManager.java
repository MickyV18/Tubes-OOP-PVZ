package ThreadManager;

import java.util.ArrayList;

import GUI.GameUI;
import Game.*;

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
            if (thread instanceof Game){
                ((Game)thread).endGame();
            } else if (thread instanceof GameUI){
                ((GameUI)thread).endGUIGame();
            }
            // nge stop setiap thread di file
        }
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
}
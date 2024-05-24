package GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class GUI<T> implements Runnable{
    // State : menu, help, zombielist, plantlist, inventory, game
    public static String state;
    private List<JFrame> scenes;

    @Override
    public void run() {
        scenes = new ArrayList<JFrame>();
        scenes.add(new MenuUI());
        scenes.add(new HelpUI());
        scenes.add(new ZombieListUI());
        scenes.add(new PlantListUI());
        scenes.add(new InventoryUI());
        scenes.add(new GameUI());
        
    }

    public void loadState(String state) {
        for (JFrame scene : scenes) {
            if (state == "menu" && scene instanceof MenuUI) scene.setVisible(true);
            else if (state == "help" && scene instanceof HelpUI) scene.setVisible(true);
            else  if (state == "plantlist" && scene instanceof PlantListUI) scene.setVisible(true);
            else if (state == "zombielist" && scene instanceof ZombieListUI) scene.setVisible(true);
            else if (state == "inventory" && scene instanceof InventoryUI) scene.setVisible(true);
            else if (state == "game" && scene instanceof GameUI) scene.setVisible(true);
        }
    }
    
}

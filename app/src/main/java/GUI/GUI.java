package GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class GUI {
    // State : menu, help, zombielist, plantlist, inventory, game
    private static List<JFrame> scenes;

    public GUI() {
        scenes = new ArrayList<JFrame>();
        scenes.add(new MenuUI());
        scenes.add(new HelpUI());
        scenes.add(new ZombieListUI());
        scenes.add(new PlantListUI());
        scenes.add(new InventoryUI());
        loadState("menu");
    }

    public static void loadState(String state) {
        for (JFrame scene : scenes) {
            if (state == "menu" && scene instanceof MenuUI) scene.setVisible(true);
            else if (state == "help" && scene instanceof HelpUI) scene.setVisible(true);
            else  if (state == "plantlist" && scene instanceof PlantListUI) scene.setVisible(true);
            else if (state == "zombielist" && scene instanceof ZombieListUI) scene.setVisible(true);
            else if (state == "inventory" && scene instanceof InventoryUI) scene.setVisible(true);
        }
    }
    
}

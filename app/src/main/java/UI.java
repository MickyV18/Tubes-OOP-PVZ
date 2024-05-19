import java.awt.Color;
import javax.swing.*;

public class UI {
    JFrame window;
    JPanel map;
    JLabel mapLabel;
    
    public UI() {
        createWindow();
        createMapPanel();
        window.setVisible(true);
    }

    private void createWindow() {
        // Making a JFrame object
        window = new JFrame();
        // Setting the size of JFrame
        window.setSize(880,660);
        // Setting the BG color
        window.getContentPane().setBackground(Color.BLACK);
        // Make the code ends when the window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set layout to null so we can rearrange the position by code
        window.setLayout(null);
        // Setting the window to be in the middle of the screen when it stars
        window.setLocationRelativeTo(null);
        // Setting the title of the window
        window.setTitle("Plants vs Zombies");
        // Set risezable to false
        window.setResizable(false);
    }

    private void createMapPanel() {
        map = new JPanel();
        map.setBounds(16, 96, 704, 384);
        map.setBackground(Color.blue);
        map.setLayout(null);
        window.add(map);

        mapLabel = new JLabel();
        mapLabel.setBounds(0, 0, 704, 384);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("Res/Map.png"));
        mapLabel.setIcon(bgIcon);
        map.add(mapLabel);

        System.out.println(bgIcon);
    }
    
    public static void main(String[] args) {
        new UI();
    }
}

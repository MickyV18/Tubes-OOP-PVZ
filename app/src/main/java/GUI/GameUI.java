package GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUI extends JFrame {
    public List<JPanel> cardPanels = new ArrayList<JPanel>();
    
    public GameUI() {
        // Setting the JFrame
        this.setLayout(null);
        this.setSize(880, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Inserting the map image
        ImageIcon mapImageIcon = new ImageIcon("src/Res/Map.png");
        JLabel mapLabel = new JLabel(mapImageIcon);
        mapLabel.setBounds(12, 90, 704, 384);
        mapLabel.setText("HAUSHUAHSUAHUH");
        this.add(mapLabel);

        // Setting the deck panel
        cardPanels = new ArrayList<JPanel>();
        for (int i = 0; i < 6; ++i) {
            JPanel cardPanel = new JPanel();
            cardPanel.setBounds(25 + (i * 100), 525, 75, 75);
            cardPanel.setBackground(Color.red);
            cardPanels.add(cardPanel);
            this.add(cardPanels.get(i));
        }

        // Set visible
        this.setVisible(true);
    }


}

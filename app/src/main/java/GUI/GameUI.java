package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public class GameUI extends JFrame implements ActionListener, MouseListener, Runnable{
    public List<JButton> cardButtons = new ArrayList<JButton>();
    // private List<ImageIcon> plantImages = new ArrayList<ImageIcon>();

    private Point[][] gridPos = new Point[6][11];
    private JLabel[][] grid = new JLabel[6][11];
    private Point mousePoint;
    private int currentlyPlacing = -1;

    private JLayeredPane mapPane = new JLayeredPane();
    
    public GameUI() {
        // Setting the JFrame
        this.setLayout(null);
        this.setSize(880, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // Setting up the map grid
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 11; ++j) {
                gridPos[i][j] = new Point(159 + (j * 66), 83 + (i * 73));
                JLabel panel = new JLabel();
                panel.setBackground(Color.RED);
                panel.setBounds(159 + (j * 66), 83 + (i * 73), 60, 60);
                panel.setVerticalAlignment(JLabel.CENTER);
                panel.setHorizontalAlignment(JLabel.CENTER);
                grid[i][j] = panel;
                mapPane.add(grid[i][j]);
            }
        }

        // Making the map pane
        mapPane.setBounds(0, 0, 886, 505);
        mapPane.addMouseListener(this);
        this.add(mapPane);

        // Inserting the map image
        ImageIcon mapImageIcon = new ImageIcon("src/Res/Map.png");
        Image mapTemp = mapImageIcon.getImage().getScaledInstance(886, 505, java.awt.Image.SCALE_SMOOTH);
        mapImageIcon = new ImageIcon(mapTemp);
        JLabel mapLabel = new JLabel(mapImageIcon);
        mapLabel.setBounds(0, 0, 886, 505);
        mapPane.add(mapLabel, JLayeredPane.DEFAULT_LAYER);

        // Making plantImages
        // ImageIcon plantImage;
        // for (int i = 0; i < 10; ++i) {
        //     plantImage = new ImageIcon("src/Res/Plant" + i + ".png");
        //     Image temp = plantImage.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        //     plantImage = new ImageIcon(temp);
        //     plantImages.add(plantImage);
        // }

        // Setting the deck panel
        for (int i = 0; i < 6; ++i) {
            int cardIdx = InventoryUI.deck.get(i);
            JButton cardButton = new JButton(InventoryUI.plantCardImages.get(cardIdx));
            cardButton.setBounds(25 + (i * 100), 525, 75, 90);
            cardButton.setBackground(Color.GRAY);
            cardButton.setOpaque(true);
            cardButton.setFocusable(false);
            cardButton.addActionListener(this);
            cardButtons.add(cardButton);
            this.add(cardButtons.get(i));
        }

        // Set visible
        this.setVisible(true);

        run();
    }

    @Override
    public void run() {
        // while (true) {
        JLabel label =  new JLabel();
        label.setBounds(0, 0, 60, 60);
        mapPane.add(label);
            
            
            
        //     try {
        //         Thread.sleep(10);
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
    }

    // Card button events
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        for (JButton jButton : cardButtons) {
            jButton.setBackground(Color.GRAY);
        }
        int idx = cardButtons.indexOf(e.getSource());
        currentlyPlacing = InventoryUI.deck.get(idx);
        cardButtons.get(idx).setBackground(Color.darkGray);
    }

    // Mouse events
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
        mousePoint = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(mousePoint, e.getComponent());
        System.out.println(mousePoint);
        boolean hasPlaced = false;
        if (currentlyPlacing != -1) {
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 11; ++j) {
                    if (insideGrid(gridPos[i][j], mousePoint))
                    {
                        grid[i][j].setIcon(InventoryUI.plantImages.get(currentlyPlacing));
                        for (JButton jButton : cardButtons) {
                            jButton.setBackground(Color.GRAY);
                        }
                        hasPlaced = true;
                        break;
                    }
                    if (hasPlaced) break;
                }
            }
            if (hasPlaced) currentlyPlacing = -1;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }


    public boolean insideGrid(Point grid, Point p) {
        return (p.getX() > grid.getX()) && (p.getX() <= grid.getX() + 66)
            && (p.getY() > grid.getY()) && (p.getY() <= grid.getY() + 73);
    }

    public void endGUIGame(){
        this.setVisible(false);
        // this.dispose();
    }
}

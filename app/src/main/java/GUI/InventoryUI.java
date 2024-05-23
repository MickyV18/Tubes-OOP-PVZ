package GUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class InventoryUI extends JFrame implements ActionListener {
    private JButton nextButton;
    private List<JButton> buttons;
    public static List<ImageIcon> plantImages;
    private List<JPanel> cardPanels;
    private int selected = 0;

    public InventoryUI() {
        // Setting the JFrame
        this.setLayout(null);
        this.setSize(880, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Setting up plant images
        plantImages = new ArrayList<ImageIcon>();
        ImageIcon plantImage;
        for (int i = 0; i < 9; ++i) {
            plantImage = new ImageIcon("src/Res/Plant" + i + ".png");
            Image temp = plantImage.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            plantImage = new ImageIcon(temp);
            plantImages.add(plantImage);
        }
        
        // Setting the buttons
        buttons = new ArrayList<JButton>();
        for (int i = 0; i < 10; ++i) {
            JButton button = new JButton();
            if (i < 5) {
                button.setBounds(50 + (i * 125), 50, 100, 100);
            }
            else {
                button.setBounds(50 + ((i - 5) * 125), 175, 100, 100);
            }
            buttons.add(button);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setBackground(Color.white);
            this.add(buttons.get(i));
        }

        // Setting the deck panel
        cardPanels = new ArrayList<JPanel>();
        for (int i = 0; i < 6; ++i) {
            JPanel cardPanel = new JPanel();
            cardPanel.setBounds(50 + (i * 100), 460, 75, 75);
            cardPanel.setBackground(Color.white);
            cardPanels.add(cardPanel);
            this.add(cardPanels.get(i));
        }

        // Setting the next button
        nextButton = new JButton("->");
        nextButton.setBounds(750, 460, 75, 75);
        nextButton.setBackground(Color.white);
        nextButton.setFocusable(false);
        nextButton.addActionListener(this);
        this.add(nextButton);
        nextButton.setVisible(false);

        // Set Visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Updating the buttons
        if (e.getSource() != nextButton) {
            JButton button = (JButton) e.getSource();
            if (button.getBackground() == Color.white && selected < 6) {
                button.setBackground(Color.GRAY);
                selected ++;
            }
            else if (button.getBackground() == Color.gray) {
                button.setBackground(Color.white);
                selected --;
            }
        }

        // Updating the card panels
        for (int i = 0; i < 6; ++i) {
            if (i < selected) {
                cardPanels.get(i).setBackground(Color.red);
            }
            else {
                cardPanels.get(i).setBackground(Color.white);
            }
        }

        // Updating the nextButton
        if (selected == 6) nextButton.setVisible(true);
        else nextButton.setVisible(false);

        // Changing JFrame
        if (e.getSource() == nextButton) {
            new GameUI();
            this.setVisible(false);
        }
    }
}

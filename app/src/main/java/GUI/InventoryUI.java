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
    public static List<Integer> deck;
    private List<JLabel> cardLabels;
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
        for (int i = 0; i < 10; ++i) {
            plantImage = new ImageIcon("src/Res/plant" + i + ".png");
            Image temp = plantImage.getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
            plantImage = new ImageIcon(temp);
            plantImages.add(plantImage);
        }
        
        // Setting the buttons
        buttons = new ArrayList<JButton>();
        for (int i = 0; i < 10; ++i) {
            JButton button = new JButton();
            button.setIcon(plantImages.get(i));
            if (i < 5) {
                button.setBounds(50 + (i * 125), 50, 100, 100);
            }
            else {
                button.setBounds(50 + ((i - 5) * 125), 175, 100, 100);
            }
            button.setFocusable(false);
            buttons.add(button);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setBackground(Color.white);
            this.add(buttons.get(i));
        }

        // Setting the deck panel
        deck = new ArrayList<Integer>();
        cardLabels = new ArrayList<JLabel>();
        for (int i = 0; i < 6; ++i) {
            JLabel cardLabel = new JLabel();
            cardLabel.setBounds(50 + (i * 125), 460, 100, 100);
            cardLabel.setHorizontalAlignment(JLabel.CENTER);
            cardLabel.setVerticalAlignment(JLabel.CENTER);
            cardLabel.setBackground(Color.white);
            cardLabel.setOpaque(true);
            cardLabels.add(cardLabel);
            this.add(cardLabels.get(i));
        }

        // Setting the next button
        nextButton = new JButton("->");
        nextButton.setBounds(780, 472, 75, 75);
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
        int idx = buttons.indexOf(e.getSource());
        // Updating the buttons
        if (e.getSource() != nextButton) {
            System.out.println(idx);
            JButton button = (JButton) e.getSource();
            if (button.getBackground() == Color.white && selected < 6) {
                // Adding card label
                deck.add(idx);
                button.setBackground(Color.GRAY);
                selected ++;
            }
            else if (button.getBackground() == Color.gray) {
                // Removing card label
                for (int i = 0; i < deck.size(); i++) {
                    if (deck.get(i) == idx) deck.remove(i);
                }
                button.setBackground(Color.white);
                selected --;
            }
        }
        System.out.println(deck.toString());
        // Updating the card panels
        for (int i = 0; i < 6; ++i) {
            if (i < selected) {
                cardLabels.get(i).setBackground(Color.GRAY);
                Integer card = deck.get(i);
                ImageIcon image = plantImages.get(card);
                cardLabels.get(i).setIcon(image);
            }
            else {
                cardLabels.get(i).setBackground(Color.white);
                cardLabels.get(i).setIcon(null);
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

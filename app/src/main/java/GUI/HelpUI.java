package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HelpUI extends JFrame implements ActionListener {
    JButton backButton;
    
    public HelpUI() {
        // Setting up the frame
        this.setLayout(null);
        this.setSize(880, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.CYAN);

        // Setting up the back button
        backButton = new JButton("<-");
        backButton.setBounds(775, 25, 75, 75);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        this.add(backButton);

        // Setting up background
        ImageIcon backgroundImg = new ImageIcon("src/Res/Help.png");
        JLabel background = new JLabel(backgroundImg);
        background.setBounds(0, 0, 880, 660);
        this.add(background);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            GUI.loadState("menu");
            this.setVisible(false);
            // new MenuUI();
            // this.setVisible(false);
        }
    }
    
}

package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlantList extends JFrame implements ActionListener {
    JButton backButton;
    
    public PlantList() {
        // Setting up the frame
        this.setLayout(null);
        this.setSize(880, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.CYAN);

        // Setting up the back button
        backButton = new JButton("<-");
        backButton.setBounds(25, 25, 75, 75);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        this.add(backButton);

        // SET VISIBLE
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new Menu();
            this.setVisible(false);
        }
    }
    
}

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
        backButton.setBounds(25, 25, 75, 75);
        backButton.addActionListener(this);
        backButton.setFocusable(false);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new MenuUI();
            this.setVisible(false);
        }
    }
    
}

package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ZombieListUI extends JFrame implements ActionListener {
    JButton backButton;
    JLabel backgroundLabel;
    JLayeredPane pane;
    List<ImageIcon> backgrounds;
    List<JButton> buttons;
    
    public ZombieListUI() {
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

        // Setting up backgrounds
        backgrounds = new ArrayList<ImageIcon>();
        for (int i = 0; i < 10; ++i) {
            ImageIcon background = new ImageIcon("src/Res/zombielist/zombie" + i + ".png");
            backgrounds.add(background);
        }

        // Setting up pane
        pane = new JLayeredPane();
        pane.setBounds(0, 0, 880, 660);
        this.add(pane);

        // Setting up JLabel
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 880, 660);
        setBackground(0);
        pane.add(backgroundLabel);

        // Setting up buttons;
        buttons = new ArrayList<JButton>();
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton("AYUAUAUYSAU");
            // button.setBackground(Color.red);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.addActionListener(this);
            if (i < 4) button.setBounds(41 + (i * 110), 120, 100, 100);
            else if (i < 8) button.setBounds(41 + ((i - 4) * 110), 230, 100, 100);
            else button.setBounds(40 + ((i - 8) * 110), 340, 100, 100);
            buttons.add(button);
            pane.add(buttons.get(i));
        }

    }

    public void setBackground(int idx) {
        backgroundLabel.setIcon(backgrounds.get(idx));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            GUI.loadState("menu");
            this.setVisible(false);
            // new MenuUI();
            // this.setVisible(false);
        }
        else
        {
            setBackground(buttons.indexOf(e.getSource()));
        }
    }
    
}

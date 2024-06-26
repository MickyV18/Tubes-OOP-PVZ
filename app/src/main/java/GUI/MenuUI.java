package GUI;
// import java.awt.Graphics;
// import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MenuUI extends JFrame implements ActionListener{
    // private Image background;
    public JButton startGameButton;
    public JButton helpButton;
    public JButton plantListButton;
    public JButton zombieListButton;
    public JButton exitButton;
    
    public MenuUI() {
        // SUPER!
        super();

        // Setting up the background
        // background = new ImageIcon("GUI/Res/menuBG.jpg").getImage();
        this.setLayout(null);
        this.setSize(880, 660);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        // Setting up the buttons
        startGameButton = new JButton("Start Game");
        setupButton(startGameButton, 0);
        plantListButton = new JButton("Plant List");
        setupButton(plantListButton, 1);
        zombieListButton = new JButton("Zombie List");
        setupButton(zombieListButton, 2);
        helpButton = new JButton("Help");
        setupButton(helpButton, 3);
        exitButton = new JButton("Exit");
        setupButton(exitButton, 4);
    }

    private void setupButton(JButton button, int iter) {
        int width = 680;
        int height = 75;
        int x = 100;
        int y = 80;
        int i = 100;
        button.setBounds(x, y + (i * iter), width, height);
        button.setFocusable(false);
        button.addActionListener(this);
        this.add(button);
    }

    // @Override
    // public void paint(Graphics g) {
    //     super.paint(g);
    //     g.drawImage(background, 0, 0, null);
    // }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            GUI.loadState("inventory");
            this.setVisible(false);
        }
        else if (e.getSource() == helpButton) {
            GUI.loadState("help");
            this.setVisible(false);
        }
        else if (e.getSource() == plantListButton) {
            GUI.loadState("plantlist");
            this.setVisible(false);
        }
        else if (e.getSource() == zombieListButton) {
            GUI.loadState("zombielist");
            this.setVisible(false);
        }
        else if (e.getSource() == exitButton) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}

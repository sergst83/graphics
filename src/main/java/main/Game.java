package main;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Game {

    public static void main(String[] args) {
        JFrame f = new JFrame("BrakePont");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLayout(new BorderLayout());
        f.add(new GamePanel(), BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
    }
}

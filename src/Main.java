import utils.GamePanel;

import javax.swing.*;
import static utils.Constant.*;

public class Main {
    private Main() {
        /* Create a main window frame for the game */
        JFrame window = new JFrame(WINDOW_TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        /* Create a game panel */
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.runThread();
    }

    public static void main(String[] args) {
        new Main();
    }
}
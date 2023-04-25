package if2212_tb_01_01;

import if2212_tb_01_01.utils.GamePanel;

import javax.swing.*;

import static if2212_tb_01_01.utils.Constant.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class App {
    public App(){
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
        new App();
    }
}
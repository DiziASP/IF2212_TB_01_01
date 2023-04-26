import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame ();
        window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE) ;
        window.setResizable (false);

        window.setTitle ("Sim-Plicity");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel) ;
        window.pack();

        
        ImageIcon logo = new ImageIcon("../resources/logo.png");
        window.setIconImage(logo.getImage());
        

        window. setLocationRelativeTo (null);
        window.setVisible (true);
    }
}
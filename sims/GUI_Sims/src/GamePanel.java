import java.awt.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel{

// SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;
    Menu menu = new Menu(this);

    public GamePanel () {

        this.setPreferredSize (new Dimension (screenWidth, screenHeight));
        this.setBackground (Color .black) ;
        this.setDoubleBuffered (true);

    }



    // public void startGameThread() {

    //     gameThread = new Thread (this) ;

    // }

    // @Override
    // public void run() {
    //     while (gameThread != null) {
            
    //         // 1 UPDATE: update information such as character positions
    //         update () ;
            
    //         // 2 DRAW: draw the screen with the updated information
    //         paintComponent ();
            
    //         }
            
    //     public void update() {
    //         return;
    //         }

        public void paintComponent (Graphics g) {
            
            super.paintComponent(g) ;
            
            Graphics2D g2 = (Graphics2D)g; 
            

                menu.draw(g2);
    }

    


}
package if2212_tb_01_01.utils;

import if2212_tb_01_01.entities.House;
import if2212_tb_01_01.entities.Sim;
import if2212_tb_01_01.entities.World;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static if2212_tb_01_01.utils.Constant.*;

public class GamePanel extends JPanel implements Runnable {
    /* Import Handler and Instance */
    public Thread gameThread;
//    /* Collision Handler */ public CollisionHandler collisionHandler = new CollisionHandler(this);
    /* Create a new input handler */ KeyHandler keyHandler = new KeyHandler();
    /* Create a new sim */ public Sim sim = new Sim(this, keyHandler);
    /* Create a new world */ public World world = World.getWorldInstance(this);

    /* Create House */ public List<House> houses = new ArrayList<>();


    public GamePanel(){
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        /* Add the input handler to the game panel */
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        /* Set the object */
        sim.position.setLocation((11-1)*tileSize,(11-1)*tileSize); //Set initial position

        for(int i = 0; i < 3; i++){
            houses.add(new House(this,this.sim, (17*(i+1))-7 , 10));
        }

    }
    public void runThread(){
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }
    @Override
    public void run(){
        /* Generate 60 FPS */
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        /* Game Loop */
        while(gameThread != null){

            /* Game Loop Thread */
            update();
            repaint();

            try {
                /* Using Thread sleep method to generate 60 FPS movable object */
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                Thread.sleep(Math.max(0, (long) remainingTime));
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        sim.update(); }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /* Draw the object */
        Graphics2D g2 = (Graphics2D) g;

        world.draw(g2);

        for (House house: houses){
            house.draw(g2);
        }
        sim.draw(g2);

        /* Debugging Only! */
//        g2.setColor(Color.WHITE);
//        g2.fillRect(100, 100, 25, 25);

        g2.dispose();
    }
}

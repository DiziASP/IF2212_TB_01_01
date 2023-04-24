package utils;

import javax.swing.*;
import java.awt.*;

import entities.Sim;
import object.SuperObject;
import object.tiles.TilesManager;

import static utils.Constant.*;

public class GamePanel extends JPanel implements Runnable{

    /* Panel Thread and other Handlers */
    public Thread gameThread;
    public InputHandler inputHandler = new InputHandler();
    public TilesManager tilesManager = new TilesManager(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionHandler collisionHandler = new CollisionHandler(this);
    public Sim sim = new Sim(this, inputHandler);
    public SuperObject[] superObject = new SuperObject[100];

    /* Object Position: DEBUGGING ONLY*/
//    Integer x = 100;
//    Integer y = 100;
//    Integer objSpeed = 4;
//    Point objPosition = new Point(100, 100);

    public GamePanel() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        /* Add the input handler to the game panel */
        this.addKeyListener(inputHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        /* Set the object */
        assetSetter.setObject();
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
                Thread.sleep((long) (remainingTime));
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() { sim.update(); }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /* Draw the object */
        Graphics2D g2 = (Graphics2D) g;
        tilesManager.draw(g2);
        for(int i=0; i<superObject.length; i++){
            if(superObject[i] != null){
                superObject[i].draw(g2, this);
            }
        }
        sim.draw(g2);

        g2.dispose();
    }
}

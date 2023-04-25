package if2212_tb_01_01.entities;

import if2212_tb_01_01.utils.GamePanel;
import if2212_tb_01_01.utils.KeyHandler;

import javax.imageio.ImageIO;

import static if2212_tb_01_01.utils.Constant.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sim extends Entity{
    /* Instantiate Game Panel and Key Handler */
    GamePanel gp;
    KeyHandler kh;

    /* Sims Asset */
    private Integer walkCount = 0;
    private Integer simOrientation = 1;
    public Integer screenX;
    public Integer screenY;
    private final BufferedImage[] simImage =  new BufferedImage[12];

    /* Sims Attribute */

       /**
     * Constructor for Sim Object
     * @param gp Game Panel
     * @param kh Input Handler
     */
    public Sim(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        solidArea = new Rectangle(8, 16, 32, 32);

        this.screenX = (WINDOW_WIDTH/2) - (tileSize);
        this.screenY = (WINDOW_HEIGHT/2) - (tileSize);


        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        initializeDefaultValues();
        getSimImage();
    }

    /* Built-in Method */
    public void initializeDefaultValues() {
        this.position = new Point(WINDOW_WIDTH/2 - tileSize, WINDOW_HEIGHT/2 - tileSize); /* Set the default position of the object */
        this.direction = "down"; /* Set the default direction of the object */
    }
    public void getSimImage(){
        /* Get the image of the sim */
        try{
            simImage[0] = ImageIO.read(getClass().getResourceAsStream("/images/sim/up1.png"));
            simImage[1] = ImageIO.read(getClass().getResourceAsStream("/images/sim/up2.png"));
            simImage[2] = ImageIO.read(getClass().getResourceAsStream("/images/sim/up3.png"));
            simImage[3] = ImageIO.read(getClass().getResourceAsStream("/images/sim/down1.png"));
            simImage[4] = ImageIO.read(getClass().getResourceAsStream("/images/sim/down2.png"));
            simImage[5] = ImageIO.read(getClass().getResourceAsStream("/images/sim/down3.png"));
            simImage[6] = ImageIO.read(getClass().getResourceAsStream("/images/sim/left1.png"));
            simImage[7] = ImageIO.read(getClass().getResourceAsStream("/images/sim/left2.png"));
            simImage[8] = ImageIO.read(getClass().getResourceAsStream("/images/sim/left3.png"));
            simImage[9] = ImageIO.read(getClass().getResourceAsStream("/images/sim/right1.png"));
            simImage[10] = ImageIO.read(getClass().getResourceAsStream("/images/sim/right2.png"));
            simImage[11] = ImageIO.read(getClass().getResourceAsStream("/images/sim/right3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        /* Update the game information */
        if(kh.up || kh.down || kh.left || kh.right){
            if (kh.up) {
                this.direction = "up";
            }
            else if (kh.down) {
                this.direction = "down";
            }
            else if (kh.left) {
                this.direction = "left";
            }
            else if (kh.right) {
                this.direction = "right";
            }

            collisionOn = false;
//            gp.collisionHandler.checkTileCollision(this);
//            gp.collisionHandler.checkTileCollision(this);
//            gp.collisionHandler.checkObj(this, true);

            // Check Collision
            if(!collisionOn){
                switch(direction) {
                    case "up":
                        position.setLocation(position.x, position.y - objSpeed);
                        break;
                    case "down":
                        position.setLocation(position.x, position.y + objSpeed);
                        break;
                    case "left":
                        position.setLocation(position.x - objSpeed, position.y);
                        break;
                    case "right":
                        position.setLocation(position.x + objSpeed, position.y);
                        break;
                }
            }

            walkCount++;
            if(walkCount > 12){
                if (simOrientation == 1) {
                    simOrientation = 2;
                }
                else if (simOrientation == 2) {
                    simOrientation = 3;
                }
                else if (simOrientation == 3) {
                    simOrientation = 1;
                }
                walkCount = 0;
            }
        }
    }
    public void draw(Graphics2D g){
        BufferedImage sim = null;

        switch (direction) {
            case "up":
                if(simOrientation == 1){
                    sim = simImage[0];

                }
                else if(simOrientation == 2){
                    sim = simImage[1];
                }
                else if(simOrientation == 3){
                    sim = simImage[2];
                }
                break;
            case "down":
                if(simOrientation == 1){
                    sim = simImage[3];
                }
                else if(simOrientation == 2){
                    sim = simImage[4];
                }
                else if(simOrientation == 3){
                    sim = simImage[5];
                }
                break;
            case "left":
                if(simOrientation == 1){
                    sim = simImage[6];
                }
                else if(simOrientation == 2){
                    sim = simImage[7];
                }
                else if(simOrientation == 3){
                    sim = simImage[8];
                }
                break;
            case "right":
                if(simOrientation == 1){
                    sim = simImage[9];
                }
                else if(simOrientation == 2){
                    sim = simImage[10];
                }
                else if(simOrientation == 3){
                    sim = simImage[11];
                }
                break;
        }

        g.drawImage(sim, screenX, screenY, tileSize, tileSize, null);
    }

    /* Sims Action */
}

package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import static utils.Constant.*;
import utils.GamePanel;
import utils.InputHandler;



public class Sim extends Entity{
    /* Instantiate Game Panel and Key Handler */
    GamePanel gp;
    InputHandler ih;

    private final BufferedImage[] simImage =  new BufferedImage[12];
    private Integer walkCount = 0;
    private Integer simAssetNum = 1;
    public Integer screenX;
    public Integer screenY;

    /* Sims Attribute */

    /**
     * Constructor for Sim Object
     * @param gp Game Panel
     * @param ih Input Handler
     */
    public Sim(GamePanel gp, InputHandler ih) {
        this.gp = gp;
        this.ih = ih;

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
            simImage[0] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/up1.png"));
            simImage[1] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/up2.png"));
            simImage[2] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/up3.png"));
            simImage[3] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/down1.png"));
            simImage[4] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/down2.png"));
            simImage[5] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/down3.png"));
            simImage[6] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/left1.png"));
            simImage[7] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/left2.png"));
            simImage[8] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/left3.png"));
            simImage[9] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/right1.png"));
            simImage[10] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/right2.png"));
            simImage[11] = ImageIO.read(getClass().getResourceAsStream("/assets/images/sim/right3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        /* Update the game information */
        if(ih.up || ih.down || ih.left || ih.right){
            if (ih.up) {
                this.direction = "up";
            }
            else if (ih.down) {
                this.direction = "down";
            }
            else if (ih.left) {
                this.direction = "left";
            }
            else if (ih.right) {
                this.direction = "right";
            }

            collisionOn = false;
            gp.collisionHandler.checkTile(this);
            gp.collisionHandler.checkObj(this, true);

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
                if (simAssetNum == 1) {
                    simAssetNum = 2;
                }
                else if (simAssetNum == 2) {
                    simAssetNum = 3;
                }
                else if (simAssetNum == 3) {
                    simAssetNum = 1;
                }
                walkCount = 0;
            }
        }
    }
    public void draw(Graphics2D g){
        BufferedImage sim = null;

        switch (direction) {
            case "up":
                if(simAssetNum == 1){
                    sim = simImage[0];

                }
                else if(simAssetNum == 2){
                    sim = simImage[1];
                }
                else if(simAssetNum == 3){
                    sim = simImage[2];
                }
                break;
            case "down":
                if(simAssetNum == 1){
                    sim = simImage[3];
                }
                else if(simAssetNum == 2){
                    sim = simImage[4];
                }
                else if(simAssetNum == 3){
                    sim = simImage[5];
                }
                break;
            case "left":
                if(simAssetNum == 1){
                    sim = simImage[6];
                }
                else if(simAssetNum == 2){
                    sim = simImage[7];
                }
                else if(simAssetNum == 3){
                    sim = simImage[8];
                }
                break;
            case "right":
                if(simAssetNum == 1){
                    sim = simImage[9];
                }
                else if(simAssetNum == 2){
                    sim = simImage[10];
                }
                else if(simAssetNum == 3){
                    sim = simImage[11];
                }
                break;
        }

        g.drawImage(sim, screenX, screenY, tileSize, tileSize, null);
    }

    /* Sims Action */
}

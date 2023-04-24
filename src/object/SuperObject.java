package object;

import utils.GamePanel;
import static utils.Constant.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public Point position;
    public Integer obj_width;
    public Integer obj_height;

    public Rectangle solidArea;
    public Integer solidAreaDefaultX = 0;
    public Integer solidAreaDefaultY = 0;


    public void draw(Graphics2D g, GamePanel gp){
            int screenX = position.x - gp.sim.position.x + gp.sim.screenX;
            int screenY = position.y - gp.sim.position.y + gp.sim.screenY;

            g.drawImage(image, screenX, screenY, obj_width*tileSize, obj_height*tileSize, null);
    }

}

package entities;

import java.awt.*;

public abstract class Entity {
    public Point position;
    public String direction;
    public Rectangle solidArea;

    public Integer solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn =  false;

}

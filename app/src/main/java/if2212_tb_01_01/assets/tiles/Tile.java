package if2212_tb_01_01.assets.tiles;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static if2212_tb_01_01.utils.Constant.*;

public class Tile {

    private BufferedImage image;
    private boolean collision;
    private Rectangle solidArea = new Rectangle(0, 0, tileSize, tileSize);

    public BufferedImage getImage() {
        return image;
    }

    public Tile setImage(BufferedImage image) {
        this.image = image;
        return this;
    }

    public boolean isCollision() {
        return collision;
    }

    public Tile setCollision(boolean collision) {
        this.collision = collision;
        return this;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public Tile setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
        return this;
    }
}

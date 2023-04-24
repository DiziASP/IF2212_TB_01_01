package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import static utils.Constant.*;

public class Bed extends SuperObject{

    public Bed(){
        this.name = "Bed";
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream("/assets/images/bed.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.obj_width = 3;
        this.obj_height = 1;

        this.solidArea = new Rectangle(0, 0, obj_width*tileSize, obj_height*tileSize);

        this.collision = true;
    }

}

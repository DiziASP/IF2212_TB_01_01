package utils;

import object.Bed;

import java.awt.*;

import static utils.Constant.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.superObject[0] =  new Bed();
        gp.superObject[0].position = new Point(5*tileSize, 3*tileSize);
    }
}

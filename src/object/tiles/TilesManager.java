package object.tiles;

import utils.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static utils.Constant.*;

public class TilesManager {
    GamePanel gp;
    public Tiles[] tile;
    public Integer[][] tilesMapper;

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        this.tile = new Tiles[20];
        this.tilesMapper = new Integer[maxWorldColumn][maxWorldRow];

        getTileImage();
        loadMap("/assets/images/tiles/worldmap.txt");
    }

    public void getTileImage(){
        try{
            tile[0] =  new Tiles();
            tile[0].tiles = ImageIO.read(getClass().getResourceAsStream("/assets/images/tiles/irontiles.png"));

            tile[1] =  new Tiles();
            tile[1].tiles = ImageIO.read(getClass().getResourceAsStream("/assets/images/tiles/tiles.png"));

            tile[2] =  new Tiles();
            tile[2].tiles = ImageIO.read(getClass().getResourceAsStream("/assets/images/tiles/wall.jpg"));
            tile[2].collision = true;

            tile[3] =  new Tiles();
            tile[3].tiles = ImageIO.read(getClass().getResourceAsStream("/assets/images/tiles/grass.jpg"));

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filepath){
        try{
            InputStream in = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int col = 0;
            int row = 0;

            while(col < maxWorldColumn && row < maxWorldRow){
                String line = br.readLine();

                while(col < maxWorldColumn){
                    String tileNum[] = line.split(" ");

                    int num = Integer.parseInt(tileNum[col]);
                    tilesMapper[col][row] = num;
                    col++;
                }

                if(col == maxWorldColumn){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < maxWorldColumn && worldRow < maxWorldRow){
            int tileNum = tilesMapper[worldCol][worldRow];

            int worldX = worldCol * tileSize;
            int worldY = worldRow * tileSize;
            int screenX = worldX - gp.sim.position.x + gp.sim.screenX;
            int screenY = worldY - gp.sim.position.y + gp.sim.screenY;

            g.drawImage(tile[tileNum].tiles, screenX, screenY, tileSize, tileSize, null);

            worldCol++;

            if(worldCol == maxWorldColumn){
                worldCol = 0;
                worldRow++;
            }

        }

    }
}

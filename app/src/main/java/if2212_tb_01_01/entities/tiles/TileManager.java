package if2212_tb_01_01.entities.tiles;
import if2212_tb_01_01.utils.GamePanel;

import static if2212_tb_01_01.utils.Constant.*;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public Integer[][] tilesMapper;

    /* Initialize Tile Manager */
    int tileHeight;
    int tileWidth;

    public TileManager(GamePanel gp, int tileHeight, int tileWidth, String filepath){
        this.gp = gp; /* Initialize Game Panel */
        this.tiles = new Tile[20]; /* Initialize Tiles Array */
        this.tilesMapper = new Integer[tileWidth][tileHeight]; /* Initialize World Tiles Mapper */

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        getTileImage();
        loadTilesMap(filepath);
    }

    public TileManager(GamePanel gp, int tileHeight, int tileWidth, Point position, String filepath){
        this.gp = gp; /* Initialize Game Panel */
        this.tiles = new Tile[20]; /* Initialize Tiles Array */
        this.tilesMapper = new Integer[tileWidth][tileHeight]; /* Initialize World Tiles Mapper */

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        getTileImage();
        loadTilesMap(filepath);
    }

    public void getTileImage(){
        try{
            /* Initialize Tiles */
            /* iron Tile: ID 0 */
            Tile iron = new Tile();
            iron.tileImg = ImageIO.read(getClass().getResourceAsStream("/images/tiles/irontiles.png"));
            tiles[0] = iron;

            /* iron Tile: ID 1 */
            Tile normal = new Tile();
            normal.tileImg = ImageIO.read(getClass().getResourceAsStream("/images/tiles/tiles.png"));
            tiles[1] = normal;

            /* Wall Tile: ID 2 */
            Tile wallTile = new Tile();
            wallTile.tileImg = ImageIO.read(getClass().getResourceAsStream("/images/tiles/wall.jpg"));
            wallTile.collision = true;
            tiles[2] = wallTile;

            /* Grass Tile: ID 3 */
            Tile grassTile = new Tile();
            grassTile.tileImg = ImageIO.read(getClass().getResourceAsStream("/images/tiles/grass.jpg"));
            tiles[3] = grassTile;

            /* Next tiles ... */
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadTilesMap(String filepath){
        try{
            InputStream in = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int col = 0;
            int row = 0;

            while(col < tileWidth && row < tileHeight){
                String line = br.readLine();

                while(col < tileWidth){
                    String[] tokens = line.split(" ");
                    int tileId = Integer.parseInt(tokens[col]);
                    tilesMapper[col][row] = tileId;
                    col++;
                }
                col = 0;
                row++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void drawWorld(Graphics2D g){
        int currCol = 0;
        int currRow = 0;

        while(currCol < tileWidth && currRow < tileHeight){
            int tileId = tilesMapper[currCol][currRow];
            Tile tile = tiles[tileId];

            int worldX = currCol * tileSize;
            int worldY = currRow * tileSize;
            int screenX = worldX - gp.sim.position.x + gp.sim.screenX;
            int screenY = worldY - gp.sim.position.y + gp.sim.screenY;

            g.drawImage(tile.tileImg, screenX, screenY, tileSize, tileSize, null);

            currCol++;

            if(currCol == tileWidth){
                currCol = 0;
                currRow++;
            }
        }
    }

    public void draw(Graphics2D g, Integer x, Integer y){
        int currCol = 0;
        int currRow = 0;

        while(currCol < tileWidth && currRow < tileHeight){
            int tileId = tilesMapper[currCol][currRow];
            Tile tile = tiles[tileId];

            int worldX = (x + currCol) * tileSize;
            int worldY = (y + currRow) * tileSize;
            int screenX = worldX - gp.sim.position.x + gp.sim.screenX;
            int screenY = worldY - gp.sim.position.y + gp.sim.screenY;

            g.drawImage(tile.tileImg, screenX, screenY, tileSize, tileSize, null);

            currCol++;

            if(currCol == tileWidth){
                currCol = 0;
                currRow++;
            }
        }


    }
}

package if2212_tb_01_01.assets.tiles;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.utils.UtilityTool;
import static if2212_tb_01_01.utils.Constant.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    private final GamePanel gp;
    private final Tile[] tiles;
    private final int[][][] mapTileNumbers;
    private final int roomIndex;

    public TileManager(GamePanel gp, int roomIndex) {
        this.gp = gp;
        this.roomIndex = roomIndex;
        this.tiles = new Tile[50];
        this.mapTileNumbers = new int[maxMaps][maxScreenRows][maxScreenColumns];

        getTileImage();

        String path = String.format("/maps/room%d.txt", roomIndex);
        loadMap(path, roomIndex, 8, 8);
    }

    public void getTileImage() {
        // PLACEHOLDER
        setup(2, "wall", true);
        setup(1, "floor01", false);
    }

    public void setup(int index, String imageName, boolean collision) {
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/" + imageName + ".png"))));
            tiles[index].setImage(UtilityTool.scaleImage(tiles[index].getImage(), tileSize, tileSize));
            tiles[index].setCollision(collision);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath, int roomIndex, int height, int width) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            while (column < width && row < height) {
                String line = bufferedReader.readLine();

                while (column < width) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);

                    mapTileNumbers[roomIndex][row][column] = number;
                    column++;
                }
                if (column == width) {
                    column = 0;
                    row++;
                }
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        int worldColumn = 0;
        int worldRow = 0;

        int width = graphics2D.getClipBounds().width;
        int height = graphics2D.getClipBounds().height;
        
        int roomX = (width - tileSize * 14) / 2;
        int roomY = (height - tileSize * 11) / 2;


        while (worldColumn < 8 && worldRow < 8) {
            int tileNumber = mapTileNumbers[roomIndex][worldRow][worldColumn];
            tiles[tileNumber].getSolidArea().setRect((roomX + worldColumn * tileSize),
                    (roomY + worldRow * tileSize),
                    tileSize, tileSize);

            graphics2D.drawImage(tiles[tileNumber].getImage(), roomX + worldColumn * tileSize, roomY + worldRow * tileSize, null);

            // Debugging
//             graphics2D.setColor(Color.BLUE);
//             graphics2D.fill(tiles[tileNumber].getSolidArea());

            worldColumn++;

            if (worldColumn == 8) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int[][][] getMapTileNumbers() {
        return mapTileNumbers;
    }
}
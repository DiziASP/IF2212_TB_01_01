package if2212_tb_01_01.entities.world;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.assets.tiles.Tile;
import if2212_tb_01_01.utils.UtilityTool;
import if2212_tb_01_01.entities.house.House;
import if2212_tb_01_01.entities.sim.Sim;

import static if2212_tb_01_01.utils.Constant.*;

public class World {
    private final GamePanel gp;
    private final Tile grass;
    private final Tile house;

    /* World Attributes */
    private int panjang;
    private int lebar;
    private ArrayList<Sim> listSim;
    private List<Object> listRumah;
    private Boolean[][] mapRumah;

    public World(GamePanel gp) {
        this.gp = gp;
        this.grass = new Tile();
        this.house = new Tile();
        setup("grass01", grass);
        setup("hut", house);
        this.panjang = maxWorldRows;
        this.lebar = maxWorldColumns;
        this.listRumah = new ArrayList<Object>();
        this.listSim = new ArrayList<Sim>();
        mapRumah = new Boolean[this.panjang][this.lebar];
        for (int i = 0; i < this.panjang; i++) {
            for (int j = 0; j < this.lebar; j++) {
                mapRumah[i][j] = false;
            }
        }
        mapRumah[63][63] = true;// temp
    }

    public void setup(String imageName, Tile tile) {
        try {
            BufferedImage image = ImageIO.read(
                    Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/" + imageName + ".png")));
            tile.setImage(UtilityTool.scaleImage(image, 2 * originalTileSize, 2 * originalTileSize));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawWorldMap(Graphics2D graphics2D) {
        int worldColumn = 0;
        int worldRow = 0;
        while (worldColumn < maxScreenColumns && worldRow < maxScreenRows) {
            graphics2D.drawImage(grass.getImage(), (worldColumn * 2 * originalTileSize), (worldRow * 2
                    * originalTileSize),
                    null);

            worldColumn++;

            if (worldColumn == maxScreenColumns) {
                worldColumn = 0;
                worldRow++;
            }

        }
        drawHouse(graphics2D);
    }

    public void drawHouse(Graphics2D graphics2D) {
        int worldColumn = 0;
        int worldRow = 0;
        while (worldColumn < maxScreenColumns && worldRow < maxScreenRows) {
            if (mapRumah[worldRow][worldColumn]) {
                graphics2D.drawImage(house.getImage(), (worldColumn * originalTileSize), (worldRow * originalTileSize),
                        null);
            }
            worldColumn++;

            if (worldColumn == maxScreenColumns) {
                worldColumn = 0;
                worldRow++;
            }

        }
    }

    // Getter method for panjang
    public int getPanjang() {
        return panjang;
    }

    // Getter method for lebar
    public int getLebar() {
        return lebar;
    }

    // Setter method for panjang
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    // Setter method for lebar
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }


    // Memeriksa apakah posisi rumah sudah terisi
    public boolean isPosisiTerisi(Point point) {
        for (int i = 0; i < listSim.size(); i++) {
            if (listSim.get(i).getRumah().getPosisi().equals(point)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPosisiTerisi(int x, int y){
        for (int i = 0; i < listSim.size(); i++) {
            if (listSim.get(i).getRumah().getPosisi().getX() == x && listSim.get(i).getRumah().getPosisi().getY() == y) {
                return true;
            }
        }
        return false;
    }


    public void addRumah(Point posisi, String kepemilikan, String namaRuangan, Point posisiRuangan) {
        if (isPosisiTerisi(posisi)) {
            System.out.println("Posisi sudah terisi!");
        } else {
            // this.listRumah.add(new Rumah(posisi, kepemilikan, namaRuangan,
            // posisiRuangan));
        }
    }

    public void addRumah(String kepemilikan, String namaRuangan, Point posisiRuangan) {
        boolean isPosisiFound = false;
        while (isPosisiFound == false) {
            Random rand = new Random();
            int min = 0;
            int max = 64;
            int randomX = rand.nextInt(max - min + 1) + min;
            int randomY = rand.nextInt(max - min + 1) + min;
            if (isPosisiTerisi(new Point(randomX, randomY)) == false) {
                // this.listRumah.add(new Rumah(new Point(randomX, randomY), kepemilikan,
                // namaRuangan, posisiRuangan));
                isPosisiFound = true;
            }
        }
    }


    public boolean isIdle() {
        boolean isIdle = false;
        for (Sim sim : listSim) {
            if (sim.getIsDoAksiAktif()) {
                isIdle = false;
                break;
            } else {
                isIdle = true;
            }
        }
        return isIdle;
    }


    public Sim getSim(int x) {
        return this.listSim.get(x);
    }

    public ArrayList<Sim> getListSim() {
        return this.listSim;
    }

    public void addSim(Sim sim) {
        this.listSim.add(sim);
    }
    public void removeSim(Sim sim) {
        this.listSim.remove(sim);
    }

    public int getJumlahSim() {
        return this.listSim.size();
    }
}

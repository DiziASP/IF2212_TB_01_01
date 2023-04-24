package if2212_tb_01_01.entities;

import if2212_tb_01_01.entities.tiles.TileManager;
import if2212_tb_01_01.utils.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class House {
    /* Define Game Panel and Tile Manager */
    GamePanel gp;
    TileManager houseTileManager;

    /* Define House Attributes */
    private static Integer HouseID = 0;
    private Sim owner;
    private Point location;

    private List<Room> daftarRuangan;

    public House(GamePanel gp, Point location) {
        this.location = location;
        this.gp = gp;
        /* Initialize Room */
        this.houseTileManager = new TileManager(gp, 12, 16, "/other/houses/house1.txt");
        this.daftarRuangan = new ArrayList<>();
    }

    public House(GamePanel gp, Sim sim, Integer x, Integer y) {
        this.location = new Point(x, y);
        this.gp = gp;
        this.owner = sim;
        HouseID++;
        String filepath = String.format("/other/houses/house%d.txt", HouseID);
        System.out.print(filepath);
        this.houseTileManager = new TileManager(gp, 12, 16, filepath);
        /* Initialize Room */
        this.daftarRuangan = new ArrayList<>();
    }

    public void draw(Graphics2D g2) {
        houseTileManager.draw(g2, location.x, location.y);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setLocation(Integer x, Integer y) {
        this.location.setLocation(x, y);
    }

    public List<Room> getRuangan() {
        return daftarRuangan;
    }

    public void addRoom(Room room) {
        this.daftarRuangan.add(room);
    }
}

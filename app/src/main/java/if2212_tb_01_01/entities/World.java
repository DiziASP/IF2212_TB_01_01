package if2212_tb_01_01.entities;

import if2212_tb_01_01.entities.tiles.TileManager;
import if2212_tb_01_01.utils.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static if2212_tb_01_01.utils.Constant.*;

/**
 * <h1>World Class</h1>
 * <p> World Class is used to generate a 64x64 World that player can travel freely. </p>
 * <p> Design Pattern: Singleton </p>
 */
@SuppressWarnings("SpellCheckingInspection")
public class World {
    public TileManager worldTilesManager;
    public GamePanel gp;
    private static World instance;
    private int panjang;
    private int lebar;
    private List<House> houses;

    /**
     * <h1>World Constructor</h1>
     * <p>Defined using Singleton Design Pattern</p>
     */
    private World(GamePanel gp) {
        /* Initialize World */
        this.panjang = maxWorldRow;
        this.lebar = maxWorldColumn;
        this.gp = gp;
        this.houses = new ArrayList<>(20);
        this.worldTilesManager = new TileManager(gp, panjang, lebar, "/other/worldmap.txt");
    }

    /* World Methods */
    /**
     * Get World Instance
     * @return World Instance
     * */
    public static World getWorldInstance(GamePanel gp) {
        if (instance == null) {
            instance = new World(gp);
        }
        return instance;
    }
    /* Asset Method */
    public void draw(Graphics2D g2){
        worldTilesManager.drawWorld(g2);
    }


    /* World's General Methods */
    public int getLebarWorld() {
        return lebar;
    }

    public int getPanjangWorld() {
        return panjang;
    }

    public void setPanjangWorld(int panjang) {
        this.panjang = panjang;
    }

    public void setLebarWorld(int lebar) {
        this.lebar = lebar;
    }

    public void addRumah(House house) {
        houses.add(house);
    }

    /* Overloading add Rumah for Position Parameter */
    public void addRumah(Point position) {
        House house = new House(gp, position);
        houses.add(house);
    }
    public void removeRumah(House house) {
        houses.remove(house);
    }

    public List<House> getDaftarRumah() {
        return houses;
    }

}

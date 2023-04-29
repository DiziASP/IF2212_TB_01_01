package if2212_tb_01_01.entities.room;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.*;
import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.assets.tiles.TileManager;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.items.Item;
import if2212_tb_01_01.items.furnitur.Furnitur;
import if2212_tb_01_01.items.furnitur.Jam;
import if2212_tb_01_01.items.furnitur.KasurQueenSize;

public class Room {
    GamePanel gp;
    TileManager tm;
    /* Room Attribute */
    private final int roomIndex;
    // private String roomName;
    private boolean isBuilded;
    private List<Item> daftarObjek;
    private static final Integer kapasitas = 36;
    private Integer[][] mapRuangan = new Integer[6][6];
    private Room roomLeft = null;
    private Room roomRight = null;
    private Room roomBelow = null;
    private Room roomAbove = null;


    private static int totalRuangan = 0;

    public Room(GamePanel gp, boolean isBuilded, Sim sim) {
        this.gp = gp;
        // this.roomName = roomName;

        totalRuangan++;
        this.roomIndex = totalRuangan;

        this.tm = new TileManager(gp, roomIndex);
        /* Initialize Room Attributes */
        daftarObjek = new ArrayList<Item>(kapasitas);
        this.isBuilded = isBuilded;

        if (this.isBuilded) {
            // Awal game ruangan langsung jadi

            /* Ini boleh di bikin fungsi biar bisa langsung setup ketiga method ini DAN INI HARUS ADA TIGA2NYA DENGAN STRUKTUR BEGINI, GABOLEH NGGAK*/
            KasurQueenSize jam = KasurQueenSize.buildKasurQueenSize(0,0);
            daftarObjek.add(jam);
            setMapRuangan(sim.getInventory().getKey(jam), jam);


//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
        tm.draw(g);

        // /* Draw Object */
         for(Item item : daftarObjek) {
             item.draw(g, ((Furnitur) item).getPosisi().x + 1, ((Furnitur) item).getPosisi().y + 1);
         }

    }

    /* Room Methods */
    public Integer[][] getMapRuangan() {
        return mapRuangan;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    // public String getRoomName() {
    //     return roomName;
    // }

    public void setMapRuangan(Integer itemIdx, Furnitur item) {

        //  if (!item.isVertikal()) {
            for (int i = item.getPosisi().y; i < item.getPosisi().getY() +
            item.getLebar(); i++) {
               for (int j = item.getPosisi().x; j < item.getPosisi().getX()
               + item.getPanjang(); j++) {
                mapRuangan[j][i] = itemIdx;
               }
            }
    //      }
    //      else {
    //         for (int i = item.getPosisi().y; i < item.getPosisi().getY() +
    //             item.getPanjang(); i++) {

    //             for (int j = item.getPosisi().x; j < item.getPosisi().getX() +
    //                 item.getLebar(); j++) {
    //                 mapRuangan[i][j] = item;
    //             }
    //         }
         }
    

    public void addObjek(Integer index, Item objek) {
        daftarObjek.add(index, objek);
    }

    public List<Item> getDaftarObjek() {
        return daftarObjek;
    }

    public boolean getIsBuilded() {
        return isBuilded;
    }

    public void setIsBuilded(boolean status) {
        isBuilded = status;
    }

    public TileManager getTileManager() {
        return tm;
    }

    public Room getRoomBelow() {
        return roomBelow;
    }

    public void setRoomBelow(Room roomBelow) {
        this.roomBelow = roomBelow;
    }

    public Room getRoomAbove() {
        return roomAbove;
    }

    public void setRoomAbove(Room roomAbove) {
        this.roomAbove = roomAbove;
    }

    public Room getRoomLeft() {
        return roomLeft;
    }

    public void setRoomLeft(Room roomLeft) {
        this.roomLeft = roomLeft;
    }

    public Room getRoomRight() {
        return roomRight;
    }

    public void setRoomRight(Room roomRight) {
        this.roomRight = roomRight;
    }

    public void newRoomRight(Room newRoom) {
        this.roomRight = newRoom;
        newRoom.setRoomLeft(this);
    }
    public void newRoomLeft(Room newRoom) {
        newRoom.setRoomRight(this);
        this.roomLeft = newRoom;
    }
    public void newRoomAbove(Room newRoom) {
        newRoom.setRoomBelow(this);
        this.roomAbove = newRoom;
    }
    public void newRoomBelow(Room newRoom) {
        newRoom.setRoomAbove(this);
        this.roomBelow = newRoom;
    }
}
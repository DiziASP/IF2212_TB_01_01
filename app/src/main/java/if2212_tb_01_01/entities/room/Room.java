package if2212_tb_01_01.entities.room;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.*;
import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.assets.tiles.TileManager;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.items.Inventory;
import if2212_tb_01_01.items.Item;
import if2212_tb_01_01.items.furnitur.Furnitur;
import if2212_tb_01_01.items.furnitur.Jam;
import if2212_tb_01_01.items.furnitur.KasurQueenSize;

public class Room {
    GamePanel gp;
    TileManager tm;
    /* Room Attribute */
    private int roomIndex = 0;
    // private String roomName;
    private boolean isBuilded;
    private List<ItemTracker> daftarObjek;
    private static final Integer kapasitas = 36;
    private Integer[][] mapRuangan = new Integer[6][6];
    private Room roomLeft = null;
    private Room roomRight = null;
    private Room roomBelow = null;
    private Room roomAbove = null;

    Inventory inventory;

    class ItemTracker{
        private Item f;
        private Rectangle interactionArea;
        private int x;
        private int y;

        ItemTracker(int idx, Rectangle interactionArea, int x, int y){
            this.f = inventory.getInventory().get(idx);
            this.interactionArea=interactionArea;
            this.x=x;
            this.y=y;
        }

        public Rectangle getInteractionArea(){
            return interactionArea;
        }
        public void setInteractionArea(Rectangle interactionArea){
            this.interactionArea = interactionArea;
        }

        public Item getF() {
            return f;
        }
    
        public void setF(Item f) {
            this.f = f;
        }
    
        public int getX() {
            return x;
        }
    
        public void setX(int x) {
            this.x = x;
        }
    
        public int getY() {
            return y;
        }
    
        public void setY(int y) {
            this.y = y;
        }
    }


    private static int totalRuangan = 0;

    public Room(GamePanel gp, boolean isBuilded, Sim sim) {
        this.gp = gp;
        // this.roomName = roomName;

        totalRuangan++;
        this.roomIndex = totalRuangan;

        this.tm = new TileManager(gp, roomIndex);
        /* Initialize Room Attributes */
        daftarObjek = new ArrayList<ItemTracker>(kapasitas);
        this.isBuilded = isBuilded;
        this.inventory = sim.getInventory();


        for (int i=0; i<6; i++){
            for (int j=0; j<6; j++){
                mapRuangan[i][j] = -1;
            }
        }

        // if (this.isBuilded) {
            // Awal game ruangan langsung jadi

            /* Ini boleh di bikin fungsi biar bisa langsung setup ketiga method ini DAN INI HARUS ADA TIGA2NYA DENGAN STRUKTUR BEGINI, GABOLEH NGGAK*/
 

//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
//            daftarObjek.add(new Item());
//            setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
        // }
    }

    public void pasangObjek(int idx, int x, int y){
        Furnitur f = (Furnitur) inventory.getInventory().get(idx);
        Rectangle ia = new Rectangle(((x + 1) *tileSize) + 48, ((y + 1) * tileSize) + 24, f.getPanjang(), f.getLebar());
        ItemTracker it = new ItemTracker(idx, ia, x, y);
        inventory.incItemPut(idx);
        // Furnitur furnitur = (Furnitur) inventory.getInventory().get(idx);
        // KasurQueenSize jam = KasurQueenSize.buildKasurQueenSize(0,0);
        daftarObjek.add(it);
        setMapRuangan(idx, it);
    }

    public int delObjek(int x, int y){
        int item = mapRuangan[y][x];
        if (item!= -1){
            boolean found = false;
            int a = ((Furnitur)inventory.getInventory().get(item)).getPanjang();
            int b = ((Furnitur)inventory.getInventory().get(item)).getLebar();
            int m=0;

            while (!found){
                if (daftarObjek.get(m).getX() <= x && daftarObjek.get(m).getX()+b > x){
                    if (daftarObjek.get(m).getY() <= y && daftarObjek.get(m).getY()+a > y){
                        found = true;
                    } else {m++;}
                } else{m++;
                }
                
            }
  
            for (int k = 0; k < a; k++) {
                for (int l = 0; l < b; l++) {
                    mapRuangan[daftarObjek.get(m).getY()+k][daftarObjek.get(m).getX()+l] = -1;
                }
            }
            daftarObjek.remove(m);
        } 
        inventory.decItem(item);
        return (item);
    }

    public void update() {

    }

    public void draw(Graphics2D g) {

        tm.draw(g);
        // /* Draw Object */
         for(ItemTracker item : daftarObjek) {
             item.getF().draw(g, item.getX() + 1, item.getY() + 1);
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

    public void setMapRuangan(Integer itemIdx, ItemTracker item) {

        //  if (!item.isVertikal()) {
            for (int i = item.getY(); i < item.getY() +
            ((Furnitur)item.getF()).getPanjang(); i++) {
               for (int j = item.getX(); j < item.getX()
               + ((Furnitur)item.getF()).getLebar(); j++) {
                mapRuangan[i][j] = itemIdx;
               }
            }
    //      }
    //      else {
    //         for (int i = item.getPosisi().y; i < item.getPosisi().getY() +
    //             item.getLebar(); i++) {

    //             for (int j = item.getPosisi().x; j < item.getPosisi().getX() +
    //                 item.getPanjang(); j++) {
    //                 mapRuangan[i][j] = item;
    //             }
    //         }
         }
    
    // pake pasang aj   
    // public void addObjek(int idx, int x, int y) {
    //     daftarObjek.add(new ItemTracker(idx,x,y));
        
    // }

    public List<ItemTracker> getDaftarObjek() {
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
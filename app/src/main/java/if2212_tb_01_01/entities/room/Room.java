package if2212_tb_01_01.entities.room;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.*;
import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.assets.AssetManager;
import if2212_tb_01_01.assets.tiles.TileManager;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.items.Inventory;
import if2212_tb_01_01.items.Item;
import if2212_tb_01_01.items.furnitur.Furnitur;
import if2212_tb_01_01.items.furnitur.Jam;
import if2212_tb_01_01.items.furnitur.KasurQueenSize;
import if2212_tb_01_01.utils.Constant;

public class Room {
    GamePanel gp;
    TileManager tm;
    /* Room Attribute */
    private String roomName;
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
        this.roomName = "Ruang Utama";

        for (int i=0; i<6; i++){
            for (int j=0; j<6; j++){
                mapRuangan[i][j] = -1;
            }
        }
    }

    public Room(GamePanel gp, boolean isBuilded, String roomName) {
        this.gp = gp;
        // this.roomName = roomName;

        totalRuangan++;
        this.roomIndex = totalRuangan;

        this.tm = new TileManager(gp, roomIndex);
        /* Initialize Room Attributes */
        daftarObjek = new ArrayList<ItemTracker>(kapasitas);
        this.isBuilded = isBuilded;
        this.roomName = roomName;


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
                } else{m++;}
                
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
         AssetManager am = new AssetManager(gp);
         Image img =  am.setup("/images/furnitur/pintu", Constant.tileSize, Constant.tileSize);
         if (this.roomAbove!=null){
            g.drawImage(img, 9*Constant.tileSize/2, Constant.tileSize/2, Constant.tileSize, Constant.tileSize, null);
         }
         if (this.roomBelow!=null){
            g.drawImage(img, 9*Constant.tileSize/2, Constant.tileSize*15/2, Constant.tileSize, Constant.tileSize, null);
         }
         if (this.roomLeft!=null){
            g.drawImage(img, Constant.tileSize, 4*Constant.tileSize, Constant.tileSize, Constant.tileSize, null);
         }
         if (this.roomRight!=null){
            g.drawImage(img, Constant.tileSize*8, 4*Constant.tileSize, Constant.tileSize, Constant.tileSize, null);
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


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

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


    public void newRoomBelow( String roomName) {
        Room roomBelow = new Room(gp, false, roomName);
        roomBelow.setInventory(this.inventory);
        roomBelow.setRoomName(roomName);
        roomBelow.roomAbove = this;
        this.roomBelow = roomBelow;
    }

    public Room getRoomAbove() {
        return roomAbove;
    }

    public void newRoomAbove(String roomName) {
        Room roomAbove = new Room(gp, false, roomName);
        roomAbove.setInventory(this.inventory);
        roomAbove.setRoomName(roomName);
        roomAbove.roomBelow = this;
        this.roomAbove = roomAbove;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Room getRoomLeft() {
        return roomLeft;
    }

    public void newRoomLeft(String roomName) {
        Room roomLeft = new Room(gp, false, roomName);
        roomLeft.setInventory(this.inventory);
        roomLeft.setRoomName(roomName);
        roomLeft.roomRight = this;
        this.roomLeft = roomLeft;
    }

    public Room getRoomRight() {
        return roomRight;
    }

    public void newRoomRight(String roomName) {
        Room roomRight = new Room(gp, false, roomName);
        roomRight.setInventory(this.inventory);
        roomRight.setRoomName(roomName);
        roomRight.roomLeft = this;
        this.roomRight = roomRight;
    }
}
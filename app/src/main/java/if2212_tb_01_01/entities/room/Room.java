package if2212_tb_01_01.entities.room;

import java.awt.*;
import java.io.Serializable;
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

public class Room implements Serializable {
    transient GamePanel gp;
    transient TileManager tm;
    /* Room Attribute */
    private String roomName;
    private int roomIndex = 0;
    // private String roomName;
    private boolean isBuilded;
    private List<ItemTracker> daftarObjek;
    private static final Integer kapasitas = 36;
    private Integer[][] mapRuangan = new Integer[6][6];
    public Room roomLeft = null;
    public Room roomRight = null;
    public Room roomBelow = null;
    public Room roomAbove = null;

    transient Inventory inventory;

    public class ItemTracker implements Serializable{
        private int f;
        private Rectangle interactionArea;
        private int x;
        private int y;
        ItemTracker(int idx, Rectangle interactionArea, int x, int y){
            this.f = idx;
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

        public int getF() {
            return f;
        }
    
        public void setF(int f) {
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

    public void draw(Graphics2D g, GamePanel gp, Inventory inventory) {
        if (tm ==null){
            this.gp = gp;
            tm = new TileManager(gp, roomIndex);
            this.inventory = inventory;
        }

        tm.draw(g);
        // /* Draw Object */
         for(ItemTracker item : daftarObjek) {
             inventory.getInventory().get(item.getF()).draw(g, item.getX() + 1, item.getY() + 1);
         }
         AssetManager am = new AssetManager(gp);
         Image img =  am.setup("/images/furnitur/pintu", Constant.tileSize, Constant.tileSize);
         if (this.roomAbove!=null){
            if (this.roomAbove.isBuilded){
                g.drawImage(img, 9*Constant.tileSize/2, Constant.tileSize/2, Constant.tileSize, Constant.tileSize, null);
            }
         }
         if (this.roomBelow!=null){
            if (this.roomBelow.isBuilded){
                g.drawImage(img, 9*Constant.tileSize/2, Constant.tileSize*15/2, Constant.tileSize, Constant.tileSize, null);
            }
         }
         if (this.roomLeft!=null){
            if (this.roomLeft.isBuilded){
                g.drawImage(img, Constant.tileSize, 4*Constant.tileSize, Constant.tileSize, Constant.tileSize, null);
            }
         }
         if (this.roomRight!=null){
            if (this.roomRight.isBuilded){
                g.drawImage(img, Constant.tileSize*8, 4*Constant.tileSize, Constant.tileSize, Constant.tileSize, null);
            }
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
            ((Furnitur)inventory.getInventory().get(item.getF())).getPanjang(); i++) {
               for (int j = item.getX(); j < item.getX()
               + ((Furnitur)inventory.getInventory().get(item.getF())).getLebar(); j++) {
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


    public void newRoomBelow(String roomName, boolean isBuilded) {
        Room roomBelow = new Room(gp, isBuilded, roomName);
        roomBelow.setInventory(this.inventory);
        roomBelow.setRoomName(roomName);

        if(this.roomLeft != null || this.roomRight != null){
            /* Check Room on the left bottom*/
            if (this.roomLeft.roomBelow != null) {
                roomBelow.roomLeft = this.roomLeft.roomBelow;
                this.roomLeft.roomBelow.roomRight = roomBelow;
            }

            /* Check Room on the right bottom */
            if (this.roomRight.roomBelow != null) {
                roomBelow.roomRight = this.roomRight.roomBelow;
                this.roomRight.roomBelow.roomLeft = roomBelow;
            }
        }

        roomBelow.roomAbove = this;
        this.roomBelow = roomBelow;
    }

    public Room getRoomAbove() {
        return roomAbove;
    }

    public void newRoomAbove(String roomName, boolean isBuilded) {
        Room roomAbove = new Room(gp, isBuilded, roomName);
        roomAbove.setInventory(this.inventory);
        roomAbove.setRoomName(roomName);

        /* Check Room on the left above */
        if(this.roomLeft != null || this.roomRight != null){
            if (this.roomLeft.roomAbove != null) {
                roomAbove.roomLeft = this.roomLeft.roomAbove;
                this.roomLeft.roomAbove = roomAbove;
            }

            /* Check Room on the right above */
            if (this.roomRight.roomAbove != null) {
                roomAbove.roomRight = this.roomRight.roomAbove;
                this.roomRight.roomAbove.roomLeft = roomAbove;
            }
        }

        roomAbove.roomBelow = this;
        this.roomAbove = roomAbove;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public Room getRoomLeft() {
        return roomLeft;
    }

    public void newRoomLeft(String roomName, boolean isBuilded) {
        Room roomLeft = new Room(gp, isBuilded, roomName);
        roomLeft.setInventory(this.inventory);
        roomLeft.setRoomName(roomName);

        if(this.roomAbove != null || this.roomBelow != null){
            if (this.roomAbove.roomLeft != null) {
                roomLeft.roomAbove = this.roomAbove.roomLeft;
                this.roomAbove.roomLeft.roomBelow = roomLeft;
            }

            /* Check Room on the right above */
            if (this.roomBelow.roomLeft != null) {
                roomLeft.roomBelow = this.roomBelow.roomLeft;
                this.roomBelow.roomLeft.roomAbove = roomLeft;
            }
        }
        roomLeft.roomRight = this;
        this.roomLeft = roomLeft;
    }

    public Room getRoomRight() {
        return roomRight;
    }

    public void newRoomRight(String roomName, boolean isBuilded) {
        Room roomRight = new Room(gp, isBuilded, roomName);
        roomRight.setInventory(this.inventory);
        roomRight.setRoomName(roomName);

        if(this.roomAbove != null || this.roomBelow != null){
            if (this.roomAbove.roomRight != null) {
                roomRight.roomAbove = this.roomAbove.roomRight;
                this.roomAbove.roomRight.roomBelow = roomRight;
            }

            /* Check Room on the right above */
            if (this.roomBelow.roomRight != null) {
                roomRight.roomBelow = this.roomBelow.roomRight;
                this.roomBelow.roomRight.roomAbove = roomRight;
            }
        }

        roomRight.roomLeft = this;
        this.roomRight = roomRight;
    }
}
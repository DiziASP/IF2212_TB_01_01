package if2212_tb_01_01.entities.sim;
import java.util.*;

import if2212_tb_01_01.items.bahanmakanan.*;
import if2212_tb_01_01.items.furnitur.*;
import if2212_tb_01_01.items.masakan.*;
import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.*;
import if2212_tb_01_01.entities.world.*;
import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.entities.house.*;
import if2212_tb_01_01.entities.room.*;
import if2212_tb_01_01.items.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static if2212_tb_01_01.utils.Constant.*;
import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.items.Item;
import if2212_tb_01_01.utils.KeyHandler;

public class Sim {
    GamePanel gp;
    KeyHandler kh;

    int roomX = (screenWidth - tileSize * 14) / 2;
    int roomY = (screenHeight - tileSize * 11) / 2;

    //aksi
    public static class Aksi{
        private String nama;
        private Sim sim;
        private int menitTersisa;
        private boolean isButuhObjek;
        public Aksi(Sim sim, String nama, int jumlahWaktu){
            this.sim = sim;
            this.nama = nama;
            this.menitTersisa = jumlahWaktu;
        }
        public Aksi(String nama, boolean isButuhObjek){
            this.nama = nama;
            this.isButuhObjek = isButuhObjek;
            this.menitTersisa = -1;
            this.sim = null;
        }
        public String getNama(){
            return nama;
        }
        public int getMenitTersisa(){
            return menitTersisa;
        }
        public void setMenitTersisa(int menitTersisa){
            this.menitTersisa = menitTersisa;
        }
        public void kurangiMenitTersisa(int menit){
            this.menitTersisa -= menit;
        }
        public static List<Aksi> getDaftarAksiAktif(){
            List<Aksi> listAksiAktif = new ArrayList<Aksi>();
            listAksiAktif.add(new Aksi("KERJA", false));
            listAksiAktif.add(new Aksi("OLAHRAGA", false));
            listAksiAktif.add(new Aksi ("TIDUR", true));
            listAksiAktif.add(new Aksi ("MAKAN", true));
            listAksiAktif.add(new Aksi ("MEMASAK", true));
            listAksiAktif.add(new Aksi ("BERKUNJUNG", false));
            listAksiAktif.add(new Aksi ("BUANG AIR", true));
            listAksiAktif.add(new Aksi ("YOGA", false));
            listAksiAktif.add(new Aksi ("IBADAH", false));
            listAksiAktif.add(new Aksi ("MENGGAMBAR", true));
            listAksiAktif.add(new Aksi ("MAIN MUSIK", true));
            listAksiAktif.add(new Aksi ("MANDI",true));
            listAksiAktif.add(new Aksi ("MEMBERSIHKAN RUMAH", true));
            listAksiAktif.add(new Aksi ("PROYEKAN", true));
            return listAksiAktif;
        }
        public static List<Aksi> getDaftarAksiNonWaktu(){
            List<Aksi> listAksiNonWaktu = new ArrayList<Aksi>();
            listAksiNonWaktu.add(new Aksi("BERPINDAH RUANGAN", false));
            listAksiNonWaktu.add(new Aksi("MELIHAT INVENTORY", false));
            listAksiNonWaktu.add(new Aksi ("MEMASANG BARANG", false));
            listAksiNonWaktu.add(new Aksi ("MELIHAT WAKTU", true));
            return listAksiNonWaktu;
        }
        public static List<Aksi> getDaftarAksiAFK(){
            List<Aksi> listAksiAFK = new ArrayList<Aksi>();
            listAksiAFK.add(new Aksi("UPGRADE RUMAH", false));
            listAksiAFK.add(new Aksi("BELI BARANG", false));
            return listAksiAFK;
        }
    }


     /* Sim Attributes */
     private String namaLengkap;
     private Pekerjaan pekerjaan;
     private int uang;
     private Inventory inventory;
     private Kesejahteraan kesejahteraan;
     private List<Aksi> status;
     private static World world;
     public void setWorld(World world) {
         Sim.world = world;
     }
     private boolean isDoAksiAktif;
     //private Point posisiRumah;
     //private Point posisiRuangan;
 

     // private Point posisi; yang butuh posisi kayanya rumah aja???
     private House rumah;
     private House currentPosition;
     private Room currentRuangan;


     //gui
     private BufferedImage[][] image = new BufferedImage[10][20];
    private int spriteIndex;
    private int spriteState;
    private int spriteCounter = 0;
    private String direction = "down";
    private final Integer speed = 5;
    private int screenX;
    private int screenY;
    private Rectangle solidArea;
    private Rectangle interactableArea;
    private boolean isCollision;


     //konstruktor

     // konstruktor kl pekerjaan di random 
     public Sim(Kesejahteraan kesejahteraan, int uang, String namaLengkap, House rumah, Point posisiRumah, Point posisiRuangan) {
         this.pekerjaan = new Pekerjaan();
         this.kesejahteraan = kesejahteraan;
         this.uang = uang;
         this.namaLengkap = namaLengkap;
         this.status = new ArrayList<Aksi>();
         this.inventory = new Inventory();
         inventory.incItem(0);
         inventory.incItem(3);
         inventory.incItem(4);
         inventory.incItem(6);
         inventory.incItem(10);
         this.rumah = rumah;
         //this.currentRuangan = rumah.getDaftarRuangan().get(0);
         setPosisiRumah(posisiRumah);
         this.currentRuangan = rumah.getRuanganAwal();
     }


    public Sim(GamePanel gp, KeyHandler kh, int spriteIndex, String namaLengkap, Point posisiRumah) {

        this.gp = gp;
        this.kh = kh;
        this.spriteIndex = spriteIndex;
        this.spriteState = 1;
        getAnimationImage();

        setScreenX(4*tileSize + roomX);
        setScreenY(4*tileSize + roomY);
        this.solidArea = new Rectangle(screenX, screenY, tileSize, tileSize);
        this.interactableArea = new Rectangle(screenX, screenY - tileSize, tileSize, tileSize);

        /* Initialize Characteristics */

         this.pekerjaan = new Pekerjaan();
         this.kesejahteraan = new Kesejahteraan();
         this.uang = 100;
         this.namaLengkap = namaLengkap;
         this.status = new ArrayList<Aksi>();
         this.inventory = new Inventory();
         this.rumah = new House(gp, this, posisiRumah);
         this.currentPosition = rumah;
         this.currentRuangan = rumah.getRuanganAwal();
         //tes
         inventory.incItem(0);
         inventory.incItem(3);
         inventory.incItem(4);
         inventory.incItem(6);
         inventory.incItem(10);
        //  this.rumah = rumah;
        //  this.currentRuangan = rumah.getDaftarRuangan().get(0);
        //  this.posisiRumah = posisiRumah;
        //  this.posisiRuangan = posisiRuangan;
        
    }

    public void update() {
        if (kh.isUpPressed() ||
                kh.isLeftPressed() ||
                kh.isRightPressed() ||
                kh.isDownPressed()) {
            if (kh.isUpPressed()) {
                setDirection("up");
                solidArea.setLocation(screenX, screenY - speed);
                interactableArea.setLocation(screenX, screenY - tileSize - speed);
            } else if (kh.isDownPressed()) {
                setDirection("down");
                solidArea.setLocation(screenX, screenY + tileSize + speed);
                interactableArea.setLocation(screenX , screenY + tileSize + speed);
            } else if (kh.isLeftPressed()) {
                setDirection("left");
                solidArea.setLocation(screenX - speed, screenY);
                interactableArea.setLocation(screenX - tileSize - speed, screenY);
            } else if (kh.isRightPressed()) {
                setDirection("right");
                solidArea.setLocation(screenX + tileSize + speed, screenY);
                interactableArea.setLocation(screenX + tileSize + speed, screenY);
            }

            isCollision = gp.collisionHandler.checkTileCollision(this) ||gp.collisionHandler.checkObjectCollision(this);
            System.out.println(gp.interactionHandler.checkOnInteractionRange(this)); /* Ini buat interaction, nanti sesuain aja */
            if (!isCollision) {
                if (kh.isUpPressed()) {
                    screenY -= speed;
                } else if (kh.isDownPressed()) {
                    screenY += speed;
                } else if (kh.isLeftPressed()) {
                    screenX -= speed;
                } else if (kh.isRightPressed()) {
                    screenX += speed;
                }

                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteState == 1) {
                        spriteState = 2;
                    } else if (spriteState == 2) {
                        spriteState = 3;
                    } else if (spriteState == 3)
                        spriteState = 1;
                    spriteCounter = 0;
                }
            }
        }
    }

    public void draw(Graphics2D g) {
        BufferedImage sim = null;

        switch (direction) {
            case "up":
                if (spriteState == 1) {
                    sim = image[spriteIndex - 1][0];
                } else if (spriteState == 2) {
                    sim = image[spriteIndex - 1][1];
                } else if (spriteState == 3) {
                    sim = image[spriteIndex - 1][2];
                }
                break;
            case "down":
                if (spriteState == 1) {
                    sim = image[spriteIndex - 1][3];
                } else if (spriteState == 2) {
                    sim = image[spriteIndex - 1][4];
                } else if (spriteState == 3) {
                    sim = image[spriteIndex - 1][5];
                }
                break;
            case "left":
                if (spriteState == 1) {
                    sim = image[spriteIndex - 1][6];
                } else if (spriteState == 2) {
                    sim = image[spriteIndex - 1][7];
                } else if (spriteState == 3) {
                    sim = image[spriteIndex - 1][8];
                }
                break;
            case "right":
                if (spriteState == 1) {
                    sim = image[spriteIndex - 1][9];
                } else if (spriteState == 2) {
                    sim = image[spriteIndex - 1][10];
                } else if (spriteState == 3) {
                    sim = image[spriteIndex - 1][11];
                }
                break;
        }

        // Debugging

//        g.setColor(Color.red);
//        g.fill(solidArea);
//        g.setColor(Color.yellow);
//        g.fill(interactableArea);
        g.drawImage(sim, screenX, screenY, null);
    }

    public void getAnimationImage() {
        for (int i=0; i<10; i++){
            for (int j=0; j<12; j++){
                if (j<=2){
                    image[i][j] = gp.getAssetManager().setup(String.format("/images/sim/%d/up%d", i+1, j+1), tileSize, tileSize);
                } else if (j<=5){
                    image[i][j] = gp.getAssetManager().setup(String.format("/images/sim/%d/down%d", i+1, j-2), tileSize, tileSize);
                } else if (j<=8){
                    image[i][j] = gp.getAssetManager().setup(String.format("/images/sim/%d/left%d", i+1, j-5), tileSize, tileSize);
                } else {
                    image[i][j] = gp.getAssetManager().setup(String.format("/images/sim/%d/right%d", i+1, j-8), tileSize, tileSize);
                }
            }
        }
    }

    /* Getter and Setter */
    public Rectangle getSolidArea() {
        return solidArea;
    }

    public Integer getSpeed() {
        return speed;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteIndex() {
        return spriteIndex;
    }

    public void setSpriteIndex(int spriteIndex) {
        this.spriteIndex = spriteIndex;
    }

    public int getSpriteState() {
        return spriteState;
    }

    public void setSpriteState(int spriteState) {
        this.spriteState = spriteState;
    }

   
    public Point getPosisiRumah(){
        return rumah.getPosisi();
    }

    public void setPosisiRumah(Point posisiRumah){
        this.rumah.setPosisi(posisiRumah);
    }

    public House getRumah(){
        return this.rumah;
    }

    public Room getRoomAwal(){
        return this.rumah.getRuanganAwal();
    }

    // public Point getPosisiRuangan(){
    //     return posisiRuangan;
    // }

    // public void setPosisiRuangan(Point posisiRuangan){
    //     this.posisiRuangan = posisiRuangan;
    // }

    public boolean getIsDoAksiAktif(){
        return isDoAksiAktif;
    }
    public void setIsDoAksiAktif(boolean isDoAksiAktif){
        this.isDoAksiAktif = isDoAksiAktif;
    }
    public Aksi getAksi(int index){
        return status.get(index);
    }

    public Rectangle getInteractableArea(){
        return interactableArea;
    }

    public void setInteractableArea(Rectangle interactableArea){
        this.interactableArea = interactableArea;
    }

    //aksi

    // public void masak(String nama, List<Makanan> makanan){
    //     boolean terpenuhi = true;
    //     int i = 0;
    //     while (terpenuhi && i<makanan.size()){
    //         if(inventory.isObjekAda(makanan.get(i))){
    //             i++;
    //         }
    //         else{
    //             terpenuhi = false;
    //         }
    //     }
    //     if(terpenuhi){
    //         inventory.addItem(new Masakan(nama), 1);
    //         for(Makanan x: makanan){
    //             inventory.removeItem(x, 1);
    //         }
    //     }

        // List<Makanan> bahanmakanan = Masakan.printGetResepMasakan(nama);
        //mengecek apakah bahan yang diperlukan semuanya ada di inventory atau tidak.
        //kalau tidak ada, akan mengeluarkan pesan "bahan yang diperlukan tidak terpenuhi"
        // kalau ada, tanyakan dulu apakah yakin? setelah itu masukin ke inventory trus bahan masakan yang dipake tadi kurangin jumlahnya di dalam inventory
    // }

    // public void viewLokasi(){
    //     System.out.println("SIM " + namaLengkap + " saat ini sedang berada di rumah dengan lokasi " + posisiRumah.toString() + " pada ruangan dengan lokasi" + posisiRuangan.toString());
    // }

    // public void viewLokasi(Rumah currentLocationRumah){
    //     System.out.println("SIM " + namaLengkap + " saat ini sedang berada di rumah dengan lokasi " + posisiRumah.toString() + " pada ruangan " + currentLocationRumah.getRuangan(posisiRuangan).getNama() + " dengan lokasi" + posisiRuangan.toString()+"\n");
    // }

    // public void viewInventory(){
    //     inventory.displayInventory();
    // }

    // public void beliBarang(String nama, String kategori){
    //     boolean found = false;
    //     int i = 0;
    //     if(kategori.equals("MAKANAN")){
    //         while(!found && i < Makanan.getListMakanan().size()){
    //             if(Makanan.getListMakanan().get(i).getNama().equals(nama)){
    //                 found = true;
    //             }
    //             else{
    //                 i++;
    //             }
    //         }
    //         if(found){
    //             Integer hargaMakanan = Makanan.getListMakanan().get(i).getHarga();
    //             if(this.uang >= hargaMakanan){
    //                 uang -= hargaMakanan;
    //                 inventory.addItem(new Makanan(nama), 1);
    //                 System.out.println(nama+" berhasil dibeli!");
    //             }
    //             else{
    //                 System.out.println("Uang tidak cukup");
    //             }
    //         }
    //         else{
    //             System.out.println("Input makanan tidak terdapat dalam list");
    //         }
    //     }
    //     else if(kategori.equals("FURNITUR")){
    //         while(!found && i < Furnitur.getListFurnitur().size()){
    //             if(Furnitur.getListFurnitur().get(i).getNama().equals(nama)){
    //                 found = true;
    //             }
    //             else{
    //                 i++;
    //             }
    //         }
    //         if(found){
    //             Integer hargaFurnitur = Furnitur.getListFurnitur().get(i).getHarga();
    //             if(this.uang >= hargaFurnitur){
    //                 uang -= hargaFurnitur;
    //                 inventory.addItem(new Furnitur(nama,new Point(-1,-1),false), 1);
    //                 System.out.println(nama+" berhasil dibeli!");
    //                 //harusnya dikirim barang dulu trus kalo dah sampe baru masuk inventory
    //             }
    //             else{
    //                 System.out.println("Uang tidak cukup");
    //             }
    //         }
    //         else{
    //             System.out.println("Input furnitur tidak terdapat dalam list");
    //         }    
    //     }
    // }

    public Sim(String namaLengkap, Pekerjaan pekerjaan) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = pekerjaan;
        this.uang = 100;
        this.inventory = new Inventory();
        this.status = new ArrayList<Aksi>();
    }

    //Getter dan Setter
    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Kesejahteraan getKesejahteraan(){
        return this.kesejahteraan;
    }

    public List<Aksi> getStatus() {
        return status;
    }

    public void setStatus(List<Aksi> status) {
        this.status = status;
    }

    public Room getCurRoom(){
        return currentRuangan;
    }
    public void setCurRoom(Room currentRuangan){
        this.currentRuangan = currentRuangan;
    }
    public House getCurHouse(){
        return currentPosition;
    }
    public void setCurHouse(House currentPosition){
        this.currentPosition = currentPosition;
    }

    // public void goToObject() {
    //     System.out.println("Pilih objek yang ingin dikunjungi: ");
    //     int i = 1;
    //     for (Objek objek : this.currentRuangan.getDaftarObjek()) {
    //         System.out.println(i + ". " + objek.getNama());
    //         i++;
    //     }
    //     Scanner scanner = new Scanner(System.in);
    //     int pilihan = scanner.nextInt();
    //     Objek objek = this.currentRuangan.getDaftarObjek().get(pilihan - 1);
    //     if (objek.getKategori().equals("peralatan")){
    //         Furnitur furnitur = (Furnitur) objek;
    //         if (furnitur.getAksi().equals("TIDUR")){
    //             System.out.println("Apakah Anda ingin tidur? (Y/N)");
    //             String pilihanTidur = scanner.next();
    //             if (pilihanTidur.equals("Y")){
    //                 this.tidur();
    //             }
    //         }
    //         else if (furnitur.getAksi().equals("BUANG AIR")){
    //             System.out.println("Apakah Anda ingin buang air? (Y/N)");
    //             String pilihanBuangAir = scanner.next();
    //             if (pilihanBuangAir.equals("Y")){
    //                 this.buangAir();
    //             }
    //         } else if(furnitur.getAksi().equals("MASAK")){
    //             System.out.println("Apakah Anda ingin memasak? (Y/N)");
    //             String pilihanMasak = scanner.next();
    //             if (pilihanMasak.equals("Y")){
    //                 this.memasak();
    //             }
    //         } else if (furnitur.getAksi().equals("MAKAN")){
    //             System.out.println("Apakah Anda ingin makan? (Y/N)");
    //             String pilihanMakan = scanner.next();
    //             if (pilihanMakan.equals("Y")){
    //                 this.makan();
    //             }
    //         } else if(furnitur.getAksi().equals("MELIHAT WAKTU")){
    //             System.out.println("Apakah Anda ingin melihat waktu? (Y/N)");
    //             String pilihanLihatWaktu = scanner.next();
    //             if (pilihanLihatWaktu.equals("Y")){
    //                 this.melihatWaktu();
    //             }
    //         }
    //     }
    // }


    //Actions of Sim
    public void addToInventory() {
        //Please provide the solution below
    }

    public void removeFromInventory() {
        //Please provide the solution below
    }

    public void kerja() {
        //Please provide the solution below
    }

    public void olahraga() {
        //Please provide the solution below
        this.status.add(new Aksi(this, "Olahraga", 20));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        try {
            int waktu = 20;
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(1000);
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Yey sudah selesai olahraga");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 5);
        this.kesejahteraan.setKekenyangan(this.kesejahteraan.getKekenyangan() - 5);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 5);
        this.isDoAksiAktif = false;
    }

    public void tidur() {
        //Please provide the solution below
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan waktu tidur (Kelipatan 4) dalam menit: ");
        int waktuTidur = scanner.nextInt();
        while (waktuTidur % 4 != 0) {
            System.out.println("Masukkan waktu tidur (Kelipatan 4) dalam menit: ");
            waktuTidur = scanner.nextInt();
        }
        final int waktu = waktuTidur*60;
        this.status.add(new Aksi(this, "Tidur", waktuTidur));
        int indexStatus = this.status.size() - 1;
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 20);
        this.isDoAksiAktif = true;
        //ActionThread thread = new ActionThread(indexStatus,this,waktu);
        //thread.start(); // memulai thread


        // nnti di main ada thread buat ngecek kl dia ga idle tp ga tidur 10 mnt haduh gmn y
    }

    public void makan() {

    }

    public void memasak(String nama) {
        if (nama.equals("BISTIK")) {
            if (inventory.isItemAda(15) && inventory.isItemAda(17)) {
                Bistik bistik = new Bistik();
                int waktumasak = (int) (bistik.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak",0));
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                try {
                    int waktu = waktumasak;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Berhasil memasak bistik");
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
                this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
                inventory.incItem(24);
                this.isDoAksiAktif = false;
            } else {
                System.out.println("Bahan tidak tersedia.");
            }
        }
        else if (nama.equals("NASI AYAM")) {
            if (inventory.isItemAda(16) && inventory.isItemAda(12)) {
                NasiAyam nasiayam = new NasiAyam();
                int waktumasak = (int) (nasiayam.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak",0));
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                try {
                    int waktu = waktumasak;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Berhasil memasak Nasi Ayam");
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
                this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
                inventory.incItem(20);
                this.isDoAksiAktif = false;
            } else {
                System.out.println("Bahan tidak tersedia.");
            }
        }
        else if (nama.equals("NASI KARI")) {
            if (inventory.isItemAda(15) && inventory.isItemAda(17) && inventory.isItemAda(16) && inventory.isItemAda(19)) {
                NasiKari nasikari = new NasiKari();
                int waktumasak = (int) (nasikari.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak",0));
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                try {
                    int waktu = waktumasak;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Berhasil memasak Nasi Kari");
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
                this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
                inventory.incItem(21);
                this.isDoAksiAktif = false;
            } else {
                System.out.println("Bahan tidak tersedia.");
            }
        }
        else if (nama.equals("SUSU KACANG")) {
            if (inventory.isItemAda(14) && inventory.isItemAda(18)) {
                SusuKacang susukacang = new SusuKacang();
                int waktumasak = (int) (susukacang.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak",0));
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                try {
                    int waktu = waktumasak;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Berhasil memasak Susu Kacang");
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
                this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
                inventory.incItem(22);
                this.isDoAksiAktif = false;
            } else {
                System.out.println("Bahan tidak tersedia.");
            }
        }
        else if (nama.equals("TUMIS SAYUR")) {
            if (inventory.isItemAda(13) && inventory.isItemAda(19)) {
                TumisSayur tumisSayur = new TumisSayur();
                int waktumasak = (int) (tumisSayur.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak",0));
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                try {
                    int waktu = waktumasak;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Berhasil memasak Tumis Sayur");
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
                this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
                inventory.incItem(23);
                this.isDoAksiAktif = false;
            } else {
                System.out.println("Bahan tidak tersedia.");
            }
        }
        else {
            System.out.println("Tidak dapat memasak masakan tersebut.");
        }
    }

    public void berkunjung() {
        //Please provide the solution below
    }

    public void buangAir() {
        //Please provide the solution below
    }

    public void upgradeRumah() {
        System.out.println("Pilih posisi ruangan baru (atas/bawah/kiri/kanan): ");
        Scanner scanner = new Scanner(System.in);
        String pilihan = scanner.nextLine();
        // if (pilihan.equals("atas")) {
        //     System.out.println("Masukkan nama ruangan: ");
        //     String namaRuangan = scanner.nextLine();
        //     this.rumah.addRuangan(new Ruangan(namaRuangan));
            
        // } else if (pilihan.equals("bawah")) {
        //     this.rumah.addRuangan(new Ruangan("Ruangan Bawah", 0, -1));
        // } else if (pilihan.equals("kiri")) {
        //     this.rumah.addRuangan(new Ruangan("Ruangan Kiri", -1, 0));
        // } else if (pilihan.equals("kanan")) {
        //     this.rumah.addRuangan(new Ruangan("Ruangan Kanan", 1, 0));
        // }


    }

    public void beliBarang(String nama) {
        //Please provide the solution below
        if (nama.equals("AYAM")) {
            BH_Ayam ayam = new BH_Ayam();
            Integer harga = ayam.getHarga();
            if (this.uang >= harga) {
                System.out.println(ayam.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang",(int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(12);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("BAYAM")) {
            BH_Bayam bayam = new BH_Bayam();
            Integer harga = bayam.getHarga();
            if (this.uang >= harga) {
                System.out.println(bayam.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(13);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("KACANG")) {
            BH_Kacang kacang = new BH_Kacang();
            Integer harga = kacang.getHarga();
            if (this.uang >= harga) {
                System.out.println(kacang.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(14);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("KENTANG")) {
            BH_Kentang kentang = new BH_Kentang();
            Integer harga = kentang.getHarga();
            if (this.uang >= harga) {
                System.out.println(kentang.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(15);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("NASI")) {
            BH_Nasi nasi =new BH_Nasi();
            Integer harga = nasi.getHarga();
            if (this.uang >= harga) {
                System.out.println(nasi.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(16);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("SAPI")) {
            BH_Sapi sapi = new BH_Sapi();
            Integer harga = sapi.getHarga();
            if (this.uang >= harga) {
                System.out.println(sapi.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(17);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("SUSU")) {
            BH_Susu susu = new BH_Susu();
            Integer harga = susu.getHarga();
            if (this.uang >= harga) {
                System.out.println(susu.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(18);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("WORTEL")) {
            BH_Wortel wortel = new BH_Wortel();
            Integer harga = wortel.getHarga();
            if (this.uang >= harga) {
                System.out.println(wortel.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(19);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("JAM")) {
            Jam jam = new Jam();
            Integer harga = jam.getHarga();
            if (this.uang >= harga) {
                System.out.println(jam.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang",(int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(0);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("KASUR KING SIZE")) {
            KasurKingSize king = new KasurKingSize();
            Integer harga = king.getHarga();
            if (this.uang >= harga) {
                System.out.println(king.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang",(int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(1);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("KASUR QUEEN SIZE")) {
            KasurQueenSize queen = new KasurQueenSize();
            Integer harga = queen.getHarga();
            if (this.uang >= harga) {
                System.out.println(queen.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(2);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("KASUR SINGLE")) {
            KasurSingle single = new KasurSingle();
            Integer harga = single.getHarga();
            if (this.uang >= harga) {
                System.out.println(single.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(3);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("KOMPOR GAS")) {
            KomporGas kompgas = new KomporGas();
            Integer harga = kompgas.getHarga();
            if (this.uang >= harga) {
                System.out.println(kompgas.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(4);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("KOMPOR LISTRIK")) {
            KomporListrik komplistrik = new KomporListrik();
            Integer harga = komplistrik.getHarga();
            if (this.uang >= harga) {
                System.out.println(komplistrik.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(5);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("MEJA DAN KURSI")) {
            MejaKursi mk = new MejaKursi();
            Integer harga = mk.getHarga();
            if (this.uang >= harga) {
                System.out.println(mk.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(6);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("PC")) {
            PC pc = new PC();
            Integer harga = pc.getHarga();
            if (this.uang >= harga) {
                System.out.println(pc.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(7);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("PIANO")) {
            Piano piano = new Piano();
            Integer harga = piano.getHarga();
            if (this.uang >= harga) {
                System.out.println(piano.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(8);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("PINTU")) {
            Pintu pintu = new Pintu();
            Integer harga = pintu.getHarga();
            if (this.uang >= harga) {
                System.out.println(pintu.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(9);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("TOILET")) {
            Toilet toilet = new Toilet();
            Integer harga = toilet.getHarga();
            if (this.uang >= harga) {
                System.out.println(toilet.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang", (int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(10);
            } else {
                System.out.println("Uang tidak cukup");
            }
        }
        else if (nama.equals("BAK MANDI")) {
            BakMandi bakmandi = new BakMandi();
            Integer harga = bakmandi.getHarga();
            if (this.uang >= harga) {
                System.out.println(bakmandi.getNama() + " berhasil dibeli");
                int indexStatus = this.status.size() - 1;
                Random rand = new Random();
                int waktubeli = (rand.nextInt(6) + 1) * 30;
                this.status.add(new Aksi(this,"beliBarang",(int) (waktubeli/60)));
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            this.getAksi(indexStatus).kurangiMenitTersisa(1);
                        }
                    }
                    this.status.remove(indexStatus);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                inventory.incItem(11);
            } else {
                System.out.println("Uang tidak cukup");
            }
        } else {
            System.out.println("Barang tidak dapat dibeli");
        }
    }

    public void berpindahRuangan(Room tujuanruangan) {
        // Harus diubah2 lagi sih
//        if (rumah.getDaftarRuangan().contains(tujuanruangan)) {
//            currentRuangan = tujuanruangan;
//            this.status.add(new Aksi(this,"berpindahRuangan", 0));
//            System.out.println("Sim berhasil berpindah ke " + currentRuangan.getRoomName());
//        } else {
//            System.out.println("Ruangan tidak tersedia");
//        }
    }

    public void melihatInventory() {
        //Please provide the solution below
    }

    public void memasangBarang() {
        //Please provide the solution below
    }

    public void melihatWaktu() {
        //Please provide the solution below
    }

    public void yoga() {
        this.status.add(new Aksi(this, "Yoga", 1));
    }

    public void berdoa(){

    }

    public void mandi(){

    }

    public void melukis(){
        this.status.add(new Aksi(this, "Melukis", 2*60));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Mozart sedang beraksi?");
        try {
            int waktu = 2*60;
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(1000);
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kren bngt banh lukisannya :<");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
        this.isDoAksiAktif = false;
    }

    public void bermainMusik(){
        this.status.add(new Aksi(this, "Main musik", 2*60));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Yippi main musik");
        try {
            int waktu = 2*60;
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(1000);
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Jago banget main musiknya kak :<");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 5);
        this.isDoAksiAktif = false;
    }

    public void viewInfo(){
        System.out.println("Nama: "+ namaLengkap);
        System.out.println("Pekerjaan: "+ pekerjaan.getNamaKerja());
        System.out.println("Kesehatan: "+ kesejahteraan.getKesehatan());
        System.out.println("Kekenyangan: "+ kesejahteraan.getKekenyangan());
        System.out.println("Mood: "+kesejahteraan.getMood());
        System.out.println("Uang: "+ uang+"\n");
    }
    // public void viewCurrentLocation(){
    //     System.out.println("Rumah: "+ rumah.getPosisi().getX()+","+rumah.getPosisi().getY());
    //     System.out.println("Ruangan: "+ this.currentRuangan.getNama()+" di koordinat "+this.posisiRuangan.getX()+","+this.posisiRuangan.getY());
    // }

    public void proyekan() {
        this.status.add(new Aksi(this, "proyekan", 180));
        int waktuproyekan = 180;
        int indexStatus = this.status.size() - 1;
        this.isDoAksiAktif = true;
        try {
            int waktu = waktuproyekan;
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(1000);
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isDoAksiAktif = false;
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() - 5);
        setUang(getUang() + 100);
    }
}




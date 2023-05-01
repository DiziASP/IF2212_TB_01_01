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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sim {
    GamePanel gp;
    KeyHandler kh;

    int roomX = (screenWidth - tileSize * 14) / 2;
    int roomY = (screenHeight - tileSize * 11) / 2;

    //aksi
    public static class Aksi{
        private String nama;
        private Sim sim;
        private int detikTersisa;
        private boolean isButuhObjek;
        public Aksi(Sim sim, String nama, int jumlahSeconds){
            this.sim = sim;
            this.nama = nama;
            this.detikTersisa = jumlahSeconds;
        }
        public Aksi(String nama, boolean isButuhObjek){
            this.nama = nama;
            this.isButuhObjek = isButuhObjek;
            this.detikTersisa = -1;
            this.sim = null;
        }
        public String getNama(){
            return nama;
        }
        public int getDetikTersisa(){
            return detikTersisa;
        }
        public void setDetikTersisa(int detikTersisa){
            this.detikTersisa = detikTersisa;
        }
        public void decDetikTersisa(){
            this.detikTersisa -= 1;
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
     private int waktuTidur=0;
     private int waktuSudahKerja=0;
     private boolean belumBerak=false;
     private ExecutorService executorService;
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
         executorService = Executors.newFixedThreadPool(10);
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
            executorService = Executors.newFixedThreadPool(10);
        
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
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

            isCollision = gp.collisionHandler.checkTileCollision(gp.getSim()) || gp.collisionHandler.checkObjectCollision(gp.getSim());
            gp.setInteract(gp.interactionHandler.checkOnInteractionRange(gp.getSim()));
            System.out.println(gp.interactionHandler.checkOnInteractionRange(gp.getSim())); /* Ini buat interaction, nanti sesuain aja */
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

    public void beliItem(int idx){
        if (idx<20){
            executorService.execute(() -> {
            int indexStatus = this.status.size() -1;
            if (this.getInventory().getHarga(idx) <= getUang()){
                this.substractUang(this.getInventory().getHarga(idx));
                Random rand = new Random();
                int waktubeli = ((rand.nextInt(6) + 1))%5 * 30;
                this.status.add(new Aksi(this,"beliBarang",(int) (waktubeli/60)));
                
                try {
                    int waktu = waktubeli;
                    int seconds = 0;
                    for (int i = 0; i < waktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        this.getAksi(indexStatus).decDetikTersisa();
                        System.out.println(waktubeli-seconds);
                    }
                    this.status.remove(indexStatus);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.inventory.incItem(idx);
                
            }
        });
        } else {
            kh.setErrorCaught(true);
        }
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

    public int getWaktuTidur(){
        return waktuTidur;
    }
    public void setWaktuTidur(int waktuTidur){
        this.waktuTidur = waktuTidur;
    }

    public boolean isCanChangePekerjaan(){
        return (waktuSudahKerja>=12);
    }

    public void substractUang(int uang){
        this.uang -= uang;
    }

    public Rectangle getInteractableArea(){
        return interactableArea;
    }

    public void setInteractableArea(Rectangle interactableArea){
        this.interactableArea = interactableArea;
    }

    //aksi


    // public void viewLokasi(){
    //     System.out.println("SIM " + namaLengkap + " saat ini sedang berada di rumah dengan lokasi " + posisiRumah.toString() + " pada ruangan dengan lokasi" + posisiRuangan.toString());
    // }

    // public void viewLokasi(Rumah currentLocationRumah){
    //     System.out.println("SIM " + namaLengkap + " saat ini sedang berada di rumah dengan lokasi " + posisiRumah.toString() + " pada ruangan " + currentLocationRumah.getRuangan(posisiRuangan).getNama() + " dengan lokasi" + posisiRuangan.toString()+"\n");
    // }

    // public void viewInventory(){
    //     inventory.displayInventory();
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

    public void kerja(int waktu) { // waktu harus kelipatan 120 detik
        final int fwaktu = waktu;
        executorService.execute(() -> {
            this.status.add(new Aksi(this, "Kerja", fwaktu));
            this.isDoAksiAktif = true;
            int indexStatus = this.status.size() - 1;
            try {
                int seconds = 0;
                for (int i = 0; i < fwaktu; i++) {
                    Thread.sleep(1000);
                    seconds++;
                    this.waktuSudahKerja++;
                    if (seconds % 120==0) {
                        this.uang += 100;
                    } if (seconds %30 ==0 ){
                        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan()-10);
                        this.kesejahteraan.setMood(this.kesejahteraan.getMood()-10);
                    }
                    this.status.get(indexStatus).decDetikTersisa();
                    gp.setActionCounter(waktu-i);
                    
                }
                this.status.remove(indexStatus);
    
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Yey sudah kelar kerja");
            this.isDoAksiAktif = false;
        });
        //Please provide the solution below
    }

    public void olahraga(int waktu) {
        //Please provide the solution below
        final int fwaktu = waktu;
        executorService.execute(() -> {
            this.status.add(new Aksi(this, "Olahraga", fwaktu));
            this.isDoAksiAktif = true;
            int indexStatus = this.status.size() - 1;
            try {
                int seconds = 0;
                for (int i = 0; i < fwaktu; i++) {
                    Thread.sleep(1000);
                    seconds++;
                    if (seconds % 20 ==0){
                        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
                        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 5);
                        this.kesejahteraan.setKekenyangan(this.kesejahteraan.getKekenyangan() - 5);
                        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 5);
                    }
                    this.status.get(indexStatus).decDetikTersisa();
                    gp.setActionCounter(waktu-i);
                    
                }
                this.status.remove(indexStatus);
    
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Yey sudah selesai olahraga");

            this.isDoAksiAktif = false;
        });
    }


    public void tidur(int waktu) {
        final int fwaktu = waktu;
        executorService.execute(() -> {
        this.status.add(new Aksi(this, "Tidur", fwaktu));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        try {
            int seconds = 0;
            for (int i = 0; i < fwaktu; i++) {
                Thread.sleep(1000); // tunggu 1 detik
                seconds++;
                this.getAksi(indexStatus).decDetikTersisa();
                this.waktuTidur++;
                if (seconds % 120==0){
                    this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
                    this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 20);
                }
                gp.setActionCounter(waktu-i);
            }
            this.status.remove(indexStatus);
            this.isDoAksiAktif = false;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Selamat bangun tidur!");
        });
    }

    // public void memasak(int idx, int waktu){

    // }

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
                            this.getAksi(indexStatus).decDetikTersisa();
                            gp.setActionCounter(waktu-i);
                    }
                    this.status.remove(indexStatus);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Berhasil memasak bistik");
                this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
                inventory.incItem(24);
                this.isDoAksiAktif = false;
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
                            this.getAksi(indexStatus).decDetikTersisa();
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
                        this.getAksi(indexStatus).decDetikTersisa();
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
                        this.getAksi(indexStatus).decDetikTersisa();
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
                        this.getAksi(indexStatus).decDetikTersisa();
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

    public void mulaiThreadbuangAirChecker(){
        executorService.execute(() -> {
            try{
                Thread.sleep(1000*4*60);
                if (this.belumBerak==true){
                    this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() - 5);
                    this.kesejahteraan.setMood(this.kesejahteraan.getMood() - 5);
                    this.belumBerak = false; //balik ke state awal
                }
                
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
    }



    public void makan(int idx, int waktu){
        final int fwaktu = waktu;

        if (idx<20){
            executorService.execute(() -> {
            int indexStatus = this.status.size() -1;
                this.status.add(new Aksi(this,"Makan",fwaktu));
                
                try {
                    int seconds = 0;
                    for (int i = 0; i < fwaktu; i++) {
                        Thread.sleep(1000);
                        seconds++;
                        if (seconds % 30 ==0){
                            this.kesejahteraan.setKekenyangan(this.kesejahteraan.getKekenyangan() + ((Masakan)(this.inventory.getInventory().get(idx))).getKekenyangan());
                            
                        }
                        this.getAksi(indexStatus).decDetikTersisa();
                        gp.setActionCounter(waktu-i);
                        
                    }
                    this.status.remove(indexStatus);
                    this.isDoAksiAktif = false;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.inventory.decItem(idx);
                this.belumBerak=true;
                System.out.println("Yey sudah makan");
                mulaiThreadbuangAirChecker(); // abis tiap makan dia ngecek 4 menit sudah bab ap blm
            });
        } else {
            kh.setErrorCaught(true);
        }
    }

    public void buangAir(int waktu) {
        final int fwaktu = waktu;
        executorService.execute(() -> {
            this.status.add(new Aksi(this, "Buang Air", fwaktu));
            this.isDoAksiAktif = true;
            this.belumBerak = false;
            int indexStatus = this.status.size() - 1;
            System.out.println("Sedang buang air");
            try {
                int seconds = 0;
                for (int i = 0; i < fwaktu; i++) {
                    Thread.sleep(1000);
                    seconds++;
                    if (seconds % 10 == 0) {
                        this.kesejahteraan.setKekenyangan(this.kesejahteraan.getKekenyangan() - 20);
                        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
                        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
                    }
                    this.getAksi(indexStatus).decDetikTersisa();
                    gp.setActionCounter(waktu-i);
                    
                }
                this.status.remove(indexStatus);
    
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Yeah lega dek kelar berak!");
            this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
            this.isDoAksiAktif = false;
        });
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

    public void yoga(int waktu) {
        final int fwaktu = waktu;
        executorService.execute(() -> {
            this.status.add(new Aksi(this, "Yoga", fwaktu));
            this.isDoAksiAktif = true;
            int indexStatus = this.status.size() - 1;
            System.out.println("Yoga dimulai, silahkan tunggu 10 detik!");
            try {
                int seconds = 0;
                for (int i = 0; i < fwaktu; i++) {
                    Thread.sleep(1000);
                    seconds++;
                    this.getAksi(indexStatus).decDetikTersisa();
                    gp.setActionCounter(waktu-i);
                    
                }
                this.status.remove(indexStatus);
    
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Yeah kelar yoga!");
            this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10*(fwaktu/60)/4);
            this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 10*(fwaktu/60)/4);
            this.isDoAksiAktif = false;
        });

    }

    public void berdoa(int waktu){
        final int fwaktu = waktu;
        executorService.execute(() -> {
        this.status.add(new Aksi(this, "Berdoa", fwaktu));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Berdoa dimulai!");
        try {
            int seconds = 0;
            for (int i = 0; i < fwaktu; i++) {
                Thread.sleep(1000);
                seconds++;
                this.getAksi(indexStatus).decDetikTersisa();
                gp.setActionCounter(waktu-i);
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Semoga doa mu terkabul!");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
        this.isDoAksiAktif = false;
        });
    }
    public void membersihkanRumah(int waktu) {
        final int fwaktu = waktu;
        executorService.execute(() -> {
        this.status.add(new Aksi(this, "Membersihkan rumah", fwaktu));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Yippi main musik");
        try {
            int seconds = 0;
            for (int i = 0; i < fwaktu; i++) {
                Thread.sleep(1000);
                seconds++;
                this.getAksi(indexStatus).decDetikTersisa();
                gp.setActionCounter(waktu-i);
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Jago banget main musiknya kak :<");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 5);
        this.isDoAksiAktif = false;
    });
    }
    public void mandi(int waktu){
        final int fwaktu = waktu;
        executorService.execute(() -> {
        this.status.add(new Aksi(this, "Mandi", fwaktu));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Mandi dimulai!");
        try {
            int seconds = 0;
            for (int i = 0; i < fwaktu; i++) {
                Thread.sleep(1000);
                seconds++;
                this.getAksi(indexStatus).decDetikTersisa();
                gp.setActionCounter(waktu-i);
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Wangi bgt lu ngab!");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 10);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() + 40);
        this.isDoAksiAktif = false;
    });
    }

    public void melukis(int waktu){
        final int fwaktu = waktu;
        executorService.execute(() -> {
        this.status.add(new Aksi(this, "Melukis", fwaktu));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Mozart sedang beraksi?");
        try {
            int seconds = 0;
            for (int i = 0; i < fwaktu; i++) {
                Thread.sleep(1000);
                seconds++;
                this.getAksi(indexStatus).decDetikTersisa();
                gp.setActionCounter(waktu-i);    
            }

            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kren bngt banh lukisannya :<");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
        this.isDoAksiAktif = false;
        });
    }

    public void bermainMusik(int waktu){
        final int fwaktu = waktu;
        executorService.execute(() -> {
        this.status.add(new Aksi(this, "Main musik", fwaktu));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Yippi main musik");
        try {
            int seconds = 0;
            for (int i = 0; i < fwaktu; i++) {
                Thread.sleep(1000);
                seconds++;
                this.getAksi(indexStatus).decDetikTersisa();
                gp.setActionCounter(waktu-i);
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Jago banget main musiknya kak :<");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 5);
        this.isDoAksiAktif = false;
    });
    }

    public void viewInfo(){
        System.out.println("Nama: "+ namaLengkap);
        System.out.println("Pekerjaan: "+ pekerjaan.getNamaKerja());
        System.out.println("Kesehatan: "+ kesejahteraan.getKesehatan());
        System.out.println("Kekenyangan: "+ kesejahteraan.getKekenyangan());
        System.out.println("Mood: "+kesejahteraan.getMood());
        System.out.println("Uang: "+ uang+"\n");
    }

    public void proyekan(int waktu) {
        final int fwaktu = waktu;
        executorService.execute(() -> {
        this.status.add(new Aksi(this, "proyekan", fwaktu));
        int indexStatus = this.status.size() - 1;
        this.isDoAksiAktif = true;
        try {
            int seconds = 0;
            for (int i = 0; i < fwaktu; i++) {
                Thread.sleep(1000);
                seconds++;
                this.getAksi(indexStatus).decDetikTersisa();
                gp.setActionCounter(waktu-i);
            }
            this.status.remove(indexStatus);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isDoAksiAktif = false;
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() - 5);
        setUang(getUang() + 100);
    });

}
}




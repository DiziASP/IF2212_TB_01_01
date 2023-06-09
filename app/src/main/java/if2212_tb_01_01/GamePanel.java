package if2212_tb_01_01;

import static if2212_tb_01_01.utils.Constant.*;

import if2212_tb_01_01.assets.AssetManager;
import if2212_tb_01_01.assets.tiles.TileManager;
import if2212_tb_01_01.config.Config;
import if2212_tb_01_01.entities.WorldClock;
import if2212_tb_01_01.entities.house.House;
import if2212_tb_01_01.entities.room.Room;
import if2212_tb_01_01.entities.sim.*;
import if2212_tb_01_01.entities.world.*;
import if2212_tb_01_01.entities.world.Point;
import if2212_tb_01_01.ui.UI;
import if2212_tb_01_01.items.furnitur.*;
// import if2212_tb_01_01.ui.WelcomeUI;
import if2212_tb_01_01.utils.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.border.Border;

public class GamePanel extends JPanel implements Runnable {
    /* Screen Generate */
    int gameState = 15;
    // gameState: 0-welcome, 1-setup, 2-help, 3-choose, 4-new, 5-stats, 6-ruangan, 7-pause, 8-create world, 
    // 9-inventory, , 11-shop, 12-MATI, 15-loadeng
    int subState = 0;
        // subState: 0-none, 1-tambahan, 2-pilihEditan, 3-pilihBarangPasang, 4-lokasiPasang, 5-lokasiBuang, 6-lokasiEdit, 7-upgrade rumah
        // 8-cari kerja, 9-pilihMakanan, 10-pilihMenuMakanan
        // 11-pilih kunjung
        // 12-tambah ruang, 13-durasiAksi, 14-aksiCounter, 15-aksiBerhasil, 16-kunjung2

    // TileManager tm;
    int actionCounter=0;
    int interact=-1;

    ArrayList<String> opsiAksi = new ArrayList<String>();

    /* Generate Thread */
    private Thread gameThread;

    /* Create Asset Manager */
    private final AssetManager assetManager = new AssetManager(this);
    private final KeyHandler keyHandler = new KeyHandler(this);
    public final CollisionHandler collisionHandler = new CollisionHandler(this);
    public final InteractionHandler interactionHandler = new InteractionHandler(this);
    public final SoundManager soundManager =  new SoundManager();
    public final Config saveLoad = new Config(this);

    private UI ui = new UI(this,keyHandler);
    private House house;
    // public Room room;
    private World world = new World(this);
    private int IndexActiveSim;
    WorldClock worldClock = new WorldClock(this, world);
    private JLabel notificationLabel;
    private Timer notificationTimer;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(c1);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        notificationLabel = new JLabel();
        add(notificationLabel);
        
        notificationLabel = new JLabel();
        add(notificationLabel);
        notificationLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        notificationLabel.setForeground(new Color(0xFDFFFF));
        notificationLabel.setBackground(c2);
        notificationLabel.setOpaque(true);
        // notificationLabel.setOpaque(true);

        // Inisialisasi timer dengan durasi 3 detik
        notificationTimer = new Timer(2800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearNotification();
            }
        });
        notificationTimer.setRepeats(false); // Menjalankan timer hanya satu kali

        //test
        
    }

    public void setupGame() {
        //readfile sim
        //test
        worldClock.getWorld().getListSim().add(new Sim(this, keyHandler, 1, "naura", new Point(7,8)));
        worldClock.getWorld().getListSim().get(0).getRoomAwal().pasangObjek(4,0, 1);
        worldClock.getWorld().getListSim().get(0).setUang(50000);
        worldClock.getWorld().getListSim().get(0).getRoomAwal().newRoomAbove("ruang relaksasi", true);
        worldClock.getWorld().getListSim().get(0).getRoomAwal().newRoomBelow("ruang dandan", true);
        worldClock.getWorld().getListSim().add(new Sim(this, keyHandler, 4, "nadira", new Point(1,1)));
        worldClock.getWorld().getListSim().get(1).getRoomAwal().newRoomLeft("ruang depresi", true);
        worldClock.getWorld().getListSim().get(1).getRoomAwal().pasangObjek(3,1, 0);
        worldClock.getWorld().getListSim().add(new Sim(this, keyHandler,7, "dizi", new Point(2,1)));
        worldClock.getWorld().getListSim().get(2).getRoomAwal().newRoomRight("ruang rindu", true);
        worldClock.getWorld().getListSim().get(2).getRoomAwal().pasangObjek(6,2, 3);
//        saveLoad.load();

        gameState=0;

        /* Play Music */
        soundManager.setFile(0);
        // soundManager.play();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startWorldClock(){
        Thread worldClockThread = new Thread(worldClock);
        worldClockThread.start();
        
    }

    @Override
    public void run() {
        double drawInterval = (double) 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        ui.update();
        if (gameState==6){
            getRoom().update();
            worldClock.getWorld().getSim(IndexActiveSim).update();

        }
    }
    public void showNotification(String message) {
        notificationLabel.setVisible(true);
        notificationLabel.setText(message);
        notificationTimer.start();
    }
    public void clearNotification() {
        notificationLabel.setText("");
        notificationLabel.setVisible(false);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // world.drawWorldMap(g2d);
        //  room.draw(g2d);
        //  sim.draw(g2d);

       ui.draw(g2d);
    }


    /* Getter */
    public AssetManager getAssetManager() {
        return assetManager;
    }

    public int getActionCounter() {
        return actionCounter;
    }

    public void setActionCounter(int actionCounter) {
        this.actionCounter = actionCounter;
    }

    public int getGs() {
        return gameState;
    }

    public void setGs(int gameState) {
        this.gameState = gameState;
        keyHandler.setArrowNum(0);
        keyHandler.setErrorCaught(false);
    }

    public int getSubState() {
        return subState;
    }

    public void setSubState(int subState) {
        this.subState = subState;
        keyHandler.setArrowNum(0);
    }

    public int getInteract() {
        return interact;
    }

    public void setInteract(int interact) {
        this.interact = interact;
    }

    public Room getRoom(){
        return getSim().getCurRoom();
    }

    public void setRoom(Room room){
        getSim().setCurRoom(room);;
    }

    public Sim getSim(){
        return worldClock.getWorld().getSim(IndexActiveSim);
    }

    public Sim getSim(int idx){
        return worldClock.getWorld().getSim(idx);
    }

    public void setSim(Sim sim){
        int idx=-1;
        for (int i=0; i < worldClock.getWorld().getListSim().size(); i++){
            if (worldClock.getWorld().getListSim().get(i).equals(sim)){
                idx=i;
                break;
            }
        }
        this.IndexActiveSim=idx;
        worldClock.getWorld().getSim(idx).setSolidArea();
    }
    public int getIndexActiveSim(){
        return IndexActiveSim;
    }
    public int setIndexActiveSim(int index){
        return IndexActiveSim = index;
    }

    public WorldClock getWorldClock(){
        return worldClock;
    }
    public void setWorldClock(WorldClock worldClock){
        this.worldClock = worldClock;
    }

    public ArrayList<Sim> getSimList(){
        return worldClock.getWorld().getListSim();
    }

    public void addSimList(Sim sim){
        worldClock.getWorld().addSim(sim);
    }

    public ArrayList<String> getOpsiAksi(){
        return opsiAksi;
    }

    public String getOpsiAksi(int a){
        return opsiAksi.get(a);
    }

    public void addOpsiAksi(String aksi){
        opsiAksi.add(0, aksi);
    }

    // public TileManager getTileManager(){
    //     return tm;
    // }
    // public void setTileManager(TileManager tm){
    //     this.tm = tm;
    // }

    public void delOpsiPindah(){
        opsiAksi.remove("pindah ruangan");
    }

    public void updateOpsiAksi(){
        opsiAksi.clear();

        if (subState==0){
            addOpsiAksi("keluar");
            addOpsiAksi("opsi lain");
            
            addOpsiAksi("edit ruangan");
            addOpsiAksi("olahraga");
            addOpsiAksi("yoga");
            addOpsiAksi("bersihkan rumah");
            // if ()
            addOpsiAksi("kerja");
            addOpsiAksi("berdoa");

            if (interact!=-1){
                if (interact<-1){
                    addOpsiAksi("pindah ruangan");
                } else{
                    String opsi = ((Furnitur)worldClock.getWorld().getSim(IndexActiveSim).getInventory().getInventory().get(interact)).getNamaAksi();
                    addOpsiAksi(opsi);
                }
            }

        } else if(subState==16){
            addOpsiAksi("keluar");
            addOpsiAksi("pulang");
            addOpsiAksi("olahraga");
            addOpsiAksi("yoga");
            addOpsiAksi("bersihkan rumah");
            addOpsiAksi("berdoa");
            if (interact!=-1){
                if (interact<-1){
                    addOpsiAksi("pindah ruangan");
                } else{
                    String opsi = ((Furnitur)worldClock.getWorld().getSim(IndexActiveSim).getInventory().getInventory().get(interact)).getNamaAksi();
                    addOpsiAksi(opsi);
                }
            }
        }else if (subState==1){
            //tambahan
            addOpsiAksi("kembali");
            if (worldClock.getIsCanAddSim()){
                addOpsiAksi("tambah sim");
            }
            addOpsiAksi("ganti sim");
            //if
            if (getSim().isCanChangePekerjaan()){
                addOpsiAksi("cari kerja");
            }
            addOpsiAksi("lihat inventory");
            addOpsiAksi("belanja");
            if (getRoom() == getSim().getCurRoom()) {
                addOpsiAksi("upgrade rumah");
            }
            addOpsiAksi("kunjungi rumah");
        } else if (subState==2){
            addOpsiAksi("kembali");
            addOpsiAksi("hapus barang");
            addOpsiAksi("pindahkan barang"); 
            addOpsiAksi("tambahkan barang");
        } else if (subState==8){
            addOpsiAksi("kembali");
            addOpsiAksi("badut sulap");
            addOpsiAksi("koki");
            addOpsiAksi("polisi");
            addOpsiAksi("programmer");
            addOpsiAksi("dokter");
        } else if (subState==7){
            addOpsiAksi("kembali");
            if (getSim().getCurRoom().getRoomRight() == null){
                addOpsiAksi("kanan");
            }
            if (getSim().getCurRoom().getRoomLeft() == null){
                addOpsiAksi("kiri");
            }
            if (getSim().getCurRoom().getRoomBelow() == null){
                addOpsiAksi("bawah");
            }
            if (getSim().getCurRoom().getRoomAbove() == null){
                addOpsiAksi("atas");
            }
        }

    }

    public void updateMenu(){
        opsiAksi.clear();
        addOpsiAksi("keluar");
        addOpsiAksi("kembali");
    }

    public KeyHandler getKeyHandler(){
        return keyHandler;
    }




}

package if2212_tb_01_01;

import static if2212_tb_01_01.utils.Constant.*;

import if2212_tb_01_01.assets.AssetManager;
import if2212_tb_01_01.assets.tiles.TileManager;
import if2212_tb_01_01.entities.WorldClock;
import if2212_tb_01_01.entities.house.House;
import if2212_tb_01_01.entities.room.Room;
import if2212_tb_01_01.entities.sim.*;
import if2212_tb_01_01.entities.world.*;
import if2212_tb_01_01.entities.world.Point;
import if2212_tb_01_01.ui.UI;
// import if2212_tb_01_01.ui.WelcomeUI;
import if2212_tb_01_01.utils.CollisionHandler;
import if2212_tb_01_01.utils.InputListener;
import if2212_tb_01_01.utils.InteractionHandler;
import if2212_tb_01_01.utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements Runnable {
    /* Screen Generate */
    int gameState = 0;
    // gameState: 0-welcome, 1-setup, 2-help, 3-choose, 4-new, 5-stats, 6-ruangan, 7-pause, 8-create world, 
    // 9-inventory, 10-world kunjungan, 11-shop
    int subState = 0;
        // subState: 0-none, 1-tambahan, 2-pilihEditan, 3-pilihBarangPasang, 4-lokasiPasang, 5-lokasiBuang, 6-lokasiEdit, 7-lokasiBaru
        // 8-cari kerja, 9-pilihMakanan, 10-pilihMenuMakanan
        // 11-tampilkan waktu
        // 12-tambah ruang, 13-durasiAksi, 14-aksiCounter, 15-aksiBerhasil, 16-batalkanAksi??

    // TileManager tm;

    ArrayList<String> opsiAksi = new ArrayList<String>();

    /* Generate Thread */
    private Thread gameThread;

    /* Create Asset Manager */
    private final AssetManager assetManager = new AssetManager(this);
    private final KeyHandler keyHandler = new KeyHandler(this);
    public final CollisionHandler collisionHandler = new CollisionHandler(this);
    public final InteractionHandler interactionHandler = new InteractionHandler(this);

    private UI ui = new UI(this,keyHandler);
    private Sim sim;;
    private ArrayList<Sim> listSim = new ArrayList<Sim>();
    private House house;
    public Room room;
    private World world = new World(this);

    WorldClock worldClock = new WorldClock(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(c1);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        //test
        
    }

    public void setupGame() {
        //readfile sim
        //test
        listSim.add(new Sim(this, keyHandler, 1, "naura", new Point(7,8)));
        listSim.add(new Sim(this, keyHandler, 4, "nadira", new Point(1,1)));
        listSim.add(new Sim(this, keyHandler,7, "dizi", new Point(2,1)));
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
            room.update(); //buggy kalo atas
            sim.update();
        }
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

    public int getGs() {
        return gameState;
    }

    public void setGs(int gameState) {
        this.gameState = gameState;
        keyHandler.setArrowNum(0);
    }

    public int getSubState() {
        return subState;
    }

    public void setSubState(int subState) {
        this.subState = subState;
        keyHandler.setArrowNum(0);
    }

    public Room getRoom(){
        return room;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    public Sim getSim(){
        return sim;
    }

    public void setSim(Sim sim){
        this.sim = sim;
    }

    public ArrayList<Sim> getSimList(){
        return listSim;
    }

    public void addSimList(Sim sim){
        listSim.add(sim);
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

    public void updateOpsiAksi(){
        opsiAksi.clear();

        if (subState==0){
            addOpsiAksi("keluar");
            addOpsiAksi("opsi lain");
            addOpsiAksi("edit ruangan");
            addOpsiAksi("olahraga");
            addOpsiAksi("yoga");
            addOpsiAksi("bersihkan rumah");
            addOpsiAksi("berdoa");
        } else if (subState==1){
            //tambahan
            addOpsiAksi("kembali");
            addOpsiAksi("tambah sim");
            addOpsiAksi("ganti sim");
            //if
            addOpsiAksi("cari kerja");

            addOpsiAksi("lihat inventory");
            addOpsiAksi("upgrade rumah"); 
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
        }
    }

    public void updateMenu(){
        opsiAksi.clear();

        addOpsiAksi("keluar");
        addOpsiAksi("kembali");
    }


}


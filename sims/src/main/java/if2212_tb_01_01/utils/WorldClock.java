package if2212_tb_01_01.utils;

import java.util.Scanner;

import if2212_tb_01_01.entities.Kesejahteraan;
import if2212_tb_01_01.entities.World;
import if2212_tb_01_01.entities.action.AksiPasif.AksiPasif;
import if2212_tb_01_01.entities.sim.Sim;

public class WorldClock implements Runnable {
    private volatile boolean running;
    private int days;
    
    private int minutes;
    private int seconds;
    World world;

    public WorldClock(World world) {
        running = true;
        days = 0;
        seconds = 0;
        minutes = 0;
        this.world = world;
    }

    public int getDays(){
        return days;
    }
    
    public int getMinute(){
        return minutes;
    }
    public int getSeconds(){
        return seconds;
    }

    public void stop() {
        running = false;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(100); // Tunggu 1 detik
                if (!world.isIdle()) {
                    seconds++;
                    doAksiPasif();
                    berakChecker();
                    if (seconds >= 60) {
                        minutes++;
                        seconds = 0;
                    }
                    if((days*12 + minutes) % 10 == 0 && seconds == 0){
                        tidurChecker();
                    }
                    if (minutes >= 12) {
                        days++;
                        minutes = 0;
                        System.out.println("Hari sudah berganti, kesempatan menambah sim dapat digunakan kembali");
                        world.setIsCanAddSim(true);
                        setBisaKerja();
                    }
                }
                // checkKematian();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkKematian(){
            Scanner scanner = new Scanner(System.in);
            Kesejahteraan x = world.getSimActive().getKesejahteraan();
            if(x.getKekenyangan().equals(0)|| x.getKesehatan().equals(0) || x.getMood().equals(0) || x.getKebersihan().equals(0) ){
                System.out.println("Sim "+world.getSimActive().getNamaLengkap()+" sudah istirahat dengan tenang");
                world.getListSim().remove(world.getSimActive());
                if(getWorld().getListSim().size() >=1){
                    System.out.println("Daftar sim yang dapat dimainkan");
                    for(int i = 0;i< getWorld().getListSim().size();i++){
                        System.out.println((i+1)+". "+getWorld().getSim(i).getNamaLengkap());
                    }
                    
                    boolean inputValid = false;
                    while(!inputValid){
                        System.out.print("Masukkan nomor sim yang ingin dimainkan: ");
                        Integer nomor = InputChecker.toAngka(scanner.nextLine());
                        if(!nomor.equals(-999)){
                            if(nomor>= 1 && nomor <= getWorld().getListSim().size()){
                                inputValid = true;
                                getWorld().setSimActive(getWorld().getListSim().get(nomor - 1));
                            }
                            else{
                                System.out.println("Input tidak valid! (Hint: input < 1 atau melebihi total sim yang dapat dimainkan)");
                            }
                        }
                    }
                }
            }
    }

    public void melihatWaktu(Sim sim) {
        System.out.println("Duniamu telah berjalan selama " + this.days + " hari.");
        System.out.println("Waktu hari ini telah berjalan selama " + (this.minutes) + " menit "+this.seconds+" detik. ");
        int jumlahAksi = sim.getListAksiPasif().size();
        if (jumlahAksi == 0) {
            System.out.println("Saat ini kamu sedang tidak melakukan aksi apa-apa.");
        } else {
            System.out.println("Mari kita lihat progres aksi yang kamu lakukan!");
            for (int i = 1; i <= jumlahAksi; i++) {
                System.out.println(i + ". " + sim.getListAksiPasif().get(i-1).getNama() + " <-- tersisa "
                        + sim.getListAksiPasif().get(i-1).getDetikTersisa() + " detik");
            }
        }
    }

    public void doAksiPasif(){
        for(Sim sim : getWorld().getListSim()){
            for(AksiPasif aksi : sim.getListAksiPasif()){
                aksi.run();
            }
        }
        for(Sim sim : getWorld().getListSim()){
            int i = 0;
            while(i < sim.getListAksiPasif().size()){
                if(sim.getListAksiPasif().get(i).getDetikTersisa() == 0){
                    sim.getListAksiPasif().remove(i);
                }
                else{
                    i++;
                }
            }
        }
    }

    public void berakChecker(){
        for(Sim sim : getWorld().getListSim()){
            for(AksiPasif aksi: sim.getBerakChecker()){
                aksi.run();
            }
        }
        for(Sim sim : getWorld().getListSim()){
            int i = 0;
            while(i < sim.getBerakChecker().size()){
                if(sim.getBerakChecker().get(i).getDetikTersisa() == 0){
                    sim.getBerakChecker().remove(i);
                }
                else{
                    i++;
                }
            }
        }
    }

    public void setBisaKerja(){
        for(Sim sim: getWorld().getListSim()){
            sim.setIsBisaKerja(true);
        }
    }

    public void tidurChecker(){
        for(Sim sim: getWorld().getListSim()){
            if(!sim.getIsUdhTidur()){
                sim.getKesejahteraan().setKesehatan(sim.getKesejahteraan().getKesehatan() - 5);
                sim.getKesejahteraan().setMood(sim.getKesejahteraan().getMood() - 5);
                System.out.println(sim.getNamaLengkap()+" belum tidur selama 10 menit");
            }
            else{
                sim.setIsUdhTidur(false);
            }
        }
    }
}


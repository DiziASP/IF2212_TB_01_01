package if2212_tb_01_01.utils;

import if2212_tb_01_01.entities.World;

public class WorldClock implements Runnable {
    private volatile boolean running;
    private int days;
    // private int minutes;
    private int seconds;
    World world;

    public WorldClock(World world) {
        running = true;
        days = 0;
        seconds = 0;
        // minutes = 0;
        this.world = world;
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

    public String getWaktuTersisa(){
        Integer detikTersisa = 12*60 - seconds;
        Integer sisaMenit = detikTersisa / 60;
        Integer sisaDetik = detikTersisa - (sisaMenit * 60);
        return (sisaMenit+" menit "+ sisaDetik+" detik. ");
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(100); // Tunggu 1 detik
                if (!world.isIdle()) {
                    seconds++;

                    if (seconds >= 60*12) {
                        days++;
                        seconds = 0;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void melihatWaktu(int indeksSim) {
        System.out.println("Duniamu telah berjalan selama " + this.days + " hari.");
        System.out.println("Sisa waktu hari ini adalah " + getWaktuTersisa());
        int jumlahAksi = world.getSim(indeksSim).getStatus().size();
        if (jumlahAksi == 0) {
            System.out.println("Saat ini kamu sedang tidak melakukan aksi apa-apa.");
        } else {
            System.out.println("Mari kita lihat progres aksi yang kamu lakukan!");
            for (int i = 1; i <= jumlahAksi; i++) {
                System.out.println(i + ". " + world.getSim(indeksSim).getAksi(i-1).getNama() + " <-- tersisa "
                        + world.getSim(indeksSim).getAksi(i-1).waktuTersisa());
            }
             
        }
    }
}

package if2212_tb_01_01.utils;

import if2212_tb_01_01.entities.*;

<<<<<<< Updated upstream
public class WorldClock extends Thread {
    private int minutes;
    private int seconds;
    private int daysInWorld;
    public int getDaysInWorld() {
        return daysInWorld;
    }
    public void setDaysInWorld(int daysInWorld) {
        this.daysInWorld = daysInWorld;
    }
    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public int getSeconds() {
        return seconds;
    }
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public WorldClock() {
        minutes = 0;
        seconds = 0;
        daysInWorld = 0;
    }
    public void run(World world) {
        while (true) {
            try {
                while (!world.getIsGameEnd()){
                    Thread.sleep(1000);
                    if (!world.isIdle()){
                        seconds++;
                        if (seconds >= 60) {
                            seconds = 0;
                            minutes++;
                            if (minutes >= 12) {
                                minutes = 0;
                                daysInWorld++;
                            }
                        }
=======
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

    public String getWaktuSisaHari(){
        Integer detik = (12*60) - this.seconds;
        Integer sisaMenit = detik / 60;
        Integer sisaDetik = detik - (sisaMenit * 60);
        return (sisaMenit+ " menit "+ sisaDetik+" detik");
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

                    if (seconds >= 12*60) {
                        // minutes++;
                        days++;
                        seconds = 0;
                        
>>>>>>> Stashed changes
                    }
                    // if (minutes >= 12) {
                    //     days++;
                    //     minutes = 0;
                    // }
                }
<<<<<<< Updated upstream
        } catch (InterruptedException e) {
            e.printStackTrace();
=======
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void melihatWaktu(int indeksSim) {
        System.out.println("Duniamu telah berjalan selama " + this.days + " hari.");

        System.out.println("Sisa waktu hari ini adalah " + getWaktuSisaHari() +".");
        int jumlahAksi = world.getSim(indeksSim).getStatus().size();
        if (jumlahAksi == 0) {
            System.out.println("Saat ini kamu sedang tidak melakukan aksi apa-apa.");
        } else {
            System.out.println("Mari kita lihat progres aksi yang kamu lakukan!");
            for (int i = 1; i <= jumlahAksi; i++) {
                System.out.println(i + ". " + world.getSim(indeksSim).getAksi(i-1).getNama() + " <-- tersisa "
                        + world.getSim(indeksSim).getAksi(i-1).waktuTersisa());
            }
             
>>>>>>> Stashed changes
        }
    }
    
    // tambahkan getter dan setter untuk hours, minutes, dan seconds
}
}


package if2212_tb_01_01.utils;

import if2212_tb_01_01.entities.Sim;

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
    public void run(int sec, Sim sim, int indexStatus) {
        while (true) {
            try {
                for (int i=0; i<sec; i++){
                    Thread.sleep(1000); // tunggu 1 detik
                    seconds++;
                    if (seconds >= 60) {
                        seconds = 0;
                        minutes++;
                        if (minutes >= 12) {
                            minutes = 0;
                            daysInWorld++;
                        }
                    }
                }
                sim.getStatus().remove(indexStatus);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // tambahkan getter dan setter untuk hours, minutes, dan seconds
}


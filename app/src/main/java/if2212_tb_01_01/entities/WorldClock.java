package if2212_tb_01_01.entities;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.sim.Sim;

public class WorldClock implements Runnable {
    GamePanel gp;
    private int minutes;
    private int seconds;
    private int daysInWorld;

    public WorldClock(GamePanel gp){
        this.gp =gp;
        minutes = 0;
        seconds = 0;
        daysInWorld = 0;
    }
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

    @Override
    public void run() {
        while (true) {
            try {
//                System.out.println("Seconds: " + seconds + " Minutes: " + minutes + " Days: " + daysInWorld);
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateSimStatus(){
//        sim.getStatus().remove(indexStatus);
    }

}

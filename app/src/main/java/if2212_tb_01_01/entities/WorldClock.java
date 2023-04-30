package if2212_tb_01_01.entities;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.world.World;


public class WorldClock implements Runnable {
    GamePanel gp;
    private volatile boolean running;
    private int minutes;
    private int seconds;
    private int daysInWorld;
    World world;
    private boolean isCanAddSim = true;
    private boolean isCanChangePekerjaan = true;
    private boolean isBelumBerakAbisMakan = false;



    public WorldClock(GamePanel gp, World world){
        this.gp =gp;
        minutes = 0;
        seconds = 0;
        daysInWorld = 0;
        this.world = world;
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
    public void setWorld(World world) {
        this.world = world;
    }
    public World getWorld() {
        return world;
    }
    public void melihatWaktu(){
        System.out.println(" Days: " + daysInWorld + " Minutes: " + minutes + "Seconds: " + seconds);
    }

    

    @Override
    public void run() {
            while (running) {
                try {
                    Thread.sleep(1000); // Tunggu 1 detik
                    if (!world.isIdle()) {
                        seconds++;
    
                        if (seconds >= 60) {
                            minutes++;
                            seconds = 0;
                        }
                        if (minutes >= 12) {
                            daysInWorld++;
                            minutes = 0;
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

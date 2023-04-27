package if2212_tb_01_01.utils;

import if2212_tb_01_01.entities.Sim;

public class ActionThread extends Thread {
    private int minutes;
    private int seconds;
    private int indexStatus;
    private Sim sim;
    private int waktuAction;
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
    public ActionThread(int indexStatus, Sim sim, int waktuAction) {
        minutes = 0;
        seconds = 0;
        this.indexStatus = indexStatus;
        this.sim = sim;
        this.waktuAction = waktuAction;
    }
    public void run() {
        while (true) {
            try {
                for (int i=0; i<waktuAction; i++){
                    Thread.sleep(1000); // tunggu 1 detik
                    seconds++;
                    if (seconds >= 60) {
                        seconds = 0;
                        minutes++;
                        sim.getAksi(indexStatus).kurangiMenitTersisa(1);
                    }
                }
                sim.getStatus().remove(indexStatus);
                sim.setIsDoAksiAktif(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // tambahkan getter dan setter untuk hours, minutes, dan seconds
}


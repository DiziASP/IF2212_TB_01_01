package if2212_tb_01_01.entities;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.world.World;


public class WorldClock implements Runnable {
    GamePanel gp;
    private volatile boolean running = true;
    private int minutes;
    private int seconds;
    private int daysInWorld;
    World world;
    private boolean isCanAddSim = true;

    public boolean getIsCanAddSim() {
        return isCanAddSim;
    }
    public void setIsCanAddSim(boolean isCanAddSim) {
        this.isCanAddSim = isCanAddSim;
    }

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
    public String melihatWaktu(){
        String info = ("<html>\nDays: " + daysInWorld + " Minutes: " + minutes + "Seconds: " + seconds + "<br>\n");
        if (world.getSim(gp.getIndexActiveSim()).getStatus().size()==0){
            info += world.getSim(gp.getIndexActiveSim()).getNamaLengkap() + "sedang tidak melakukan apa-apa.";
        } else {
            info+= "Wow " + world.getSim(gp.getIndexActiveSim()).getNamaLengkap() + " sedang melakukan aksi: \n";
            for (int i=0; i<world.getSim(gp.getIndexActiveSim()).getStatus().size(); i++){
                if (world.getSim(gp.getIndexActiveSim()).getStatus().get(i).getNama().equals("beli barang")){
                    info += world.getSim(gp.getIndexActiveSim()).getNamaLengkap() + "sedang menunggu pengiriman barang" + ;
                }
                info += world.getSim(gp.getIndexActiveSim()).getNamaLengkap() + "sedang melakukan " + world.getSim(gp.getIndexActiveSim()).getStatus().get(i).getNama() + "\n";
                info += "dengan " + world.getSim(gp.getIndexActiveSim()).getStatus().get(i).getDetikTersisa() + " detik tersisa."+ "\n";
            }
        }
        return info;
    }

    public void checkKematian(){
        if (world.getSim(gp.getIndexActiveSim()).getKesejahteraan().getMood()<=0 || world.getSim(gp.getIndexActiveSim()).getKesejahteraan().getKekenyangan()<=0 || world.getSim(gp.getIndexActiveSim()).getKesejahteraan().getKesehatan()<=0 ){
            System.out.println("lu mati");
            world.removeSim(world.getSim(gp.getIndexActiveSim()));
            gp.setGs(12);
            gp.setSubState(0);
        } 
    }

    public void checkerHarian(){
        isCanAddSim = true;
    }

    public void checker10menit(){
        if (world.getSim(gp.getIndexActiveSim()).getWaktuTidur()>0){
            world.getSim(gp.getIndexActiveSim()).setWaktuTidur(0);
        } else{
            this.world.getSim(gp.getIndexActiveSim()).setWaktuTidur(0);
            this.world.getSim(gp.getIndexActiveSim()).getKesejahteraan().setKesehatan(this.world.getSim(gp.getIndexActiveSim()).getKesejahteraan().getKesehatan()-5);
            this.world.getSim(gp.getIndexActiveSim()).getKesejahteraan().setMood(this.world.getSim(gp.getIndexActiveSim()).getKesejahteraan().getMood()-5);
        }
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
                            checkerHarian();
                        }
                        if (minutes % 10 == 0 && seconds == 0) {
                            checker10menit();
                        }
                    }
                    checkKematian();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
    

    public void updateSimStatus(){
//        sim.getStatus().remove(indexStatus);
    }
    

}

package if2212_tb_01_01.entities;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.entities.world.World;

import java.io.Serializable;


public class WorldClock implements Runnable, Serializable {
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
        String info = ("<html> Days: " + daysInWorld + " Minutes: " + minutes + " Seconds: " + seconds + "<br>\n");
        if (world.getSim(gp.getIndexActiveSim()).getStatus().size()==0){
            info += world.getSim(gp.getIndexActiveSim()).getNamaLengkap() + " sedang tidak melakukan apa-apa.<br></html>\n";
        } else {
            info+= "Wow " + world.getSim(gp.getIndexActiveSim()).getNamaLengkap() + " sedang melakukan aksi: <br>\n";
            for (int i=0; i<world.getSim(gp.getIndexActiveSim()).getStatus().size(); i++){
                if (world.getSim(gp.getIndexActiveSim()).getStatus().get(i).getNama().equals("beli barang")){
                    info += "Menunggu " + world.getSim(gp.getIndexActiveSim()).getInventory().getInventory().get(world.getSim(gp.getIndexActiveSim()).getStatus().get(i).getIndexBeli()).getNama();
                } else {
                    info += "Melakukan " + world.getSim(gp.getIndexActiveSim()).getStatus().get(i).getNama() + "\n";
                }
                info += " dengan " + world.getSim(gp.getIndexActiveSim()).getStatus().get(i).getDetikTersisa() + " detik tersisa."+ "<br>\n";
            }
            info += "</html>\n";
        }
        System.out.println(info);
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
        for (Sim sim : world.getListSim()){
            if (sim.getWaktuTidur()>0){
                sim.setWaktuTidur(0);
            } else{
                sim.setWaktuTidur(0);
                sim.getKesejahteraan().setKesehatan(this.world.getSim(gp.getIndexActiveSim()).getKesejahteraan().getKesehatan()-5);
                sim.getKesejahteraan().setMood(this.world.getSim(gp.getIndexActiveSim()).getKesejahteraan().getMood()-5);
            }
        }
    }

    @Override
    public void run() {
            while (running) {
                try {
                    Thread.sleep(1000); // Tunggu 1 detik
                    if (!world.isIdle()) {
                        for(Sim sim: getWorld().getListSim()){
                            int i = 0;
                            while(i < sim.getStatus().size()){
                                if(sim.getStatus().get(i).getIsAksiPasif()){
                                    sim.getStatus().get(i).decDetikTersisa();
                                    if(sim.getStatus().get(i).getDetikTersisa() <= 0){
                                        if (sim.getStatus().get(i).getNama().equals("beli barang")){
                                            sim.getInventory().incItem(sim.getStatus().get(i).getIndexBeli());
                                        } else if (sim.getStatus().get(i).getNama().equals("upgrade rumah")){
                                            sim.getStatus().get(i).getRoomUpgrade().setIsBuilded(true);
                                        }
                                        sim.getStatus().remove(i);
                                        

                                        
                                    }
                                    else{
                                        i++;
                                    }
                                }
                                else {
                                    i++;
                                } 
                            }
                            if (sim.getWaktuSetelahGantiKerja()!=-999){
                                sim.setWaktuSetelahGantiKerja(sim.getWaktuSetelahGantiKerja()+1);
                            }
                            if (sim.getWaktuSetelahGantiKerja()>=12*60){
                                sim.setWaktuSetelahGantiKerja(-999);
                            } 
                        }
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
                        if (minutes % 10 == 0 && minutes!=0 && seconds == 0) {
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

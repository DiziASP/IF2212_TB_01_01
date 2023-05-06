package if2212_tb_01_01.entities.action.AksiPasif;

import if2212_tb_01_01.entities.action.Aksi;
import if2212_tb_01_01.entities.sim.Sim;

public abstract class AksiPasif extends Aksi {
    // boolean worldIdle;
    String nama;
    public AksiPasif(Sim sim, int jumlahWaktu, String nama){
        super(sim,jumlahWaktu);
        this.nama = nama;
    }   
    // public void run(int waktu){
    //     try {
    //         int seconds = 0;
    //         while(seconds < waktu && getDetikTersisa()!= 0){
    //             Thread.sleep(100);
    //             seconds++;
    //             this.kurangiDetikTersisa(1);
    //         }
    //         if(this.getDetikTersisa() == 0){
    //             efekToSim();
    //         }
    //     } catch (InterruptedException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }

    public void run(){
        this.kurangiDetikTersisa(1);
        if(this.getDetikTersisa() == 0){
            efekToSim();
        }
    }

    public String getNama(){
        return nama;
    }

    public abstract void efekToSim();



}

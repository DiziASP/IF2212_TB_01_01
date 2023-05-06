package if2212_tb_01_01.entities.action.AksiAktif;

import if2212_tb_01_01.entities.sim.Sim;

public class Berdoa extends AksiAktif{
    public Berdoa(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().getKesejahteraan().setMood(currentMood + (getWaktuKelipatan() / 10));
    }
}

package if2212_tb_01_01.entities.action.AksiAktif;

import if2212_tb_01_01.entities.sim.Sim;

public class Proyekan extends AksiAktif{
    public Proyekan(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().setUang(this.getSim().getUang() + 100);
        this.getSim().getKesejahteraan().setMood(currentMood  - 5);
    }
}

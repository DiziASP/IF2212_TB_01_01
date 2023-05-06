package if2212_tb_01_01.entities.action.AksiAktif;

import if2212_tb_01_01.entities.sim.Sim;

public class BersihRumah extends AksiAktif{
    public BersihRumah(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        int currentMood = this.getSim().getKesejahteraan().getMood();
        int currentKebersihan = this.getSim().getKesejahteraan().getKebersihan();
        this.getSim().getKesejahteraan().setMood(currentMood + (30));
        this.getSim().getKesejahteraan().setKebersihan(currentKebersihan - (5));
    }
}

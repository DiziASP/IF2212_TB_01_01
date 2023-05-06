package if2212_tb_01_01.entities.action.AksiAktif;

import if2212_tb_01_01.entities.sim.Sim;

public class Mandi extends AksiAktif{
    public Mandi(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        int currentMood = this.getSim().getKesejahteraan().getMood();
        int currentKesehatan = this.getSim().getKesejahteraan().getKesehatan();
        int currentKebersihan = this.getSim().getKesejahteraan().getKebersihan();
        this.getSim().getKesejahteraan().setMood(currentMood + (10));
        this.getSim().getKesejahteraan().setKesehatan(currentKesehatan + (10));
        this.getSim().getKesejahteraan().setKebersihan(currentKebersihan + 40);
    }
}

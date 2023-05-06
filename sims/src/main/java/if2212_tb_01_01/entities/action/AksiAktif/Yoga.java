package if2212_tb_01_01.entities.action.AksiAktif;

import if2212_tb_01_01.entities.sim.Sim;

public class Yoga extends AksiAktif{
    public Yoga(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        int currentMood = this.getSim().getKesejahteraan().getMood();
        int currentKesehatan = this.getSim().getKesejahteraan().getKesehatan();
        this.getSim().getKesejahteraan().setMood(currentMood + (10*(getWaktuKelipatan()/60)/4));
        this.getSim().getKesejahteraan().setKesehatan(currentKesehatan + (10*(getWaktuKelipatan()/60)/4));
    }
}

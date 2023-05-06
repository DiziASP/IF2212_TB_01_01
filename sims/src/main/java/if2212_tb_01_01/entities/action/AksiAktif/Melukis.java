package if2212_tb_01_01.entities.action.AksiAktif;

import if2212_tb_01_01.entities.sim.Sim;

public class Melukis extends AksiAktif {
    public Melukis(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        int currentMood = this.getSim().getKesejahteraan().getMood();
        int currentKebersihan = this.getSim().getKesejahteraan().getKebersihan();
        this.getSim().getKesejahteraan().setKebersihan(currentKebersihan - (getWaktuKelipatan()/10));
        this.getSim().getKesejahteraan().setMood(currentMood + (getWaktuKelipatan() / 10));
    }
}

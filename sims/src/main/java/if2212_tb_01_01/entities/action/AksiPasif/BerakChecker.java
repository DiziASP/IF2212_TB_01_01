package if2212_tb_01_01.entities.action.AksiPasif;

import if2212_tb_01_01.entities.sim.Sim;

public class BerakChecker extends AksiPasif{
    public BerakChecker(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,"berak checker");
    }
    public void efekToSim(){
        getSim().getKesejahteraan().setKesehatan(getSim().getKesejahteraan().getKesehatan() - 5);
        getSim().getKesejahteraan().setMood(getSim().getKesejahteraan().getMood() - 5);
        System.out.println(getSim().getNamaLengkap()+ " berak di celana!");
    }
}

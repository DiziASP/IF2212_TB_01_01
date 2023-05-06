package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.sim.Sim;

public class MainMusik extends AksiAktif{
    public MainMusik(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        int currentKebersihan = this.getSim().getKesejahteraan().getKebersihan();
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().getKesejahteraan().setKebersihan(currentKebersihan - 5);
        this.getSim().getKesejahteraan().setMood(currentMood + (30));
    }
}

package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.sim.Sim;

public class Tidur extends AksiAktif{
    public Tidur(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,240);
    }
    public void efekToSim(){
        int currentKesehatan = this.getSim().getKesejahteraan().getKesehatan();
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().getKesejahteraan().setKesehatan(currentKesehatan + (20));
        this.getSim().getKesejahteraan().setMood(currentMood + (30));
    }
}

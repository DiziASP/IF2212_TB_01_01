package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.sim.Sim;

public class Olahraga extends AksiAktif {
    public Olahraga(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,20);
    }
    public void efekToSim(){
        int currentKesehatan = this.getSim().getKesejahteraan().getKesehatan();
        int currentKekenyangan = this.getSim().getKesejahteraan().getKekenyangan();
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().getKesejahteraan().setKesehatan(currentKesehatan + (5));
        this.getSim().getKesejahteraan().setKekenyangan(currentKekenyangan - (5));
        this.getSim().getKesejahteraan().setMood(currentMood + (10));
    }
    
}

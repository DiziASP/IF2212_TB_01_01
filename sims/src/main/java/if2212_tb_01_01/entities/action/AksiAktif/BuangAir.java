package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.sim.Sim;

public class BuangAir extends AksiAktif{
    public BuangAir(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,jumlahWaktu);
    }
    public void efekToSim(){
        // this.getSim().setCurrentRuangan();
        int currentKekenyangan = this.getSim().getKesejahteraan().getKekenyangan();
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().getKesejahteraan().setKekenyangan(currentKekenyangan - (20));
        this.getSim().getKesejahteraan().setMood(currentMood + (10));
    }
}

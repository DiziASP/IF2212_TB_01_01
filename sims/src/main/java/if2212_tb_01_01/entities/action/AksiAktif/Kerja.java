package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.sim.Sim;

public class Kerja extends AksiAktif{
    public Kerja(Sim sim, int jumlahWaktu){
        super(sim,jumlahWaktu,30);
    }
    public void efekToSim(){
        int currentKekenyangan = this.getSim().getKesejahteraan().getKekenyangan();
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().getKesejahteraan().setKekenyangan(currentKekenyangan - (10));
        this.getSim().getKesejahteraan().setMood(currentMood - (10));
        this.getSim().incDurasiKerja(this.getWaktuKelipatan());
        if(this.getSim().getDurasiKerja() % 240 == 0 && this.getSim().getDurasiKerja() != 0){
            this.getSim().setUang(this.getSim().getUang() + this.getSim().getPekerjaan().getGaji());
        }
    }
}

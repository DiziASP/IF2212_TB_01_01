package if2212_tb_01_01.entities.action.AksiAktif;

// import if2212_tb_01_01.entities.action.Aksi;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;
import if2212_tb_01_01.objects.masakan.Masakan;

public class Masak extends AksiAktif{
    int indeksInventory;
    public Masak(Sim sim, int jumlahWaktu, int indeksInventory){
        super(sim,jumlahWaktu , jumlahWaktu);
        this.indeksInventory = indeksInventory;
    }
    public void efekToSim(){
        int currentMood = this.getSim().getKesejahteraan().getMood();
        this.getSim().getKesejahteraan().setMood(currentMood + (10));
        getSim().getInventory().incItem(indeksInventory);
        Masakan masak = (Masakan) getSim().getInventory().getInventory().get(indeksInventory);
        for(BahanMakanan bh : masak.getBahan()){
            getSim().getInventory().decItem(bh.getIndeks());
        }
    }
}

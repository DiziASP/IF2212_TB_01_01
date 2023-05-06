package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;
import if2212_tb_01_01.objects.masakan.Masakan;

public class Makan extends AksiAktif {
    int indeksInventory;
    public Makan(Sim sim, int jumlahWaktu, int indeksInventory){
        super(sim,jumlahWaktu,30);
        this.indeksInventory = indeksInventory;
    }
    public void efekToSim(){
        int currentKekenyangan = this.getSim().getKesejahteraan().getKekenyangan();
        if(getSim().getInventory().getInventory().get(indeksInventory) instanceof Masakan){
            Masakan masak = (Masakan) getSim().getInventory().getInventory().get(indeksInventory);
            this.getSim().getKesejahteraan().setKekenyangan(currentKekenyangan + (masak.getKekenyangan()));
        }
        else if(getSim().getInventory().getInventory().get(indeksInventory) instanceof BahanMakanan){
            BahanMakanan masak = (BahanMakanan) getSim().getInventory().getInventory().get(indeksInventory);
            this.getSim().getKesejahteraan().setKekenyangan(currentKekenyangan + (masak.getKekenyangan()));
        }
        getSim().getInventory().decItem(indeksInventory);

        
        //benerin inventory dulu
    }
    
}
package if2212_tb_01_01.entities.action.AksiPasif;

import if2212_tb_01_01.entities.sim.Sim;

public class BeliBarang extends AksiPasif{
    int indexBarang;
    public BeliBarang(Sim sim, int jumlahWaktu, int indexBarang){
        super(sim,jumlahWaktu,"beli barang");
        this.indexBarang = indexBarang;
    }
    public void efekToSim(){
        getSim().getInventory().incItem(indexBarang);
        System.out.println("Item "+getSim().getInventory().getInventory().get(indexBarang).getNama()+" sudah sampai dan masuk ke dalam inventory! ");
    }
}

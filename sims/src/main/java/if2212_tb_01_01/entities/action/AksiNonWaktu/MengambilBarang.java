package if2212_tb_01_01.entities.action.AksiNonWaktu;

import if2212_tb_01_01.entities.sim.Sim;

public class MengambilBarang extends AksiNonWaktu{
    int indeksObjekRuangan;
    public MengambilBarang(Sim sim, int indeksObjekRuangan){
        super(sim);
        this.indeksObjekRuangan = indeksObjekRuangan;
    }
    public void efekToSim(){
        // getSim().getCurrentRuangan().removeObjek;
        // getSim().getInventory().addItem();
        // getSim().getCurrentRuangan().setMapRuangan();
    }
}

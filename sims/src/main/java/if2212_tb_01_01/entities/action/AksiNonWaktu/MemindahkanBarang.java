package if2212_tb_01_01.entities.action.AksiNonWaktu;

import if2212_tb_01_01.entities.sim.Sim;

public class MemindahkanBarang extends AksiNonWaktu{
    int indeksObjekRuangan;
    public MemindahkanBarang(Sim sim, int indeksObjekRuangan){
        super(sim);
        this.indeksObjekRuangan = indeksObjekRuangan;
    }
    public void efekToSim(){
        // getSim().getCurrentRuangan().addObjek;
        // getSim().getCurrentRuangan().setMapRuangan();
    }
}

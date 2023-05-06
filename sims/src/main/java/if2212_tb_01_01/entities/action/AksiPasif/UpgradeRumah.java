package if2212_tb_01_01.entities.action.AksiPasif;

import if2212_tb_01_01.entities.room.Ruangan;
import if2212_tb_01_01.entities.sim.Sim;

public class UpgradeRumah extends AksiPasif{
    Ruangan upgradedRuangan;
    public UpgradeRumah(Sim sim, int jumlahWaktu, Ruangan upgradedRuangan){
        super(sim,jumlahWaktu,"upgrade rumah");
        this.upgradedRuangan = upgradedRuangan;
    }
    public void efekToSim(){
        upgradedRuangan.setIsBuilded(true);
        System.out.println("Ruangan "+upgradedRuangan.getNama()+" sudah selesai dibangun!");
    }
}

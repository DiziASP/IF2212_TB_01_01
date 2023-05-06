package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.entities.house.Rumah;


public class Berkunjung extends AksiAktif {
    Rumah rumahTujuan;
    public Berkunjung(Sim sim, int jumlahWaktu, Rumah rumahTujuan){
        super(sim,jumlahWaktu,jumlahWaktu);
        this.rumahTujuan = rumahTujuan;
    }
    public void efekToSim(){
        this.getSim().setCurrentRumah(rumahTujuan);
        this.getSim().setCurrentRuangan(rumahTujuan.getRuanganAwal());
    }
}

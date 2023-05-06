package if2212_tb_01_01.entities.action.AksiAktif;
import if2212_tb_01_01.entities.action.Aksi;
import if2212_tb_01_01.entities.sim.Sim;

public abstract class AksiAktif extends Aksi {
    int waktuKelipatan;
    int aksiKelipatan;
    public AksiAktif(Sim sim, int jumlahWaktu, int waktuKelipatan){
        super(sim, jumlahWaktu);
        this.waktuKelipatan = waktuKelipatan;
        this.aksiKelipatan = jumlahWaktu / waktuKelipatan;
    }
    public void run(){
        int waktu = this.getDetikTersisa();
        getSim().setIsDoAksiAktif(true);
        try {
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(100);
                seconds++;
                // System.out.println(seconds);
                this.kurangiDetikTersisa(1);
                if (seconds % waktuKelipatan == 0) {
                    efekToSim();
                }
            }
            getSim().setIsDoAksiAktif(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWaktuKelipatan(){
        return waktuKelipatan;
    }
    public abstract void efekToSim();
}

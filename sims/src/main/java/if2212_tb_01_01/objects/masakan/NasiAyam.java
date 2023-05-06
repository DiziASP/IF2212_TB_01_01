package if2212_tb_01_01.objects.masakan;

import if2212_tb_01_01.objects.bahanmakanan.BH_Ayam;
import if2212_tb_01_01.objects.bahanmakanan.BH_Nasi;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;

import java.util.ArrayList;
import java.util.List;

public class NasiAyam extends Masakan{

    public NasiAyam() {
        super("NASI AYAM", 16,20);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Nasi());
        bahan.add(new BH_Ayam());
        super.setBahan(bahan);
    }
}

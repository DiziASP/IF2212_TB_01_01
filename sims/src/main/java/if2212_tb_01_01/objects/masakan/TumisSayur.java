package if2212_tb_01_01.objects.masakan;

import if2212_tb_01_01.objects.bahanmakanan.BH_Bayam;
import if2212_tb_01_01.objects.bahanmakanan.BH_Wortel;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;

import java.util.ArrayList;
import java.util.List;

public class TumisSayur extends Masakan{

    public TumisSayur() {
        super("TUMIS SAYUR", 5,23);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Wortel());
        bahan.add(new BH_Bayam());
        super.setBahan(bahan);

    }
}
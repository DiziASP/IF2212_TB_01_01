package if2212_tb_01_01.items.masakan;

import if2212_tb_01_01.items.bahanmakanan.BH_Bayam;
import if2212_tb_01_01.items.bahanmakanan.BH_Wortel;
import if2212_tb_01_01.items.bahanmakanan.BahanMakanan;

import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class TumisSayur extends Masakan{

    public TumisSayur() {
        super("TUMIS SAYUR",  "/images/makanan/tumis_sayur.PNG", tileSize, tileSize, 5);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Wortel());
        bahan.add(new BH_Bayam());
        super.setBahan(bahan);

    }
}
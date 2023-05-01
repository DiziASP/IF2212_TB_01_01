package if2212_tb_01_01.items.masakan;

import if2212_tb_01_01.items.bahanmakanan.BH_Ayam;
import if2212_tb_01_01.items.bahanmakanan.BH_Nasi;
import if2212_tb_01_01.items.bahanmakanan.BahanMakanan;

import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class NasiAyam extends Masakan{

    public NasiAyam() {
        super("NASI AYAM", "/images/makanan/nasi_ayam.PNG", tileSize, tileSize, 16);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Nasi());
        bahan.add(new BH_Ayam());
        super.setBahan(bahan);
    }
}

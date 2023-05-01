package if2212_tb_01_01.items.masakan;

import if2212_tb_01_01.items.bahanmakanan.BH_Kacang;
import if2212_tb_01_01.items.bahanmakanan.BH_Susu;
import if2212_tb_01_01.items.bahanmakanan.BahanMakanan;

import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class SusuKacang extends Masakan{
    public SusuKacang() {
        super("SUSU KACANG","/images/makanan/susu_kacang.PNG", tileSize, tileSize, 5);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Susu());
        bahan.add(new BH_Kacang());
        super.setBahan(bahan);

    }
}

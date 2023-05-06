package if2212_tb_01_01.objects.masakan;

import if2212_tb_01_01.objects.bahanmakanan.BH_Kacang;
import if2212_tb_01_01.objects.bahanmakanan.BH_Susu;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;

import java.util.ArrayList;
import java.util.List;

public class SusuKacang extends Masakan{
    public SusuKacang() {
        super("SUSU KACANG", 5,22);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Susu());
        bahan.add(new BH_Kacang());
        super.setBahan(bahan);

    }
}

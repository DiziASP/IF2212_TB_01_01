package if2212_tb_01_01.objects.masakan;

import if2212_tb_01_01.objects.bahanmakanan.BH_Kentang;
import if2212_tb_01_01.objects.bahanmakanan.BH_Sapi;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;

import java.util.List;

import java.util.ArrayList;

public class Bistik extends Masakan{

    public Bistik() {
        super("BISTIK", 22,24);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Kentang());
        bahan.add(new BH_Sapi());
        super.setBahan(bahan);
    }
}
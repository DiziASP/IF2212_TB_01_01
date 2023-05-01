package if2212_tb_01_01.items.masakan;

import if2212_tb_01_01.items.bahanmakanan.BH_Kentang;
import if2212_tb_01_01.items.bahanmakanan.BH_Sapi;
import if2212_tb_01_01.items.bahanmakanan.BahanMakanan;

import java.util.List;

import java.util.ArrayList;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class Bistik extends Masakan{

    public Bistik() {
        super("BISTIK", "/images/makanan/bistik.PNG", tileSize, tileSize, 22);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Kentang());
        bahan.add(new BH_Sapi());
        super.setBahan(bahan);
    }
}
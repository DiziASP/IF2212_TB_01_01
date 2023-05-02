package if2212_tb_01_01.items.masakan;

import if2212_tb_01_01.items.bahanmakanan.*;

import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class NasiKari extends Masakan{

    public NasiKari() {
        super("NASI KARI", "/images/makanan/nasi_kari.PNG", tileSize, tileSize, 30);
        List<BahanMakanan>bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Nasi());
        bahan.add(new BH_Kentang());
        bahan.add(new BH_Wortel());
        bahan.add(new BH_Sapi());
        super.setBahan(bahan);
    }
}

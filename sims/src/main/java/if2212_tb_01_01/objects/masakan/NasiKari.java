package if2212_tb_01_01.objects.masakan;

import if2212_tb_01_01.objects.bahanmakanan.*;

import java.util.ArrayList;
import java.util.List;

public class NasiKari extends Masakan{

    public NasiKari() {
        super("NASI KARI",30,21);
        List<BahanMakanan>bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Nasi());
        bahan.add(new BH_Kentang());
        bahan.add(new BH_Wortel());
        bahan.add(new BH_Sapi());
        super.setBahan(bahan);
    }
}

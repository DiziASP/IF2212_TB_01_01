package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class Kanvas extends Furnitur {

    public Kanvas(){
        super("KANVAS", "FURNITUR",9);
        setNamaAksi("melukis");
        setHarga(50);
        setPanjang(1);
        setLebar(2);
        setPosisi(new Point(-1,-1));
    }
}

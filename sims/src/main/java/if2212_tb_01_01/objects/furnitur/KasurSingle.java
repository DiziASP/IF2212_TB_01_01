package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class KasurSingle extends Furnitur {

    public KasurSingle(){
        super("KASUR SINGLE", "FURNITUR",3);
        setNamaAksi("tidur");
        setHarga(50);
        setPanjang(4);
        setLebar(1);
        setPosisi(new Point(-1,-1));
    }
}

package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class KomporListrik extends Furnitur {

    public KomporListrik(){
        super("KOMPOR LISTRIK", "FURNITUR",5);
        setNamaAksi("masak");
        setHarga(200);
        setPanjang(1);
        setLebar(1);
        setPosisi(new Point(-1,-1));
    }
}

package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class KomporGas extends Furnitur {

    public KomporGas(){
        super("KOMPOR GAS", "FURNITUR",4);
        setNamaAksi("masak");
        setHarga(100);
        setPanjang(2);
        setLebar(1);
        setPosisi(new Point(-1,-1));
    }
}

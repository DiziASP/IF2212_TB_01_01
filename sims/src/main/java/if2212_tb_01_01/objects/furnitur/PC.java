package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class PC extends Furnitur{

    public PC(){
        super("PC", "FURNITUR",7);
        setNamaAksi("proyekan");
        setHarga(50);
        setPanjang(1);
        setLebar(1);
        setPosisi(new Point(-1,-1));
    }
}

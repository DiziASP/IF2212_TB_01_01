package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class Piano extends Furnitur{

    public Piano(){
        super("PIANO", "FURNITUR",8);
        setNamaAksi("Main musik");
        setHarga(50);
        setPanjang(2);
        setLebar(2);
        setPosisi(new Point(-1,-1));
    }
}

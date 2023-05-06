package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class Toilet extends Furnitur  {

    public Toilet(){
        super("TOILET", "FURNITUR",10);
        setNamaAksi("buang air");
        setHarga(50);
        setPanjang(1);
        setLebar(1);
        setPosisi(new Point(-1,-1));
    }
}

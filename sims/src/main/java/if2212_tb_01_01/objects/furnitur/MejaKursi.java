package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class MejaKursi extends Furnitur  {

    public MejaKursi(){
        super("MEJA DAN KURSI", "FURNITUR",6);
        setNamaAksi("makan");
        setHarga(50);
        setPanjang(3);
        setLebar(3);
        setPosisi(new Point(-1,-1));
    }

}

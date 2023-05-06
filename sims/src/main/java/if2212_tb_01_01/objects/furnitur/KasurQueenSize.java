package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;

public class KasurQueenSize extends Furnitur  {

    public KasurQueenSize(){
        super("KASUR QUEEN SIZE", "FURNITUR",2);
        setNamaAksi("tidur");
        setHarga(100);
        setPanjang(4);
        setLebar(2);
        setPosisi(new Point(-1,-1));

    }
}

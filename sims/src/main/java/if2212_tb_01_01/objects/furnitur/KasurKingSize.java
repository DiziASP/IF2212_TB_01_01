package if2212_tb_01_01.objects.furnitur;
import if2212_tb_01_01.utils.Point;

public class KasurKingSize extends Furnitur {
    public KasurKingSize(){
        super("KASUR KING SIZE", "FURNITUR",1);
        setNamaAksi("tidur");
        setHarga(150);
        setPanjang(5);
        setLebar(2);
        setPosisi(new Point(-1,-1));
    }
}

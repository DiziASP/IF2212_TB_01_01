package if2212_tb_01_01.objects.furnitur;
import if2212_tb_01_01.utils.Point;

// import static if2212_tb_01_01.utils.Constant.*;

public class Jam extends Furnitur {
    public Jam(){
        super("JAM", "FURNITUR",0);
        setNamaAksi("melihat waktu");
        setHarga(10);
        setPanjang(1);
        setLebar(1);
        setPosisi(new Point(-1,-1));
    }
}

package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class MejaKursi extends Furnitur  {

    public MejaKursi(){
        super("MEJA DAN KURSI", "FURNITUR", "/images/furnitur/meja_dan_kursi.png", 3*tileSize, 3*tileSize);
        setNamaAksi("makan");
        setHarga(50);
        setPanjang(3);
        setLebar(3);
    }

    public static MejaKursi buildMejaKursi(Integer x, Integer y){
        MejaKursi newMejaKursi = new MejaKursi();
        newMejaKursi.setPosisi(new Point(x, y));
        newMejaKursi.setInteractionArea(new Rectangle(((newMejaKursi.getPosisi().x + 1) *tileSize) + roomX, ((newMejaKursi.getPosisi().y + 1) * tileSize) + roomY, 3*tileSize, 3*tileSize));
        return newMejaKursi;
    }

}

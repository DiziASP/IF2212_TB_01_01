package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KomporListrik extends Furnitur {

    public KomporListrik(){
        super("KOMPOR LISTRIK", "FURNITUR", "/images/furnitur/kompor_listrik.png", 2*tileSize, tileSize);
        setNamaAksi("MASAK");
        setHarga(200);
        setPanjang(1);
        setLebar(2);
    }

    public static KomporListrik buildKomporListrik(Integer x, Integer y){
        KomporListrik newKomporListrik = new KomporListrik();
        newKomporListrik.setPosisi(new Point(x, y));
        newKomporListrik.setInteractionArea(new Rectangle(((newKomporListrik.getPosisi().x + 1) *tileSize) + roomX, ((newKomporListrik.getPosisi().y + 1) * tileSize) + roomY, 2*tileSize, tileSize));
        return newKomporListrik;
    }
}

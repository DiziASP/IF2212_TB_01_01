package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KasurSingle extends Furnitur  {

    public KasurSingle(){
        super("KASUR SINGLE", "FURNITUR", "/images/furnitur/kasur_single.png", tileSize, 4*tileSize);
        setNamaAksi("TIDUR");
        setHarga(50);
        setPanjang(4);
        setLebar(1);
    }

    public static KasurSingle buildKasurSingle(Integer x, Integer y){
        KasurSingle newKasurSingle = new KasurSingle();
        newKasurSingle.setPosisi(new Point(x, y));
        newKasurSingle.setInteractionArea(new Rectangle(((newKasurSingle.getPosisi().x + 1) *tileSize) + roomX, ((newKasurSingle.getPosisi().y + 1) * tileSize) + roomY, tileSize, 4*tileSize));
        return newKasurSingle;
    }
}

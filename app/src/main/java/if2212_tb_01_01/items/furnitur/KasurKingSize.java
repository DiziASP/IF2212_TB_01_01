package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KasurKingSize extends Furnitur {
    public KasurKingSize(){
        super("KASUR KING SIZE", "FURNITUR", "/images/furnitur/kasur_king_size.png", 4*tileSize, 3*tileSize);
        setNamaAksi("tidur");
        setHarga(100);
        setPanjang(3);
        setLebar(4);
    }

    public static KasurKingSize buildKasurKingSize(Integer x, Integer y){
        KasurKingSize newKasurKingSize = new KasurKingSize();
        newKasurKingSize.setPosisi(new Point(x, y));
        newKasurKingSize.setInteractionArea(new Rectangle(((newKasurKingSize.getPosisi().x + 1) *tileSize) + roomX, ((newKasurKingSize.getPosisi().y + 1) * tileSize) + roomY, 4*tileSize, 3*tileSize));
        return newKasurKingSize;
    }
}

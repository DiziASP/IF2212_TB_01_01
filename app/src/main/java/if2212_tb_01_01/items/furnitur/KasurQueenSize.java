package if2212_tb_01_01.items.furnitur;


import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KasurQueenSize extends Furnitur  {

    public KasurQueenSize(){
        super("KASUR QUEEN SIZE", "FURNITUR", "/images/furnitur/kasur_queen_size.png", 4*tileSize, 2*tileSize);
        setNamaAksi("tidur");
        setHarga(100);
        // setVertikal(isVertikal);
        setPanjang(2);
        setLebar(4);
    }

    public static KasurQueenSize buildKasurQueenSize(Integer x, Integer y){
        KasurQueenSize newKasurQueenSize = new KasurQueenSize();
        newKasurQueenSize.setPosisi(new Point(x, y));
        newKasurQueenSize.setInteractionArea(new Rectangle(((newKasurQueenSize.getPosisi().x + 1) *tileSize) + roomX, ((newKasurQueenSize.getPosisi().y + 1) * tileSize) + roomY, 4*tileSize, 2*tileSize));
        return newKasurQueenSize;
    }
}

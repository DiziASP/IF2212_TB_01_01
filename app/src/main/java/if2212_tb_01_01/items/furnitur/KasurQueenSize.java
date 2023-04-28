package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KasurQueenSize extends Furnitur  {

    public KasurQueenSize(Point posisi, boolean isVertikal){
        super("KASUR QUEEN SIZE", "FURNITUR", "/images/furnitur/kasur_queen_size.png", 4*tileSize, 2*tileSize);
        setNamaAksi("TIDUR");
        setHarga(100);
        // setVertikal(isVertikal);
        setPanjang(2);
        setLebar(4);
        setPosisi(posisi);

        int roomX = (screenWidth - tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }

}

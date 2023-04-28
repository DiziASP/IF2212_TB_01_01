package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KasurKingSize extends Furnitur {
    public KasurKingSize(Point posisi, boolean isVertikal){
        super("KASUR KING SIZE", "FURNITUR", "/images/furnitur/kasur_king_size.png", 4*tileSize, 3*tileSize);
        setNamaAksi("TIDUR");
        setHarga(100);
        setPanjang(3);
        setLebar(4);
        setPosisi(posisi);
        // setVertikal(isVertikal);

        int roomX = (screenWidth- tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }

}

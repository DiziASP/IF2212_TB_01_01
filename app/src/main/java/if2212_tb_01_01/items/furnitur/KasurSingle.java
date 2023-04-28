package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KasurSingle extends Furnitur  {

    public KasurSingle(Point posisi, boolean isVertikal){
        super("KASUR SINGLE", "FURNITUR", "/images/furnitur/kasur_single.png", tileSize, 4*tileSize);
        setNamaAksi("TIDUR");
        setHarga(50);
        setPanjang(4);
        setLebar(1);
        // setVertikal(isVertikal);
        setPosisi(posisi);

        int roomX = (screenWidth - tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }
}

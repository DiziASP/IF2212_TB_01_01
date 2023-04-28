package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;
public class BakMandi extends Furnitur{
    public BakMandi(Point posisi, boolean isVertikal){
        super("BAK MANDI", "FURNITUR", "/images/furnitur/bak_mandi.png", 2*tileSize, 1*tileSize);
        setNamaAksi("MANDI");
        setHarga(50);
        setPanjang(1);
        setLebar(2);
        setPosisi(posisi);
        // setVertikal(isVertikal);

        int roomX = (screenWidth- tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;

        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY,
        //         getLebar()*tileSize, getPanjang()*tileSize));
    }
}

package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;
public class Piano extends Furnitur{

    public Piano(Point posisi, boolean isVertikal){
        super("PIANO", "FURNITUR", "/images/furnitur/piano.png", 2*tileSize, 2*tileSize);
        setNamaAksi("BERMAIN");
        setHarga(50);
        setPanjang(2);
        setLebar(2);
        setPosisi(posisi);
        // setVertikal(isVertikal);

        int roomX = (screenWidth- tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }
}

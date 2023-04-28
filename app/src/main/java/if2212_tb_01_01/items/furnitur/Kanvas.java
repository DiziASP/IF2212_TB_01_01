package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class Kanvas extends Furnitur {

    public Kanvas(Point posisi, boolean isVertikal){
        super("KANVAS", "FURNITUR", "/images/furnitur/kanvas.png", 2*tileSize, 1*tileSize);
        setNamaAksi("MELUKIS");
        setHarga(50);
        setPanjang(1);
        setLebar(2);

        int roomX = (screenWidth- tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }
}

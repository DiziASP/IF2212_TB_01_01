package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KomporListrik extends Furnitur {

    public KomporListrik(Point posisi, boolean isVertikal){
        super("KOMPOR LISTRIK", "FURNITUR", "/images/furnitur/kompor_listrik.png", 2*tileSize, tileSize);
        setNamaAksi("MASAK");
        setHarga(200);
        setPanjang(1);
        setLebar(2);
        setPosisi(posisi);
        //setVertikal(isVertikal);

        int roomX = (screenWidth - tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }
}

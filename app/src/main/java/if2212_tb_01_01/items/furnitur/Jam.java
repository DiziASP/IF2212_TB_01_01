package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class Jam extends Furnitur {
    public Jam(Point posisi, boolean isVertikal){
        super("JAM", "FURNITUR", "/images/furnitur/jam.png", tileSize, tileSize);

        setNamaAksi("MELIHAT WAKTU");
        setHarga(10);
        setPanjang(1);
        setLebar(1);
        setPosisi(posisi);
        // setVertikal(isVertikal);

        int roomX = (screenWidth - tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }

}

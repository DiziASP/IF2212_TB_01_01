package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class MejaKursi extends Furnitur  {

    public MejaKursi(Point posisi, boolean isVertikal){
        super("MEJA DAN KURSI", "FURNITUR", "/images/furnitur/meja_dan_kursi.png", 3*tileSize, 3*tileSize);
        setNamaAksi("MAKAN");
        setHarga(50);
        setPanjang(3);
        setLebar(3);
        setPosisi(posisi);
        // setVertikal(isVertikal);

        int roomX = (screenWidth - tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }

}

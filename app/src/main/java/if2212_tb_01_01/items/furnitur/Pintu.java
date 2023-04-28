package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;
public class Pintu extends Furnitur{
    public Pintu(Point posisi, boolean isVertikal){
        super("PINTU", "FURNITUR", "/images/furnitur/pintu.png", tileSize, 2*tileSize);
        setNamaAksi("MASUK");
        setHarga(0);
        setPanjang(2);
        setLebar(1);
        setPosisi(posisi);
        //setVertikal(isVertikal);

        int roomX = (screenWidth- tileSize * 14) / 2;
        int roomY = (screenHeight - tileSize * 11) / 2;
        // setInteractionArea(new Rectangle((getPosisi().x + 1) * tileSize + roomX, (getPosisi().y + 1) * tileSize + roomY, getLebar()*tileSize, getPanjang()*tileSize));
    }
}

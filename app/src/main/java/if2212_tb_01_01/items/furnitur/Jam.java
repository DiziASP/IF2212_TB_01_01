package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class Jam extends Furnitur {
    public Jam(){
        super("JAM", "FURNITUR", "/images/furnitur/jam.png", tileSize, tileSize);

        setNamaAksi("MELIHAT WAKTU");
        setHarga(10);
        setPanjang(1);
        setLebar(1);
        // setVertikal(isVertikal);
    }

    public static Jam buildJam(Integer x, Integer y){
        Jam newJam = new Jam();
            newJam.setPosisi(new Point(x, y));
            newJam.setInteractionArea(new Rectangle(((newJam.getPosisi().x + 1) *tileSize) + roomX, ((newJam.getPosisi().y + 1) * tileSize) + roomY, tileSize, tileSize));
            return newJam;
    }

}

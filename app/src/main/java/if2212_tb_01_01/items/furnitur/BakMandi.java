package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;
public class BakMandi extends Furnitur{
    public BakMandi(){
        super("BAK MANDI", "FURNITUR", "/images/furnitur/bak_mandi.png", 2*tileSize, 1*tileSize);
        setNamaAksi("MANDI");
        setHarga(50);
        setPanjang(1);
        setLebar(2);
    }

    public static BakMandi buildBakMandi(Integer x, Integer y){
        BakMandi newBakMandi = new BakMandi();
        newBakMandi.setPosisi(new Point(x, y));
        newBakMandi.setInteractionArea(new Rectangle(((newBakMandi.getPosisi().x + 1) *tileSize) + roomX, ((newBakMandi.getPosisi().y + 1) * tileSize) + roomY, 2*tileSize, tileSize));
        return newBakMandi;
    }
}

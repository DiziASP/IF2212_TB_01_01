package if2212_tb_01_01.items.furnitur;


import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class Toilet extends Furnitur  {

    public Toilet(){
        super("TOILET", "FURNITUR", "/images/furnitur/toilet.png", tileSize, tileSize);
        setNamaAksi("buang air");
        setHarga(50);
        setPanjang(1);
        setLebar(1);
    }

    public static Toilet buildToilet(Integer x, Integer y){
        Toilet newToilet = new Toilet();
        newToilet.setPosisi(new Point(x, y));
        newToilet.setInteractionArea(new Rectangle(((newToilet.getPosisi().x + 1) *tileSize) + roomX, ((newToilet.getPosisi().y + 1) * tileSize) + roomY, tileSize, tileSize));
        return newToilet;
    }
}

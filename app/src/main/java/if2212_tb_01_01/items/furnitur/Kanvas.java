package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class Kanvas extends Furnitur {

    public Kanvas(){
        super("KANVAS", "FURNITUR", "/images/furnitur/kanvas.png", 2*tileSize, 1*tileSize);
        setNamaAksi("melukis");
        setHarga(50);
        setPanjang(1);
        setLebar(2);
    }

    public static Kanvas buildKanvas(Integer x, Integer y){
        Kanvas newKanvas = new Kanvas();
        newKanvas.setPosisi(new Point(x, y));
        newKanvas.setInteractionArea(new Rectangle(((newKanvas.getPosisi().x + 1) *tileSize) + roomX, ((newKanvas.getPosisi().y + 1) * tileSize) + roomY, 2*tileSize, tileSize));
        return newKanvas;
    }
}

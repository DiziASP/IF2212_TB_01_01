package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class PC extends Furnitur{

    public PC(){
        super("PC", "FURNITUR", "/images/furnitur/pc.png", tileSize, tileSize);
        setNamaAksi("MENGGUNAKAN");
        setHarga(50);
        setPanjang(1);
        setLebar(1);
    }

    public static PC buildPC(Integer x, Integer y){
        PC newPC = new PC();
        newPC.setPosisi(new Point(x, y));
        newPC.setInteractionArea(new Rectangle(((newPC.getPosisi().x + 1) *tileSize) + roomX, ((newPC.getPosisi().y + 1) * tileSize) + roomY, tileSize, tileSize));
        return newPC;
    }
}

package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;
public class Pintu extends Furnitur{
    public Pintu(){
        super("PINTU", "FURNITUR", "/images/furnitur/pintu.png", tileSize, 2*tileSize);
        setNamaAksi("MASUK");
        setHarga(0);
        setPanjang(2);
        setLebar(1);
    }

    public static Pintu buildPintu(Integer x, Integer y){
        Pintu newPintu = new Pintu();
        newPintu.setPosisi(new Point(x, y));
        newPintu.setInteractionArea(new Rectangle(((newPintu.getPosisi().x + 1) *tileSize) + roomX, ((newPintu.getPosisi().y + 1) * tileSize) + roomY, tileSize, 2*tileSize));
        return newPintu;
    }
}

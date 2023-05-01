package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;
public class Piano extends Furnitur{

    public Piano(){
        super("PIANO", "FURNITUR", "/images/furnitur/piano.png", 2*tileSize, 2*tileSize);
        setNamaAksi("Main musik");
        setHarga(50);
        setPanjang(2);
        setLebar(2);
    }

    public static Piano buildPiano(Integer x, Integer y){
        Piano newPiano = new Piano();
        newPiano.setPosisi(new Point(x, y));
        newPiano.setInteractionArea(new Rectangle(((newPiano.getPosisi().x + 1) *tileSize) + roomX, ((newPiano.getPosisi().y + 1) * tileSize) + roomY, 2*tileSize, 2*tileSize));
        return newPiano;
    }
}

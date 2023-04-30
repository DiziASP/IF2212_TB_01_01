package if2212_tb_01_01.items.furnitur;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public class KomporGas extends Furnitur {

    public KomporGas(){
        super("KOMPOR GAS", "FURNITUR", "/images/furnitur/kompor_gas.png", 2*tileSize, tileSize);
        setNamaAksi("MASAK");
        setHarga(200);
        setPanjang(1);
        setLebar(2);
    }
    public static KomporGas buildKomporGas(Integer x, Integer y){
        KomporGas newKomporGas = new KomporGas();
        newKomporGas.setPosisi(new Point(x, y));
        newKomporGas.setInteractionArea(new Rectangle(((newKomporGas.getPosisi().x + 1) *tileSize) + roomX, ((newKomporGas.getPosisi().y + 1) * tileSize) + roomY, 2*tileSize, tileSize));
        return newKomporGas;
    }
}

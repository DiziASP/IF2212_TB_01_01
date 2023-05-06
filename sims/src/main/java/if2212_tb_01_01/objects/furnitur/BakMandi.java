package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.utils.Point;


public class BakMandi extends Furnitur{
    public BakMandi(){
        super("BAK MANDI", "FURNITUR",11);
        setNamaAksi("mandi");
        setHarga(50);
        setPanjang(1);
        setLebar(2);
        setPosisi(new Point(-1,-1));
    }

    // public static BakMandi buildBakMandi(Integer x, Integer y){
    //     BakMandi newBakMandi = new BakMandi();
    //     newBakMandi.setPosisi(new Point(x, y));
    //     newBakMandi.setInteractionArea(new Rectangle(((newBakMandi.getPosisi().x + 1) *tileSize) + roomX, ((newBakMandi.getPosisi().y + 1) * tileSize) + roomY, 2*tileSize, tileSize));
    //     return newBakMandi;
    // }
}

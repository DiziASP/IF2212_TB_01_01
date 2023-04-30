package if2212_tb_01_01.items.masakan;

import if2212_tb_01_01.items.Item;
import if2212_tb_01_01.items.bahanmakanan.BH_Kacang;
import if2212_tb_01_01.items.bahanmakanan.BH_Susu;
import if2212_tb_01_01.items.bahanmakanan.BahanMakanan;

import java.util.ArrayList;
import java.util.List;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class SusuKacang extends Masakan{
    // private List<BahanMakanan> bahan;
    // private int kekenyangan;

    public SusuKacang() {
        super("SUSU KACANG","/images/makanan/susu_kacang.PNG", tileSize, tileSize, 5);
        List<BahanMakanan> bahan = new ArrayList<BahanMakanan>();
        bahan.add(new BH_Susu());
        bahan.add(new BH_Kacang());
        super.setBahan(bahan);

    }
    // public List<BahanMakanan> getBahan(){
    //     return bahan;
    // }

    // // Setter method for bahan
    // public void setBahan(List<BahanMakanan> bahan){
    //     this.bahan = bahan;
    // }

    // // Getter method for kekenyangan
    // public int getKekenyangan(){
    //     return kekenyangan;
    // }

    // // Setter method for kekenyangan
    // public void setKekenyangan(int kekenyangan){
    //     this.kekenyangan = kekenyangan;
    // }

    // //method untuk print list masakan yang dapat dibuat

    // //method untuk print list bahan yang diperlukan untuk membuat masakan


    // // Method for get info masakan
    // public String getInfo(){
    //     String info = "Nama Masakan : " + this.getNama() + "\n" +
    //             "Kekenyangan : " + this.kekenyangan + "\n" +
    //             "Bahan : \n";
    //     for (BahanMakanan bahanMakanan : bahan) {
    //         info += bahanMakanan.getNamaBahanMakanan();
    //     }
    //     return info;
    // }

    // public void printInfo(){
    //     System.out.println(getInfo());
    // }
}

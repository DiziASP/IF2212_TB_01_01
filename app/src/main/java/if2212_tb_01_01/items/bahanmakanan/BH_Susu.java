package if2212_tb_01_01.items.bahanmakanan;

import if2212_tb_01_01.items.Item;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class BH_Susu extends BahanMakanan {
    // private String namaBahanMakanan;
    // private Integer harga;
    // private Integer kekenyangan;

    public BH_Susu() {
        super("SUSU", "MAKANAN", "/images/makanan/susu.PNG", tileSize, tileSize, 2, 1);
    }

    // public String getNamaBahanMakanan() {
    //     return namaBahanMakanan;
    // }

    // public Integer getHarga() {
    //     return harga;
    // }

    // public Integer getKekenyangan() {
    //     return kekenyangan;
    // }

    // public void setHarga(int harga) {
    //     this.harga = harga;
    // }

    // public void setKekenyangan(int kekenyangan) {
    //     this.kekenyangan = kekenyangan;
    // }
    // public String getInfo(){
    //     return "Nama Bahan Makanan : " + this.namaBahanMakanan + "\n" +
    //             "Harga : " + this.harga + "\n" +
    //             "Kekenyangan : " + this.kekenyangan + "\n";
    // }

    // public void printInfo(){
    //     System.out.println(getInfo());
    // }
}

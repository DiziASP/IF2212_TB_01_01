package if2212_tb_01_01.items.bahanmakanan;

import if2212_tb_01_01.items.Item;

import static if2212_tb_01_01.utils.Constant.tileSize;

public class BH_Nasi extends BahanMakanan {
    // private String namaBahanMakanan;
    // private Integer harga;
    // private Integer kekenyangan;

    public BH_Nasi() {
        super("NASI", "MAKANAN", "/images/makanan/nasi.PNG", tileSize, tileSize, 5, 5);
        // this.namaBahanMakanan = "NASI";
        // this.harga = 5;
        // this.kekenyangan = 5;
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

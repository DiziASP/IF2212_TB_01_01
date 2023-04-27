package if2212_tb_01_01.objects.bahanmakanan;

import if2212_tb_01_01.objects.Objek;

public class Ayam extends Objek implements BahanMakanan {

    private String namaBahanMakanan;
    private Integer harga;
    private Integer kekenyangan;

    public Ayam() {
        super("AYAM", "MAKANAN");
        this.namaBahanMakanan = "AYAM";
        this.harga = 10;
        this.kekenyangan = 8;
    }

    public String getNamaBahanMakanan() {
        return namaBahanMakanan;
    }

    public Integer getHarga() {
        return harga;
    }

    public Integer getKekenyangan() {
        return kekenyangan;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }    
    public String getInfo(){
        return "Nama Bahan Makanan : " + this.namaBahanMakanan + "\n" +
                "Harga : " + this.harga + "\n" +
                "Kekenyangan : " + this.kekenyangan + "\n";
    }

    public void printInfo(){
        System.out.println(getInfo());
    }
}

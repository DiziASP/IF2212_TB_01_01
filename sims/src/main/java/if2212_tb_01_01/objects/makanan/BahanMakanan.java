package if2212_tb_01_01.objects.makanan;

public class BahanMakanan {
    private String namaBahanMakanan;
    private Integer harga;
    private Integer kekenyangan;

    public BahanMakanan(String namaBahanMakanan, Integer harga, Integer kekenyangan) {
        this.namaBahanMakanan = namaBahanMakanan;
        this.harga = harga;
        this.kekenyangan = kekenyangan;
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

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public void setKekenyangan(Integer kekenyangan) {
        this.kekenyangan = kekenyangan;
    }
}

package if2212_tb_01_01.objects;

import java.util.List;

public class Masakan extends Objek{
    private List<Makanan> bahan;
    private Integer kekenyangan;

    // Constructor
    public Masakan(String nama, List<Makanan> bahan, Integer kekenyangan){
        super(nama, "makanan");
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;
    }

    // Getter method for bahan
    public List<Makanan> getBahan() {
        return this.bahan;
    }

    // Setter method for bahan
    public void setBahan(List<Makanan> bahan) {
        this.bahan = bahan;
    }

    // Getter method for kekenyangan
    public Integer getKekenyangan() {
        return this.kekenyangan;
    }

    // Setter method for kekenyangan
    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public String getInfo(){
        String ret = String.format("Nama: %s\nKategori: Makanan\nHarga: %d\nKekenyangan: %d", this.getNama(), bahan.toString(), kekenyangan);
        return(ret);
    }
}
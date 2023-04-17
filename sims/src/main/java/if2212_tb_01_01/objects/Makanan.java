package if2212_tb_01_01.objects;

public class Makanan extends Objek{
    private Integer harga;
    private Integer kekenyangan;

    // Constructor
    public Makanan(String nama, Integer harga, Integer kekenyangan){
        super(nama, "makanan");
        this.harga = harga;
        this.kekenyangan = kekenyangan;
    }

    // Getter method for harga
    public Integer getHarga() {
        return this.harga;
    }

    // Setter method for harga
    public void setHarga(int harga) {
        this.harga = harga;
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
        String ret = String.format("Nama: %s\nKategori: makananan\nHarga: %d\nKekenyangan: %d", this.getNama(), harga, kekenyangan);
        return(ret);
    }
}
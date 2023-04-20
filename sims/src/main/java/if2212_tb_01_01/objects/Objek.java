package if2212_tb_01_01.objects;

public abstract class Objek {
    private String nama;
    private String kategori;

    // Constructor
    public Objek(String nama, String kategori){
        this.nama = nama;
        this.kategori = kategori;
    }

    // Getter method for nama
    public String getNama() {
        return this.nama;
    }

    // Setter method for nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter method for kategori
    public String getKategori() {
        return this.kategori;
    }

    // Setter method for kategori
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public abstract String getInfo();
}

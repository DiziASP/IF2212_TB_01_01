package if2212_tb_01_01.objects;

public class Objek {
    private String nama;
    private String kategori;
    public Objek(String nama, String kategori) {
        this.nama = nama;
        this.kategori = kategori;
    }
    public String getNama() {
        return nama;
    }
    public String getKategori() {
        return kategori;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}

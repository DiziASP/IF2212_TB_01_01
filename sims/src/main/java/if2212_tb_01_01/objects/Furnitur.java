package if2212_tb_01_01.objects;
import if2212_tb_01_01.utils.Point;

public class Furnitur extends Objek{
    private String aksi;
    private Integer harga;
    private Integer panjang;
    private Integer lebar;
    private Point posisi = new Point(-1, -1);
    private boolean isVertikal = true;

    // Constructor
    public Furnitur(String nama, String aksi, Integer harga, Integer panjang, Integer lebar){
        super(nama, "peralatan");
        this.aksi = aksi;
        this.harga = harga;        
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public Furnitur(String nama, String aksi, Integer harga, Integer panjang, Integer lebar, Point posisi, boolean isVertikal){
        super(nama, "peralatan");
        this.aksi = aksi;
        this.harga = harga;        
        this.panjang = panjang;
        this.lebar = lebar;
        this.posisi = posisi;
        this.isVertikal = isVertikal;
    }

    public String getAksi(){
        return this.aksi;
    }

    public void setAksi(String aksi) {
        this.aksi = aksi;
    }

    // Getter method for harga
    public Integer getHarga() {
        return this.harga;
    }

    // Setter method for harga
    public void setHarga(int harga) {
        this.harga = harga;
    }

    // Getter method for panjang
    public Integer getPanjang() {
        return this.panjang;
    }

    // Setter method for panjang
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    // Getter method for lebar
    public Integer getLebar() {
        return this.lebar;
    }

    // Setter method for lebar
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    // Getter method for posisi
    public Point getPosisi() {
        return this.posisi;
    }

    // Setter method for posisi
    public void setPosisi(Point posisi) {
        this.posisi = posisi;
    }

    // Getter method for isVertikal
    public boolean isVertikal() {
        return this.isVertikal;
    }

    // Setter method for isVertikal
    public void setVertikal(boolean isVertikal) {
        this.isVertikal = isVertikal;
    }

    public String getInfo(){
        String ret = String.format("Nama: %s\nKategori: Peralatan\nAksi: %s\nHarga: %d\nDimensi: %dx%d", this.getNama(), aksi, harga, panjang, lebar);
        return(ret);
    }
}
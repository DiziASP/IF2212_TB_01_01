package if2212_tb_01_01.objects.furnitur;
import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.utils.Point;

public class KasurSingle extends Objek implements Furnitur {
    private String namaAksi;
    private String jenis;
    private int harga;
    private int panjang;
    private int lebar;
    private Point posisi;
    private boolean isVertikal;
    
    public KasurSingle(String jenis, Point posisi, boolean isVertikal){
        super("KASUR SINGLE", "FURNITUR");
        this.namaAksi = "TIDUR";
        this.harga = 50;
        this.panjang = 4;
        this.lebar = 1;
        this.posisi = posisi;
        this.isVertikal = isVertikal;
    }

    public KasurSingle() {
        super("KASUR SINGLE", "FURNITUR");
        this.harga = 50;
    }

    //Getter method for aksi
    public String getNamaAksi(){
        return namaAksi;
    }

    //Setter method for aksi
    public void setNamaAksi(String aksi){
        this.namaAksi = aksi;
    }

    // Getter method for harga
    public int getHarga(){
        return harga;
    }
    // Setter method for harga
    public void setHarga(int harga){
        this.harga = harga;
    }

    // Getter method for panjang
    public Integer getPanjang(){
        return panjang;
    }

    // Setter method for panjang
    public void setPanjang(int panjang){
        this.panjang = panjang;
    }
    // Getter method for lebar
    public Integer getLebar(){
        return lebar;
    }

    // Setter method for lebar
    public void setLebar(int lebar){
        this.lebar = lebar;
    }

    // Getter method for posisi
    public Point getPosisi(){
        return posisi;
    }

    // Setter method for posisi
    public void setPosisi(Point posisi){
        this.posisi = posisi;
    }

    // Getter method for isVertikal
    public boolean isVertikal(){
        return isVertikal;
    }

    // Setter method for isVertikal
    public void setVertikal(boolean isVertikal){
        this.isVertikal = isVertikal;
    }


    public String getInfo(){
        return "Nama: " + this.getNama() + "\n" +
                "Harga: " + this.harga + "\n" +
                "Panjang: " + this.panjang + "\n" +
                "Lebar: " + this.lebar + "\n" +
                "Posisi: " + this.posisi + "\n" +
                "Vertikal: " + this.isVertikal + "\n";
    }
    public void printInfo(){
        System.out.println(getInfo());
    }

}
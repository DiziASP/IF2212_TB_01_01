package if2212_tb_01_01.objects.furnitur;
import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.utils.Point;

public class Kompor extends Objek implements Furnitur {
    private String namaAksi;
    private String jenis;
    private int harga;
    private int panjang;
    private int lebar;
    private Point posisi;
    private boolean isVertikal;
    
    public Kompor(String jenis, Point posisi, boolean isVertikal){
        super("KOMPOR", "FURNITUR");
        this.namaAksi = "MASAK";
        if (jenis.equals("GAS")){
            this.harga = 100;
            this.panjang = 2;
            this.lebar = 1;
        } else if (jenis.equals("LISTRIK")){
            this.harga = 200;
            this.panjang = 1;
            this.lebar = 2;
        }
        this.posisi = posisi;
        this.isVertikal = isVertikal;
    }

    public Kompor(String jenis) {
        super("KOMPOR", "FURNITUR");
        if (jenis.equals("GAS")){
            this.harga = 100;
        } else if (jenis.equals("LISTRIK")){
            this.harga = 200;
        }
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
        return "Nama: " + this.getNama() + " " + this.jenis + "\n" +
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
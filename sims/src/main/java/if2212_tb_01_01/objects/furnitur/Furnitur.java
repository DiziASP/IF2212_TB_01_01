package if2212_tb_01_01.objects.furnitur;

import if2212_tb_01_01.objects.Objek;

// import java.awt.*;

import if2212_tb_01_01.utils.Point;
import java.util.List;
import java.util.ArrayList;

// import static if2212_tb_01_01.utils.Constant.*;

public abstract class Furnitur extends Objek {

    private Point posisi;
    // private List<Point> area;
    private String namaAksi;
    private int harga;
    private int panjang;
    private int lebar;
    private int amountPut;

    public Furnitur(String nama, String kategori, int indeks){
        super(nama, kategori, indeks);
        // area = new ArrayList<>();
    }

    // public Furnitur(Furnitur furnitur){
    //     super(furnitur.getNama(), furnitur.getKategori());
    // }


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
    public String getInfo(){
        return "Nama: " + this.getNama() + "\n" +
                "Jumlah: " + this.getAmount() + "\n" +
                "Sisa di Inventory: " + (this.getAmount()-this.getAmountPut()) + "\n" + 
                "Harga: " + this.harga + "\n" +
                "Panjang: " + this.panjang + "\n" +
                "Lebar: " + this.lebar + "\n" ;
    }
    public void printInfo(){
        System.out.println(getInfo());
    }

    // Getter method for posisi
    public Point getPosisi() {
        return posisi;
    }

    // Setter method for posisi
    public void setPosisi(Point posisi) {
        this.posisi = posisi;
    }

    public int getAmountPut(){
        return this.amountPut;
    }

    public void incAmountPut(){
        this.amountPut++;
    }

    public void decAmountPut(){
        this.amountPut--;
    }

    public int getSisa() {
        return getAmount()-amountPut;
    }
}

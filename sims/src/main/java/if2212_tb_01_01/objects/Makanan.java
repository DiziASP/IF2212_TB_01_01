package if2212_tb_01_01.objects;

import java.util.List;
import java.util.ArrayList;

public class Makanan extends Objek {
    private Integer harga;
    private Integer kekenyangan;
    private static List<Makanan> listMakanan = makeListMakanan();

    // Constructor
    public Makanan(String nama, Integer harga, Integer kekenyangan) {
        super(nama, "makanan");
        this.harga = harga;
        this.kekenyangan = kekenyangan;
    }

    // Constructor Makanan
    public Makanan(String nama) {
        super(nama, "makanan");
        if (nama == "NASI") {
            harga = 5;
            kekenyangan = 5;
        } else if (nama == "KENTANG") {
            harga = 3;
            kekenyangan = 4;
        } else if (nama == "AYAM") {
            harga = 10;
            kekenyangan = 8;
        } else if (nama == "SAPI") {
            harga = 12;
            kekenyangan = 15;
        } else if (nama == "WORTEL") {
            harga = 3;
            kekenyangan = 2;
        } else if (nama == "BAYAM") {
            harga = 3;
            kekenyangan = 2;
        } else if (nama == "KACANG") {
            harga = 2;
            kekenyangan = 2;
        } else if (nama == "SUSU") {
            harga = 2;
            kekenyangan = 1;
        }
    }

    // Membuat list Makanan
    private static List<Makanan> makeListMakanan() {
        List<Makanan> makanan = new ArrayList<>();
        makanan.add(new Makanan("NASI"));
        makanan.add(new Makanan("KENTANG"));
        makanan.add(new Makanan("AYAM"));
        makanan.add(new Makanan("SAPI"));
        makanan.add(new Makanan("WORTEL"));
        makanan.add(new Makanan("BAYAM"));
        makanan.add(new Makanan("KACANG"));
        makanan.add(new Makanan("SUSU"));
        return makanan;
    }

    // getter method for listMakanan
    public static List<Makanan> getListMakanan() {
        return listMakanan;
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

    // Method for print list makanan yang dapat dibeli
    public static void printListMakanan() {
        int i = 1;
        System.out.println("List makanan yang dapat dibeli: ");
        for (Makanan x : listMakanan) {
            System.out.println(
                    i + ". " + x.getNama() + " (Harga: " + x.getHarga() + " ,Kekenyangan: " + x.getKekenyangan() + ")");
            i++;
        }
    }

    public String getInfo() {
        String ret = String.format("Nama: %s\nKategori: makananan\nHarga: %d\nKekenyangan: %d", this.getNama(), harga,
                kekenyangan);
        return (ret);
    }

    public void printInfo() {
        System.out.println(getInfo());
    }

    // public static Makanan getMakanan(String nama){
    // Makanan makanan = new Makanan(nama);
    // return makanan;
    // }
}
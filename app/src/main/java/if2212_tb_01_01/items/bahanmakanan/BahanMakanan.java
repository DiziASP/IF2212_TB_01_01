package if2212_tb_01_01.items.bahanmakanan;

import if2212_tb_01_01.entities.sim.BufferedImage;
import if2212_tb_01_01.items.Item;

import java.util.List;
import java.util.ArrayList;

public abstract class BahanMakanan extends Item{
    private Integer harga;
    private Integer kekenyangan;
    // private static List<Makanan> listMakanan = makeListMakanan();

    // Constructor
    public BahanMakanan(String nama, String kategori, String img, int iw, int ih, int harga, int kekenyangan){
        super(nama, kategori, img, iw, ih);
        this.harga = harga;
        this.kekenyangan = kekenyangan;
    }

    // // Constructor Makanan
    // public Makanan(String nama){
    //     super(nama, "makanan");
    //     if(nama == "NASI"){
    //         harga = 5;
    //         kekenyangan = 5;
    //     }
    //     else if (nama == "KENTANG"){
    //         harga = 3;
    //         kekenyangan = 4;
    //     }
    //     else if (nama == "AYAM"){
    //         harga = 10;
    //         kekenyangan = 8;
    //     }
    //     else if (nama == "SAPI"){
    //         harga = 12;
    //         kekenyangan = 15;
    //     }
    //     else if (nama == "WORTEL"){
    //         harga = 3;
    //         kekenyangan = 2;
    //     }
    //     else if(nama == "BAYAM"){
    //         harga = 3;
    //         kekenyangan = 2;
    //     }
    //     else if(nama == "KACANG"){
    //         harga = 2;
    //         kekenyangan =2;
    //     }
    //     else if(nama == "SUSU"){
    //         harga = 2;
    //         kekenyangan = 1;
    //     }
    // }

    // // Membuat list Makanan
    // private static List<Makanan> makeListMakanan(){
    //     List<Makanan> makanan = new ArrayList<>();
    //     makanan.add(new Makanan("NASI"));
    //     makanan.add(new Makanan("KENTANG"));
    //     makanan.add(new Makanan("AYAM"));
    //     makanan.add(new Makanan("SAPI"));
    //     makanan.add(new Makanan("WORTEL"));
    //     makanan.add(new Makanan("BAYAM"));
    //     makanan.add(new Makanan("KACANG"));
    //     makanan.add(new Makanan("SUSU"));
    //     return makanan;
    // }

    //getter method for listMakanan
    // public static List<Makanan> getListMakanan(){
    //     return listMakanan;
    // }

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
        return "Nama: " + this.getNama() + "\n" +
                "Jumlah: " + this.getAmount() + "\n" +
                "Harga: " + this.harga + "\n" +
                "Kekenyangan: " + this.kekenyangan + "\n" ;
    }

    public void printInfo(){
        System.out.println(getInfo());
    }

}

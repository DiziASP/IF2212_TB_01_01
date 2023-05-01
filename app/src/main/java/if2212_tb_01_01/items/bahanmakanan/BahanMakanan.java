package if2212_tb_01_01.items.bahanmakanan;

import if2212_tb_01_01.items.Item;


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

package if2212_tb_01_01.objects.bahanmakanan;

import if2212_tb_01_01.objects.Objek;


public abstract class BahanMakanan extends Objek{
    private Integer harga;
    private Integer kekenyangan;
    // private static List<Makanan> listMakanan = makeListMakanan();

    // Constructor
    public BahanMakanan(String nama, String kategori,int harga, int kekenyangan, int indeks){
        super(nama, kategori,indeks);
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

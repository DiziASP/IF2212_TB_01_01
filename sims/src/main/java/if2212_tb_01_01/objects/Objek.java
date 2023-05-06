package if2212_tb_01_01.objects;

public abstract class Objek {
    private int indeks;
    private String nama;
    private String kategori;
    private int amount;

    // Constructor
    public Objek(String nama, String kategori, int indeks){
        this.nama = nama;
        this.kategori = kategori;
        this.amount = 0;
        this.indeks = indeks;
    }

    public int getIndeks(){
        return indeks;
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

    public abstract void printInfo();

    public int getAmount(){
        return this.amount;
    }

    public void incAmount(){
        this.amount++;
    }

    public void decAmount(){
        this.amount--;
    }
}
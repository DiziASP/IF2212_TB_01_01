package if2212_tb_01_01.objects.furnitur;
import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.utils.Point;

public interface Furnitur{

    //Membuat list Furnitur

    //Getter method for listFurnitur

    //Getter method for aksi
    public String getNamaAksi();

    //Setter method for aksi
    public void setNamaAksi(String aksi);

    // Getter method for harga
    public int getHarga();
    // Setter method for harga
    public void setHarga(int harga);

    // Getter method for panjang
    public Integer getPanjang();

    // Setter method for panjang
    public void setPanjang(int panjang);
    // Getter method for lebar
    public Integer getLebar();

    // Setter method for lebar
    public void setLebar(int lebar);

    // Getter method for posisi
    public Point getPosisi();

    // Setter method for posisi
    public void setPosisi(Point posisi);

    // Getter method for isVertikal
    public boolean isVertikal();

    // Setter method for isVertikal
    public void setVertikal(boolean isVertikal);


    public String getInfo();
    public void printInfo();

}
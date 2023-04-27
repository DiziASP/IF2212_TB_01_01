package if2212_tb_01_01.objects.masakan;

import java.util.List;
import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;

import java.util.ArrayList;

public interface Masakan{
    public List<BahanMakanan> getBahan();

    // Setter method for bahan
    public void setBahan(List<BahanMakanan> bahan);

    // Getter method for kekenyangan
    public int getKekenyangan();

    // Setter method for kekenyangan
    public void setKekenyangan(int kekenyangan);

    //method untuk print list masakan yang dapat dibuat

    //method untuk print list bahan yang diperlukan untuk membuat masakan


    // Method for get info masakan
    public String getInfo();

    public void printInfo();
}
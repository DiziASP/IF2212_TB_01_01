package if2212_tb_01_01.objects.masakan;

import java.util.List;
import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.objects.bahanmakanan.*;

import java.util.ArrayList;

public class TumisSayur extends Objek implements Masakan{
    private List<BahanMakanan> bahan;
    private int kekenyangan;

    public TumisSayur() {
        super("TUMIS SAYUR", "MASAKAN");
        this.kekenyangan = 5;
        this.bahan = new ArrayList<BahanMakanan>();
        this.bahan.add(new Wortel());
        this.bahan.add(new Bayam());

    }
    public List<BahanMakanan> getBahan(){
        return bahan;
    }

    // Setter method for bahan
    public void setBahan(List<BahanMakanan> bahan){
        this.bahan = bahan;
    }

    // Getter method for kekenyangan
    public int getKekenyangan(){
        return kekenyangan;
    }

    // Setter method for kekenyangan
    public void setKekenyangan(int kekenyangan){
        this.kekenyangan = kekenyangan;
    }

    //method untuk print list masakan yang dapat dibuat

    //method untuk print list bahan yang diperlukan untuk membuat masakan


    // Method for get info masakan
    public String getInfo(){
        String info = "Nama Masakan : " + this.getNama() + "\n" +
                "Kekenyangan : " + this.kekenyangan + "\n" +
                "Bahan : \n";
        for (BahanMakanan bahanMakanan : bahan) {
            info += bahanMakanan.getNamaBahanMakanan();
        }
        return info;
    }

    public void printInfo(){
        System.out.println(getInfo());
    }
}
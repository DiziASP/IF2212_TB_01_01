package if2212_tb_01_01.items.masakan;
import if2212_tb_01_01.items.*;
import if2212_tb_01_01.items.bahanmakanan.*;

import java.util.List;
import java.util.ArrayList;

public class Masakan extends Item{
    private List<BahanMakanan> bahan;
    private Integer kekenyangan;

    // Constructor
    public Masakan(String nama, String img, int iw, int ih, int kekenyangan){
        super(nama, "masakan", img, iw, ih);
        this.kekenyangan = kekenyangan;
    }

    // Getter method for bahan
    public List<BahanMakanan> getBahan() {
        return this.bahan;
    }

    public List<Integer> getIdxBahan(){
        ArrayList<Integer> listIdx = new ArrayList<Integer>();
        for (BahanMakanan makanan : bahan){
            if (makanan.getNama()=="AYAM"){
                listIdx.add(12);
            } else if (makanan.getNama()=="BAYAM"){
                listIdx.add(13);
            } else if (makanan.getNama()=="KACANG"){
                listIdx.add(14);
            } else if (makanan.getNama()=="KENTANG"){
                listIdx.add(15);
            } else if (makanan.getNama()=="NASI"){
                listIdx.add(16);
            } else if (makanan.getNama()=="SAPI"){
                listIdx.add(17);
            } else if (makanan.getNama()=="SUSU"){
                listIdx.add(18);
            } else if (makanan.getNama()=="WORTEL"){
                listIdx.add(19);
            }
        }
        return listIdx;
    }
    
    // Setter method for bahan
    public void setBahan(List<BahanMakanan> bahan) {
        this.bahan = bahan;
    }

    // Getter method for kekenyangan
    public Integer getKekenyangan() {
        return this.kekenyangan;
    }

    // Setter method for kekenyangan
    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public String infoBahan(){
        String text = "";
        for (BahanMakanan bm : bahan){
            text += bm.getNama() + "\n       ";
        }
        return text.substring(0, text.length()-8);
    }

    // Method for get info masakan
    public String getInfo(){
        return "Nama: " + this.getNama() + "\n" +
                "Jumlah: " + this.getAmount() + "\n" +
                "Bahan: " + this.infoBahan() + "\n" +
                "Kekenyangan: " + this.kekenyangan + "\n" ;
    }

    public void printInfo(){
        System.out.println(getInfo());
    }
}

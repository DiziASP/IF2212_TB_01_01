package if2212_tb_01_01.items.masakan;
import if2212_tb_01_01.items.*;
import if2212_tb_01_01.items.bahanmakanan.*;

import java.util.List;
import java.util.ArrayList;

public class Masakan extends Item{
    private List<BahanMakanan> bahan;
    private Integer kekenyangan;
    // private static List<Masakan> listMasakan = makeListMasakan();

    // Constructor
    public Masakan(String nama, String img, int iw, int ih, int kekenyangan){
        super(nama, "masakan", img, iw, ih);
        this.kekenyangan = kekenyangan;
    }

    // Getter method for bahan
    public List<BahanMakanan> getBahan() {
        return this.bahan;
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

    // //method untuk print list masakan yang dapat dibuat
    // public static void printListMasakan(){
    //     int i = 1;
    //     System.out.println("List masakan yang dapat dibuat: ");
    //     for(Masakan x : listMasakan){
    //         System.out.println(i+". "+x.getNama()+"(Kekenyangan: "+x.getKekenyangan()+")");
    //         i++;
    //     }
    // }

    // //method untuk print list bahan yang diperlukan untuk membuat masakan
    // public static List<BahanMakanan> printGetResepMasakan(String nama){
    //     List<BahanMakanan> BahanMakanan = new ArrayList<>();
    //     int number = 1;
    //     int i = 0;
    //     boolean found = false;
    //     while (!found && i < listMasakan.size()){
    //         if(listMasakan.get(i).getNama().equals(nama)){
    //             found = true;
    //             BahanMakanan = listMasakan.get(i).getBahan();
    //             System.out.println("List BahanMakanan yang diperlukan untuk membuat "+ nama);
    //             for(BahanMakanan x : BahanMakanan){
    //                 System.out.println(number + ". "+ x);
    //                 number++;
    //             }
    //         }
    //         else{
    //             i++;
    //         }
    //     }
    //     return BahanMakanan;
    // }

    // public static List<BahanMakanan> getResepMasakan(String nama){
    //     List<BahanMakanan> BahanMakanan = new ArrayList<>();
    //     int i = 0;
    //     boolean found = false;
    //     while(!found && i<listMasakan.size())
    // }

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

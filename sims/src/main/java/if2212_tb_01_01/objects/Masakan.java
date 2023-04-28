package if2212_tb_01_01.objects;

import java.util.List;
import java.util.ArrayList;

public class Masakan extends Objek{
    private List<Makanan> bahan;
    private Integer kekenyangan;
    private static List<Masakan> listMasakan = makeListMasakan();

    // Constructor
    public Masakan(String nama, List<Makanan> bahan, Integer kekenyangan){
        super(nama, "masakan");
        this.bahan = bahan;
        this.kekenyangan = kekenyangan;
    }

    //Constructor Masakan
    public Masakan(String nama){
        super(nama, "masakan");
        bahan = makeListBahan(nama);
        if(nama == "NASI AYAM"){
            kekenyangan = 16;
        }
        else if (nama == "NASI KARI"){
            kekenyangan = 30;
        }
        else if(nama == "SUSU KACANG"){
            kekenyangan = 5;
        }
        else if(nama == "TUMIS SAYUR"){
            kekenyangan = 5;
        }
        else if(nama == "BISTIK"){
            kekenyangan = 22;
        }
    }

    //Membuat list masakan yang dapat dibuat
    private static List<Masakan> makeListMasakan(){
        List<Masakan> masakan = new ArrayList<>();
        masakan.add(new Masakan("NASI AYAM"));
        masakan.add(new Masakan("NASI KARI"));
        masakan.add(new Masakan("SUSU KACANG"));
        masakan.add(new Masakan("TUMIS SAYUR"));
        masakan.add(new Masakan("BISTIK"));
        return masakan;
    }

    //Membuat list bahan
    private List<Makanan> makeListBahan(String nama){
        List<Makanan> bahan = new ArrayList<>();
        if(nama == "NASI AYAM"){
            bahan.add(new Makanan("NASI"));
            bahan.add(new Makanan("AYAM"));

        }
        else if (nama == "NASI KARI"){
            bahan.add(new Makanan("NASI"));
            bahan.add(new Makanan("KENTANG"));
            bahan.add(new Makanan("WORTEL"));
            bahan.add(new Makanan("SAPI"));
        }
        else if(nama == "SUSU KACANG"){
            bahan.add(new Makanan("SUSU"));
            bahan.add(new Makanan("KACANG"));
        }
        else if(nama == "TUMIS SAYUR"){
            bahan.add(new Makanan("WORTEL"));
            bahan.add(new Makanan("BAYAM"));
        }
        else if(nama == "BISTIK"){
            bahan.add(new Makanan("KENTANG"));
            bahan.add(new Makanan("SAPI"));
        }
        return bahan;
    }

    // Getter method for list Masakan
    public static List<Masakan> getListMasakan(){
        return listMasakan;
    }

    // Getter method for bahan
    public List<Makanan> getBahan() {
        return this.bahan;
    }

    // Setter method for bahan
    public void setBahan(List<Makanan> bahan) {
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

    //method untuk print list masakan yang dapat dibuat
    public static void printListMasakan(){
        int i = 1;
        System.out.println("List masakan yang dapat dibuat: ");
        for(Masakan x : listMasakan){
            System.out.println(i+". "+x.getNama()+"(Kekenyangan: "+x.getKekenyangan()+")");
            i++;
        }
    }

    //method untuk print list bahan yang diperlukan untuk membuat masakan
    public static List<Makanan> printGetResepMasakan(String nama){
        List<Makanan> makanan = new ArrayList<>();
        int number = 1;
        int i = 0;
        boolean found = false;
        while (!found && i < listMasakan.size()){
            if(listMasakan.get(i).getNama().equals(nama)){
                found = true;
                makanan = listMasakan.get(i).getBahan();
                System.out.println("List makanan yang diperlukan untuk membuat "+ nama);
                for(Makanan x : makanan){
                    System.out.println(number + ". "+ x);
                    number++;
                }
            }
            else{
                i++;
            }
        }
        return makanan;
    }

    // public static List<Makanan> getResepMasakan(String nama){
    //     List<Makanan> makanan = new ArrayList<>();
    //     int i = 0;
    //     boolean found = false;
    //     while(!found && i<listMasakan.size())
    // }

    // Method for get info masakan
    public String getInfo(){
        String ret = String.format("Nama: %s\nKategori: Masakan\nKekenyangan: %d", this.getNama(), kekenyangan);
        return(ret);
    }

    public void printInfo(){
        System.out.println(getInfo());
    }
}
package if2212_tb_01_01.entities;

import java.util.*;
import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.occupation.*;
import if2212_tb_01_01.objects.*;

/**
 * Class of Sim Object
 * 
 */
public class Sim {

    class Aksi {
    }

    /* Sim Attributes */
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Objek> inventory;
    private String status;
    private Kesejahteraan kesejahteraan;
    private List<Aksi> aksi;
    private Point posisiRumah;
    private Point posisiRuangan;

    // private Point posisi; yang butuh posisi kayanya rumah aja???
    private Rumah rumah;
    public Sim(Kesejahteraan kesejahteraan, int uang, Pekerjaan pekerjaan, String namaLengkap) {
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.pekerjaan = pekerjaan;
        this.namaLengkap = namaLengkap;
        this.status = "";
        this.inventory = new Inventory<Objek>();
    }
    // konstruktor kl pekerjaan di random 
    public Sim(Kesejahteraan kesejahteraan, int uang, String namaLengkap, Rumah rumah, Point posisiRumah, Point posisiRuangan) {
        this.pekerjaan = new Pekerjaan();
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.namaLengkap = namaLengkap;
        this.status = "";
        this.inventory = new Inventory<Objek>();
        this.rumah = rumah;
        this.posisiRumah = posisiRumah;
        this.posisiRuangan = posisiRuangan;
    }

    public Point getPosisiRumah(){
        return posisiRumah;
    }

    public void setPosisiRumah(Point posisiRumah){
        this.posisiRumah = posisiRumah;
    }

    public Point getPosisiRuangan(){
        return posisiRuangan;
    }

    public void setPosisiRuangan(Point posisiRuangan){
        this.posisiRuangan = posisiRuangan;
    }

    public Inventory<Objek> getInventory(){
        return inventory;
    }

    //aksi

    public void masak(String nama, List<Makanan> makanan){
        boolean terpenuhi = true;
        int i = 0;
        while (terpenuhi && i<makanan.size()){
            if(inventory.isObjekAda(makanan.get(i))){
                i++;
            }
            else{
                terpenuhi = false;
            }
        }
        if(terpenuhi){
            inventory.addItem(new Masakan(nama), 1);
            for(Makanan x: makanan){
                inventory.removeItem(x, 1);
            }
        }

        // List<Makanan> bahanmakanan = Masakan.printGetResepMasakan(nama);
        //mengecek apakah bahan yang diperlukan semuanya ada di inventory atau tidak.
        //kalau tidak ada, akan mengeluarkan pesan "bahan yang diperlukan tidak terpenuhi"
        // kalau ada, tanyakan dulu apakah yakin? setelah itu masukin ke inventory trus bahan masakan yang dipake tadi kurangin jumlahnya di dalam inventory
    }

    public void viewLokasi(){
        System.out.println("SIM " + namaLengkap + " saat ini sedang berada di rumah dengan lokasi " + posisiRumah.toString() + " pada ruangan dengan lokasi" + posisiRuangan.toString());
    }

    public void viewLokasi(Rumah currentLocationRumah){
        System.out.println("SIM " + namaLengkap + " saat ini sedang berada di rumah dengan lokasi " + posisiRumah.toString() + " pada ruangan " + currentLocationRumah.getRuangan(posisiRuangan).getNama() + " dengan lokasi" + posisiRuangan.toString()+"\n");
    }

    public void viewInventory(){
        inventory.displayInventory();
    }

    public void beliBarang(String nama, String kategori){
        boolean found = false;
        int i = 0;
        if(kategori.equals("MAKANAN")){
            while(!found && i < Makanan.getListMakanan().size()){
                if(Makanan.getListMakanan().get(i).getNama().equals(nama)){
                    found = true;
                }
                else{
                    i++;
                }
            }
            if(found){
                Integer hargaMakanan = Makanan.getListMakanan().get(i).getHarga();
                if(this.uang >= hargaMakanan){
                    uang -= hargaMakanan;
                    inventory.addItem(new Makanan(nama), 1);
                    System.out.println(nama+" berhasil dibeli!");
                }
                else{
                    System.out.println("Uang tidak cukup");
                }
            }
            else{
                System.out.println("Input makanan tidak terdapat dalam list");
            }
        }
        else if(kategori.equals("FURNITUR")){
            while(!found && i < Furnitur.getListFurnitur().size()){
                if(Furnitur.getListFurnitur().get(i).getNama().equals(nama)){
                    found = true;
                }
                else{
                    i++;
                }
            }
            if(found){
                Integer hargaFurnitur = Furnitur.getListFurnitur().get(i).getHarga();
                if(this.uang >= hargaFurnitur){
                    uang -= hargaFurnitur;
                    inventory.addItem(new Furnitur(nama,new Point(-1,-1),false), 1);
                    System.out.println(nama+" berhasil dibeli!");
                    //harusnya dikirim barang dulu trus kalo dah sampe baru masuk inventory
                }
                else{
                    System.out.println("Uang tidak cukup");
                }
            }
            else{
                System.out.println("Input furnitur tidak terdapat dalam list");
            }    
        }
    }

    public void viewInfo(){
        System.out.println("Nama: "+ namaLengkap);
        System.out.println("Pekerjaan: "+ pekerjaan.getNamaKerja());
        System.out.println("Kesehatan: "+ kesejahteraan.getKesehatan());
        System.out.println("Kekenyangan: "+ kesejahteraan.getKekenyangan());
        System.out.println("Mood: "+kesejahteraan.getMood());
        System.out.println("Uang: "+ uang+"\n");
    }
}

package if2212_tb_01_01.entities;
import java.util.*;

import javax.swing.Action;

import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.objects.*;
import if2212_tb_01_01.objects.bahanmakanan.Ayam;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;
import if2212_tb_01_01.objects.bahanmakanan.Bayam;
import if2212_tb_01_01.objects.bahanmakanan.Kacang;
import if2212_tb_01_01.objects.bahanmakanan.Kentang;
import if2212_tb_01_01.objects.bahanmakanan.Nasi;
import if2212_tb_01_01.objects.bahanmakanan.Sapi;
import if2212_tb_01_01.objects.bahanmakanan.Susu;
import if2212_tb_01_01.objects.bahanmakanan.Wortel;
import if2212_tb_01_01.objects.furnitur.Jam;
import if2212_tb_01_01.objects.furnitur.KasurKingSize;
import if2212_tb_01_01.objects.furnitur.KasurQueenSize;
import if2212_tb_01_01.objects.furnitur.KasurSingle;
import if2212_tb_01_01.objects.furnitur.Kompor;
import if2212_tb_01_01.objects.furnitur.MejaKursi;
import if2212_tb_01_01.objects.furnitur.Toilet;
import if2212_tb_01_01.occupation.*;
import if2212_tb_01_01.objects.masakan.Bistik;
import if2212_tb_01_01.objects.masakan.Masakan;
import if2212_tb_01_01.objects.masakan.NasiAyam;
import if2212_tb_01_01.objects.masakan.NasiKari;
import if2212_tb_01_01.objects.masakan.SusuKacang;
import if2212_tb_01_01.objects.masakan.TumisSayur;;;

/**
 * Class of Sim Object
 * 
 */
public class Sim {

    public static class Aksi {
        private String nama;
        private Sim sim;
        private int menitTersisa;
        private boolean isButuhObjek;
        public Aksi(Sim sim, String nama, int jumlahWaktu){
            this.sim = sim;
            this.nama = nama;
            this.menitTersisa = jumlahWaktu;
        }
        public Aksi(String nama, boolean isButuhObjek){
            this.nama = nama;
            this.isButuhObjek = isButuhObjek;
            this.menitTersisa = -1;
            this.sim = null;
        }
        public String getNama(){
            return nama;
        }
        public int getMenitTersisa(){
            return menitTersisa;
        }
        public void setMenitTersisa(int menitTersisa){
            this.menitTersisa = menitTersisa;
        }
        public void kurangiMenitTersisa(int menit){
            this.menitTersisa -= menit;
        }
        public static List<Aksi> getDaftarAksiAktif(){
            List<Aksi> listAksiAktif = new ArrayList<Aksi>();
            listAksiAktif.add(new Aksi("KERJA", false));
            listAksiAktif.add(new Aksi("OLAHRAGA", false));
            listAksiAktif.add(new Aksi ("TIDUR", true));
            listAksiAktif.add(new Aksi ("MAKAN", true));
            listAksiAktif.add(new Aksi ("MEMASAK", true));
            listAksiAktif.add(new Aksi ("BERKUNJUNG", false));
            listAksiAktif.add(new Aksi ("BUANG AIR", true));
            listAksiAktif.add(new Aksi ("YOGA", false));
            listAksiAktif.add(new Aksi ("IBADAH", false));
            listAksiAktif.add(new Aksi ("MENGGAMBAR", true));
            listAksiAktif.add(new Aksi ("MAIN MUSIK", true));
            listAksiAktif.add(new Aksi ("MANDI",true));
            listAksiAktif.add(new Aksi ("MEMBERSIHKAN RUMAH", true));
            listAksiAktif.add(new Aksi ("PROYEKAN", true));
            return listAksiAktif;
        }
        public static List<Aksi> getDaftarAksiNonWaktu(){
            List<Aksi> listAksiNonWaktu = new ArrayList<Aksi>();
            listAksiNonWaktu.add(new Aksi("BERPINDAH RUANGAN", false));
            listAksiNonWaktu.add(new Aksi("MELIHAT INVENTORY", false));
            listAksiNonWaktu.add(new Aksi ("MEMASANG BARANG", false));
            listAksiNonWaktu.add(new Aksi ("MELIHAT WAKTU", true));
            return listAksiNonWaktu;
        }
        public static List<Aksi> getDaftarAksiAFK(){
            List<Aksi> listAksiAFK = new ArrayList<Aksi>();
            listAksiAFK.add(new Aksi("UPGRADE RUMAH", false));
            listAksiAFK.add(new Aksi("BELI BARANG", false));
            return listAksiAFK;
        }

    }


    /* Sim Attributes */
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Objek> inventory;
    private Kesejahteraan kesejahteraan;
    private List<Aksi> status;
    private static World world;
    public void setWorld(World world) {
        Sim.world = world;
    }
    private boolean isDoAksiAktif;
    private Point posisiRumah;
    private Point posisiRuangan;

    // private Point posisi; yang butuh posisi kayanya rumah aja???
    private Rumah rumah;
    private Ruangan currentRuangan;
    public Sim(Kesejahteraan kesejahteraan, int uang, Pekerjaan pekerjaan, String namaLengkap) {
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.pekerjaan = pekerjaan;
        this.namaLengkap = namaLengkap;
        this.status = new ArrayList<Aksi>();
        this.inventory = new Inventory<Objek>();
    }
    // konstruktor kl pekerjaan di random 
    public Sim(Kesejahteraan kesejahteraan, int uang, String namaLengkap, Rumah rumah, Point posisiRumah, Point posisiRuangan) {
        this.pekerjaan = new Pekerjaan();
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.namaLengkap = namaLengkap;
        this.status = new ArrayList<Aksi>();
        this.inventory = new Inventory<Objek>();
        this.rumah = rumah;
        this.currentRuangan = rumah.getDaftarRuangan().get(0);
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

    public boolean getIsDoAksiAktif(){
        return isDoAksiAktif;
    }
    public void setIsDoAksiAktif(boolean isDoAksiAktif){
        this.isDoAksiAktif = isDoAksiAktif;
    }
    public Aksi getAksi(int index){
        return status.get(index);
    }


    //aksi

    public void masak(String nama, List<BahanMakanan> makanan){
        boolean terpenuhi = true;
        int i = 0;
        while (terpenuhi && i<makanan.size()){
            if(inventory.isObjekAda((Objek) makanan.get(i))){
                i++;
            }
            else{
                terpenuhi = false;
            }
        }
        if(terpenuhi){
            if (nama.equals("NASI AYAM")) {
                NasiAyam nasiAyam = new NasiAyam();
                inventory.addItem(nasiAyam,1);
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                final int waktu = (int) (nasiAyam.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak", waktu));
                ActionThread thread = new ActionThread(indexStatus, this, waktu);
                thread.run();
                this.isDoAksiAktif=false;
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);

            } else if (nama.equals("BISTIK")) {
                Bistik bistik = new Bistik();
                inventory.addItem(bistik, 1);
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                final int waktu = (int) (bistik.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak", waktu));
                ActionThread thread = new ActionThread(indexStatus, this, waktu);
                thread.run();
                this.isDoAksiAktif=false;
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);

            } else if (nama.equals("NASI KARI")) {
                NasiKari nasikari = new NasiKari();
                inventory.addItem(nasikari, 1);
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                final int waktu = (int) (nasikari.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak", waktu));
                ActionThread thread = new ActionThread(indexStatus, this, waktu);
                thread.run();
                this.isDoAksiAktif=false;
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);

            } else if (nama.equals("SUSU KACANG")) {
                SusuKacang susukacang = new SusuKacang();
                inventory.addItem(susukacang, 1);
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                final int waktu = (int) (susukacang.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak", waktu));
                ActionThread thread = new ActionThread(indexStatus, this, waktu);
                thread.run();
                this.isDoAksiAktif=false;
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);

            } else if (nama.equals("TUMIS SAYUR")) {
                TumisSayur tumissayur = new TumisSayur();
                inventory.addItem(tumissayur, 1);
                this.isDoAksiAktif = true;
                int indexStatus = this.status.size() - 1;
                final int waktu = (int) (tumissayur.getKekenyangan() * 1.5);
                this.status.add(new Aksi(this, "masak", waktu));
                ActionThread thread = new ActionThread(indexStatus, this, waktu);
                thread.run();
                this.isDoAksiAktif=false;
                this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);

            }
            System.out.println("Memasak Selesai!");
            for(BahanMakanan x: makanan){
                inventory.removeItem((Objek) x, 1);
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
        String[] bahanmakanan = {"AYAM", "BAYAM", "KACANG", "KENTANG","NASI","SAPI","SUSU","WORTEL"};
        String[] furnitur = {"KASUR KING SIZE", "KASUR QUEEN SIZE", "JAM", "KASUR SINGLE","KOMPOR GAS","KOMPOR LISTRIK", "MEJA DAN KURSI", "TOILET"};
        if(kategori.equals("MAKANAN")){
            for (i = 0 ; i < bahanmakanan.length; i++) {
                if (nama.equals(bahanmakanan[i])) {
                    found = true;
                    break;
                }
            }

            if (found && nama.equals("AYAM")) {
                Ayam ayam = new Ayam();
                Integer harga = ayam.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(ayam, 1);
                    System.out.println(ayam.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("BAYAM")) {
                Bayam bayam = new Bayam();
                Integer harga = bayam.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(bayam, 1);
                    System.out.println(bayam.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }
            
            else if (found && nama.equals("KACANG")) {
                Kacang kacang = new Kacang();
                Integer harga = kacang.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(kacang, 1);
                    System.out.println(kacang.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("KENTANG")) {
                Kentang kentang = new Kentang();
                Integer harga = kentang.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(kentang, 1);
                    System.out.println(kentang.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("NASI")) {
                Nasi nasi = new Nasi();
                Integer harga = nasi.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(nasi, 1);
                    System.out.println(nasi.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("SAPI")) {
                Sapi sapi = new Sapi();
                Integer harga = sapi.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(sapi, 1);
                    System.out.println(sapi.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("SUSU")) {
                Susu susu = new Susu();
                Integer harga = susu.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(susu, 1);
                    System.out.println(susu.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("WORTEL")) {
                Wortel wortel = new Wortel();
                Integer harga = wortel.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(wortel, 1);
                    System.out.println(wortel.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else {
                System.out.println("Makanan tidak tersedia");
            }
        }
        else if(kategori.equals("FURNITUR")){
            for (i = 0 ; i < furnitur.length; i++) {
                if (nama.equals(furnitur[i])) {
                    found = true;
                    break;
                }
            }
            
            if (found && nama.equals("KASUR KING SIZE")) {
                KasurKingSize kasurking = new KasurKingSize();
                Integer harga = kasurking.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(kasurking, 1);
                    System.out.println(kasurking.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("KASUR QUEEN SIZE")) {
                KasurQueenSize kasurqueen = new KasurQueenSize();
                Integer harga = kasurqueen.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(kasurqueen, 1);
                    System.out.println(kasurqueen.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("KASUR SINGLE")) {
                KasurSingle kasursingle = new KasurSingle();
                Integer harga = kasursingle.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(kasursingle, 1);
                    System.out.println(kasursingle.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("JAM")) {
                Jam jam = new Jam();
                Integer harga = jam.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(jam, 1);
                    System.out.println(jam.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("KOMPOR GAS")) {
                Kompor komporgas = new Kompor("GAS");
                Integer harga = komporgas.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(komporgas, 1);
                    System.out.println(komporgas.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("KOMPOR LISTRIK")) {
                Kompor komporgas = new Kompor("LISTRIK");
                Integer harga = komporgas.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(komporgas, 1);
                    System.out.println(komporgas.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("MEJA DAN KURSI")) {
                MejaKursi mejakursi = new MejaKursi();
                Integer harga = mejakursi.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(mejakursi, 1);
                    System.out.println(mejakursi.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else if (found && nama.equals("TOILET")) {
                Toilet toilet = new Toilet();
                Integer harga = toilet.getHarga();
                if (this.uang >= harga) {
                    uang -= harga;
                    inventory.addItem(toilet, 1);
                    System.out.println(toilet.getNama() + " berhasil dibeli");
                    int indexStatus = this.status.size() - 1;
                    Random rand = new Random();
                    final int waktu = (rand.nextInt(6) + 1) * 30;
                    this.status.add(new Aksi(this,"beliBarang", waktu));
                    ActionThread thread = new ActionThread(indexStatus,this, waktu);
                    thread.run();
                } else {
                    System.out.println("Uang tidak cukup");
                }
            }

            else{
                System.out.println("Input furnitur tidak terdapat dalam list");
            }    
        } else {
            System.out.println("Barang tidak dapat dibeli");
        }
    }

    public Sim(String namaLengkap, Pekerjaan pekerjaan) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = pekerjaan;
        this.uang = 100;
        this.inventory = new Inventory<Objek>();
        this.status = new ArrayList<Aksi>();
    }

    //Getter dan Setter
    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public Inventory<Objek> getInventory() {
        return inventory;
    }

    public void setInventory(Inventory<Objek> inventory) {
        this.inventory = inventory;
    }

    public Kesejahteraan getKesejahteraan() {
        return kesejahteraan;
    }

    public List<Aksi> getStatus() {
        return status;
    }

    public void setStatus(List<Aksi> status) {
        this.status = status;
    }
    public void goToObject() {
        System.out.println("Pilih objek yang ingin dikunjungi: ");
        int i = 1;
        for (Objek objek : this.currentRuangan.getDaftarObjek()) {
            System.out.println(i + ". " + objek.getNama());
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int pilihan = scanner.nextInt();
        Objek objek = this.currentRuangan.getDaftarObjek().get(pilihan - 1);
        if (objek.getKategori().equals("peralatan")){
            Furnitur furnitur = (Furnitur) objek;
            if (furnitur.getAksi().equals("TIDUR")){
                System.out.println("Apakah Anda ingin tidur? (Y/N)");
                String pilihanTidur = scanner.next();
                if (pilihanTidur.equals("Y")){
                    this.tidur();
                }
            }
            else if (furnitur.getAksi().equals("BUANG AIR")){
                System.out.println("Apakah Anda ingin buang air? (Y/N)");
                String pilihanBuangAir = scanner.next();
                if (pilihanBuangAir.equals("Y")){
                    this.buangAir();
                }
            } else if(furnitur.getAksi().equals("MASAK")){
                System.out.println("Apakah Anda ingin memasak? (Y/N)");
                String pilihanMasak = scanner.next();
                if (pilihanMasak.equals("Y")){
                    this.memasak();
                }
            } else if (furnitur.getAksi().equals("MAKAN")){
                System.out.println("Apakah Anda ingin makan? (Y/N)");
                String pilihanMakan = scanner.next();
                if (pilihanMakan.equals("Y")){
                    this.makan();
                }
            } else if(furnitur.getAksi().equals("MELIHAT WAKTU")){
                System.out.println("Apakah Anda ingin melihat waktu? (Y/N)");
                String pilihanLihatWaktu = scanner.next();
                if (pilihanLihatWaktu.equals("Y")){
                    this.melihatWaktu();
                }
            }
        }
    }
    //Actions of Sim
    public void addToInventory() {
        //Please provide the solution below
    }

    public void removeFromInventory() {
        //Please provide the solution below
    }

    public void kerja() {
        //Please provide the solution below
    }

    public void olahraga() {
        //Please provide the solution below
    }

    public void tidur() {
        //Please provide the solution below
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan waktu tidur (Kelipatan 4) dalam menit: ");
        int waktuTidur = scanner.nextInt();
        while (waktuTidur % 4 != 0) {
            System.out.println("Masukkan waktu tidur (Kelipatan 4) dalam menit: ");
            waktuTidur = scanner.nextInt();
        }
        final int waktu = waktuTidur*60;
        this.status.add(new Aksi(this, "Tidur", waktuTidur));
        int indexStatus = this.status.size() - 1;
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 20);
        this.isDoAksiAktif = true;
        ActionThread thread = new ActionThread(indexStatus,this,waktu);
        thread.start(); // memulai thread
        // nnti di main ada thread buat ngecek kl dia ga idle tp ga tidur 10 mnt haduh gmn y
    }

    public void makan() {
        //Please provide the solution below
    }

    public void berkunjung() {
        //Please provide the solution below
    }

    public void buangAir() {
        //Please provide the solution below
    }

    public void upgradeRumah() {
        System.out.println("Pilih posisi ruangan baru (atas/bawah/kiri/kanan): ");
        Scanner scanner = new Scanner(System.in);
        String pilihan = scanner.nextLine();
        // if (pilihan.equals("atas")) {
        //     System.out.println("Masukkan nama ruangan: ");
        //     String namaRuangan = scanner.nextLine();
        //     this.rumah.addRuangan(new Ruangan(namaRuangan));
            
        // } else if (pilihan.equals("bawah")) {
        //     this.rumah.addRuangan(new Ruangan("Ruangan Bawah", 0, -1));
        // } else if (pilihan.equals("kiri")) {
        //     this.rumah.addRuangan(new Ruangan("Ruangan Kiri", -1, 0));
        // } else if (pilihan.equals("kanan")) {
        //     this.rumah.addRuangan(new Ruangan("Ruangan Kanan", 1, 0));
        // }


    }
    public void berpindahRuangan(Ruangan tujuanRuangan) {
        //Please provide the solution below
        if (rumah.getDaftarRuangan().contains(tujuanRuangan)) {
            currentRuangan = tujuanRuangan;
            this.status.add(new Aksi(this,"berpindahRuangan", 0));
            System.out.println("Sim berhasil berpindah ke " + currentRuangan.getNama());
        } else {
            System.out.println("Ruangan tidak tersedia");
        }
    }

    public void melihatInventory() {
        //Please provide the solution below
    }

    public void memasangBarang() {
        //Please provide the solution below
    }

    public void melihatWaktu() {
        //Please provide the solution below
    }

    public void yoga() {
        this.status.add(new Aksi(this, "Yoga", 1));
    }

    public void viewInfo(){
        System.out.println("Nama: "+ namaLengkap);
        System.out.println("Pekerjaan: "+ pekerjaan.getNamaKerja());
        System.out.println("Kesehatan: "+ kesejahteraan.getKesehatan());
        System.out.println("Kekenyangan: "+ kesejahteraan.getKekenyangan());
        System.out.println("Mood: "+kesejahteraan.getMood());
        System.out.println("Uang: "+ uang+"\n");
    }
    public void viewCurrentLocation(){
        System.out.println("Rumah: "+ rumah.getPosisi().getX()+","+rumah.getPosisi().getY());
        System.out.println("Ruangan: "+ this.currentRuangan.getNama()+" di koordinat "+this.posisiRuangan.getX()+","+this.posisiRuangan.getY());
    }

    public void proyekan () {
        this.status.add(new Aksi(this,"proyekan", 180));
        this.kesejahteraan.setMood(this.kesejahteraan.getMood()-5);
        setUang(getUang() + 100);
        int waktu = 180;
        int indexStatus = this.status.size() - 1;
        this.isDoAksiAktif = true;
        ActionThread thread = new ActionThread(indexStatus, this, waktu);
        thread.run();
        this.isDoAksiAktif = false;
    }   
}


package if2212_tb_01_01.entities;
import java.util.*;
import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.occupation.*;

/**
 * Class of Sim Object
 * 
 */
public class Sim {

    class Aksi {
        private String nama;
        private Sim sim;
        private int menitTersisa;
        public Aksi(Sim sim, String nama, int jumlahWaktu){
            this.sim = sim;
            this.nama = nama;
            this.menitTersisa = jumlahWaktu;
        }

    }


    /* Sim Attributes */
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Objek> inventory;
    private String status;
    private Kesejahteraan kesejahteraan;
    private List<Aksi> aksi;
    private static World world;
    public void setWorld(World world) {
        Sim.world = world;
    }
    // private Point posisi; yang butuh posisi kayanya rumah aja???
    private Rumah rumah;
    public Sim(Kesejahteraan kesejahteraan, int uang, Pekerjaan pekerjaan, String namaLengkap) {
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.pekerjaan = pekerjaan;
        this.namaLengkap = namaLengkap;
        this.status = "";
        this.inventory = new Inventory();
    }
    // konstruktor kl pekerjaan di random 
    public Sim(Kesejahteraan kesejahteraan, int uang, String namaLengkap, Rumah rumah) {
        List <Pekerjaan> pekerjaan = new ArrayList<Pekerjaan>();
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Badut Sulap", "Badut sulap memiliki gaji harian 15", 15).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Koki", "Koki memiliki gaji harian 30", 30).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Polisi", "Polisi memiliki gaji harian 35", 35).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Programmer", "Progammer memiliki gaji harian 45", 45).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Dokter", "Dokter memiliki gaji harian 50", 50).build());
        Random rand = new Random();
        int min = 0;
        int max = 4;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        this.pekerjaan = pekerjaan.get(randomNum);
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.namaLengkap = namaLengkap;
        this.status = "";
        this.inventory = new Inventory();
        this.rumah = rumah;
        this.aksi = new ArrayList<Aksi>();

    }

    public Sim(String namaLengkap, Pekerjaan pekerjaan) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = pekerjaan;
        this.uang = 100;
        this.inventory = new Inventory();
        this.status = "";
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Kesejahteraan getKesejahteraan() {
        return kesejahteraan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        this.aksi.add(new Aksi(this, "Tidur", 4));
        world.setIdle(false);
        this.status = "Tidur";
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 20);
        world.worldClock.run(4);
        // nnti di main ada thread buat ngecek kl dia ga idle tp ga tidur 10 mnt
    }

    public void makan() {
        //Please provide the solution below
    }

    public void memasak() {
        //Please provide the solution below
    }

    public void berkunjung() {
        //Please provide the solution below
    }

    public void buangAir() {
        //Please provide the solution below
    }

    public void upgradeRumah() {
        //Please provide the solution below
    }

    public void beliBarang() {
        //Please provide the solution below
    }

    public void berpindahRuangan() {
        //Please provide the solution below
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
}

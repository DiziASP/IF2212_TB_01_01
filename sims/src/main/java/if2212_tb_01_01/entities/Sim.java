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

    class Inventory {
        private Map<Objek, Integer> isi;
        public Inventory() {
            this.isi = new HashMap<Objek, Integer>();
        }
    }

    class Aksi {
    }

    /* Sim Attributes */
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory inventory;
    private String status;
    private Kesejahteraan kesejahteraan;
    private List<Aksi> aksi;
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

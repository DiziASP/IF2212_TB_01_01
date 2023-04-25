package if2212_tb_01_01.entities;

import java.util.*;
import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.occupation.*;
import if2212_tb_01_01.entities.Rumah.Ruangan;
import if2212_tb_01_01.objects.*;

/**
 * Class of Sim Object
 * 
 */
public class Sim {

    /* Sim Attributes */
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Objek> inventory;
    private String status;
    private Kesejahteraan kesejahteraan;
    // private Point posisi; yang butuh posisi kayanya rumah aja???
    private Rumah rumah;
    private Ruangan ruangan;

    public Sim(Kesejahteraan kesejahteraan, int uang, Pekerjaan pekerjaan, String namaLengkap) {
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.pekerjaan = pekerjaan;
        this.namaLengkap = namaLengkap;
        this.status = "";
        this.inventory = new Inventory<Objek>();
    }

    // konstruktor kl pekerjaan di random
    public Sim(Kesejahteraan kesejahteraan, int uang, String namaLengkap, Rumah rumah) {
        List<Pekerjaan> pekerjaan = new ArrayList<Pekerjaan>();
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
        this.inventory = new Inventory<Objek>();
        this.rumah = rumah;
    }

    public Sim(String namaLengkap, Pekerjaan pekerjaan) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = pekerjaan;
        this.uang = 100;
        this.inventory = new Inventory<Objek>();
        this.status = "";
    }

    // Getter dan Setter
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Actions of Sim
    public void addToInventory() {
        // Please provide the solution below
    }

    public void removeFromInventory() {
        // Please provide the solution below
    }

    public void kerja() {
        // Please provide the solution below
    }

    public void olahraga() {
        // Please provide the solution below
    }

    public void tidur() {
        // Please provide the solution below
    }

    public void makan() {
        // Please provide the solution below
    }

    public void memasak(Masakan masakan) {
        //Cek apakah bahan komplit
        Thread t = new Thread(new Runnable() {
            public void run () {
                try {
                    boolean bahanAda = true;
                    for (Makanan makanan : masakan.getBahan()) {
                        if (!(inventory.isContain(makanan)) || inventory.jumlahItem(makanan) == 0) {
                            bahanAda = false;
                            break;
                        }
                    }

        //Memasak
                    if (bahanAda) {
                        for (Makanan makanan : masakan.getBahan()) {
                            inventory.removeItem(makanan, 1);
                        }

                    inventory.addItem(masakan, 1);
                    kesejahteraan.setMood(kesejahteraan.getMood()+10);
                    setStatus("Memasak");

                    int waktuMasak = (int) (1.5 * masakan.getKekenyangan());
                    Thread.sleep(waktuMasak*1000);

                    //After cooking
                    setStatus("idle");
                    } else {
                        System.out.println("Bahan makanan tidak tersedia");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void berkunjung() {
        // Please provide the solution below
    }

    public void buangAir() {
        // Please provide the solution below
    }

    public void upgradeRumah() {
        // Please provide the solution below
    }

    public void beliBarang(Objek objek) {
        // Please provide the solution below
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    if (objek instanceof Furnitur) {
                        Furnitur furnitur = (Furnitur) objek;
                        int harga = furnitur.getHarga();
                        if (uang >= harga) {
                            uang -= harga;
                            System.out.println("Berhasil membeli " + furnitur.getNama());
                            Random rand = new Random();
                            int waktuPengiriman = (rand.nextInt(6) + 1) * 30;
                            Thread.sleep(waktuPengiriman * 1000);
                            inventory.addItem(furnitur, 1);
                            System.out.println(furnitur.getNama() + " telah masuk ke dalam inventory");
                        } else {
                            System.out.println("Uang tidak cukup");
                        }
                    } else if (objek instanceof Makanan) {
                        Makanan makanan = (Makanan) objek;
                        int harga = makanan.getHarga();
                        if (uang >= harga) {
                            uang -= harga;
                            System.out.println("Berhasil membeli " + makanan.getNama());
                            Random rand = new Random();
                            int waktuPengiriman = (rand.nextInt(6) + 1) * 30;
                            Thread.sleep(waktuPengiriman * 1000);
                            inventory.addItem(makanan, 1);
                            System.out.println(makanan.getNama() + " telah masuk ke dalam inventory");
                        } else {
                            System.out.println("Uang tidak cukup");
                        }
                    } else {
                        System.out.println("Barang tidak dapat dibeli");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.run();
    }

    public void berpindahRuangan(Ruangan tujuanRuangan) {
        // Please provide the solution below
        Thread t = new Thread (new Runnable() {
            public void run() {
                if (rumah.getDaftarRuangan().contains(tujuanRuangan)) {
                    ruangan = tujuanRuangan;
                    System.out.println("Sim berhasil berpindah ke " + ruangan.getNama());
                } else {
                    System.out.println("Ruangan tidak tersedia");
                }
            }
        });
        t.run();        
    }

    public void melihatInventory() {
        // Please provide the solution below
    }

    public void memasangBarang() {
        // Please provide the solution below
    }

    public void melihatWaktu() {
        // Please provide the solution below
    }

    public void viewInfo() {
        System.out.println("Nama: " + namaLengkap);
        System.out.println("Pekerjaan: " + pekerjaan.getNamaKerja());
        System.out.println("Kesehatan: " + kesejahteraan.getKesehatan());
        System.out.println("Kekenyangan: " + kesejahteraan.getKekenyangan());
        System.out.println("Mood: " + kesejahteraan.getMood());
        System.out.println("Uang: " + uang + "\n");
    }

    public void yoga() {
        // Please provide the solution below
    }

    public void menontonFilm() {
        // Please provide the solution below
    }

    public void menggambar() {
        // Please provide the solution below
    }

    public void mainMusik() {
        // Please provide the solution below
    }

    public void mandi() {
        // Please provide the solution below
    }

    public void membersihkanRumah() {
        // Please provide the solution below
    }

    public void proyekan() {
        Thread t  = new Thread(new Runnable() {
            public void run() {
                kesejahteraan.setMood(kesejahteraan.getMood() - 10);
                setUang(getUang() + 200000);
            }
        });
        t.run();
    }
}

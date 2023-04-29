package if2212_tb_01_01.entities;
import java.util.*;

import javax.swing.Action;

import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.objects.*;
import if2212_tb_01_01.occupation.*;

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
            listAksiAktif.add(new Aksi ("KERJA", false));
            listAksiAktif.add(new Aksi ("OLAHRAGA", false));
            listAksiAktif.add(new Aksi ("TIDUR", true));
            listAksiAktif.add(new Aksi ("MAKAN", true));
            listAksiAktif.add(new Aksi ("MEMASAK", true));
            listAksiAktif.add(new Aksi ("BERKUNJUNG", false));
            listAksiAktif.add(new Aksi ("BUANG AIR", true));
            listAksiAktif.add(new Aksi ("YOGA", false));
            listAksiAktif.add(new Aksi ("BERDOA", false));
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
    private Point posisiObjek = new Point(-1,-1);

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

        //Menambahkan furnitur awal ke dalam inventory
        inventory.addItem(new Furnitur("KASUR SINGLE", new Point(-1,-1), false),1);
        inventory.addItem(new Furnitur("TOILET", new Point(-1,-1), false),1);
        inventory.addItem(new Furnitur("KOMPOR GAS", new Point(-1,-1), false),1);
        inventory.addItem(new Furnitur("MEJA DAN KURSI", new Point(-1,-1), false),1);
        inventory.addItem(new Furnitur("JAM", new Point(-1,-1), false),1);
    }

    public Sim(String namaLengkap, Pekerjaan pekerjaan) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = pekerjaan;
        this.uang = 100;
        this.inventory = new Inventory<Objek>();
        this.status = new ArrayList<Aksi>();
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
    public Ruangan getCurrentRuangan(){
        return currentRuangan;
    }
    public void setCurrentRuangan(Ruangan currentRuangan){
        this.currentRuangan = currentRuangan;
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
    public Rumah getRumah(){
        return rumah;
    }
    public void setRumah(Rumah rumah){
        this.rumah = rumah;
    }
    //Getter dan Setter
    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }
    public Point getPosisiObjek(){
        return posisiObjek;
    }
    public void setPosisiObjek(Point posisiObjek){
        this.posisiObjek = posisiObjek;
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
        System.out.println("SIM " + namaLengkap + " saat ini sedang berada di rumah dengan lokasi " + posisiRumah.toString() + " pada ruangan " + currentLocationRumah.getRuangan(posisiRuangan).getNama() + " dengan lokasi " + posisiRuangan.toString()+"\n");
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
                    //harusnya dikirim barang dulu trus kalo dah sampe baru masuk inventory
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


    
    public void goToObject(Furnitur objek) {
        this.posisiObjek = objek.getPosisi();
        System.out.println(this.getNamaLengkap() +" berhasil pindah ke objek "+ objek.getNama());
        Scanner scanner = new Scanner(System.in);
        if (objek.getKategori().equals("peralatan")){
            Furnitur furnitur = (Furnitur) objek;
            if (furnitur.getAksi().equals("TIDUR")){
                System.out.println("Apakah Anda ingin tidur? (Y/N)");
                String pilihanTidur = scanner.nextLine().toUpperCase();
                if (pilihanTidur.equals("Y")){
                    this.tidur();
                }
            }
            else if (furnitur.getAksi().equals("BUANG AIR")){
                System.out.println("Apakah Anda ingin buang air? (Y/N)");
                String pilihanBuangAir = scanner.nextLine().toUpperCase();
                if (pilihanBuangAir.equals("Y")){
                    this.buangAir();
                }
            } else if(furnitur.getAksi().equals("MASAK")){
                System.out.println("Apakah Anda ingin memasak? (Y/N)");
                String pilihanMasak = scanner.nextLine().toUpperCase();
                if (pilihanMasak.equals("Y")){
                    System.out.println("Masukkan nama masakan yang ingin dibuat: ");
                    // String namaMasakan = scanner.next();
                    // this.masak();
                }
            } else if (furnitur.getAksi().equals("MAKAN")){
                System.out.println("Apakah Anda ingin makan? (Y/N)");
                String pilihanMakan = scanner.nextLine().toUpperCase();
                if (pilihanMakan.equals("Y")){
                    this.makan();
                }
            } else if(furnitur.getAksi().equals("MELIHAT WAKTU")){
                System.out.println("Apakah Anda ingin melihat waktu? (Y/N)");
                String pilihanLihatWaktu = scanner.nextLine().toUpperCase();
                if (pilihanLihatWaktu.equals("Y")){
                    this.melihatWaktu();
                }
            } else if(furnitur.getAksi().equals("MELUKIS")){
                System.out.println("Apakah Anda ingin melukis? (Y/N)");
                String pilihanMelukis = scanner.nextLine().toUpperCase();
                if (pilihanMelukis.equals("Y")){
                    this.melukis();
                }
        } else if(furnitur.getAksi().equals("BERMAIN MUSIK")){
            System.out.println("Apakah Anda ingin bermain musik? (Y/N)");
            String pilihanMusik = scanner.nextLine().toUpperCase();
            if (pilihanMusik.equals("Y")){
                this.bermainMusik();
            }
        } else if(furnitur.getAksi().equals("MANDI")){
            System.out.println("Apakah Anda ingin mandi? (Y/N)");
            String pilihanMandi = scanner.nextLine().toUpperCase();
            if (pilihanMandi.equals("Y")){
                this.mandi();
            }
        } else if(furnitur.getAksi().equals("MEMBERSIHKAN RUMAH")){
            System.out.println("Apakah Anda ingin membersihkan rumah? (Y/N)");
            String pilihanBersihRumah = scanner.nextLine().toUpperCase();
            if (pilihanBersihRumah.equals("Y")){
                this.membersihkanRumah();
            }
        } else if(furnitur.getAksi().equals("PROYEKAN")){
            System.out.println("Apakah Anda ingin mengerjakan proyek? (Y/N)");
            String pilihanProyekan = scanner.nextLine().toUpperCase();
            if (pilihanProyekan.equals("Y")){
                this.proyekan();
            }
        } else {
            System.out.println("Aksi tidak tersedia");
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

    public void gantiPekerjaan() {
        //Please provide the solution below
    }

    public void olahraga() {
        //Please provide the solution below
        this.status.add(new Aksi(this, "Olahraga", 20));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        try {
            int waktu = 20;
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(1000);
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Yey sudah selesai olahraga");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 10);
        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 5);
        this.kesejahteraan.setKekenyangan(this.kesejahteraan.getKekenyangan() - 5);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 5);
        this.isDoAksiAktif = false;
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
        this.status.add(new Aksi(this, "Tidur", waktuTidur));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        try {
            int waktu = waktuTidur*60;
            int seconds = 0;
            for (int i=0; i<waktu; i++){
                Thread.sleep(1000); // tunggu 1 detik
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Selamat bangun tidur!");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKesehatan(this.kesejahteraan.getKesehatan() + 20);
        
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
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Pilih nomor ruangan yang ingin dijadikan acuan penambahan: ");
        int pilihan1 = scanner.nextInt();
        Ruangan ruangan = this.rumah.getDaftarRuangan().get(pilihan1 - 1);
        System.out.println("Pilih posisi ruangan baru (atas/bawah/kiri/kanan): ");
        String pilihan = scanner.nextLine();
        this.status.add(new Aksi(this, "Upgrade Rumah", 18));
        Point point = new Point(-1,-1);
        if (pilihan.equals("atas")) {
            System.out.println("Masukkan nama ruangan: ");
            String namaRuangan = scanner.nextLine();
            point.setX(ruangan.getPosisi().getX());
            point.setY(ruangan.getPosisi().getY() + 1);
            this.rumah.addRuangan(new Ruangan(namaRuangan, point, false));
        } else if (pilihan.equals("bawah")) {
            System.out.println("Masukkan nama ruangan: ");
            String namaRuangan = scanner.nextLine();
            point.setX(ruangan.getPosisi().getX());
            point.setY(ruangan.getPosisi().getY() - 1);
            this.rumah.addRuangan(new Ruangan(namaRuangan, point, false));
        } else if (pilihan.equals("kiri")) {
            System.out.println("Masukkan nama ruangan: ");
            String namaRuangan = scanner.nextLine();
            point.setX(ruangan.getPosisi().getX() - 1);
            point.setY(ruangan.getPosisi().getY());
            this.rumah.addRuangan(new Ruangan(namaRuangan, point, false));
        } else if (pilihan.equals("kanan")) {
            System.out.println("Masukkan nama ruangan: ");
            String namaRuangan = scanner.nextLine();
            point.setX(ruangan.getPosisi().getX() + 1);
            point.setY(ruangan.getPosisi().getY());
            this.rumah.addRuangan(new Ruangan(namaRuangan, point, false));
        }
        
        System.out.println("Rumah sedang diupgrade, silahkan tunggu 18 menit!");
        int indexstatus = this.status.size() - 1;
        try {
            int waktu = 18*60;
            int seconds = 0;
            for (int i=0; i<waktu; i++){
                Thread.sleep(1000); // tunggu 1 detik
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexstatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexstatus);
            this.rumah.getRuangan(point).setIsBuilded(true);
            System.out.println("Rumah berhasil di upgrade!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void berpindahRuangan(Ruangan tujuanRuangan) {
        //Please provide the solution below
        if (rumah.getDaftarRuangan().contains(tujuanRuangan)) {
            currentRuangan = tujuanRuangan;
            // this.status.add(new Aksi(this,"berpindahRuangan", 0));
            System.out.println("Sim berhasil berpindah ke " + currentRuangan.getNama());
        } else {
            System.out.println("Ruangan tidak tersedia");
        }
    }

    public void berpindahRuangan(Point pointTujuan, String namaRuangan) {
        posisiRuangan = pointTujuan;
        posisiObjek.setPoint(-1, -1); //sim tidak berada di objek
        System.out.println("Sim berhasil berpindah ke " + namaRuangan);
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
        
    }

    public void berdoa(){

    }

    public void mandi(){

    }

    public void melukis(){
        this.status.add(new Aksi(this, "Melukis", 2*60));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Mozart sedang beraksi?");
        try {
            int waktu = 2*60;
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(1000);
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Kren bngt banh lukisannya :<");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 10);
        this.isDoAksiAktif = false;
    }

    public void bermainMusik(){
        this.status.add(new Aksi(this, "Main musik", 2*60));
        this.isDoAksiAktif = true;
        int indexStatus = this.status.size() - 1;
        System.out.println("Yippi main musik");
        try {
            int waktu = 2*60;
            int seconds = 0;
            for (int i = 0; i < waktu; i++) {
                Thread.sleep(1000);
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    this.getAksi(indexStatus).kurangiMenitTersisa(1);
                }
            }
            this.status.remove(indexStatus);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Jago banget main musiknya kak :<");
        this.kesejahteraan.setMood(this.kesejahteraan.getMood() + 30);
        this.kesejahteraan.setKebersihan(this.kesejahteraan.getKebersihan() - 5);
        this.isDoAksiAktif = false;
    }

    public void membersihkanRumah(){

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
        this.status.add(new Aksi(this,"proyekan", 3));
        this.kesejahteraan.setMood(this.kesejahteraan.getMood()-5);
        setUang(getUang() + 100);
        int waktu = 180;
        int indexStatus = this.status.size() - 1;
        this.isDoAksiAktif = true;
        // ActionThread thread = new ActionThread(indexStatus, this, waktu);
        // thread.start();
    }   
}


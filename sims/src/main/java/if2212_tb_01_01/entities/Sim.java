package if2212_tb_01_01.entities;
import java.util.*;
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

    public Sim(String namaLengkap, Pekerjaan pekerjaan) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = pekerjaan;
        this.uang = 100;
        this.inventory = new Inventory();
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
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
}

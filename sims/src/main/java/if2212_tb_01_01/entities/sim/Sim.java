package if2212_tb_01_01.entities.sim;

import java.util.*;

import javax.swing.Action;

import if2212_tb_01_01.utils.*;
import if2212_tb_01_01.entities.Inventory;
import if2212_tb_01_01.entities.Kesejahteraan;
import if2212_tb_01_01.entities.World;
import if2212_tb_01_01.entities.action.Aksi;
import if2212_tb_01_01.entities.action.AksiAktif.*;
import if2212_tb_01_01.entities.action.AksiPasif.AksiPasif;
import if2212_tb_01_01.entities.action.AksiPasif.BeliBarang;
import if2212_tb_01_01.entities.action.AksiPasif.BerakChecker;
import if2212_tb_01_01.entities.action.AksiPasif.UpgradeRumah;
import if2212_tb_01_01.entities.house.Rumah;
import if2212_tb_01_01.entities.room.Ruangan;
import if2212_tb_01_01.objects.*;
import if2212_tb_01_01.objects.bahanmakanan.BahanMakanan;
import if2212_tb_01_01.objects.furnitur.Furnitur;
import if2212_tb_01_01.objects.masakan.Masakan;
import if2212_tb_01_01.occupation.*;

/**
 * Class of Sim Object
 *  
 */
public class Sim {

    /* Sim Attributes */
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory inventory;
    private Kesejahteraan kesejahteraan;
    private Aksi aksi = null;
    private List<AksiPasif> listAksiPasif = new ArrayList<>();
    private List<AksiPasif> berakChecker = new ArrayList<>();
    private int sisaDetikGantiKerja = 12*60;
    private int durasiKerja = 0;
    private Boolean isBisaKerja = true;
    private boolean isUdhTidur = false;
    private boolean isDoAksiAktif = false;
    private boolean isBerkunjung = false;
    private Rumah rumah;
    private Rumah currentRumah;
    private Ruangan currentRuangan;
    private Point posisiObjek;

    public Sim(Kesejahteraan kesejahteraan, int uang, Pekerjaan pekerjaan, String namaLengkap) {
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.pekerjaan = pekerjaan;
        this.namaLengkap = namaLengkap;
        // this.status = new ArrayList<Aksi>();
        this.inventory = new Inventory();
    }

    // konstruktor kl pekerjaan di random
    public Sim(Kesejahteraan kesejahteraan, int uang, String namaLengkap, Rumah rumah) {
        this.pekerjaan = new Pekerjaan();
        this.kesejahteraan = kesejahteraan;
        this.uang = uang;
        this.namaLengkap = namaLengkap;
        // this.status = new ArrayList<Aksi>();
        this.inventory = new Inventory();
        this.rumah = rumah;
        this.currentRumah = rumah;
        this.currentRuangan = rumah.getRuanganAwal();
        
        inventory.incItem(0);
        inventory.incItem(3);
        inventory.incItem(4);
        inventory.incItem(6);
        inventory.incItem(10);
    }

    public Sim(String namaLengkap, Pekerjaan pekerjaan) {
        this.namaLengkap = namaLengkap;
        this.pekerjaan = pekerjaan;
        this.uang = 100;
        this.inventory = new Inventory();
        // this.status = new ArrayList<Aksi>();
    }

    public List<AksiPasif> getBerakChecker(){
        return berakChecker;
    }

    public int getDurasiKerja(){
        return durasiKerja;
    }

    public void incDurasiKerja(int waktu){
        durasiKerja += waktu;
    }

    public boolean isBerakCheckerNull(){
        return (berakChecker == null);
    }

    public void setBerakChecker(List<AksiPasif> berakChecker){
        this.berakChecker = berakChecker; 
    }

    public boolean getIsUdhTidur(){
        return isUdhTidur;
    }

    public void setIsUdhTidur(boolean isUdhTidur){
        this.isUdhTidur = isUdhTidur;
    }

    public Aksi getAksi(){
        return aksi;
    }

    public void setAksi(Aksi aksi){
        this.aksi = aksi;
    }

    public List<AksiPasif> getListAksiPasif(){
        return listAksiPasif;
    }

    public int getSisaDetikGantiKerja(){
        return sisaDetikGantiKerja;
    }
    public boolean getIsBisaKerja(){
        return isBisaKerja;
    }
    public void setSisaDetikGantiKerja(int waktu){
        sisaDetikGantiKerja = waktu;
    }
    public void setIsBisaKerja(boolean status){
        isBisaKerja = status;
    }

    public Ruangan getCurrentRuangan() {
        return currentRuangan;
    }

    public Rumah getRumah(){
        return rumah;
    }

    public void setCurrentRuangan(Ruangan currentRuangan) {
        this.currentRuangan = currentRuangan;
    }

    public Rumah getCurrentRumah() {
        return currentRumah;
    }

    public void setCurrentRumah(Rumah currentRumah) {
        this.currentRumah = currentRumah;
    }

    public boolean getIsDoAksiAktif() {
        return isDoAksiAktif;
    }

    public void setIsDoAksiAktif(boolean isDoAksiAktif) {
        this.isDoAksiAktif = isDoAksiAktif;
    }

    // public Aksi getAksi(int index) {
    //     return status.get(index);
    // }

    // Getter dan Setter
    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Point getPosisiObjek() {
        return posisiObjek;
    }

    public void setPosisiObjek(Point posisiObjek) {
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Kesejahteraan getKesejahteraan() {
        return kesejahteraan;
    }

    //Ganti kerja
    public void changeKerja(){
        Scanner scanner = new Scanner(System.in);
        String command;
        if(sisaDetikGantiKerja <= 0){
            System.out.println("1. Badut sulap (Gaji: 15)");
            System.out.println("2. Koki (Gaji: 30)");
            System.out.println("3. Polisi (Gaji: 35)");
            System.out.println("4. Programmer (Gaji: 45)");
            System.out.println("5. Dokter (Gaji: 50)");
            System.out.print("Masukkan nama pekerjaan: ");
            command = scanner.nextLine().toUpperCase();
            if(command.equals(this.getPekerjaan().getNamaKerja())){
                System.out.println("Nama yang dipilih sama seperti pekerjaan sebelumnya, change kerja gagal");
            }
            else{
                if(command.equals("DOKTER")){
                    Pekerjaan pekerjaan = new Pekerjaan.PekerjaanBuilder("DOKTER", "Dokter memiliki gaji harian 50", 50).build();
                    Double biaya = 0.5 * pekerjaan.getGaji();
                    if(this.getUang()>= Math.round(biaya.floatValue())){
                        this.setPekerjaan(pekerjaan);
                        this.setUang(this.getUang() - Math.round(biaya.floatValue()));
                        System.out.println("Berhasil ganti kerja jadi "+ pekerjaan.getNamaKerja());
                        this.setSisaDetikGantiKerja(12*60);
                        this.setIsBisaKerja(false);
                        this.durasiKerja = 0;
                    }
                }
                else if(command.equals("KOKI")){
                    Pekerjaan pekerjaan = new Pekerjaan.PekerjaanBuilder("KOKI", "Koki memiliki gaji harian 30", 30).build();
                    Double biaya = 0.5 * pekerjaan.getGaji();
                    if(this.getUang()>= Math.round(biaya.floatValue())){
                        this.setPekerjaan(pekerjaan);
                        this.setUang(this.getUang() - Math.round(biaya.floatValue()));
                        System.out.println("Berhasil ganti kerja jadi "+ pekerjaan.getNamaKerja());
                        this.setSisaDetikGantiKerja(12*60);
                        this.setIsBisaKerja(false);
                        this.durasiKerja = 0;
                    }
    
                }
                else if(command.equals("POLISI")){
                    Pekerjaan pekerjaan = new Pekerjaan.PekerjaanBuilder("POLISI", "Polisi memiliki gaji harian 35", 35).build();
                    Double biaya = 0.5 * pekerjaan.getGaji();
                    if(this.getUang()>= Math.round(biaya.floatValue())){
                        this.setPekerjaan(pekerjaan);
                        this.setUang(this.getUang() - Math.round(biaya.floatValue()));
                        System.out.println("Berhasil ganti kerja jadi "+ pekerjaan.getNamaKerja());
                        this.setSisaDetikGantiKerja(12*60);
                        this.setIsBisaKerja(false);
                        this.durasiKerja = 0;
                    }
                }
                else if(command.equals("PROGRAMMER")){
                    Pekerjaan pekerjaan = new Pekerjaan.PekerjaanBuilder("PROGRAMMER", "Programmer memiliki gaji harian 45", 45).build();
                    Double biaya = 0.5 * pekerjaan.getGaji();
                    if(this.getUang()>= Math.round(biaya.floatValue())){
                        this.setPekerjaan(pekerjaan);
                        this.setUang(this.getUang() - Math.round(biaya.floatValue()));
                        System.out.println("Berhasil ganti kerja jadi "+ pekerjaan.getNamaKerja());
                        this.setSisaDetikGantiKerja(12*60);
                        this.setIsBisaKerja(false);
                        this.durasiKerja = 0;
                    }
                }
                else if(command.equals("BADUT SULAP")){
                    Pekerjaan pekerjaan = new Pekerjaan.PekerjaanBuilder("BADUT SULAP", "Badut sulap memiliki gaji harian 15", 15).build();
                    Double biaya = 0.5 * pekerjaan.getGaji();
                    if(this.getUang()>= Math.round(biaya.floatValue())){
                        this.setPekerjaan(pekerjaan);
                        this.setUang(this.getUang() - Math.round(biaya.floatValue()));
                        System.out.println("Berhasil ganti kerja jadi "+ pekerjaan.getNamaKerja());
                        this.setSisaDetikGantiKerja(12*60);
                        this.setIsBisaKerja(false);
                        this.durasiKerja = 0;
                    }
                }
                else{
                    System.out.println("Input tidak valid! Change kerja gagal");
                }
            }
            
        }
        else{
            System.out.println("Kamu belum bisa ganti kerja! (Hint: harus terpenuhi 12 menit kerja)");
        }
    }

    //Aksi Non Waktu
    public void masangBarang(){
        Scanner scanner = new Scanner(System.in);
        this.getInventory().printOpsiMasangBarang();
        System.out.print("Masukkan nomor barang yang ingin dipasang: ");
        Integer nomor = InputChecker.toAngka(scanner.nextLine());
        if(!nomor.equals(-999)){
            if(nomor >= 1 && nomor <=12){
                if(inventory.isItemAda(nomor - 1)){
                    this.getCurrentRuangan().printMapRuangan();
                    System.out.println("Hint: pojok kiri atas adalah koordinat 0,0");
                    System.out.println("x memanjang ke kanan, dan y memanjang ke bawah");
                    System.out.println("Yang dijadikan acuan adalah bagian pojok kiri atas objek\n");
                    boolean inputValid = false;
                    while(!inputValid){
                        System.out.println("Masukkan posisi dari barang yang ingin dipasang");
                        String posisi = scanner.nextLine();
                        boolean posisiValid = InputChecker.isPointRuangan(posisi);
                        if(posisiValid){
                            Point posisiObjek = Point.makePoint(posisi);
                            if(this.getCurrentRuangan().isMapTerisi((Furnitur) this.inventory.getInventory().get(nomor - 1), posisiObjek)){
                                System.out.println("Input tidak valid! masukkan posisi lain");
                            }
                            else{
                                inputValid = true;
                                this.getCurrentRuangan().addObjekFromInventory((Furnitur) this.inventory.getInventory().get(nomor - 1), posisiObjek, inventory);
                                System.out.println(this.inventory.getInventory().get(nomor - 1).getNama()+" sudah berhasil tersimpan");
                            }
                        }
                    }
                }
                else{
                    System.out.println("Item tidak tersedia! Masang barang gagal dilakukan");
                }   
            }
            else{
                System.out.println("Input tidak valid! Masang barang gagal dilakukan");
            }
        }  
    }

    public void ambilBarang(){
        Scanner scanner = new Scanner(System.in);
        this.getCurrentRuangan().printMapRuangan();
        this.getCurrentRuangan().printDaftarObjek();
        System.out.print("Masukkan nomor item yang ingin diambil: ");
        Integer nomor = InputChecker.toAngka(scanner.nextLine());
        if(!nomor.equals(-999)){
            if(nomor >= 1 && nomor <= this.currentRuangan.getDaftarObjek().size()){
                this.currentRuangan.removeObjekToInventory(nomor - 1, inventory);
                System.out.println("Barang berhasil diambil");
            }
            else{
                System.out.println("Input tidak valid! Ambil barang gagal dilakukan");
            }
        }
    }

    public void masak() {
        Scanner scanner = new Scanner(System.in);
        this.getInventory().printOpsiMasak();
        System.out.print("Masukkan nomor item yang ingin dimasak: ");
        Integer index = InputChecker.toAngka(scanner.nextLine());
        if(!index.equals(-999)){
            int indexItem = index + 19;
            if(indexItem>=20 && indexItem <=24){
                if(this.getInventory().isBisaMasak(indexItem)){
                    Masakan masak = (Masakan) this.getInventory().getInventory().get(indexItem);
                    Double t = (1.5 * masak.getKekenyangan());
                    Integer waktu = Math.round(t.floatValue());
                    this.setAksi(new Masak(this,waktu,indexItem));
                    // this.setIsDoAksiAktif(true);
                    this.getAksi().run();
                    System.out.println("Sudah selesai masak!");
                }
                else{
                    System.out.println("Bahan untuk membuat item tidak tersedia! Aksi masak gagal");
                }
            }
            else{
                System.out.println("Input tidak valid! Aksi masak gagal");
            }
        }
        
    }

    public void beliBarang() {
        Scanner scanner = new Scanner(System.in);
        this.getInventory().printOpsiBeliBarang();
        System.out.print("Masukkan nomor item yang ingin dibeli: ");
        Integer nomor = InputChecker.toAngka(scanner.nextLine());
        if(!nomor.equals(-999)){
            if(nomor >=1 && nomor <= 20){
                if(nomor >= 1 && nomor <= 12){
                    Furnitur x = (Furnitur) this.getInventory().getInventory().get(nomor - 1);
                    if(this.getUang() >= x.getHarga()){
                        this.setUang(this.getUang() - x.getHarga());
                        Random rand = new Random();
                        int min = 1;
                        int max = 5;
                        int randomNum = rand.nextInt((max - min) + 1) + min;
                        int waktubeli = randomNum * 30;
                        this.listAksiPasif.add(new BeliBarang(this,waktubeli, nomor - 1));
                        System.out.println("Furnitur berhasil dibeli! silakan menunggu");
                    }
                }
                else if(nomor >= 13 && nomor <= 20){
                    BahanMakanan x = (BahanMakanan) this.getInventory().getInventory().get(nomor - 1);
                    if(this.getUang() >= x.getHarga()){
                        this.setUang(this.getUang() - x.getHarga());
                        Random rand = new Random();
                        int min = 1;
                        int max = 5;
                        int randomNum = rand.nextInt((max - min) + 1) + min;
                        int waktubeli = randomNum * 30;
                        this.listAksiPasif.add(new BeliBarang(this,waktubeli, nomor - 1));
                        System.out.println("Bahan Makanan berhasil dibeli! silakan menunggu");
                    }
                }
            }
            else{
                System.out.println("Input tidak valid! Beli barang gagal dilakukan");
            }
        }
    }

    public void upgradeHouse(){
        Scanner scanner = new Scanner(System.in);
        if(this.getUang() >= 1500){
            System.out.println("Kamu sedang berada di ruangan "+ this.getCurrentRuangan().getNama());
            if(currentRuangan.isAdaRoomAbove() && currentRuangan.isAdaRoomBelow() && currentRuangan.isAdaRoomLeft() && currentRuangan.isAdaRoomRight()){
                System.out.println("Tidak terdapat opsi penambahan ruangan yang tersedia, silakan pindah ruangan terlebih dahulu");
            }
            else{
                System.out.print("Masukkan arah ruangan yang ingin dibangun ( Pilihan opsi -> "+ currentRuangan.opsiUpgradeRoom()+"): ");
                String arah = scanner.nextLine().toUpperCase();
                if(arah.equals("KANAN")){
                    if(!this.getCurrentRuangan().isAdaRoomRight()){
                        boolean inputValid = false;
                        while(!inputValid){
                            System.out.print("Masukkan nama ruangan: ");
                            String namaRuangan = scanner.nextLine();
                            if(!namaRuangan.isBlank()){
                                inputValid = true;
                                this.getCurrentRuangan().setRoomRight(new Ruangan(namaRuangan,false));
                                this.listAksiPasif.add( new UpgradeRumah(this, 18*60,this.getCurrentRuangan().getRoomRight()));
                                System.out.println("Berhasil menambah ruangan! Silakan menunggu 18 menit");
                                this.setUang(this.getUang() - 1500);
                            }
                            else{
                                System.out.println("Input tidak valid! (Hint : input anda kosong)");
                            }
                        }
                    }
                    else{
                        System.out.println("Ruangan sudah terisi! Upgrade rumah gagal");
                    }
                }
                if(arah.equals("KIRI")){
                    if(!this.getCurrentRuangan().isAdaRoomLeft()){
                        boolean inputValid = false;
                        while(!inputValid){
                            System.out.print("Masukkan nama ruangan: ");
                            String namaRuangan = scanner.nextLine();
                            if(!namaRuangan.isBlank()){
                                inputValid = true;
                                this.getCurrentRuangan().setRoomLeft(new Ruangan(namaRuangan,false));
                                this.listAksiPasif.add( new UpgradeRumah(this, 18*60,this.getCurrentRuangan().getRoomLeft()));
                                System.out.println("Berhasil menambah ruangan! Silakan menunggu 18 menit");
                                this.setUang(this.getUang() - 1500);
                            }
                            else{
                                System.out.println("Input tidak valid! (Hint : input anda kosong)");
                            }
                        }
                    }
                    else{
                        System.out.println("Ruangan sudah terisi! Upgrade rumah gagal");
                    }
                }
                if(arah.equals("ATAS")){
                    if(!this.getCurrentRuangan().isAdaRoomAbove()){
                        boolean inputValid = false;
                        while(!inputValid){
                            System.out.print("Masukkan nama ruangan: ");
                            String namaRuangan = scanner.nextLine();
                            if(!namaRuangan.isBlank()){
                                inputValid = true;
                                this.getCurrentRuangan().setRoomAbove(new Ruangan(namaRuangan,false));
                                this.listAksiPasif.add( new UpgradeRumah(this, 18*60,this.getCurrentRuangan().getRoomAbove()));
                                System.out.println("Berhasil menambah ruangan! Silakan menunggu 18 menit");
                                this.setUang(this.getUang() - 1500);
                            }
                            else{
                                System.out.println("Input tidak valid! (Hint : input anda kosong)");
                            }
                        }
                    }
                    else{
                        System.out.println("Ruangan sudah terisi! Upgrade rumah gagal");
                    }
                }
                if(arah.equals("BAWAH")){
                    if(!this.getCurrentRuangan().isAdaRoomBelow()){
                        boolean inputValid = false;
                        while(!inputValid){
                            System.out.print("Masukkan nama ruangan: ");
                            String namaRuangan = scanner.nextLine();
                            if(!namaRuangan.isBlank()){
                                inputValid = true;
                                this.getCurrentRuangan().setRoomBelow(new Ruangan(namaRuangan,false));
                                this.listAksiPasif.add( new UpgradeRumah(this, 18*60,this.getCurrentRuangan().getRoomBelow()));
                                System.out.println("Berhasil menambah ruangan! Silakan menunggu 18 menit");
                                this.setUang(this.getUang() - 1500);
                            }
                            else{
                                System.out.println("Input tidak valid! (Hint : input anda kosong)");
                            }
                        }
                    }
                    else{
                        System.out.println("Ruangan sudah terisi! Upgrade rumah gagal");
                    }
                }

            }    
        }
        else{
            System.out.println("Uang untuk upgrade house tidak cukup! Upgrade house gagal dilakukan!");
        }
    }

    public void kerja() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.print("Masukkan waktu kerja (Kelipatan 120) dalam detik: ");
            Integer waktuTidur = InputChecker.toAngka(scanner.nextLine());
            if(!waktuTidur.equals(-999)){
                if(waktuTidur % 120 == 0 && waktuTidur != 0){
                    inputValid = true;
                    System.out.println("Kerja! Kerja! Kerja!");
                    this.setAksi(new Kerja(this,waktuTidur));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Kerja bagus! Tetap bersama! ");
                    this.isDoAksiAktif = false;
                    if(sisaDetikGantiKerja > 0){
                        this.setSisaDetikGantiKerja(this.getSisaDetikGantiKerja() - waktuTidur);
                        if(getSisaDetikGantiKerja()<=0){
                            System.out.println("Kamu sudah bisa ganti kerja");
                        }
                    }
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 120!)");
                }
            }
        }
    }

    public void olahraga() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.println("Masukkan waktu olahraga (Kelipatan 20) dalam detik: ");
            Integer waktuOlahraga = InputChecker.toAngka(scanner.nextLine());
            if(!waktuOlahraga.equals(-999)){
                if(waktuOlahraga % 20 == 0){
                    inputValid = true;
                    this.setAksi(new Olahraga(this,waktuOlahraga));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai olahraga! ");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 20!)");
                }
            }
        }
    }

    public void tidur() {
        // Please provide the solution below
        
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.print("Masukkan waktu tidur (Kelipatan 240) dalam detik: ");
            Integer waktuTidur = InputChecker.toAngka(scanner.nextLine());
            if(!waktuTidur.equals(-999)){
                if(waktuTidur % 240 == 0){
                    inputValid = true;
                    this.setIsUdhTidur(true);
                    this.setAksi(new Tidur(this,waktuTidur));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Selamat bangun tidur masbro, kerja woi tidur mulu! ");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 240!)");
                }
            }
        }
    }

    public void makan() {
        Scanner scanner = new Scanner(System.in);
        this.getInventory().printOpsiMakan();
        System.out.print("Masukkan nomor item yang ingin dimakan: ");
        Integer index = InputChecker.toAngka(scanner.nextLine());
        if(!index.equals(-999)){
            int indexItem = index + 11;
            if(indexItem>=12 && indexItem <=24){
                if(this.getInventory().isItemAda(indexItem)){
                    this.setAksi(new Makan(this,30,indexItem));
                    this.getAksi().run();
                    System.out.println("Sudah selesai makan! jangan lupa berak ya bro (Hint: Harus buang air setelah 4 menit makan)");
                    this.berakChecker.add(new BerakChecker(this, 4*60));
                }
                else{
                    System.out.println("Item tidak tersedia! Aksi makan gagal");
                }
            }
            else{
                System.out.println("Input tidak valid! Aksi makan gagal");
            }
        }
        // Please provide the solution below
    }

    public void berkunjung(World world){
        Scanner scanner = new Scanner(System.in);
        world.printListSim();
        System.out.print("Masukkan nomor rumah sim yang ingin dikunjungi: ");
        Integer nomor = InputChecker.toAngka(scanner.nextLine());
        if(!nomor.equals(-999)){
            if(nomor >= 1 && nomor <= world.getListSim().size()){
                if(!this.getCurrentRumah().getPosisi().isPointEqual(world.getListSim().get(nomor - 1).getRumah().getPosisi())){
                    Point posisiAwal = this.getCurrentRumah().getPosisi();
                    Point posisiAkhir = world.getListSim().get(nomor - 1).getRumah().getPosisi();
                    Double waktuD = Math.sqrt((posisiAwal.getX() - posisiAkhir.getX())*(posisiAwal.getX() - posisiAkhir.getX()) + (posisiAwal.getY() - posisiAkhir.getY())*(posisiAwal.getY() - posisiAkhir.getY()));
                    Integer waktu = Math.round(waktuD.floatValue());
                    this.aksi = new Berkunjung(this, waktu, world.getListSim().get(nomor - 1).getRumah());
                    aksi.run();
                    System.out.println("Kamu sudah sampai di rumah "+world.getSim(nomor - 1).getNamaLengkap());
                }
                else{
                    System.out.println("Lokasimu sudah berada di situ! Berkunjung gagal");
                }
            }
            else{
                System.out.println("Input tidak valid! Berkunjung gagal");
            }
        }
    }

    public void buangAir() {
        System.out.println("Pengen berak cokk...");
        this.setAksi(new BuangAir(this,10));
        this.isDoAksiAktif = true;
        this.getAksi().run();
        System.out.println("Ah akhirnya lega");
        this.isDoAksiAktif = false;
        this.berakChecker.clear();
    }

    public void yoga() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.print("Masukkan waktu yoga (Kelipatan 60) dalam detik: ");
            Integer waktuYoga = InputChecker.toAngka(scanner.nextLine());
            if(!waktuYoga.equals(-999)){
                if(waktuYoga % 60 == 0 && waktuYoga != 0){
                    inputValid = true;
                    this.setAksi(new Yoga(this,waktuYoga));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai yoga");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 60!)");
                }
            }
        }
    }

    public void berdoa() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.print("Masukkan waktu berdoa dalam detik: ");
            Integer waktuBerdoa = InputChecker.toAngka(scanner.nextLine());
            if(!waktuBerdoa.equals(-999)){
                if(waktuBerdoa >= 30){
                    inputValid = true;
                    this.setAksi(new Berdoa(this,waktuBerdoa));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai berdoa, semoga doamu terkabul");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: Gak malu mas doanya cepet?)");
                }
            }
        }
    }

    public void mandi() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.println("Masukkan waktu mandi dalam detik: ");
            Integer waktuMandi = InputChecker.toAngka(scanner.nextLine());
            if(!waktuMandi.equals(-999)){
                if(waktuMandi >= 60){
                    inputValid = true;
                    this.setAksi(new Yoga(this,waktuMandi));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai Mandi, wangi bang");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: Cepet cepet amat bang (Minimal 60 detik))");
                }
            }
        }
    }

    public void melukis() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.println("Masukkan waktu Melukis (Kelipatan 10) dalam detik: ");
            Integer waktuMelukis = InputChecker.toAngka(scanner.nextLine());
            if(!waktuMelukis.equals(-999)){
                if(waktuMelukis % 10 == 0 && waktuMelukis != 0){
                    inputValid = true;
                    this.setAksi(new Yoga(this,waktuMelukis));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai Melukis");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 10!)");
                }
            }
        }
    }

    public void bermainMusik() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.println("Masukkan waktu Main musik (Kelipatan 30) dalam detik: ");
            Integer waktuMelukis = InputChecker.toAngka(scanner.nextLine());
            if(!waktuMelukis.equals(-999)){
                if(waktuMelukis % 30 == 0 && waktuMelukis != 0){
                    inputValid = true;
                    this.setAksi(new MainMusik(this,waktuMelukis));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai Main Musik");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 30!)");
                }
            }
        }
    }

    public void membersihkanRumah() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.println("Masukkan waktu Membersihkan Rumah (Kelipatan 60) dalam detik: ");
            Integer waktuMelukis = InputChecker.toAngka(scanner.nextLine());
            if(!waktuMelukis.equals(-999)){
                if(waktuMelukis % 60 == 0 && waktuMelukis != 0){
                    inputValid = true;
                    this.setAksi(new BersihRumah(this,waktuMelukis));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai Membersihkan Rumah");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 60!)");
                }
            }
        }
    }

    public void viewInfo() {
        System.out.println("Nama: " + namaLengkap);
        System.out.println("Pekerjaan: " + pekerjaan.getNamaKerja());
        System.out.println("Kesehatan: " + kesejahteraan.getKesehatan());
        System.out.println("Kekenyangan: " + kesejahteraan.getKekenyangan());
        System.out.println("Kebersihan: "+kesejahteraan.getKebersihan());
        System.out.println("Mood: " + kesejahteraan.getMood());
        System.out.println("Uang: " + uang + "\n");
    }

    public void viewCurrentLocation(){
        System.out.println("Saat ini Sim "+this.getNamaLengkap()+" sedang berada di");
        System.out.println("Rumah : "+this.getCurrentRumah().getKepemilikan()+ " (lokasi: "+ this.getCurrentRumah().getPosisi().toString()+")");
        System.out.println("Ruangan : "+this.getCurrentRuangan().getNama());
    }

    public void viewInventory(){
        this.getInventory().displayInventory();
    }

    public void proyekan() {
        Scanner scanner = new Scanner(System.in);
        boolean inputValid = false;
        while(!inputValid){
            System.out.println("Masukkan waktu proyekan (Kelipatan 120) dalam detik: ");
            Integer waktuMelukis = InputChecker.toAngka(scanner.nextLine());
            if(!waktuMelukis.equals(-999)){
                if(waktuMelukis % 120 == 0 && waktuMelukis != 0){
                    inputValid = true;
                    this.setAksi(new Proyekan(this,waktuMelukis));
                    this.isDoAksiAktif = true;
                    this.getAksi().run();
                    System.out.println("Sudah selesai Proyekan");
                    this.isDoAksiAktif = false;
                }
                else{
                    System.out.println("Input tidak valid! (Hint: input harus kelipatan 120!)");
                }
            }
        }
    }
}

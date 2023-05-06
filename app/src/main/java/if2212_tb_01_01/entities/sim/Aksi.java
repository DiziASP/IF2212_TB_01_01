package if2212_tb_01_01.entities.sim;

import if2212_tb_01_01.entities.room.Room;
import java.util.ArrayList;
import java.util.List;

public class Aksi{
    private String nama;
    private Sim sim;
    private int detikTersisa;
    private boolean isButuhObjek;
    private int indexBeli;
    private boolean isAksiPasif;
    private Room roomUpgrade;
    public Aksi(Sim sim, String nama, int jumlahSeconds){
        this.sim = sim;
        this.nama = nama;
        this.detikTersisa = jumlahSeconds;
        this.isAksiPasif =false;
    }
    public Aksi(String nama, boolean isButuhObjek){
        this.nama = nama;
        this.isButuhObjek = isButuhObjek;
        this.detikTersisa = -1;
        this.sim = null;
    }

    public Aksi(Sim sim, String nama, int jumlahSeconds, boolean isAksiPasif){
        this.sim = sim;
        this.nama = nama;
        this.detikTersisa = jumlahSeconds;
        this.isAksiPasif = isAksiPasif;
    }

    public Aksi(Sim sim, String nama, int jumlahSeconds, boolean isAksiPasif, int indexBeli){
        this.sim = sim;
        this.nama = nama;
        this.detikTersisa = jumlahSeconds;
        this.isAksiPasif = isAksiPasif;
        this.indexBeli = indexBeli;
    }

    public Aksi(Sim sim, String nama, int jumlahSeconds, boolean isAksiPasif, Room roomUpgrade){
        this.sim = sim;
        this.nama = nama;
        this.detikTersisa = jumlahSeconds;
        this.isAksiPasif = isAksiPasif;
        this.roomUpgrade = roomUpgrade;
    }

    public String getNama(){
        return nama;
    }
    public boolean getIsAksiPasif(){
        return isAksiPasif;
    }
    public int getDetikTersisa(){
        return detikTersisa;
    }
    public void setDetikTersisa(int detikTersisa){
        this.detikTersisa = detikTersisa;
    }
    public void decDetikTersisa(){
        this.detikTersisa -= 1;
    }
    public int getIndexBeli(){
        return indexBeli;
    }
    public Room getRoomUpgrade(){
        return roomUpgrade;
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

package if2212_tb_01_01.objects;
import if2212_tb_01_01.utils.Point;

import java.util.List;
import java.util.ArrayList;

public class Furnitur extends Objek{
    private String aksi;
    private Integer harga;
    private Integer panjang;
    private Integer lebar;
    private Point posisi = new Point(-1, -1);
    private boolean isVertikal;
    private static List<Furnitur> listFurnitur = makeListFurnitur();


    // Constructor
    public Furnitur(String nama, String aksi, Integer harga, Integer panjang, Integer lebar){
        super(nama, "peralatan");
        this.aksi = aksi;
        this.harga = harga;        
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public Furnitur(String nama, String aksi, Integer harga, Integer panjang, Integer lebar, Point posisi, boolean isVertikal){
        super(nama, "peralatan");
        this.aksi = aksi;
        this.harga = harga;        
        this.panjang = panjang;
        this.lebar = lebar;
        this.posisi = posisi;
        this.isVertikal = isVertikal;
    }

    public Furnitur(String nama, Point posisi, boolean isVertikal){
        super(nama, "peralatan");
        if(nama.equals("KASUR SINGLE")){
            aksi = "TIDUR";
            harga = 50;
            panjang = 4;
            lebar = 1;
        }

        else if(nama.equals("KASUR QUEEN SIZE")){
            aksi = "TIDUR";
            harga = 100;
            panjang = 4;
            lebar = 2;
        }

        else if(nama.equals("KASUR KING SIZE")){
            aksi = "TIDUR";
            harga = 150;
            panjang = 5;
            lebar = 2;
        }

        else if(nama.equals("TOILET")){
            aksi = "BUANG AIR";
            harga = 50;
            panjang = 1;
            lebar = 1;
        }

        else if(nama.equals("KOMPOR GAS")){
            aksi = "MASAK";
            harga = 100;
            panjang =  2;
            lebar = 1;
        }

        else if(nama.equals("KOMPOR LISTRIK")){
            aksi = "MASAK";
            harga = 200;
            panjang =  1;
            lebar = 1;
        }

        else if(nama.equals("MEJA DAN KURSI")){
            aksi = "MAKAN";
            harga = 50;
            panjang =  3;
            lebar = 3;
        }

        else if(nama.equals("JAM")){
            aksi = "MELIHAT WAKTU";
            harga = 10;
            panjang =  1;
            lebar = 1;
        }
        this.posisi = posisi;
        this.isVertikal = isVertikal;
    }

    //Membuat list Furnitur
    private static List<Furnitur> makeListFurnitur(){
        List<Furnitur> furnitur = new ArrayList<>();
        furnitur.add(new Furnitur("KASUR SINGLE", new Point(-1,-1),false));
        furnitur.add(new Furnitur("KASUR QUEEN SIZE", new Point(-1,-1),false));
        furnitur.add(new Furnitur("KASUR KING SIZE", new Point(-1,-1),false));
        furnitur.add(new Furnitur("TOILET", new Point(-1,-1),false));
        furnitur.add(new Furnitur("KOMPOR GAS", new Point(-1,-1),false));
        furnitur.add(new Furnitur("KOMPOR LISTRIK", new Point(-1,-1),false));
        furnitur.add(new Furnitur("MEJA DAN KURSI", new Point(-1,-1),false));
        furnitur.add(new Furnitur("JAM", new Point(-1,-1),false));
        return furnitur;
    }

    //Getter method for listFurnitur
    public static List<Furnitur> getListFurnitur(){
        return listFurnitur;
    } 

    //Getter method for aksi
    public String getAksi(){
        return this.aksi;
    }

    //Setter method for aksi
    public void setAksi(String aksi) {
        this.aksi = aksi;
    }

    // Getter method for harga
    public Integer getHarga() {
        return this.harga;
    }

    // Setter method for harga
    public void setHarga(int harga) {
        this.harga = harga;
    }

    // Getter method for panjang
    public Integer getPanjang() {
        return this.panjang;
    }

    // Setter method for panjang
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    // Getter method for lebar
    public Integer getLebar() {
        return this.lebar;
    }

    // Setter method for lebar
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    // Getter method for posisi
    public Point getPosisi() {
        return this.posisi;
    }

    // Setter method for posisi
    public void setPosisi(Point posisi) {
        this.posisi = posisi;
    }

    // Getter method for isVertikal
    public boolean isVertikal() {
        return this.isVertikal;
    }

    // Setter method for isVertikal
    public void setVertikal(boolean isVertikal) {
        this.isVertikal = isVertikal;
    }

    public static void printListFurnitur(){
        int i = 1;
        System.out.println("List furnitur yang dapat dibeli: ");
        for(Furnitur x: listFurnitur){
            System.out.println(i+". "+x.getNama()+" (Aksi: "+x.getAksi()+" ,Harga: "+x.getHarga()+" ,Dimensi: "+x.getPanjang()+"x"+x.getLebar()+")");
            i++;
        }
    }

    public String getInfo(){
        String ret = String.format("Nama: %s\nKategori: Peralatan\nAksi: %s\nHarga: %d\nDimensi: %dx%d", this.getNama(), aksi, harga, panjang, lebar);
        return(ret);
    }

    public void printInfo(){
        System.out.println(getInfo());
    }

}
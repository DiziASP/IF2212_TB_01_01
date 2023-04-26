package if2212_tb_01_01.entities;

import if2212_tb_01_01.utils.Point;
import java.util.List;
import java.util.ArrayList;

import if2212_tb_01_01.objects.Furnitur;
import if2212_tb_01_01.objects.Objek;

public class Rumah {
    private Point posisi;
    private List<Ruangan> daftarRuangan;
    private static Integer kapasitas = 36;
    private String kepemilikan;

    //Constructor
    public Rumah(Point posisiRumah, String kepemilikan, String namaRuangan, Point posisiRuangan) {
        this.posisi = posisiRumah;
        this.daftarRuangan = new ArrayList<Ruangan>(kapasitas);
        this.kepemilikan = kepemilikan;
        daftarRuangan.add(new Ruangan(namaRuangan, posisiRuangan ,true));
    }

    //Getter method for Posisi
    public Point getPosisi() {
        return posisi;
    }

    //Getter method for Kapasitas
    public static Integer getKapasitas() {
        return kapasitas;
    }

    //Getter method for Daftar Ruangan
    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    //Getter method for kepemilikan
    public String getKepemilikan(){
        return kepemilikan;
    }
    
    //Mendapatkan ruangan berdasarkan lokasi ruangan
    public Ruangan getRuangan(Point point){
        boolean found = false;
        int i = 0;
        while(!found && i < daftarRuangan.size()){
            if(daftarRuangan.get(i).getPosisi().equals(point)){
                found = true;
            }
            else{
                i++;
            }
        }
        return daftarRuangan.get(i);
    }

    //Mendapatkan ruangan berdasarkan nama ruangan
    public Ruangan getRuangan(String namaRuangan){
        boolean found = false;
        int i = 0;
        while(!found && i < daftarRuangan.size()){
            if(daftarRuangan.get(i).getNama().equals(namaRuangan)){
                found = true;
            }
            else{
                i++;
            }
        }
        return daftarRuangan.get(i);
    }

    //Setter method for posisi
    public void setPosisi(Integer x, Integer y) {
        this.posisi.setX(x);
        this.posisi.setY(y);
    }

    //Method untuk menambahkan ruangan ke dalam rumah
    public void addRuangan(Ruangan ruangan) {
        this.daftarRuangan.add(ruangan);
    }

}
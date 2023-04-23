package if2212_tb_01_01.entities;

import if2212_tb_01_01.utils.Point;
import java.util.List;
import java.util.ArrayList;
import if2212_tb_01_01.objects.Objek;

public class Rumah {
    class Ruangan {
        private String nama;
        private Point posisi;
        private List<Objek> daftarObjek;
        private boolean isBuilded;
        private static Integer kapasitas = 36;

        public Ruangan(String nama, Point posisi){
            this.nama = nama;
            this.posisi = posisi;
            daftarObjek = new ArrayList<Objek>(kapasitas);
            isBuilded = false;
        }
        public void addObjek(Integer index, Objek objek){
            daftarObjek.add(index,objek);
        }
        public String getNama(){
            return nama;
        }
        public void setNama(String nama){
            this.nama = nama;
        }
        public Point getPosisi(){
            return posisi;
        }
        public void setPosisi(Point posisi){
            this.posisi = posisi;
        }
        public List<Objek> getDaftarObjek(){
            return daftarObjek;
        } 
        public boolean getIsBuilded(){
            return isBuilded;
        }
        public void setIsBuilded(boolean status){
            isBuilded = status;
        }
    }

    private Point posisi;
    private List<Ruangan> daftarRuangan;
    private static Integer kapasitas = 36;

    public Rumah(Point posisi) {
        this.posisi = posisi;
        this.daftarRuangan = new ArrayList<Ruangan>(kapasitas);

        // Bikin satu ruangan dulu random buat doi!
    }

    public Point getPosisi() {
        return posisi;
    }

    public Integer getKapasitas() {
        return kapasitas;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    public void setPosisi(Integer x, Integer y) {
        this.posisi.setX(x);
        this.posisi.setY(y);
    }

    public void addRuangan(Integer index, Ruangan ruangan) {
        this.daftarRuangan.add(index, ruangan);
    }

}

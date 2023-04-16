package if2212_tb_01_01.entities;

import if2212_tb_01_01.utils.Point;
import java.util.List;
import java.util.ArrayList;

public class Rumah {
    class Ruangan {
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

package if2212_tb_01_01.entities.house;

import java.util.ArrayList;
import java.util.List;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.room.Room;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.entities.world.*;

public class House {
    GamePanel gp;
    // private List<Room> daftarRuangan;
    private Room ruanganAwal;
    private static Integer kapasitas = 36;
    private Sim kepemilikan;
    private Point posisi;

    private static int totalHouse = 0;

    // Constructor
    public House(GamePanel gp, Sim kepemilikan, Point posisi) {
        this.gp = gp;
        // this.daftarRuangan = new ArrayList<Room>(kapasitas);
        this.kepemilikan = kepemilikan;
        totalHouse++;
        this.posisi = posisi;
        this.ruanganAwal = new Room(gp, true, kepemilikan);

        /* Add Satu ruanga */
        // daftarRuangan.add(new Room(gp, "Test",  true, this.kepemilikan));
    }

    // Getter method for Posisi
    public Point getPosisi(){
        return posisi;
    }

    public void setPosisi(Point posisi){
        this.posisi = posisi;
    }

    public Room getRuanganAwal(){
        return ruanganAwal;
    }

    // Getter method for Kapasitas
    public static Integer getKapasitas() {
        return kapasitas;
    }

    // Getter method for Daftar Ruangan
    // public List<Room> getDaftarRuangan() {
    //     return daftarRuangan;
    // }

    // Getter method for kepemilikan
    public Sim getKepemilikan() {
        return kepemilikan;
    }

    // Mendapatkan ruangan berdasarkan lokasi ruangan
    // public Room getRuangan(Point point) {
    // boolean found = false;
    // int i = 0;
    // while (!found && i < daftarRuangan.size()) {
    // if (daftarRuangan.get(i).getPosisi().equals(point)) {
    // found = true;
    // } else {
    // i++;
    // }
    // }
    // return daftarRuangan.get(i);
    // }

    // Mendapatkan ruangan berdasarkan nama ruangan
    public Room getRuangan() {
        // boolean found = false;
        // int i = 0;
        // while (!found && i < daftarRuangan.size()) {
        //     if (daftarRuangan.get(i).getRoomName().equals(namaRuangan)) {
        //         found = true;
        //     } else {
        //         i++;
        //     }
        // }
        // return daftarRuangan.get(i);

        return ruanganAwal;
    }

    // Method untuk menambahkan ruangan ke dalam rumah
    // public void addRuangan(Room ruangan) {
    //     this.daftarRuangan.add(ruangan);
    // }
}

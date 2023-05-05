package if2212_tb_01_01.entities.house;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.room.Room;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.entities.world.*;

import java.io.Serializable;

public class House implements Serializable {
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
        this.kepemilikan = kepemilikan;
        totalHouse++;
        this.posisi = posisi;
        this.ruanganAwal = new Room(gp, true, kepemilikan);

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

    // Getter method for kepemilikan
    public Sim getKepemilikan() {
        return kepemilikan;
    }


    // Mendapatkan ruangan berdasarkan nama ruangan
    public Room getRuangan() {
        return ruanganAwal;
    }

}

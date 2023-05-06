package if2212_tb_01_01.entities.house;

import if2212_tb_01_01.utils.Point;

import if2212_tb_01_01.entities.room.Ruangan;

public class Rumah {
    private Point posisi;
    private Ruangan ruanganAwal;
    // private static Integer kapasitas = 36;
    private String kepemilikan;

    // Constructor
    public Rumah(Point posisiRumah, String kepemilikan, String namaRuangan) {
        this.posisi = posisiRumah;
        this.ruanganAwal = new Ruangan(namaRuangan, true);
        this.kepemilikan = kepemilikan;

    }

    // Getter method for Posisi
    public Point getPosisi() {
        return posisi;
    }

    public Ruangan getRuanganAwal(){
        return ruanganAwal;
    }

    public void setRuanganAwal(Ruangan ruanganAwal){
        this.ruanganAwal = ruanganAwal;
    }

    // Getter method for Kapasitas
    // public static Integer getKapasitas() {
    //     return kapasitas;
    // }

    // // Getter method for Daftar Ruangan
    // public List<Ruangan> getDaftarRuangan() {
    //     return daftarRuangan;
    // }

    // Getter method for kepemilikan
    public String getKepemilikan() {
        return kepemilikan;
    }

    // Mendapatkan ruangan berdasarkan lokasi ruangan
    // public Ruangan getRuangan(Point point) {
    //     boolean found = false;
    //     int i = 0;
    //     while (!found && i < daftarRuangan.size()) {
    //         if (daftarRuangan.get(i).getPosisi().equals(point)) {
    //             found = true;
    //         } else {
    //             i++;
    //         }
    //     }
    //     return daftarRuangan.get(i);
    // }

    // Mendapatkan ruangan berdasarkan nama ruangan
    // public Ruangan getRuangan(String namaRuangan) {
    //     boolean found = false;
    //     int i = 0;
    //     while (!found && i < daftarRuangan.size()) {
    //         if (daftarRuangan.get(i).getNama().equals(namaRuangan)) {
    //             found = true;
    //         } else {
    //             i++;
    //         }
    //     }
    //     return daftarRuangan.get(i);
    // }

    // Mendapatkan ruangan berdasarkan indeks
    // public Ruangan getRuangan(Integer indeks) {
    //     return daftarRuangan.get(indeks);
    // }

    // Setter method for posisi
    public void setPosisi(Integer x, Integer y) {
        this.posisi.setX(x);
        this.posisi.setY(y);
    }

    // Method untuk menambahkan ruangan ke dalam rumah
    // public void addRuangan(Ruangan ruangan) {
    //     this.daftarRuangan.add(ruangan);
    // }

    // public boolean adaRuangan(Ruangan ruangan) {
    //     return daftarRuangan.contains(ruangan);
    // }

    // Memeriksa apakah sudah ada ruangan dengan nama tertentu
    // public boolean adaRuangan(String nama) {
    //     boolean found = false;
    //     int i = 0;
    //     while (!found && i < daftarRuangan.size()) {
    //         if (daftarRuangan.get(i).getNama().equals(nama)) {
    //             found = true;
    //         } else {
    //             i++;
    //         }
    //     }
    //     return found;
    // }

    // Memeriksa apakah sudah ada ruangan pada posisi tertentu
    // public boolean adaRuangan(Point point) {
    //     boolean found = false;
    //     int i = 0;
    //     while (!found && i < daftarRuangan.size()) {
    //         if (daftarRuangan.get(i).getPosisi().isPointEqual(point)) {
    //             found = true;
    //         } else {
    //             i++;
    //         }
    //     }
    //     return found;
    // }

    // public void printDaftarRuangan() {
    //     System.out.println("Berikut adalah daftar ruangan di rumah " + getKepemilikan() + ": ");
    //     int i = 1;
    //     for (Ruangan ruangan : this.getDaftarRuangan()) {
    //         System.out.println(i + ". " + ruangan.getNama());
    //         i++;
    //     }
    // }

    // public void upgradeRumah(String namaRuangan, Point posisi) {
    //     daftarRuangan.add(new Ruangan(namaRuangan, posisi, false));

    // }

}
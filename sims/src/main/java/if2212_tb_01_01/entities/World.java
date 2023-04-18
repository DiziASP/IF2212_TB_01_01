package if2212_tb_01_01.entities;

import java.util.List;
import java.util.ArrayList;

public class World {
    private int panjang;
    private int lebar;
    private List<Rumah> listRumah;
    public World(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.listRumah = new ArrayList<Rumah>();
    }
    public int getPanjang() {
        return panjang;
    }
    public int getLebar() {
        return lebar;
    }
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }
    public List<Rumah> getDaftarRumah() {
        return listRumah;
    }
    public boolean isPosisiTerisi(int x, int y) {
        for (Rumah rumah : listRumah) {
            if (rumah.getPosisi().getX() == x && rumah.getPosisi().getY() == y) {
                return true;
            }
        }
        return false;
    }
    public void addRumah(Rumah rumah) {
        if (isPosisiTerisi(rumah.getPosisi().getX(), rumah.getPosisi().getY())) {
            System.out.println("Posisi sudah terisi!");
        } else {
            this.listRumah.add(rumah);
        }
    }
    public void removeRumah(Rumah rumah) {
        this.listRumah.remove(rumah);
    } 



}

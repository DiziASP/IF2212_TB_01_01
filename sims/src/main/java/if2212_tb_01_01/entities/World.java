package if2212_tb_01_01.entities;

import java.util.List;
import java.util.Random;

import if2212_tb_01_01.utils.Point;

import java.util.ArrayList;

public class World {
    private int panjang;
    private int lebar;
    private int totalDays;
    private boolean isIdle;
    private List<Rumah> listRumah;
    public World(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.listRumah = new ArrayList<Rumah>();
        this.totalDays = 0;
        this.isIdle = true;
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
    public void addRumah(Point posisi) {
        if (isPosisiTerisi(posisi.getX(), posisi.getY())) {
            System.out.println("Posisi sudah terisi!");
        } else {
            this.listRumah.add(new Rumah(posisi));
        }
    }
    public void addRumah(){
        boolean isPosisiFound = false;
        while (isPosisiFound == false) {
            Random rand = new Random();
            int min = 0;
            int max = 64;
            int randomX = rand.nextInt(max - min + 1) + min;
            int randomY = rand.nextInt(max - min + 1) + min;
            if (isPosisiTerisi(randomX, randomY) == false) {
                this.listRumah.add(new Rumah(new Point(randomX, randomY)));
                isPosisiFound = true;
            } 
        }
    }
    public void removeRumah(Rumah rumah) {
        this.listRumah.remove(rumah);
    } 
    public int getTotalDays() {
        return totalDays;
    }
    public void incrementTotalDays() {
        this.totalDays++;
    }
    public boolean isIdle() {
        return isIdle;
    }
    public void setIdle(boolean isIdle) {
        this.isIdle = isIdle;
    }
    public Rumah getRumah(int x){
        return this.listRumah.get(x);
    }
    public Rumah getLastRumah(){
        return this.listRumah.get(this.listRumah.size()-1);
    }
    

}

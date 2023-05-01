package if2212_tb_01_01.entities;

import java.util.List;
import java.util.Random;

import if2212_tb_01_01.utils.*;

import java.util.ArrayList;

public class World {
    private int panjang;
    private int lebar;
    private WorldClock worldClock;
    // private boolean isIdle;
    private List<Sim> listSim;
    private List<Rumah> listRumah;
    private Boolean[][] mapRumah;
    private Boolean isGameEnd;

    //Construktor
    public World(int panjang, int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
        this.listRumah = new ArrayList<Rumah>();
        this.worldClock = new WorldClock();
        mapRumah = new Boolean[this.panjang][this.lebar];
        for(int i = 0;i<this.panjang;i++){
            for(int j = 0;j<this.lebar;j++){
                mapRumah[i][j] = false;
            }
        }
    }

    //Method untuk print map world
    public void printMapWorld(){
        for(int k = 0; k < lebar;k++){
            System.out.print("x-");
            if(k == lebar - 1){
                System.out.println("x");
            }    
        }
        for (int i = 0;i<panjang;i++){
            for (int j = 0;j<lebar;j++){
                if(!mapRumah[i][j]){
                    System.out.print("| ");
                }
                else{
                    System.out.print("|X");
                }
                if(j== lebar - 1){
                    System.out.println("|");
                }
            }
            for(int k = 0; k < lebar;k++){
                System.out.print("x-");
                if(k == lebar - 1){
                    System.out.println("x");
                }    
            }
        }
    }

    //Getter method for panjang
    public int getPanjang() {
        return panjang;
    }

    //Getter method for lebar
    public int getLebar() {
        return lebar;
    }

    //Setter method for panjang
    public void setPanjang(int panjang) {
        this.panjang = panjang;
    }

    //Setter method for lebar
    public void setLebar(int lebar) {
        this.lebar = lebar;
    }

    //Getter method for Daftar Rumah 
    public List<Rumah> getDaftarRumah() {
        return listRumah;
    }

    //Mendapatkan rumah berdasarkan lokasi
    public Rumah getRumah(Point point){
        boolean found = false;
        int i = 0;
        while(!found && i < listRumah.size()){
            if(listRumah.get(i).getPosisi().isPointEqual(point)){
                found = true;
            }
            else{
                i++;
            }
        }
        return listRumah.get(i);
    }

    //Mendapatkan rumah berdasarkan lokasi
    public Rumah getRumah(String kepemilikan){
        boolean found = false;
        int i = 0;
        while(!found && i < listRumah.size()){
            if(listRumah.get(i).getKepemilikan().equals(kepemilikan)){
                found = true;
            }
            else{
                i++;
            }
        }
        return listRumah.get(i);
    }

    //Memeriksa apakah posisi rumah sudah terisi
    public boolean isPosisiTerisi(Point point) {
        for (Rumah rumah : listRumah) {
            if (rumah.getPosisi().isPointEqual(point)) {
                return true;
            }
        }
        return false;
    }
    public void addRumah(Rumah rumah) {
        if (isPosisiTerisi(rumah.getPosisi())) {
            System.out.println("Posisi sudah terisi!");
        } else {
            this.listRumah.add(rumah);
        }
    }
    public void addRumah(Point posisi, String kepemilikan, String namaRuangan, Point posisiRuangan) {
        if (isPosisiTerisi(posisi)) {
            System.out.println("Posisi sudah terisi!");
        } else {
            this.listRumah.add(new Rumah(posisi,kepemilikan,namaRuangan,posisiRuangan));
        }
    }
    public void addRumah(String kepemilikan, String namaRuangan, Point posisiRuangan){
        boolean isPosisiFound = false;
        while (isPosisiFound == false) {
            Random rand = new Random();
            int min = 0;
            int max = 64;
            int randomX = rand.nextInt(max - min + 1) + min;
            int randomY = rand.nextInt(max - min + 1) + min;
            if (isPosisiTerisi(new Point(randomX, randomY)) == false) {
                this.listRumah.add(new Rumah(new Point(randomX, randomY),kepemilikan, namaRuangan, posisiRuangan));
                isPosisiFound = true;
            } 
        }
    }

    public void removeRumah(Rumah rumah) {
        this.listRumah.remove(rumah);
    } 
    public WorldClock getWorldClock() {
        return worldClock;
    }
    public void setWorldClock(WorldClock worldClock) {
        this.worldClock = worldClock;
    }
    public boolean isIdle() {
        boolean isIdle = false;
        for (Sim sim : listSim){
            if (sim.getStatus().size()==0){
                isIdle = true;
            } else {
                isIdle = false;
                break;
            }
        }
        return isIdle;
    }
    public boolean getIsGameEnd() {
        return isGameEnd;
    }
    // public void setIdle(boolean isIdle) {
    //     this.isIdle = isIdle;
    // }
    public Rumah getRumah(int x){
        return this.listRumah.get(x);
    }
    public Rumah getLastRumah(){
        return this.listRumah.get(this.listRumah.size()-1);
    }
    

    public void printListRumah(){
        System.out.println("Berikut list rumah yang dapat dikunjungi: ");
        int i = 1;
        for(Rumah x: listRumah){
            System.out.println(i+". Rumah "+x.getKepemilikan()+" lokasi "+ x.getPosisi().toString());
            i++;
        }
    }

}

package if2212_tb_01_01.entities.room;

import if2212_tb_01_01.utils.Point;
import java.util.List;
import java.util.ArrayList;

import if2212_tb_01_01.entities.Inventory;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.objects.furnitur.*;

public class Ruangan {
    private String nama;
    private Ruangan roomLeft;
    private Ruangan roomRight;
    private Ruangan roomAbove;
    private Ruangan roomBelow;
    private List<Furnitur> daftarObjek = new ArrayList<>();
    private boolean isBuilded;
    private static Integer kapasitas = 36;
    private Boolean[][] mapRuangan = new Boolean[6][6];
    public Ruangan(String nama, boolean isBuilded) {
        this.nama = nama;
        roomLeft = null;
        roomAbove = null;
        roomRight = null;
        roomBelow = null;
        this.isBuilded = isBuilded;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                mapRuangan[i][j] = false;
            }
        }

        // // this.posisi = posisi;
        // daftarObjek = new ArrayList<Furnitur>(kapasitas);
        // this.isBuilded = isBuilded;
        
        // if(this.isBuilded){

        //Awal game ruangan langsung jadi
        // Furnitur kasur = (Furnitur) new KasurSingle();
        // daftarObjek.add(kasur);
        // daftarObjek.get(0).setPosisi(new Point(0,0));
        // daftarObjek.add((Furnitur) new Toilet());
        // daftarObjek.get(1).setPosisi(new Point(0,5));
        // daftarObjek.add((Furnitur) new KomporGas());
        // daftarObjek.get(2).setPosisi(new Point(2,5));
        // daftarObjek.add((Furnitur) new MejaKursi());
        // daftarObjek.get(3).setPosisi(new Point(2,2));
        // daftarObjek.add((Furnitur) new Jam());
        // daftarObjek.get(4).setPosisi(new Point(5,5));
        // setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
        // daftarObjek.add(new Furnitur("TOILET", new Point(0,5), false));
        // setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
        // daftarObjek.add(new Furnitur("KOMPOR GAS", new Point(2,5), false));
        // setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
        // daftarObjek.add(new Furnitur("MEJA DAN KURSI", new Point(2,2), false));
        // setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
        // daftarObjek.add(new Furnitur("JAM", new Point(5,5), false));
        // setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
        }

    public void setMapRuangan(Furnitur furnitur, boolean status) {
        for(int i = furnitur.getPosisi().getY(); i < furnitur.getPosisi().getY() + furnitur.getLebar();i++){
            for (int j = furnitur.getPosisi().getX(); j < furnitur.getPosisi().getX()+ furnitur.getPanjang(); j++) {
                    mapRuangan[i][j] = status;
                }
        }
    }

    public void MasangBarang(int index, Sim sim, Point posisi){
        if(index >=0 && index <=11){
            Furnitur furnitur = (Furnitur) sim.getInventory().getInventory().get(index); 
            if(isMapTerisi(furnitur, posisi)){
                System.out.println("Pada posisi tersebut sudah terdapat objek, memasang barang gagal dilakukan");
            }
            else{
                if(sim.getInventory().isItemSisa(index)){
                    daftarObjek.add(furnitur);
                    daftarObjek.get(daftarObjek.size() - 1).setPosisi(posisi);
                    setMapRuangan(daftarObjek.get(daftarObjek.size() - 1), true);
                    sim.getInventory().decItem(index);
                    sim.getInventory().incItemPut(index);
                }
                else{
                    System.out.println("Item tidak ada di inventory, memasang barang gagal dilakukan");
                }
            }  
        }
        else{
            System.out.println("Input melebihi pilihan yang tersedia atau < 1");
        }
    }

    public boolean isMapTerisi (Furnitur furnitur, Point posisi){
        boolean terisi = false;
        if(posisi.getX()+furnitur.getPanjang() <= 6 && posisi.getY()+furnitur.getLebar() <= 6){
            for(int i = posisi.getY(); i < posisi.getY() + furnitur.getLebar();i++){
                for (int j = posisi.getX(); j < posisi.getX()+ furnitur.getPanjang(); j++) {
                        if(mapRuangan[i][j]){
                            terisi = true;
                            break;
                        }
                    }
                    // if(terisi){
                    //     break;
                    // }
            }
        }
        else{
            terisi = true;
        }
        return terisi;
    }

    public void printMapRuangan() {
        for (int k = 0; k < 6; k++) {
            System.out.print("x---");
            if (k == 6 - 1) {
                System.out.println("x");
            }
        } 
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (!mapRuangan[i][j]) {
                    System.out.print("|   ");
                } else {
                    System.out.print("| X ");
                }
                if (j == 6 - 1) {
                    System.out.println("|");
                }
            }
            for (int k = 0; k < 6; k++) {
                System.out.print("x---");
                if (k == 6 - 1) {
                    System.out.println("x");
                }
            }
        }
    }

    public void addObjek(Furnitur objek, Point posisi) {
        daftarObjek.add(objek);
        daftarObjek.get(daftarObjek.size() - 1).setPosisi(posisi);
        setMapRuangan(daftarObjek.get(daftarObjek.size() - 1),true);
    }

    public void addObjekFromInventory(Furnitur objek, Point posisi, Inventory inventory) {
        daftarObjek.add(objek);
        daftarObjek.get(daftarObjek.size() - 1).setPosisi(posisi);
        setMapRuangan(daftarObjek.get(daftarObjek.size() - 1),true);
        inventory.decItem(daftarObjek.get(daftarObjek.size() - 1).getIndeks());
        inventory.incItemPut(daftarObjek.get(daftarObjek.size() - 1).getIndeks());
    }

    public void removeObjekToInventory(int indeks, Inventory inventory){
        setMapRuangan(daftarObjek.get(indeks),false);
        inventory.incItem(daftarObjek.get(indeks).getIndeks());
        inventory.decItemPut(daftarObjek.get(indeks).getIndeks());
        daftarObjek.remove(indeks);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Ruangan getRoomAbove(){
        return roomAbove;
    }

    public Ruangan getRoomBelow(){
        return roomBelow;
    }

    public Ruangan getRoomLeft(){
        return roomLeft;
    }

    public Ruangan getRoomRight(){
        return roomRight;
    }

    public boolean isAdaRoomAbove(){
        return (roomAbove != null);
    }

    public boolean isAdaRoomBelow(){
        return (roomBelow != null);
    }

    public boolean isAdaRoomLeft(){
        return (roomLeft != null);
    }

    public boolean isAdaRoomRight(){
        return (roomRight != null);
    }

    public void setRoomAbove(Ruangan roomAbove){
        this.roomAbove = roomAbove;
        this.roomAbove.roomBelow = this;
    }
    public void setRoomBelow(Ruangan roomBelow){
        this.roomBelow = roomBelow;
        this.roomBelow.roomAbove = this;
    }

    public void setRoomLeft(Ruangan roomLeft){
        this.roomLeft = roomLeft;
        this.roomLeft.roomRight = this;
    }

    public void setRoomRight(Ruangan roomRight){
        this.roomRight = roomRight;
        this.roomRight.roomLeft = this;
    }

    public List<Furnitur> getDaftarObjek() {
        return daftarObjek;
    }

    public boolean getIsBuilded() {
        return isBuilded;
    }

    public void setIsBuilded(boolean status) {
        isBuilded = status;
    }

    public Furnitur getObjek(Integer indeks) {
        return daftarObjek.get(indeks);
    }

    public String opsiMoveRoom(){
        String opsi = "";
        if(isAdaRoomAbove()){
            opsi += "ATAS ";
        }
        if(isAdaRoomBelow()){
            opsi += "BAWAH ";
        }
        if (isAdaRoomLeft()){
            opsi += "KIRI ";
        }
        if (isAdaRoomRight()){
            opsi += "KANAN ";
        }
        return opsi;
    }

    public String opsiUpgradeRoom(){
        String opsi = "";
        if(!isAdaRoomAbove()){
            opsi += "ATAS ";
        }
        if(!isAdaRoomBelow()){
            opsi += "BAWAH ";
        }
        if (!isAdaRoomLeft()){
            opsi += "KIRI ";
        }
        if (!isAdaRoomRight()){
            opsi += "KANAN ";
        }
        return opsi;
    }

    public void printDaftarObjek(){
        for(int i = 0; i < daftarObjek.size();i++){
            System.out.println((i+1)+". "+daftarObjek.get(i).getNama()+" (lokasi: "+daftarObjek.get(i).getPosisi().toString()+")");
        }
    }
}
package if2212_tb_01_01.entities;

import java.util.*;
import if2212_tb_01_01.objects.bahanmakanan.*;
import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.objects.furnitur.*;
import if2212_tb_01_01.objects.masakan.*;

public class Inventory {
    private Map<Integer, Objek> inventory;

    public Inventory() {
        inventory = new HashMap<Integer, Objek>();
        inventory.put(0, new Jam());
        inventory.put(1, new KasurKingSize());
        inventory.put(2, new KasurQueenSize());
        inventory.put(3, new KasurSingle());
        inventory.put(4, new KomporGas());
        inventory.put(5, new KomporListrik());
        inventory.put(6, new MejaKursi());
        inventory.put(7, new PC());
        inventory.put(8, new Piano());
        inventory.put(9, new Kanvas());
        inventory.put(10,new Toilet());
        inventory.put(11, new BakMandi());
        inventory.put(12, new BH_Ayam());
        inventory.put(13, new BH_Bayam());
        inventory.put(14, new BH_Kacang());
        inventory.put(15, new BH_Kentang());
        inventory.put(16, new BH_Nasi());
        inventory.put(17, new BH_Sapi());
        inventory.put(18, new BH_Susu());
        inventory.put(19, new BH_Wortel());
        inventory.put(20, new NasiAyam());
        inventory.put(21, new NasiKari());
        inventory.put(22, new SusuKacang());
        inventory.put(23, new TumisSayur());
        inventory.put(24, new Bistik());
    }

    public Map<Integer, Objek> getInventory() {
        return inventory;
    }

    public boolean isKosong() {
        for (Integer i=0; i<25; i++){
            if (isItemAda(i)){
                return false;
            }
        }
        return true;
    }

    public boolean isItemAda(Integer i){
        return(inventory.get(i).getAmount() >0);
    }

    public boolean isItemSisa(Integer i){
        return(((Furnitur)inventory.get(i)).getSisa() >0);
    }

    public int jumlahItem(Integer i) {
        return(inventory.get(i).getAmount());
    }

    public void incItem (Integer i) {
        inventory.get(i).incAmount();
    }

    public void decItem (Integer i) {
        // inventory.get(i).decAmount();
        inventory.get(i).decAmount();
    }

    public void incItemPut(Integer i){
        if (inventory.get(i) instanceof Furnitur){
            Furnitur in = (Furnitur) inventory.get(i);
            in.incAmountPut();
        }
    }

    public void decItemPut(Integer i){
        if (inventory.get(i) instanceof Furnitur){
            Furnitur in = (Furnitur) inventory.get(i);
            in.decAmountPut();
        }
    }

    public boolean isBisaMasak(int idx){
        boolean bisa = true;
        Masakan masakan = (Masakan) this.getInventory().get(idx);
        for (int itBahan : masakan.getIdxBahan()){
            if (!this.isItemAda(itBahan)){
                bisa = false;
            }
        }
        return bisa;
    }

    public int getJumMakanan(){
        int jumlah = 0;
        for (int i=12; i<25; i++){
            jumlah += getInventory().get(i).getAmount();
        }
        return jumlah;
    }

    public int getJumMasakan(){
        int jumlah = 0;
        for (int i=12; i<20; i++){
            jumlah += getInventory().get(i).getAmount();
        }
        return jumlah;
    }
    
    public int getKey(Objek item){
        for (Map.Entry<Integer, Objek> entry : inventory.entrySet()) {
            if (entry.getValue().getClass().equals(item.getClass())){
                return entry.getKey();
            }
        }
        return -1;
    }

    public int getHarga(int idx){
        if (inventory.get(idx) instanceof Furnitur){
            return (((Furnitur) inventory.get(idx)).getHarga());
        } else if (inventory.get(idx) instanceof BahanMakanan){
            return (((BahanMakanan) inventory.get(idx)).getHarga());
        } else {
            return -1;
        }
    }

    public void clearIsi() {
        inventory.clear();
    }

    public void displayInventory() {
        if (isKosong()) {
            System.out.println("Inventory Kosong!");
        } else {
            int nomor = 1;
            int index = 0;
            System.out.println("Inventory: ");
            for (Map.Entry<Integer, Objek> entry : inventory.entrySet()) {
                if (inventory.get(index) instanceof Furnitur){
                    if(isItemSisa(index)){
                        System.out.println(nomor + ". " + entry.getValue().getNama() + " : " + entry.getValue().getAmount());
                        nomor++;
                    }
                    index++;
                }
                else{
                    if(isItemAda(index)){
                        System.out.println(nomor + ". " + entry.getValue().getNama() + " : " + entry.getValue().getAmount());
                        nomor++;
                    }
                    index++;
                }
                
            }
        }
    }

    public void printOpsiMakan(){
        int nomor = 1;
        System.out.println("Daftar Item yang dapat dimakan! ");
        for(int i = 12; i<=19; i++){
            BahanMakanan bh = (BahanMakanan) inventory.get(i);
            System.out.println(nomor+". "+ inventory.get(i).getNama()+": "+ inventory.get(i).getAmount()+" (Kekenyangan: "+bh.getKekenyangan()+")");
            nomor++;
        }
        for(int i = 20; i<= 24; i++){
            Masakan bh = (Masakan) inventory.get(i);
            System.out.println(nomor+". "+ inventory.get(i).getNama()+": "+ inventory.get(i).getAmount()+" (Kekenyangan: "+bh.getKekenyangan()+")");
            nomor++;
        }
    }

    public void printOpsiMasak(){
        int nomor = 1;
        System.out.println("Daftar Item yang dapat dimasak! ");
        for(int i = 20;i<=24;i++){
            Masakan masak = (Masakan) inventory.get(i);
            System.out.println(nomor+". "+inventory.get(i).getNama()+ " (Bahan: "+ masak.infoBahan()+")");
            nomor++;
        }
    }

    public void printOpsiBeliBarang(){
        int nomor = 1;
        System.out.println("Daftar item yang dapat dibeli");
        for(int i = 0;i<=11;i++){
            Furnitur x = (Furnitur) inventory.get(i);
            
            System.out.println(nomor+". "+x.getNama()+"(Harga: "+x.getHarga()+")");
            nomor++;
        }
        for(int i = 12;i<=19;i++){
            BahanMakanan x = (BahanMakanan) inventory.get(i);
            
            System.out.println(nomor+". "+x.getNama()+"(Harga: "+x.getHarga()+")");
            nomor++;
        }
    }

    public void printOpsiMasangBarang(){
        for(int i = 0;i<=11;i++){
            System.out.println((i+1)+". "+getInventory().get(i).getNama()+" (Amount: "+getInventory().get(i).getAmount()+")");
        }
    }

}
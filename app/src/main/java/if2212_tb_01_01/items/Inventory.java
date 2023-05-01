package if2212_tb_01_01.items;

import java.util.*;

import if2212_tb_01_01.items.furnitur.*;
import if2212_tb_01_01.items.masakan.*;
import if2212_tb_01_01.items.bahanmakanan.*;

public class Inventory {
    private Map<Integer, Item> inventory;

    public Inventory() {
        inventory = new HashMap<Integer, Item>();
        inventory.put(0, (Item) new Jam());
        inventory.put(1, (Item) new KasurKingSize());
        inventory.put(2, (Item) new KasurQueenSize());
        inventory.put(3, (Item) new KasurSingle());
        inventory.put(4, (Item) new KomporGas());
        inventory.put(5, (Item) new KomporListrik());
        inventory.put(6, (Item) new MejaKursi());
        inventory.put(7, (Item) new PC());
        inventory.put(8, (Item) new Piano());
        inventory.put(9, (Item) new Kanvas());
        inventory.put(10, (Item) new Toilet());
        inventory.put(11, (Item) new BakMandi());
        inventory.put(12, (Item) new BH_Ayam());
        inventory.put(13, (Item) new BH_Bayam());
        inventory.put(14, (Item) new BH_Kacang());
        inventory.put(15, (Item) new BH_Kentang());
        inventory.put(16, (Item) new BH_Nasi());
        inventory.put(17, (Item) new BH_Sapi());
        inventory.put(18, (Item) new BH_Susu());
        inventory.put(19, (Item) new BH_Wortel());
        inventory.put(20, (Item) new NasiAyam());
        inventory.put(21, (Item) new NasiKari());
        inventory.put(22, (Item) new SusuKacang());
        inventory.put(23, (Item) new TumisSayur());
        inventory.put(24, (Item) new Bistik());
        
    }

    public Map<Integer,Item> getInventory() {
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
        if (inventory.get(i) instanceof Furnitur){
            Furnitur in = (Furnitur) inventory.get(i);
            in.decAmountPut();
        } else {
            inventory.get(i).decAmount();
        }
    }
    public void incItemPut(Integer i){
        if (inventory.get(i) instanceof Furnitur){
            Furnitur in = (Furnitur) inventory.get(i);
            in.incAmountPut();
        }
    }

    public void clearIsi () {
        inventory.clear();
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
    
    public int getKey(Item item){
        for (Map.Entry<Integer, Item> entry : inventory.entrySet()) {
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
} 
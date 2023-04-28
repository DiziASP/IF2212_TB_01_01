package if2212_tb_01_01.items;

import java.util.*;

import if2212_tb_01_01.items.furnitur.*;
import if2212_tb_01_01.items.masakan.*;
import if2212_tb_01_01.items.bahanmakanan.*;

public class Inventory {
    private Map<Integer, Item> inventory;

    public Inventory() {
        inventory = new HashMap<Integer, Item>();
        inventory.put(0, (Item) new Jam(null, false));
        inventory.put(1, (Item) new KasurKingSize(null, false));
        inventory.put(2, (Item) new KasurQueenSize(null, false));
        inventory.put(3, (Item) new KasurSingle(null, false));
        inventory.put(4, (Item) new KomporGas(null, false));
        inventory.put(5, (Item) new KomporListrik(null, false));
        inventory.put(6, (Item) new MejaKursi(null, false));
        inventory.put(7, (Item) new PC(null, false));
        inventory.put(8, (Item) new Piano(null, false));
        inventory.put(9, (Item) new Pintu(null, false));
        inventory.put(10, (Item) new Toilet(null, false));
        inventory.put(11, (Item) new BakMandi(null, false));
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

    public int jumlahItem(Integer i) {
        return(inventory.get(i).getAmount());
    }

    public void incItem (Integer i) {
        inventory.get(i).incAmount();
    }

    public void decItem (Integer i) {
        inventory.get(i).decAmount();
    }

    public void clearIsi () {
        inventory.clear();
    }

    // public void displayInventory() {
    //     String text = "Inventory: ";
    //     boolean found = false;
    //     for (Integer i=0; i<25; i++){
            
    //         if (isItemAda(i)){
    //             found = true;

    //             Item item = inventory.get(i);
    //             text += item.getInfo() + "\n\n";
    //         }
    //     } 
    //     else {
    //         for (Map.Entry<Integer, Item> entry : inventory.entrySet()) {
    //             System.out.println(entry.getKey().getNama() + " : " + entry.getValue());
    //         }
    //     }
    // }

} 
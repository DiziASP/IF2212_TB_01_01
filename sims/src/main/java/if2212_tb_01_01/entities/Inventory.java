package if2212_tb_01_01.entities;

import java.util.*;
import if2212_tb_01_01.objects.*;

public class Inventory<T extends Objek> {
    private Map<T, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<T, Integer>();
    }

    public Map<T,Integer> getInventory() {
        return inventory;
    }

    public boolean isKosong() {
        return inventory.isEmpty();
    }

    public boolean isObjekAda(T item){
        boolean found = false;
        for(T x: inventory.keySet()){
            if(x.getNama().equals(item.getNama())){
                found = true;
                break;
            }
        }
        return found;
    }

    public int jumlahItem(T item) {
        int jumlah = 0;
        for(T x: inventory.keySet()){
            if(x.getNama().equals(item.getNama())){
                jumlah = inventory.get(x);
                break;
            }
        }
        return jumlah;
    }

    public void addItem (T item, int quantity) {
        boolean found = false;
        for(T x: inventory.keySet()){
            if(x.getNama().equals(item.getNama())){
                inventory.replace(x, inventory.get(x) + quantity);
                found = true;
                break;
            }
        }
        if(!found){
            inventory.put(item,quantity);
        }
    }

    public void removeItem (T item, int quantity) {
        boolean found = false;
        for(T x: inventory.keySet()){
            if(x.getNama().equals(item.getNama())){
                found = true;
                int remainder = inventory.get(x) - quantity;
                if (remainder > 1) {
                    inventory.replace(x, remainder);
                }
                else if (remainder == 0){
                    inventory.remove(x);
                }
                else{
                    System.out.println("Insufficient Materials!"); 
                }
                break;
            }
            if(!found){
                System.out.println("No item(s) to remove!");
            }
        }
    }

    public void clearIsi () {
        inventory.clear();
    }

    public void displayInventory() {
        System.out.println ("Inventory: ");
        if (inventory.isEmpty()) {
            System.out.println("Inventory is Empty");
        }
        else {
            for (Map.Entry<T, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey().getNama() + " : " + entry.getValue());
            }
        }
    }

} 
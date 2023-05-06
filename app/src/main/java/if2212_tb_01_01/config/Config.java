package if2212_tb_01_01.config;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.entities.house.House;
import if2212_tb_01_01.entities.sim.Kesejahteraan;
import if2212_tb_01_01.entities.sim.Pekerjaan;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.items.Inventory;
import if2212_tb_01_01.items.furnitur.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp){
        this.gp = gp;
    }

    public void save(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("feihan.dat")));

            for(Sim each: gp.getSimList()){
                if (each.getNamaLengkap()!= "nadira" && each.getNamaLengkap()!= "naura" && each.getNamaLengkap()!= "dizi"){
                    oos.writeObject(each.getSpriteIndex());
                    oos.writeObject(each.getNamaLengkap());
                    oos.writeObject(each.getPekerjaan());
                    oos.writeObject(each.getUang());
                    oos.writeObject(each.getInventory());
                    oos.writeObject(each.getKesejahteraan());
                    // oos.writeObject(each.getRumah().getRuanganAwal().getDaftarObjek());
                    // oos.writeObject(each.getRumah().getRuanganAwal());
                    // oos.writeObject(each.getRumah().getPosisi());
                    oos.writeObject(each.getRumah());
                    // oos.writeObject(each.getCurHouse());
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("feihan.dat")));
            List<Sim> listSim = new ArrayList<Sim>();

            while(true){
                try{
                    Integer spriteIndex = (Integer) ois.readObject();
                    String namaLengkap = (String) ois.readObject();
                    Pekerjaan pekerjaan = (Pekerjaan) ois.readObject();
                    int uang = (int) ois.readObject();
                    Inventory inventory = (Inventory) ois.readObject();
                    Kesejahteraan kesejahteraan = (Kesejahteraan) ois.readObject();
                    House rumah = (House) ois.readObject();

                    Sim sim = new Sim(gp, gp.getKeyHandler(), spriteIndex, namaLengkap, pekerjaan, uang, inventory, kesejahteraan, rumah, rumah.getRuanganAwal());
                    listSim.add(sim);
                }
                catch(EOFException e){
                    break;
                }
            }

            for(Sim each: listSim){
                gp.getSimList().add(each);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

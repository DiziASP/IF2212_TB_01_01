package if2212_tb_01_01.config;

import if2212_tb_01_01.entities.house.House;
import if2212_tb_01_01.entities.room.Room;
import if2212_tb_01_01.entities.sim.Kesejahteraan;
import if2212_tb_01_01.entities.sim.Pekerjaan;
import if2212_tb_01_01.items.Inventory;

import java.io.Serializable;

public class GameData implements Serializable {
    public int spriteIndex;
    public String namaLengkap;
    public Pekerjaan pekerjaan;
    public int uang;
    public Inventory inventory;
    public Kesejahteraan kesejahteraan;
    public House rumah;
    public House currentPosition;
    public Room currentRuangan;


}

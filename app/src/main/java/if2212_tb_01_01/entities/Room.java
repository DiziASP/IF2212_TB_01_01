package if2212_tb_01_01.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Room{
        private Point posisi;
        private List<String> daftarObjek;
        private boolean isBuilded;
        private static final Integer kapasitas = 36;

        public Room(Point posisi){
            this.posisi = posisi;
            daftarObjek = new ArrayList<String>(kapasitas);
            isBuilded = false;
        }

        public Room(Integer x, Integer y){
            this.posisi = posisi;
            daftarObjek = new ArrayList<String>(kapasitas);
            isBuilded = false;
        }
        public void addObjek(Integer index, String objek){
            daftarObjek.add(index,objek);
        }
        public Point getPosisi(){
            return posisi;
        }
        public void setPosisi(Point posisi){
            this.posisi = posisi;
        }
        public List<String> getDaftarObjek(){
            return daftarObjek;
        }
        public boolean getIsBuilded(){
            return isBuilded;
        }
        public void setIsBuilded(boolean status){
            isBuilded = status;
        }
}

package if2212_tb_01_01.entities;

import if2212_tb_01_01.utils.Point;
import java.util.List;
import java.util.ArrayList;

import if2212_tb_01_01.objects.Objek;
import if2212_tb_01_01.objects.Furnitur.Furnitur;

public class Ruangan {
        private String nama;
        private Point posisi;
        private List<Furnitur> daftarObjek;
        private boolean isBuilded;
        private static Integer kapasitas = 36;
        private String[][] mapRuangan = new String[6][6];

        public Ruangan(String nama, Point posisi, boolean isBuilded){
            this.nama = nama;
            this.posisi = posisi;
            daftarObjek = new ArrayList<Furnitur>(kapasitas);
            this.isBuilded = isBuilded;
            for(int i = 0; i < 6;i++){
                for(int j = 0; j < 6;j++){
                    mapRuangan[i][j] = "";
                }
            }
            if(this.isBuilded){
                //Awal game ruangan langsung jadi
                daftarObjek.add(new Furnitur("KASUR SINGLE", new Point(0,0), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new Furnitur("TOILET", new Point(0,5), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new Furnitur("KOMPOR GAS", new Point(2,5), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new Furnitur("MEJA DAN KURSI", new Point(2,2), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new Furnitur("JAM", new Point(5,5), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
            }
        }

        public void setMapRuangan(Furnitur furnitur){
            if(!furnitur.isVertikal()){
                for(int i = furnitur.getPosisi().getY();i<furnitur.getPosisi().getY() + furnitur.getLebar();i++){
                    for(int j = furnitur.getPosisi().getX(); j<furnitur.getPosisi().getX() + furnitur.getPanjang();j++){
                        mapRuangan[i][j] = furnitur.getNama();
                    }
                }
            }
            else{
                for(int i = furnitur.getPosisi().getY();i<furnitur.getPosisi().getY() + furnitur.getPanjang();i++){
                    for(int j = furnitur.getPosisi().getX(); j<furnitur.getPosisi().getX() + furnitur.getLebar();j++){
                        mapRuangan[i][j] = furnitur.getNama();
                    }
                } 
            }
        }

        public void printMapRuangan(){
            for(int k = 0; k < 6;k++){
                System.out.print("x---");
                if(k == 6 - 1){
                    System.out.println("x");
                }    
            }
            for (int i = 0;i<6;i++){
                for (int j = 0;j<6;j++){
                    if(mapRuangan[i][j].equals("")){
                        System.out.print("|   ");
                    }
                    else{
                        System.out.print("| X ");
                    }
                    if(j== 6 - 1){
                        System.out.println("|");
                    }
                }
                for(int k = 0; k < 6;k++){
                    System.out.print("x---");
                    if(k == 6 - 1){
                        System.out.println("x");
                    }    
                }
            }
        }

        public void addObjek(Integer index, Furnitur objek){
            daftarObjek.add(index,objek);
        }
        public String getNama(){
            return nama;
        }
        public void setNama(String nama){
            this.nama = nama;
        }
        public Point getPosisi(){
            return posisi;
        }
        public void setPosisi(Point posisi){
            this.posisi = posisi;
        }
        public List<Furnitur> getDaftarObjek(){
            return daftarObjek;
        } 
        public boolean getIsBuilded(){
            return isBuilded;
        }
        public void setIsBuilded(boolean status){
            isBuilded = status;
        }
}

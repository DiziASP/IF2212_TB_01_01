package if2212_tb_01_01.entities;

import if2212_tb_01_01.utils.Point;
import java.util.List;
import java.util.ArrayList;

import if2212_tb_01_01.objects.Objek;

public class Ruangan {
        private String nama;
        private Point posisi;
        private List<Objek> daftarObjek;
        private boolean isBuilded;
        private static Integer kapasitas = 36;
        private String[][] mapRuangan = new String[6][6];

        public Ruangan(String nama, Point posisi, boolean isBuilded){
            this.nama = nama;
            this.posisi = posisi;
            daftarObjek = new ArrayList<Objek>(kapasitas);
            this.isBuilded = isBuilded;
            for(int i = 0; i < 6;i++){
                for(int j = 0; j < 6;j++){
                    mapRuangan[i][j] = "";
                }
            }
            if(this.isBuilded){
                //Awal game ruangan langsung jadi
                daftarObjek.add(new KasurSingle(new Point(0,0), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new Toilet(new Point(0,5), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new KomporGas(new Point(2,5), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new MejaKursi(new Point(2,2), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
                daftarObjek.add(new Jam(new Point(5,5), false));
                setMapRuangan(daftarObjek.get(daftarObjek.size() - 1));
            }
        }

        public void setMapRuangan(Objek furnitur){
            if (furnitur instanceof KasurSingle){
                KasurSingle furnitur2 = (KasurSingle) furnitur;
            } else if(furnitur instanceof KasurQueenSize){
                KasurQueenSize furnitur2 = (KasurQueenSize) furnitur;
            } else if (furnitur instanceof KasurKingSize){
                KasurKingSize furnitur2 = (KasurKingSize) furnitur;
            } else if (furnitur instanceof KomporGas){
                KomporGas furnitur2 = (KomporGas) furnitur;
            } else if (furnitur instanceof KomporListrik){
                KomporListrik furnitur2 = (KomporListrik) furnitur;
            } else if (furnitur instanceof Jam){
                Jam furnitur2 = (Jam) furnitur;
            } else if (furnitur instanceof MejaKursi){
                MejaKursi furnitur2 = (MejaKursi) furnitur;
            } else if (furnitur instanceof Toilet){
                Toilet furnitur2 = (Toilet) furnitur;
            } 
            if(!furnitur.isVertikal()){
                for(int i = furnitur2.getPosisi().getY();i<furnitur2.getPosisi().getY() + furnitur2.getLebar();i++){
                    for(int j = furnitur2.getPosisi().getX(); j<furnitur2.getPosisi().getX() + furnitur2.getPanjang();j++){
                        mapRuangan[i][j] = furnitur2.getNama();
                    }
                }
            }
            else{
                for(int i = furnitur2.getPosisi().getY();i<furnitur2.getPosisi().getY() + furnitur2.getPanjang();i++){
                    for(int j = furnitur2.getPosisi().getX(); j<furnitur2.getPosisi().getX() + furnitur2.getLebar();j++){
                        mapRuangan[i][j] = furnitur2.getNama();
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

        public void addObjek(Integer index, Objek objek){
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
        public List<Objek> getDaftarObjek(){
            return daftarObjek;
        } 
        public boolean getIsBuilded(){
            return isBuilded;
        }
        public void setIsBuilded(boolean status){
            isBuilded = status;
        }
}

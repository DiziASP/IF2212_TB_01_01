package if2212_tb_01_01.entities.action.AksiNonWaktu;
import if2212_tb_01_01.entities.sim.Sim;

public class BerpindahRuangan extends AksiNonWaktu{
    String tujuan;
    public BerpindahRuangan(Sim sim, String tujuan){
        super(sim);
        this.tujuan = tujuan;
    }
    public void efekToSim(){
        if(tujuan.equals("ATAS")){
            if(!getSim().getCurrentRuangan().isAdaRoomAbove()){
                System.out.println("Ruangan tidak ada, berpindah ruangan gagal");
            }
            else{
                if(!getSim().getCurrentRuangan().getRoomAbove().getIsBuilded()){
                    System.out.println("Ruangan belum selesai dibangun, berpindah ruangan gagal");
                }
                else{
                    getSim().setCurrentRuangan(getSim().getCurrentRuangan().getRoomAbove());
                    System.out.println("Berhasil mengunjungi ruangan "+getSim().getCurrentRuangan().getNama());
                }
                
            }
        }
        else if(tujuan.equals("BAWAH")){
            if(!getSim().getCurrentRuangan().isAdaRoomBelow()){
                System.out.println("Ruangan tidak ada, berpindah ruangan gagal");
            }
            else{
                if(!getSim().getCurrentRuangan().getRoomBelow().getIsBuilded()){
                    System.out.println("Ruangan belum selesai dibangun, berpindah ruangan gagal");
                }
                else{
                    getSim().setCurrentRuangan(getSim().getCurrentRuangan().getRoomBelow());
                    System.out.println("Berhasil mengunjungi ruangan "+getSim().getCurrentRuangan().getNama());
                }
            }
        }
        else if(tujuan.equals("KIRI")){
            if(!getSim().getCurrentRuangan().isAdaRoomLeft()){
                System.out.println("Ruangan tidak ada, berpindah ruangan gagal");
            }
            else{
                if(!getSim().getCurrentRuangan().getRoomLeft().getIsBuilded()){
                    System.out.println("Ruangan belum selesai dibangun, berpindah ruangan gagal");
                }
                else{
                    getSim().setCurrentRuangan(getSim().getCurrentRuangan().getRoomLeft());
                    System.out.println("Berhasil mengunjungi ruangan "+getSim().getCurrentRuangan().getNama());
                }
            }
        } 
        else if(tujuan.equals("KANAN")){
            if(!getSim().getCurrentRuangan().isAdaRoomRight()){
                System.out.println("Ruangan tidak ada, berpindah ruangan gagal");
            }
            else{
                if(!getSim().getCurrentRuangan().getRoomRight().getIsBuilded()){
                    System.out.println("Ruangan belum selesai dibangun, berpindah ruangan gagal");
                }
                else{
                    getSim().setCurrentRuangan(getSim().getCurrentRuangan().getRoomRight());
                    System.out.println("Berhasil mengunjungi ruangan "+getSim().getCurrentRuangan().getNama());
                }
            }
        }
    }
}

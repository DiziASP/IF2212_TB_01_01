package if2212_tb_01_01.entities.action;

public class Aksi implements Runnable {
    String aksi;

    /**
     * Aksi Tidur
     */
    public void tidur(){
        aksi = "Tidur";
    }

    /**
     * Aksi Makan
     */
    public void makan(){
        aksi = "Makan";
    }

    @Override
    public void run() {
        System.out.println("Aksi " + aksi + " sedang dilakukan");
    }
}

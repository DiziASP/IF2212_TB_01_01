package if2212_tb_01_01.entities.action;

import if2212_tb_01_01.entities.sim.Sim;

public abstract class Aksi implements Runnable{
        private Sim sim;
        private int detikTersisa;

        public Aksi(Sim sim, int jumlahWaktu) {
            this.sim = sim;
            this.detikTersisa = jumlahWaktu;
        }

        public Sim getSim() {
            return sim;
        }

        public int getDetikTersisa() {
            return detikTersisa;
        }

        public void kurangiDetikTersisa(int detik) {
            this.detikTersisa -= detik;
        }

        public void setDetikTersisa(int detik) {
            this.detikTersisa = detik;
        }

        public abstract void run();
    }

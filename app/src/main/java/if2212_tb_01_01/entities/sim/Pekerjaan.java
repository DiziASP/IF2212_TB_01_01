package if2212_tb_01_01.entities.sim;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pekerjaan implements Serializable {
    /* Attributes */
    private String namaKerja;
    private Integer gaji;

    public Pekerjaan(){
        Pekerjaan pekerjaan = randomizedPekerjaan();
        this.namaKerja = pekerjaan.namaKerja;
        this.gaji = pekerjaan.gaji;
    }

    public Pekerjaan(String namaKerja){
        if (namaKerja.equals("Badut Sulap")){
            this.namaKerja = "Badut Sulap";
            this.gaji = 15;
        } else if (namaKerja.equals("Koki")){
            this.namaKerja = "Koki";
            this.gaji = 30;
        } else if (namaKerja.equals("Polisi")){
            this.namaKerja = "Polisi";
            this.gaji = 35;
        } else if (namaKerja.equals("Programmer")){
            this.namaKerja = "Programmer";
            this.gaji = 45;
        } else if (namaKerja.equals("Dokter")){
            this.namaKerja = "Dokter";
            this.gaji = 50;
        }
    }

    /**
     * Getter Function
     *
     * @return namaKerja
     */
    public String getNamaKerja() {
        return this.namaKerja;
    }

    /**
     * Getter Function
     *
     * @return gaji
     */
    public Integer getGaji() {
        return gaji;
    }

    /**
     * Constructor for Pekerjaan
     *
     *
     */
    private Pekerjaan(PekerjaanBuilder builder) {
        this.namaKerja = builder.namaKerja;
        this.gaji = builder.gaji;
    }

    public static class PekerjaanBuilder {
        /* Main Param */
        private String namaKerja;
        private Integer gaji;

        /**
         * Builder Constructor for Pekerjaan
         *
         * @return namaKerja
         */
        public PekerjaanBuilder(String namaKerja, Integer gaji) {
            this.namaKerja = namaKerja;
            this.gaji = gaji;
        }

        public void setNamaKerja(String namaKerja) {
            this.namaKerja = namaKerja;
        }

        public void setGaji(Integer gaji) {
            this.gaji = gaji;
        }

        /**
         * Build Pekerjaan Object
         *
         * @return namaKerja
         */
        public Pekerjaan build() {
            return new Pekerjaan(this);
        }
    }

    public Pekerjaan randomizedPekerjaan(){
        List<Pekerjaan> pekerjaan = new ArrayList<>();
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Badut Sulap", 15).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Koki", 30).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Polisi", 35).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Programmer", 45).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("Dokter", 50).build());
        Random rand = new Random();
        int min = 0;
        int max = 4;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return pekerjaan.get(randomNum);
    
    }


}

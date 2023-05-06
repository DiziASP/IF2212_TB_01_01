package if2212_tb_01_01.occupation;

import java.util.*;

public class Pekerjaan {
    /* Main Param */
    private String namaKerja;
    private String deskripsiKerja;
    private Integer gaji;

    /**
     * Getter Function
     * 
     * @return namaKerja
     */
    public String getNamaKerja() {
        return namaKerja;
    }

    /**
     * Getter Function
     * 
     * @return deskripsiKerja
     */
    public String getDeskripsiKerja() {
        return deskripsiKerja;
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
        this.deskripsiKerja = builder.deskripsiKerja;
        this.gaji = builder.gaji;
    }

    public Pekerjaan(){
        Pekerjaan pekerjaan = randomizedPekerjaan();
        this.namaKerja = pekerjaan.namaKerja;
        this.deskripsiKerja = pekerjaan.deskripsiKerja;
        this.gaji = pekerjaan.gaji;
    }

    public static class PekerjaanBuilder {
        /* Main Param */
        private String namaKerja;
        private String deskripsiKerja;
        private Integer gaji;

        /**
         * Builder Constructor for Pekerjaan
         * 
         * @return namaKerja
         */
        public PekerjaanBuilder(String namaKerja, String deskripsiKerja, Integer gaji) {
            this.namaKerja = namaKerja;
            this.deskripsiKerja = deskripsiKerja;
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

    private Pekerjaan randomizedPekerjaan(){
        List <Pekerjaan> pekerjaan = new ArrayList<Pekerjaan>();
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("BADUT SULAP", "Badut sulap memiliki gaji harian 15", 15).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("KOKI", "Koki memiliki gaji harian 30", 30).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("POLISI", "Polisi memiliki gaji harian 35", 35).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("PROGRAMMER", "Progammer memiliki gaji harian 45", 45).build());
        pekerjaan.add(new Pekerjaan.PekerjaanBuilder("DOKTER", "Dokter memiliki gaji harian 50", 50).build());
        Random rand = new Random();
        int min = 0;
        int max = 4;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return pekerjaan.get(randomNum);
    }


}
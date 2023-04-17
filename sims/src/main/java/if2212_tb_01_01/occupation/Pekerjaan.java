package if2212_tb_01_01.occupation;

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
}

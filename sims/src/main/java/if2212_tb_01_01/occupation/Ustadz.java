package if2212_tb_01_01.occupation;

public class Ustadz implements Pekerjaan {
    private String namaPekerjaan;
    private String deskripsiPekerjaan;
    private Integer gaji = 15;
    private Integer daysWorked;

    public Ustadz(String namaPekerjaan, String deskripsiPekerjaan, Integer gaji, Integer daysWorked) {
        this.namaPekerjaan = namaPekerjaan;
        this.deskripsiPekerjaan = deskripsiPekerjaan;
        this.gaji = gaji;
        this.daysWorked = daysWorked;
    }

    public String getNamaKerja() {
        return namaPekerjaan;
    }

    public String getDeskripsiKerja() {
        return deskripsiPekerjaan;
    }

    public Integer getGaji() {
        return gaji;
    }

    public boolean canChangeJob() {
        return daysWorked > 12;
    }
}

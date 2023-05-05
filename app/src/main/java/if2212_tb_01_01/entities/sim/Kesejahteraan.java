package if2212_tb_01_01.entities.sim;

public class Kesejahteraan {
    private Integer mood;
    private Integer kekenyangan;
    private Integer kesehatan;
    private Integer kebersihan;

    public Kesejahteraan() {
        this.mood = 80;
        this.kekenyangan = 80;
        this.kesehatan = 80;
        this.kebersihan = 80;
    }
    public Kesejahteraan(Integer mood, Integer kekenyangan, Integer kesehatan, Integer kebersihan) {
        this.mood = mood;
        this.kekenyangan = kekenyangan;
        this.kesehatan = kesehatan;
        this.kebersihan = kebersihan;
    }

    public Integer getMood() {
        return mood;
    }

    public Integer getKekenyangan() {
        return kekenyangan;
    }

    public Integer getKesehatan() {
        return kesehatan;
    }

    public void setMood(Integer mood) {
        this.mood = mood;
        if (this.mood > 100) {
            this.mood = 100;
        }
    }

    public void setKekenyangan(Integer kekenyangan) {
        this.kekenyangan = kekenyangan;
        if (this.kekenyangan > 100) {
            this.kekenyangan = 100;
        }
    }

    public void setKesehatan(Integer kesehatan) {
        this.kesehatan = kesehatan;
        if (this.kesehatan > 100) {
            this.kesehatan = 100;
        }
    }
    public void setKebersihan(Integer kebersihan) {
        this.kebersihan = kebersihan;
        if (this.kebersihan > 100) {
            this.kebersihan = 100;
        }
    }
    public Integer getKebersihan() {
        return kebersihan;
    }
}

package if2212_tb_01_01.utils;

public class InputChecker {
    public static Integer toAngka(String input) {
        Integer angka = -999;
        try {
            angka = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("Input tidak valid! (Hint : input harus berupa angka) ");
        }
        return angka;
    }

    public static boolean isPoint(String input) {
        boolean point = true;
        if (input.isBlank()) {
            point = false;
        } else {
            String[] masukan = input.split(",");
            if (masukan.length != 2) {
                point = false;
            } else {
                if ((toAngka(masukan[0]).equals(-999)) || (toAngka(masukan[1]).equals(-999))) {
                    point = false;
                }
            }
        }
        return point;
    }

    public static boolean isPointRumah(String input) {
        boolean point = true;
        if (input.isBlank()) {
            System.out.println("Masukan tidak valid! (Hint: input anda kosong)");
            point = false;
        } else {
            String[] masukan = input.split(",");
            if (masukan.length != 2) {
                System.out.println("Masukan tidak valid! (Hint : Masukan harus dengan format x,y)");
                point = false;
            } else {
                if ((toAngka(masukan[0]).equals(-999)) || (toAngka(masukan[1]).equals(-999))) {
                    point = false;
                } else {
                    if ((toAngka(masukan[0]) < 0 || toAngka(masukan[0]) > 64)
                            || (toAngka(masukan[1]) < 0 || toAngka(masukan[1]) > 64)) {
                        System.out.println("Masukan tidak valid! (Hint : lokasi yang valid dari 0,0 sampai 64,64)");
                        point = false;
                    }
                }
            }
        }
        return point;
    }

    public static Integer isWaktuValid(String input, Integer kelipatan){
        Integer waktu = toAngka(input); //kalo input bukan angka bakal langsung return -999
        if(!waktu.equals(-999)){
            if(waktu % kelipatan != 0){
                waktu = -999; //kalo input invalid bakal return -999
                System.out.println("Input tidak valid! (Hint: masukkan waktu harus kelipataan "+kelipatan);
            }
        }
        return waktu;
    }
}

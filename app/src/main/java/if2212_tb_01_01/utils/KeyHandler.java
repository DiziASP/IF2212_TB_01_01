package if2212_tb_01_01.utils;


import if2212_tb_01_01.assets.AssetManager;
import if2212_tb_01_01.assets.tiles.TileManager;
import if2212_tb_01_01.entities.house.House;
import if2212_tb_01_01.entities.room.Room;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.entities.world.Point;
import if2212_tb_01_01.entities.world.World;
import if2212_tb_01_01.ui.UI;
import if2212_tb_01_01.items.Item;
import if2212_tb_01_01.items.masakan.Masakan;
import if2212_tb_01_01.items.furnitur.*;
import static if2212_tb_01_01.utils.Constant.*;

import java.awt.event.KeyListener;

import if2212_tb_01_01.GamePanel;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    private boolean upPressed, downPressed, leftPressed, rightPressed, EPressed, enterPressed, escapePressed;

    private int commandNum = 0;
    private int arrowNum = 0;
    private boolean errorCaught = false;
    private boolean bisaTambah = false, bisaGantiKerja = false;
    String input = "";
    int in1=0, in2=0;
    int pointer = 0;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int k = e.getKeyCode();

        // debugging
        // System.out.println(k + " " +(char) k);
        
        if((gp.getSubState()!=13 && gp.getSubState()!=14) || gp.getGs()!=6){
            switch (k) {    
                
                case KeyEvent.VK_W:
                    upPressed = true;
                    commandNum--;
                    break;
                case KeyEvent.VK_A:
                    leftPressed = true;
                    commandNum++;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = true;
                    break;
                case KeyEvent.VK_E:
                    EPressed = true;
                    break;

                case 38:
                case 37:
                    arrowNum--;
                    break;
                case 40:
                case 39:
                    arrowNum++;
                    break; 
                case KeyEvent.VK_ENTER:
                    enterPressed = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    escapePressed = true;
                    break;
            }
        } else {
            switch(k) {
                case KeyEvent.VK_ENTER:
                    enterPressed = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    escapePressed = true;
                    break;
            }
        }


        if (gp.getGs() == 0){
            //welcome

            arrowNum = (arrowNum+4)%4;

            if(k == KeyEvent.VK_ENTER){
                switch(arrowNum){
                    case 0:
                        gp.setGs(1);
                        break;
                    case 1:
                        gp.setGs(2);
                        break;
                    case 2:
                        gp.saveLoad.save();
                        System.exit(0);
                        break;
                    case 3:
                        System.exit(0);
                }

            } else if(isEscapePressed()){
                //exit();
                System.exit(0);
            } 


        } else if (gp.getGs() == 1){
            //setup

            arrowNum = (arrowNum+4) %4;

            if(k == KeyEvent.VK_ENTER){
                switch(arrowNum){
                    case 0:
                        gp.setGs(3); 
                        break;
                    case 1:
                        gp.setGs(4);
                        break;
                    case 2:
                        gp.saveLoad.load();
                        break;
                    case 3:
                        gp.setGs(0);
                        break;
                }
                arrowNum = (0);

            } else if(isEscapePressed()){
                gp.setGs(0);
                arrowNum = (0);
            } 

        } else if (gp.getGs() == 2){
            //help

            arrowNum = (arrowNum +5)%5;

            if(isEnterPressed() || isEscapePressed()){
                gp.setGs(0);
                arrowNum = (0);
            } 

        } else if (gp.getGs() == 3){
            //choose

            int js = gp.getSimList().size();

            if (js>0){
                arrowNum = (arrowNum+js)%js;
            }

            if(isEnterPressed()){
                // this sim
                gp.setSim(gp.getSimList().get(arrowNum));
                gp.setIndexActiveSim(arrowNum);
                if (gp.getRoom() != null){
                    gp.getRoom().setIsBuilded(false);
                }

                gp.setRoom(gp.getSim().getRoomAwal());
                gp.getRoom().setIsBuilded(true);
                // gp.setTileManager(new TileManager(gp,1));
                gp.setGs(5);
                gp.setSubState(0);
            } else if(isEscapePressed()){
                gp.setGs(1);
            } 

        } else if (gp.getGs() == 4){
            //new baru

            arrowNum = (arrowNum+10) %10;

            if(isEnterPressed()){
                errorCaught = false;
                
                for (Sim s : gp.getSimList()){
                    if (s.getNamaLengkap().equals(input)){
                        errorCaught = true;
                        break;
                    }
                }

                if (!errorCaught){
                    Sim s = new Sim(gp, this, arrowNum+1, input, null);
                    input = "";
                    gp.getWorldClock().getWorld().addSim(s);
                    gp.setIndexActiveSim(gp.getSimList().size()-1);
                    gp.setSim(s);
                    gp.setGs(8);
                }

            } else if(isEscapePressed()){
                gp.setGs(1);
                input = "";
            } else {
    
                if (input.length()<14){
                    if (k==32){
                        input += (char) (k);
                    }
                    else if (k>=65 && k<=90){
                        input += (char) (k+32);
                    } else if (k>=48 && k<=57){
                        input += (char) (k);
                    }
                    
                }
                if (k == 8){
                    if (input.length()>0){
                        input = input.substring(0, input.length() - 1);
                    }
                }
            
            }

        } else if (gp.getGs() == 5){
            //stats
            if(isEnterPressed()){
                //error check ///////////////////////
                gp.setGs(6);
            } else if(isEscapePressed()){
                gp.setGs(7);
            } 

        } else if (gp.getGs() == 6){
            //ruangan
            if (gp.getOpsiAksi().size()>0){
                arrowNum = (arrowNum+gp.getOpsiAksi().size()) %gp.getOpsiAksi().size();
            }

            //pointer
            if (gp.getSubState()==3){
                if (k == 37){
                    pointer = (pointer+11)%12;
                    while (!gp.getSim().getInventory().isItemSisa(pointer)){
                        pointer = (pointer+11)%12;
                    }
                } else if (k == 39){
                    pointer = (pointer+1)%12;
                    while (!gp.getSim().getInventory().isItemSisa(pointer)){
                        pointer = (pointer+1)%12;
                    }
                } else if (k == 40){
                    pointer = (pointer+4)%12;
                    while (!gp.getSim().getInventory().isItemSisa(pointer)){
                        pointer = (pointer+4)%12;
                    }
                } else if (k == 38){
                    pointer = (pointer+8)%12;
                    while (!gp.getSim().getInventory().isItemSisa(pointer)){
                        pointer = (pointer+8)%12;
                    }
                }
            } else if (gp.getSubState()>=4 && gp.getSubState()<=6){
                if (k == 37){
                    pointer = (pointer+35)%36;
                } else if (k == 39){
                    pointer = (pointer+1)%36;
                } else if (k == 40){
                    pointer = (pointer+6)%36;
                } else if (k == 38){
                    pointer = (pointer+30)%36;
                }
            } else if(gp.getSubState()==9){
                if (k == 37){
                    pointer = (pointer+12)%13;
                } else if (k == 39){
                    pointer = (pointer+1)%13;
                } else if (k == 40){
                    if (pointer<8){
                        pointer = (pointer+4)%13;
                    } else if (pointer<12){
                        pointer = (pointer+5)%13;
                    } else{
                        pointer = (pointer+1)%13;
                    }
                } else if (k == 38){
                    if (pointer>3){
                        pointer = (pointer+9)%13;
                    } else if (pointer>0){
                        pointer = (pointer+8)%13;
                    } else{
                        pointer = (pointer+12)%13;
                    }
                }
            } else if(gp.getSubState()==10){
                if (k == 37){
                    pointer = (pointer+4)%5;
                } else if (k == 39){
                    pointer = (pointer+1)%5;
                    } else if (k == 40){
                    pointer = (pointer+4)%5;
                } else if (k == 38){
                    pointer = (pointer+4)%5;
                }
            } else if (gp.getSubState()==13){
                if (k>=48 && k<=57){
                    in1 = (in1*10 + k-48);
                    if (in1>1000000000){
                        in1 = 1000000000;
                    }
                } else if (k==8){
                    in1 = in1/10;
                }
            } else if (gp.getSubState()==7){
                if (input.length()<14){
                    if (k==32){
                        input += (char) (k);
                    }
                    else if (k>=65 && k<=90){
                        input += (char) (k+32);
                    } else if (k>=48 && k<=57){
                        input += (char) (k);
                    }
                }
                if (k == 8){
                    if (input.length()>0){
                        input = input.substring(0, input.length() - 1);
                    }
                }
            } else if (gp.getSubState()==11) {
                int js = gp.getSimList().size();

                if (js > 0) {
                    arrowNum = (arrowNum) % js;
                    if (arrowNum == gp.getIndexActiveSim()) {
                        arrowNum = (arrowNum+1) % js;
                    }
                } else if (js==1){
                    arrowNum=0;
                }    

            }

            if(isEnterPressed()){

                if (gp.getSubState()==0){
                    //utama
                    switch (gp.getOpsiAksi(arrowNum)){ 
                        case "pindah ruangan":
                            gp.getSim().pindahRuangan();
                            break;
                        case "edit ruangan":
                            gp.setSubState(2);
                            break;
                        case "opsi lain":
                            gp.setSubState(1);
                            break; 
                        case "keluar":
                            gp.setGs(7);
                            break;
                        case "melihat waktu":
                            gp.showNotification(gp.getWorldClock().melihatWaktu());
                            gp.setSubState(0);
                            break;
                        case "makan":
                            gp.setSubState(9);
                            // gp.getSim().makan(in2, in1);
                            break;
                        case "masak":
                            gp.setSubState(10);
                            // gp.getSim().masak(in2, in1);
                            break;

                        default:
                            input = gp.getOpsiAksi(arrowNum);
                            gp.setSubState(13);
                            // System.out.println(input);
                            break;
                    }
                } else if (gp.getSubState()==1){
                    //extra
                    switch (gp.getOpsiAksi(arrowNum)){ 
                        case "kembali":
                            gp.setSubState(0);
                            break;
                        case "cari kerja":
                            if (gp.getSim().isCanChangePekerjaan()){
                                gp.setSubState(8);
                                System.out.println(gp.getOpsiAksi(arrowNum));
                                // gp.getSim().gantiKerja(input);
                                gp.showNotification("<html>Berhasil mengganti pekerjaan!<br>Uangmu terpotong sebanyak 1/2 gaji pekerjaan awal.<br>Kamu harus menunggu 12 menit untuk memulai kerja :D</html>");
                                gp.getSim().setWaktuSudahKerja(0);
                            }
                            else {
                                gp.showNotification("Tidak bisa ganti pekerjaan! Kamu harus bekerja selama 12 menit.");
                            }
                            break;
                        case "ganti sim":
                            gp.setGs(3);
                            break;
                        case "tambah sim":
                            if (gp.getWorldClock().getIsCanAddSim()){
                                gp.setGs(4);
                                gp.getWorldClock().setIsCanAddSim(false);
                            } else{
                                gp.showNotification("Hold on! Kamu hari ini sudah menambahkan sim baru.");
                            }
                            break;
                        case "belanja":
                            gp.setGs(11);
                            break;
                        case "lihat inventory":
                            gp.setGs(9);
                            break;
                        case "upgrade rumah":
                            if (gp.getSim().getUang()>=1500){
                                gp.setSubState(7);
                            } else{
                                gp.showNotification("Butuh uang sebesar 1500 untuk upgrade rumah!");
                            }
                            break;
                        case "kunjungi rumah":
                            gp.setSubState(11);
                            if (arrowNum == gp.getIndexActiveSim()) {
                                arrowNum++;
                            }
                            break;
                    }

                } else if (gp.getSubState()==2){
                    //edit room
                    switch (gp.getOpsiAksi(arrowNum)){ 
                        case "tambahkan barang":
                            gp.setSubState(3);
                            break;
                        case "pindahkan barang":
                            gp.setSubState(6);
                            break;
                        case "hapus barang":
                            gp.setSubState(5);
                            break;
                        case "kembali":
                            gp.setSubState(0);
                    }
                } else if (gp.getSubState()==3){
                    //pilih barang pasang

                    if(gp.getSim().getInventory().isItemSisa(pointer)){
                        in1 = pointer;
                        pointer = 0;
                        errorCaught=false;
                        gp.setSubState(4);
                    } else{
                        errorCaught = true;
                    }
                } else if (gp.getSubState()==4){
                    //lokasi pasang
                    errorCaught = false;

                    int y = ((Furnitur) gp.getSim().getInventory().getInventory().get(in1)).getPanjang();
                    int x = ((Furnitur) gp.getSim().getInventory().getInventory().get(in1)).getLebar();
                    int i=0; int j=0;
                    
                    while (!errorCaught && i<y && j<x){
                        if (pointer/6 + i >6 || pointer%6 +j >6){
                            errorCaught = true;
                        } else if (gp.getRoom().getMapRuangan()[pointer/6 + i][pointer%6 +j] != -1){
                            errorCaught = true;
                        } else {
                            if (i<y){
                                i++;
                            } else {
                                i=0; j++;
                            }
                        }
                    }
                    
                    if (!errorCaught){
                        gp.getRoom().pasangObjek(in1, pointer%6, pointer/6);
                        pointer=0;
                        in1=0;
                        gp.getSim().setSolidArea();
                        gp.setSubState(0);
                        errorCaught = false;
                    } else {
                        gp.showNotification("barang tidak boleh menimpa barang lain!");
                    }

                } else if (gp.getSubState()==5){
                    //lokasi buang
                    if (gp.getRoom().getMapRuangan()[pointer/6][pointer%6]!=-1){
                        gp.getRoom().delObjek(pointer%6, pointer/6);
                        gp.setSubState(0);
                    } else{
                        errorCaught = true;
                    }
                } else if (gp.getSubState()==6){
                    //lokasi edit
                    if (gp.getRoom().getMapRuangan()[pointer/6][pointer%6]!=-1){
                        in1 = gp.getRoom().delObjek(pointer%6, pointer/6);
                        gp.setSubState(4);
                    } else {
                        errorCaught = true;
                    }
                } else if (gp.getSubState()==7){
                    //ruang baru upgrade rumah
                    switch(gp.getOpsiAksi(arrowNum)){
                        case "kembali":
                            break;
                        default:
                            gp.getSim().upgradeRumah(gp.getOpsiAksi(arrowNum), input);
                            gp.setSubState(0);
                            input = "";
                            break;
                    }

                    gp.setSubState(0);
                } else if (gp.getSubState()==8){
                    // cari kerja
                    switch (gp.getOpsiAksi(arrowNum)){ 
                        case "kembali":
                            gp.setSubState(0);
                            break;
                        default:
//                            gp.getSim().setPekerjaan(gp.getOpsiAksi(arrowNum));
                            break;
                    }

                } else if (gp.getSubState()==9){
                    //pilih makanan
                    if(gp.getSim().getInventory().isItemAda(12+pointer)){
                        in2 = pointer;
                        gp.getSim().makan(pointer+12);
                        input = "makan";
                        gp.setSubState(14);
                    } else{
                        gp.showNotification("Kamu tidak punya makanan itu!");
                    }

                } else if (gp.getSubState()==10){
                    //pilih menu makanan
                    if (!gp.getSim().getInventory().isBisaMasak(pointer+20)){
                        gp.showNotification("Bahan tidak tersedia:(");
                    }
                    else{
                        gp.getSim().memasak(pointer+20);
                        input = "masak";
                        gp.setSubState(14);
                    }
                } else if (gp.getSubState()==11){
                    //berkunjung

                        // this sim
                        Sim s = gp.getWorldClock().getWorld().getSim(arrowNum);
                        gp.setRoom(s.getRoomAwal());
                        gp.getRoom().setIsBuilded(true);
                        gp.showNotification("berkunjung ke rumah "+s.getNamaLengkap());
                        gp.setSubState(16);


//                } else if (gp.getSubState()==12){
//                    //tambah ruang upgrade rumah

                } else if (gp.getSubState()==13){
                    //durasi aksi
                    System.out.println(input);
                    switch (input){ 
                        case "olahraga":
                            if (in1%20==0 ){
                                    gp.setSubState(14);
                                    gp.getSim().olahraga(in1);
                            } else {
                                    gp.showNotification("input harus kelipatan 20");
                                    errorCaught = true;
                                }
                                break;
                        case "tidur":
                            if (in1%240==0){
                                    gp.setSubState(14);
                                    gp.getSim().tidur(in1);
                            } else {
                                gp.showNotification("input harus kelipatan 240");
                                errorCaught = true;
                            }
                            break;
                        case "buang air":
                            if (in1%10==0){
                                    gp.setSubState(14);
                                    gp.getSim().buangAir(in1);
                            } else {
                                gp.showNotification("input harus kelipatan 10");
                                errorCaught = true;
                            }
                            break;
                        case "kerja":
                            if (in1%120==0){
                                    gp.setSubState(14);
                                    if (gp.getSim().isCanKerjaHabisGanti()){
                                        gp.getSim().kerja(in1);
                                    } else{
                                        gp.showNotification("Kamu baru berganti pekerjaan, tunggu " + (12*60 - gp.getSim().getWaktuSetelahGantiKerja())/60 + " menit lagi");
                                    }
                            } else {
                                gp.showNotification("input harus kelipatan 120");
                                errorCaught = true;
                            }
                            break;
                        case "yoga":
                            if (in1%60==0){
                                    gp.setSubState(14);
                                    gp.getSim().yoga(in1);
                            } else {
                                gp.showNotification("input harus kelipatan 60");
                                errorCaught = true;
                            }
                            break;
                        case "berdoa":
                            if (in1>20){
                                    gp.setSubState(14);
                                    gp.getSim().berdoa(in1);
                            } else {
                                gp.showNotification("ga malu cepet banget berdoanya??");
                                // gp.showNotification(gp.getWorldClock().melihatWaktu());
                                errorCaught = true;
                            }
                            break;
                        case "melukis":
                            if (in1%20==0){
                                    gp.setSubState(14);
                                    gp.getSim().melukis(in1);
                            } else {
                                gp.showNotification("input harus kelipatan 20");
                                errorCaught = true;
                            }
                            break;
                        case "main musik":
                            if (in1%20==0){
                                    gp.soundManager.play();
                                    gp.setSubState(14);
                                    gp.getSim().bermainMusik(in1);
                            } else {
                                gp.showNotification("input harus kelipatan 20");
                                errorCaught = true;
                            }
                            break;
                        case "mandi":
                            if (in1>30){
                                    gp.setSubState(14);
                                    gp.getSim().mandi(in1);
                            } else {
                                gp.showNotification("lama banget bang");
                                errorCaught = true;
                            }
                            break;
                        case "bersihkan rumah":
                            if (in1%20==0){
                                    gp.setSubState(14);
                                    gp.getSim().membersihkanRumah(in1);
                            } else {
                                gp.showNotification("input harus kelipatan 20");
                                errorCaught = true;
                            }
                            break;
                        case "proyekan":
                            if (in1%30==0){
                                    gp.setSubState(14);
                                    gp.getSim().proyekan(in1);
                            } else {
                                gp.showNotification("input harus kelipatan 30");
                                errorCaught = true;
                            }
                            break;
                        
                    }                    
                } else if (gp.getSubState()==14){
                    //aksi counter
                    if (gp.getActionCounter()==0){
                        gp.soundManager.stop();
                        gp.setSubState(0);
                    }
                } else if (gp.getSubState()==15){
                    //aksi berhasil
                    gp.setSubState(0);
                } else if (gp.getSubState()==16){
                    //rumah orang
                    switch(gp.getOpsiAksi(arrowNum)){
                        case "keluar":
                            gp.setGs(7);
                            break;
                        case "pulang":
                            gp.setSubState(0);
                            gp.setRoom(gp.getSim().getRoomAwal());
                            break; 
                        case "pindah ruangan":
                            gp.getSim().pindahRuangan();
                            break;
                        case "melihat waktu":
                            gp.showNotification(gp.getWorldClock().melihatWaktu());
                            gp.setSubState(0);
                            break;
                        default:
                            input = gp.getOpsiAksi(arrowNum);
                            gp.setSubState(13);
                            break;
                    }
                }
            } else if(isEscapePressed()){
                if (gp.getSubState()==3 ||gp.getSubState()==5 || gp.getSubState()==6 ||gp.getSubState()==9 || gp.getSubState()==10 || gp.getSubState()==12){
                    gp.setSubState(0);
                } else{
                    gp.setGs(7);
                }
            }
            
        } else if (gp.getGs() == 7){
            //pause menu

            arrowNum = (arrowNum+ 2)%2; 

            if (isEnterPressed()){
                switch(gp.getOpsiAksi(arrowNum)){
                    case "kembali":
                        gp.setGs(6);
                        break;
                    case "keluar":
                        gp.setGs(0);
                        gp.setSubState(0);
                        break;
                } 
            } else if (isEscapePressed()){
                gp.setGs(6);
            }
    
        } else if (gp.getGs() == 8){
            //world (create)

            arrowNum = (arrowNum +2)%2;
            
            if(enterPressed){
                if (gp.getWorldClock().getWorld().isPosisiTerisi(in1, in2)){
                    gp.showNotification("Posisi sudah terisi, pilih posisi lain!");
                } else {
                    gp.setGs(5);
                    gp.getSim().setPosisiRumah(new Point(in1,in2));
                    gp.setRoom(gp.getSim().getCurRoom());
                    gp.getRoom().setIsBuilded(true);
    
                    in1 = 0; in2 = 0;
                    input = "";
                }
            } else if (escapePressed){
                gp.setGs(3);
                in1 = 0; in2 = 0;

            } else if(arrowNum==0){
                if (k>=48 && k<=57){
                    in1 = (in1*10 + k-48);
                    if (in1>64){
                        in1 = 64;
                    }
                } else if (k==8){
                    in1 = in1/10;
                }
            } else if(arrowNum==1){
                if (k>=48 && k<=57){
                    in2 = (in2*10 + k-48);
                    if (in2>64){
                        in2 = 64;
                    }
                } else if (k==8){
                    in2 = in2/10;
                }
            }


        } else if (gp.getGs() == 9){
            //inventory
            arrowNum = (arrowNum+3)%3;

            if (leftPressed){
                pointer = (pointer+24)%25;
            } else if (rightPressed){
                pointer = (pointer+1)%25;
            } else if (downPressed){
                pointer = (pointer+5)%25;
            } else if (upPressed){
                pointer = (pointer+20)%25;
            }

            if(escapePressed){
                gp.setGs(6);
            } else if(enterPressed){
                if (arrowNum==0){
                    if (pointer<20){
                        gp.getSim().beliItem(pointer);
                        gp.showNotification("item dalam pengiriman!");
                    } else{
                        gp.showNotification("masakan tidak dapat dibeli!");
                    }
                } else if (arrowNum==1) {
                    if (pointer<12){
                        if (gp.getSim().getInventory().isItemSisa(pointer)){
                            in1=pointer;
                            gp.setGs(6);
                            gp.setSubState(4);
                        } else {
                            gp.showNotification("tidak ada sisa barang untuk dipasang!");
                        }
                    } else {
                        gp.showNotification("dekati meja makan!");;
                    }
                } else{
                    gp.setGs(6);
                }
            }

        // } else if (gp.getGs() == 10){
        //     //world (kunjungan)
        //     arrowNum = (arrowNum +2)%2;

        //     if(enterPressed){
        //         // gp.setRoom() world??
        //         gp.setGs(6);
        //         in1 = 0; in2 = 0;
        //         gp.setSubState(0);
        //     } else if (escapePressed){
        //         gp.setGs(7);
        //         in1 = 0; in2 = 0;
        //     } else if(arrowNum==0){
        //         if (k>=48 && k<=57){
        //             in1 = in1*10 + k-48;
        //         } else if (k==8){
        //             in1 = in1/10;
        //         }
        //     } else if(arrowNum==1){
        //         if (k>=48 && k<=57){
        //             in2 = in2*10 + k-48;
        //         } else if (k==8){
        //             in2 = in2/10;
        //         }
        //     }
        } else if (gp.getGs() == 11){   
            //shop belanja
            arrowNum = (arrowNum+2)%2;

            if (leftPressed){
                pointer = (pointer+24)%25;
            } else if (rightPressed){
                pointer = (pointer+1)%25;
            } else if (downPressed){
                pointer = (pointer+5)%25;
            } else if (upPressed){
                pointer = (pointer+20)%25;
            }

            if(escapePressed){
                gp.setGs(6);

            } else if(enterPressed){
                if (arrowNum==0){
                    if (pointer<20){
                        gp.getSim().beliItem(pointer);
                    } else {
                        errorCaught = true;
                        gp.showNotification("masakan tidak dapat dibeli!");
                    }
                } else {
                    gp.setGs(6);
                }
            }
         } else if (gp.getGs() == 12){
             // Kadek
            if(enterPressed || escapePressed){
                gp.setGs(0);
                gp.setSubState(0);
            }
        } 

    } 

    

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_E:
                EPressed = false;
                break;
            case KeyEvent.VK_ENTER:
                enterPressed = false;
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public int getCommandNum() {
        return commandNum;
    }

    public int getArrowNum() {
        return arrowNum;
    }

    public void setArrowNum(int arrowNum){
        this.arrowNum = arrowNum;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isEPressed() {
        return EPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void read(){
        enterPressed = false;
        escapePressed = false;
    }

    public boolean isEscapePressed() {
        return escapePressed;
    }

    public String getInput(){
        return input;
    }

    public void clearInput(){
        input = "";
    }

            
    public boolean isErrorCaught() {
        return errorCaught;
    }

    public void setErrorCaught(boolean errorCaught) {
        this.errorCaught = errorCaught;
    }

    public boolean isBisaTambah() {
        return bisaTambah;
    }

    public void setBisaTambah(boolean bisaTambah) {
        this.bisaTambah = bisaTambah;
    }

    public boolean isBisaGantiKerja() {
        return bisaGantiKerja;
    }

    public void setBisaGantiKerja(boolean bisaGantiKerja) {
        this.bisaGantiKerja = bisaGantiKerja;
    }

    public int getIn1(){
        return in1;
    }
    public void setIn1(int in1){
        this.in1 = in1;
    }

    public int getIn2(){
        return in2;
    }
    public void setIn2(int in2){
        this.in2 = in2;
    }

    public int getPointer(){
        return this.pointer;
    }

}

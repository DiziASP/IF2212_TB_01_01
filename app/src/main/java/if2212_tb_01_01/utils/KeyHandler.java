package if2212_tb_01_01.utils;


import if2212_tb_01_01.assets.AssetManager;
import if2212_tb_01_01.entities.house.House;
import if2212_tb_01_01.entities.room.Room;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.entities.world.World;
import if2212_tb_01_01.ui.UI;


import java.awt.event.KeyListener;

import if2212_tb_01_01.GamePanel;

import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    private boolean upPressed, downPressed, leftPressed, rightPressed, EPressed, enterPressed, escapePressed;

    private int commandNum = 0;
    private int arrowNum = 0;
    private boolean errorCaught = false, sedangAksiAktif = false, sedangAksiPasif = false;
    private boolean bisaTambah = false, bisaGantiKerja = false;
    String input = "";
    int in1,in2;
    int pointer = 0;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int k = e.getKeyCode();

        // debugging System.out.println(k + " " +(char) k);
        

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


        if (gp.getGs() == 0){
            //welcome

            arrowNum = (arrowNum+3)%3;

            if(k == KeyEvent.VK_ENTER){
                switch(arrowNum){
                    case 0:
                        gp.setGs(1);
                        break;
                    case 1:
                        gp.setGs(2);
                        break;
                    case 2:
                        // exit()
                        break;
                }

            } else if(isEscapePressed()){
                //exit();
            } 


        } else if (gp.getGs() == 1){
            //setup

            arrowNum = (arrowNum+3) %3;

            if(k == KeyEvent.VK_ENTER){
                switch(arrowNum){
                    case 0:
                        gp.setGs(3); 
                        break;
                    case 1:
                        gp.setGs(4);
                        break;
                    case 2:
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

            // arrowNum = (arrowNum%3);

            if(isEnterPressed() || isEscapePressed()){
                gp.setGs(0);
                arrowNum = (0);
            } 

        } else if (gp.getGs() == 3){
            //choose

            int js = gp.getSimList().size();

            arrowNum = (arrowNum+js)%js;

            if(isEnterPressed()){
                // this sim
                gp.setSim(gp.getSimList().get(arrowNum));
                gp.setGs(5);
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
                    Sim s = new Sim(gp, this, arrowNum+1, input);
                    gp.setSim(s);
                    gp.addSimList(s);

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
            arrowNum = (arrowNum+gp.getOpsiAksi().size()) %gp.getOpsiAksi().size();

            if(isEnterPressed()){
                switch (gp.getOpsiAksi(arrowNum)){ 
                    case "opsi lain":
                        gp.setGs(7);
                        break; 
                    case "olahraga":
                        break;
                    case "tidur":
                        break;
                    case "buang air":
                        break;
                    case "masak":
                        break;
                    case "makan":
                        break;
                    case "lihat waktu":
                        break;
                    case "yoga":
                        break;
                    case "doa":
                        break;
                    case "gambar":
                        break;
                    case "main musik":
                        break;
                    case "mandi":
                        break;
                    case "bersihkan rumah":
                        break;
                    case "kerjakan proyek":
                        break;
                    case "pindah ruangan":
                        break;
                       
                }
            } else if(isEscapePressed()){
                gp.setGs(7);
            }
            
        } else if (gp.getGs() == 7){
            //pause menu

            arrowNum = (arrowNum+ gp.getOpsiAksi().size()) %gp.getOpsiAksi().size(); 

            if (isEnterPressed()){
                switch(gp.getOpsiAksi(arrowNum)){
                    case "cari kerja":
                        break;
                    case "ganti sim":
                        gp.setGs(3);
                        break;
                    case "tambah sim":
                        gp.setGs(4);
                        break;
                    case "lihat inventory":
                        gp.setGs(9);
                        break;
                    case "upgrade rumah":
                        break;
                    case "beli barang":
                        break;
                    case "pasang barang":
                        break;
                    case "kunjungi rumah":
                        gp.setGs(10);
                        break;
                    case "kembali":
                        gp.setGs(6);
                    case "keluar":
                        gp.setGs(0);
                        break;
                } 
            } else if (isEscapePressed()){
                gp.setGs(6);
            }
    
        } else if (gp.getGs() == 8){
            //world (create)

            arrowNum = (arrowNum +2)%2;

            if(enterPressed){
                gp.setGs(5);
                in1 = 0; in2 = 0;
                input = "";
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
            }

        } else if (gp.getGs() == 10){
            //world (kunjungan)
            arrowNum = (arrowNum +2)%2;

            if(enterPressed){
                gp.setGs(6);
                in1 = 0; in2 = 0;
            } else if (escapePressed){
                gp.setGs(7);
                in1 = 0; in2 = 0;

            } else if(arrowNum==0){
                if (k>=48 && k<=57){
                    in1 = in1*10 + k-48;
                } else if (k==8){
                    in1 = in1/10;
                }
            } else if(arrowNum==1){
                if (k>=48 && k<=57){
                    in2 = in2*10 + k-48;
                } else if (k==8){
                    in2 = in2/10;
                }
            }
        } else if (gp.getGs() == 11){   
            //shop 

        
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

    public boolean isSedangAksiAktif() {
        return sedangAksiAktif;
    }

    public void setSedangAksiAktif(boolean sedangAksiAktif) {
        this.sedangAksiAktif = sedangAksiAktif;
    }

    public boolean isSedangAksiPasif() {
        return sedangAksiPasif;
    }

    public void setSedangAksiPasif(boolean sedangAksiPasif) {
        this.sedangAksiPasif = sedangAksiPasif;
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

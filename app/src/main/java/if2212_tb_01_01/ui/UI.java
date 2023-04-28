package if2212_tb_01_01.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import if2212_tb_01_01.GamePanel;
import if2212_tb_01_01.assets.AssetManager;
import if2212_tb_01_01.entities.sim.Sim;
import if2212_tb_01_01.items.Inventory;
import if2212_tb_01_01.items.Item;
import if2212_tb_01_01.items.bahanmakanan.BahanMakanan;
import if2212_tb_01_01.items.furnitur.Furnitur;
import if2212_tb_01_01.items.masakan.Masakan;
import if2212_tb_01_01.utils.KeyHandler;
import if2212_tb_01_01.utils.Constant;

public class UI {
    GamePanel gp;
    AssetManager am;
    KeyHandler kh;
    // String kh.getInput;
    // boolean errorCaught = false;
    
    // screen: 0-welcome, 1-setup, 2-help, 3- choose, 4-new, 5-stats, 6-ruangan, 7-world, 8-inventory, 9-pause

    Font f20, f40, f80;

    public UI(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        this.am = new AssetManager(gp);
        f20 = new Font("Courier New", Font.BOLD, 18);
        f40 = new Font("Courier New", Font.BOLD, 30);
        f80 = new Font("Courier New", Font.BOLD, 42);
    }

    public void update() {

    }

    // @Override
    public void draw(Graphics2D g2) {
        if (gp.getGs() == 0){
            g2.setColor(Constant.c5);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            g2.setFont(f80);
            String text = "Welcome to Sim-Plicity!";
            
            int x = centerX(g2,text);
            int y = 3*Constant.tileSize;
            g2.setColor(Constant.c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);

            x = Constant.screenWidth/2 - Constant.tileSize;
            y = 4*Constant.tileSize;
            Image img =  am.setup("/cat", Constant.tileSize*2, Constant.tileSize*2);
            g2.drawImage(img, x, y, Constant.tileSize*2, Constant.tileSize*2, null);

            g2.setFont(f40);
            text = "Play Game!";
            x = centerX(g2,text);
            y += 4*Constant.tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==0){
                g2.drawString(">",x-40,y);
            }

            text = "Help";
            x = centerX(g2,text);
            y += Constant.tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==1){
                g2.drawString(">",x-40,y);
            }

            text = "Exit";
            x = centerX(g2,text);
            y += Constant.tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==2){
                g2.drawString(">",x-40,y);
            }

        } else if (gp.getGs() == 1) {

            g2.setColor(Constant.c5);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            g2.setFont(f80);
            String text = "Pilih SIM-mu ^^";
            
            int x = centerX(g2,text);
            int y = 3*Constant.tileSize;
            g2.setColor(Constant.c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);

            x = Constant.screenWidth/2 - Constant.tileSize*2;
            y = 4*Constant.tileSize;
            Image img =  am.setup("/cat2", Constant.tileSize*4, Constant.tileSize*2);
            g2.drawImage(img, x, y, Constant.tileSize*4, Constant.tileSize*2, null);

            g2.setFont(f40);
            text = "Pilih SIM";
            x = centerX(g2,text);
            y += 4*Constant.tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==0){
                g2.drawString(">",x-40,y);
            }

            text = "Buat SIM baru";
            x = centerX(g2,text);
            y += Constant.tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==1){
                g2.drawString(">",x-40,y);
            }

            text = "Kembali";
            x = centerX(g2,text);
            y += Constant.tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==2){
                g2.drawString(">",x-40,y);
            }

        } else if (gp.getGs() == 2) {
            g2.setColor(Constant.c5);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            g2.setFont(f80);
            String text = "Help [placeholder]";
            int x = centerX(g2,text);
            int y = 3*Constant.tileSize;
            g2.setColor(Constant.c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);

            
            g2.setFont(f20);
            text = "Kamu dapat melakukan aksi apapun yang kamu inginkan";
            x = centerX(g2,text);
            y += 2*Constant.tileSize;
            g2.drawString(text,x,y);

            text = "sebagai SIM yang kamu pilih. Kreasikan rumah impianmu!";
            x = centerX(g2,text);
            y += Constant.tileSize;
            g2.drawString(text,x,y);

            text = "Tetapi jangan lupa untuk menjaga diri ya^^";
            x = centerX(g2,text);
            y += Constant.tileSize;
            g2.drawString(text,x,y);
            

            g2.setFont(f40);
            text = "Kembali";
            x = centerX(g2,text);
            y += 2*Constant.tileSize;
            g2.drawString(text,x,y);
            g2.drawString(">",x-40,y);
            

        } else if (gp.getGs() == 3){
            g2.setColor(Constant.c5);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            g2.setFont(f80);
            String text = "Pilih SIM-mu";
            int x = centerX(g2,text);
            int y = 3*Constant.tileSize;
            g2.setColor(Constant.c1);
            g2.drawString(text,x+3,y+3);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);


            g2.setFont(f20);

            x = Constant.tileSize*5;
            y = 4*Constant.tileSize;
            Image img =  am.setup("/larrow", Constant.tileSize*1, Constant.tileSize*1);
            g2.drawImage(img, x, y, Constant.tileSize*1, Constant.tileSize*1, null);

            x = Constant.tileSize*10;
            img =  am.setup("/rarrow", Constant.tileSize*1, Constant.tileSize*1);
            g2.drawImage(img, x, y, Constant.tileSize*1, Constant.tileSize*1, null);


            Sim s = gp.getSimList().get(kh.getArrowNum());

            x = Constant.tileSize*7;
            y -= 24;
            String path = String.format("/images/sim/%d/down1", s.getSpriteIndex());
            img =  am.setup(path, Constant.tileSize*2, Constant.tileSize*2);
            g2.drawImage(img, x, y, Constant.tileSize*2, Constant.tileSize*2, null);


            drawSimInfo(g2, 7*Constant.tileSize, 2*Constant.tileSize, 5*Constant.tileSize, 9*Constant.tileSize, 13*Constant.tileSize, Constant.tileSize, s);  


        } else if (gp.getGs() == 4){
            g2.setColor(Constant.c5);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            g2.setFont(f80);
            String text = "Buat SIM baru";
            int x = centerX(g2,text);
            int y = Constant.tileSize +20;
            g2.setColor(Constant.c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);


            // pilih avatar
            g2.setFont(f40);
            text = "Pilih avatarmu:";
            x = 2*Constant.tileSize;
            y = 7*Constant.tileSize - 15;
            g2.drawString(text,x,y);

            x = Constant.tileSize*5;
            y = 8*Constant.tileSize;
            Image img =  am.setup("/larrow", Constant.tileSize*1, Constant.tileSize*1);
            g2.drawImage(img, x, y, Constant.tileSize*1, Constant.tileSize*1, null);

            x = Constant.tileSize*10;
            img =  am.setup("/rarrow", Constant.tileSize*1, Constant.tileSize*1);
            g2.drawImage(img, x, y, Constant.tileSize*1, Constant.tileSize*1, null);

            x = Constant.tileSize*7;
            y -= Constant.tileSize/2;
            String path = String.format("/images/sim/%d/down1", kh.getArrowNum()+1);
            img =  am.setup(path, Constant.tileSize*2, Constant.tileSize*2);
            g2.drawImage(img, x, y, Constant.tileSize*2, Constant.tileSize*2, null);

            text = "> Klik ENTER untuk lanjut";
            x = centerX(g2,text);
            y += 3*Constant.tileSize;
            g2.drawString(text,x-10,y);



            // nama

            text = "Pilih namamu:";
            x = 2*Constant.tileSize;
            y = 3*Constant.tileSize -15;
            g2.drawString(text,x,y);

            x = Constant.tileSize*2;
            y = 3*Constant.tileSize;
            img =  am.setup("/innama", Constant.tileSize*12, Constant.tileSize*2);
            g2.drawImage(img, x, y, Constant.tileSize*12, Constant.tileSize*2, null);

            x = 3*Constant.tileSize;
            y += Constant.tileSize +5;
            if (kh.getInput().equals("")){
                text = "ketik namamu...";
            } else{
                text = kh.getInput();
                g2.setColor(Constant.c1);
            }
            g2.drawString(text,x,y);

            if(kh.isErrorCaught()){
                

                g2.setFont(f20);
                x = Constant.tileSize;
                y += Constant.tileSize+20;

                
                text = "sudah ada sim dengan nama itu! silahkan pilih nama lain.";
                g2.setColor(Constant.c6); 
                g2.drawString(text,x+1, y+1);
                g2.setColor(Constant.c3);
                g2.drawString(text,x, y);

                g2.setFont(f40);

                g2.setColor(Constant.c6);                
            }
            


        } else if (gp.getGs() == 5){
            g2.setColor(Constant.c3);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            g2.setFont(f80);
            String text = "Selamat bermain!";
            int x = centerX(g2,text);
            int y = 2*Constant.tileSize;
            g2.setColor(Constant.c1);
            g2.drawString(text,x+3,y+3);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);


            g2.setFont(f20);


            Sim s = gp.getSim();

            x = Constant.tileSize*7;
            y = 3*Constant.tileSize;
            String path = String.format("/images/sim/%d/down1", s.getSpriteIndex());
            Image img =  am.setup(path, Constant.tileSize*2, Constant.tileSize*2);
            g2.drawImage(img, x, y, Constant.tileSize*2, Constant.tileSize*2, null);


            drawSimInfo(g2, 6*Constant.tileSize, 2*Constant.tileSize, 5*Constant.tileSize, 9*Constant.tileSize, 13*Constant.tileSize, Constant.tileSize, s);

            g2.setFont(f40);
            text = "> Klik ENTER untuk lanjut";
            x = centerX(g2,text);
            y = 11*Constant.tileSize;
            g2.drawString(text,x-10,y-24);


        } else if (gp.getGs() == 6){
            //play dalam ruangan world
            gp.updateOpsiAksi();

            g2.setColor(Constant.c3);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            gp.getRoom().draw(g2);
            gp.getSim().draw(g2);


            //simmm

            Sim s  = gp.getSim();


            g2.setColor(Constant.c7);
            g2.fillRect(Constant.tileSize, Constant.tileSize*8 +24, Constant.tileSize*14, Constant.tileSize*3);
            g2.setColor(Constant.c1);
            g2.fillRect(Constant.tileSize+12, Constant.tileSize*9-12, Constant.tileSize*13+24, Constant.tileSize*2+24);

            g2.setFont(f20);
            g2.setColor(Constant.c6);

            drawSimInfo(g2, 9*Constant.tileSize +18, 2*Constant.tileSize, 5*Constant.tileSize, 9*Constant.tileSize, 13*Constant.tileSize, 24, s); 
            String text = "Lokasi: " + "<4, 1>";
            int x = 12 +Constant.tileSize;
            int y = 8+ 8*Constant.tileSize;
            g2.drawString(text, x, y);


            //aksi
            g2.setColor(Constant.c7);
            g2.fillRect(Constant.tileSize*9, 24, Constant.tileSize*6, Constant.tileSize*8);
            g2.setColor(Constant.c2);
            g2.fillRect(Constant.tileSize*9 +12, 36, Constant.tileSize*6-24, Constant.tileSize*8-12);

            g2.setColor(Constant.c6);
            text = "Aksi:";
            x = 10*Constant.tileSize-12;
            y = Constant.tileSize+24;
            g2.drawString(text,x,y);

            g2.setColor(Constant.c1);
            g2.fillRect(10*Constant.tileSize-12, Constant.tileSize*2 - 8 + kh.getArrowNum()*24, Constant.tileSize*4 +24, 24);

            g2.setColor(Constant.c6);
            x += 12;
            y +=32;

            for (String optAksi : gp.getOpsiAksi()){
                g2.drawString(optAksi,x,y);
                y+=24;
            }    

        } else if (gp.getGs() == 7){

            gp.updateMenu();

            g2.setColor(Constant.c3);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            outlinedRect(g2, Constant.tileSize*4-24, Constant.tileSize, Constant.tileSize*9, Constant.tileSize*9, 24, 12, Constant.c7, Constant.c2);

            g2.setFont(f80);
            String text = "Menu Tambahan";
            int x = centerX(g2,text);
            int y = 3*Constant.tileSize;
            g2.setColor(Constant.c1);
            g2.drawString(text,x+3,y+3);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);

            g2.setColor(Constant.c1);
            g2.fillRect(5*Constant.tileSize-24, Constant.tileSize*4-4 + kh.getArrowNum()*24, Constant.tileSize*7 , 24);

            g2.setFont(f20);
            g2.setColor(Constant.c6);
            x = 5*Constant.tileSize;
            y +=60;

            for (String optAksi : gp.getOpsiAksi()){
                g2.drawString(optAksi,x,y);
                y+=24;
            }   

        } else if (gp.getGs() == 8){
            //choose world
            drawWorldChoose(g2);
        } else if (gp.getGs() == 9){
            //inventory
            g2.setColor(Constant.c3);
            g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

            Sim s = gp.getSim();
            Inventory items = s.getInventory();

            g2.setFont(f80);
            String text = "Inventory " + s.getNamaLengkap();
            int x = centerX(g2,text);
            int y = Constant.tileSize+12;
            g2.setColor(Constant.c2);
            g2.drawString(text,x+3,y+3);
            g2.setColor(Constant.c6);
            g2.drawString(text,x,y);

            g2.setColor(Constant.c1);
            g2.fillRect(Constant.tileSize, Constant.tileSize +24, Constant.tileSize*14, Constant.tileSize*9+24);



            for (int i = 0; i< 5; i++){
                for (int j=0; j<5; j++){
                    x = Constant.tileSize*7 + j*72;
                    y = Constant.tileSize*2 + i*72;

                    if (items.isItemAda(i*5 + j)){
                        outlinedRect(g2, x, y, 72, 72, 3, 3, Constant.c1, Constant.c2);
                    } else{
                        outlinedRect(g2, x, y, 72, 72, 3, 3, Constant.c1, Constant.c7);
                    }

                    Item item = items.getInventory().get(i*5+j);

                    if (kh.getPointer() == i*5 + j){
                        g2.setColor(Constant.c5);
                        g2.fillRect(x+3, y+3, 66, 66);

                        if (items.isItemAda(i*5 + j)){
                            g2.setColor(Constant.c2);
                        } else{
                            g2.setColor(Constant.c7);
                        }
                        g2.fillRect(Constant.tileSize +27, Constant.tileSize*2+3, Constant.tileSize*5+18, Constant.tileSize*7+18);


                        g2.drawImage(item.getImage(), Constant.tileSize+ (Constant.tileSize*7-item.getIW())/2, Constant.tileSize*2 + (Constant.tileSize*5-item.getIH())/2, null);
                        g2.setColor(Constant.c6);
                        g2.setFont(f20);
                        int x2 = Constant.tileSize*2-12;
                        int y2 = Constant.tileSize*6;
                        for (String line : item.getInfo().split("\n")){
                            g2.drawString(line, x2, y2 += 20);
                        }

                        x2= Constant.tileSize*5;
                        y2= Constant.tileSize*10 -12;
                        g2.drawString("Pilih aksi:", Constant.tileSize*2, Constant.tileSize*10 +12);
                        g2.setColor(Constant.c5);
                        g2.fillRect(x2, y2 + kh.getArrowNum()*20, Constant.tileSize*9, 20);
                        
                        if (item instanceof BahanMakanan){
                            text = "Beli\nMakan";
                        } else if (item instanceof Masakan){
                            text = "Makan\nMasak";
                        } else if (item instanceof Furnitur){
                            text = "Beli\nPasang";
                        }
                        y2-=8;
                        g2.setColor(Constant.c6);
                        for (String line : text.split("\n")){
                            g2.drawString(line, x2+48, y2 += 20);
                        }
                    }        

                    g2.drawImage(item.getImage(), x+6, y+6, 60, 60, null);
                }
            }

        } else if (gp.getGs() == 10){
        } 
    }

    private void drawWorldChoose(Graphics2D g2){
        g2.setColor(Constant.c3);
        g2.fillRect(0,0,Constant.screenWidth,Constant.screenHeight);

        outlinedRect(g2, Constant.tileSize*2, Constant.tileSize*3-12, Constant.tileSize*12, Constant.tileSize*6+24, 24, 12, Constant.c2, Constant.c3);

        g2.setFont(f80);
        String text = "Pilih Lokasi Rumah";
        int x = centerX(g2,text);
        int y = 24+ 4*Constant.tileSize;
        g2.setColor(Constant.c1);
        g2.drawString(text,x+3,y+3);
        g2.setColor(Constant.c6);
        g2.drawString(text,x,y);

        g2.setColor(Constant.c2);
        g2.fillRect(Constant.tileSize*3 +24, Constant.tileSize*6+12, Constant.tileSize*4, Constant.tileSize);
        g2.fillRect(Constant.tileSize*8 +24, Constant.tileSize*6+12, Constant.tileSize*4, Constant.tileSize);


        g2.setFont(f40);
        g2.setColor(Constant.c6);
        text = "input x:";
        x = 24+ 3*Constant.tileSize;
        y = 6*Constant.tileSize;
        g2.drawString(text, x, y);
        g2.drawString(String.valueOf(kh.getIn1()), x+12, y+48);

        text = "input y:";
        x = 24+ 8*Constant.tileSize;
        g2.drawString(text, x, y);
        g2.drawString(String.valueOf(kh.getIn2()), x+12, y+48);

        text = ">            <";
        if (kh.getArrowNum()==0){
            g2.drawString(text, 3*Constant.tileSize-8, y+44);
        } else {
            g2.drawString(text, 8*Constant.tileSize-8, y+44);
        }

        text = "> Klik ENTER untuk lanjut";
            x = centerX(g2,text);
            y = 11*Constant.tileSize;
            g2.drawString(text,x-10,y);

    }

    private void outlinedRect(Graphics2D g2, int x, int y, int width, int height, int xspace, int yspace, Color pc1, Color pc2){
        g2.setColor(pc1);
        g2.fillRect(x, y, width, height);
        g2.setColor(pc2);
        g2.fillRect(x+xspace, y+yspace, width-2*xspace, height-2*yspace);

    }

    private void drawSimInfo(Graphics2D g2, int y1, int x1, int x2, int x3, int x4, int space, Sim s){
        String text = "Nama      :";
        int x = x1;
        int y = y1;
        g2.drawString(text,x,y);

        text = s.getNamaLengkap();
        x = x2;
        g2.drawString(text,x,y);

        text = "Pekerjaan :";
        x = x1;
        y += space;
        g2.drawString(text,x,y);

        text = s.getPekerjaan().getNamaKerja();
        x = x2;
        g2.drawString(text,x,y);

        text = "Uang      :";
        x = x1;
        y += space;
        g2.drawString(text,x,y);

        text = String.valueOf(s.getUang());
        x = x2;
        g2.drawString(text,x,y);

        text = "Rumah     :";
        x = x1;
        y += space;
        g2.drawString(text,x,y);

        //text = s.getPosisiRumah();
        text = "<1,1>";
        x = x2;
        g2.drawString(text,x,y);


        text = "Mood        :";
        x = x3;
        y = y1;
        g2.drawString(text,x,y);

        text = String.valueOf(s.getKesejahteraan().getMood());
        x = x4;
        g2.drawString(text,x,y);


        text = "Kekenyangan :";
        x = x3;
        y += space;
        g2.drawString(text,x,y);

        text = String.valueOf(s.getKesejahteraan().getKekenyangan());
        x = x4;
        g2.drawString(text,x,y);

        text = "Kesehatan   :";
        x = x3;
        y += space;
        g2.drawString(text,x,y);

        text = String.valueOf(s.getKesejahteraan().getKesehatan());
        x = x4;
        g2.drawString(text,x,y);

        text = "Kebersihan  :";
        x = x3;
        y += space;
        g2.drawString(text,x,y);

        text = String.valueOf(s.getKesejahteraan().getKebersihan());
        x = x4;
        g2.drawString(text,x,y);
    }

    public int centerX(Graphics2D g2, String text){
        int len = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

        int x = Constant.screenWidth/2 - len/2;
        return x;
    }
}
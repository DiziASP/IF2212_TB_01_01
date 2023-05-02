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
import static if2212_tb_01_01.utils.Constant.*;

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
            g2.setColor(c5);
            g2.fillRect(0,0,screenWidth,screenHeight);

            g2.setFont(f80);
            String text = "Welcome to Sim-Plicity!";
            
            int x = centerX(g2,text);
            int y = 3*tileSize;
            g2.setColor(c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(c6);
            g2.drawString(text,x,y);

            x = screenWidth/2 - tileSize;
            y = 4*tileSize;
            Image img =  am.setup("/cat", tileSize*2, tileSize*2);
            g2.drawImage(img, x, y, tileSize*2, tileSize*2, null);

            g2.setFont(f40);
            text = "Play Game!";
            x = centerX(g2,text);
            y += 4*tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==0){
                g2.drawString(">",x-40,y);
            }

            text = "Help";
            x = centerX(g2,text);
            y += tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==1){
                g2.drawString(">",x-40,y);
            }

            text = "Exit";
            x = centerX(g2,text);
            y += tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==2){
                g2.drawString(">",x-40,y);
            }

        } else if (gp.getGs() == 1) {

            g2.setColor(c5);
            g2.fillRect(0,0,screenWidth,screenHeight);

            g2.setFont(f80);
            String text = "Pilih SIM-mu ^^";
            
            int x = centerX(g2,text);
            int y = 3*tileSize;
            g2.setColor(c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(c6);
            g2.drawString(text,x,y);

            x = screenWidth/2 - tileSize*2;
            y = 4*tileSize;
            Image img =  am.setup("/cat2", tileSize*4, tileSize*2);
            g2.drawImage(img, x, y, tileSize*4, tileSize*2, null);

            g2.setFont(f40);
            text = "Pilih SIM";
            x = centerX(g2,text);
            y += 4*tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==0){
                g2.drawString(">",x-40,y);
            }

            text = "Buat SIM baru";
            x = centerX(g2,text);
            y += tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==1){
                g2.drawString(">",x-40,y);
            }

            text = "Kembali";
            x = centerX(g2,text);
            y += tileSize;
            g2.drawString(text,x,y);
            if (kh.getArrowNum()==2){
                g2.drawString(">",x-40,y);
            }

        } else if (gp.getGs() == 2) {
            g2.setColor(c5);
            g2.fillRect(0,0,screenWidth,screenHeight);
            String text;
            int x; int y;

            x = 24;
            y = 5*tileSize;
            Image img =  am.setup("/larrow", 24, tileSize*1);
            g2.drawImage(img, x, y, 24, tileSize*1, null);

            x = tileSize*15;
            img =  am.setup("/rarrow", 24, tileSize*1);
            g2.drawImage(img, x, y, 24, tileSize*1, null);

            
            if (kh.getArrowNum()==0){
                g2.setFont(f80);
                text = "Help";
                x = centerX(g2,text);
                y = 3*tileSize-24;
                g2.setColor(c1);
                g2.drawString(text,x+2,y+2);
                g2.setColor(c6);
                g2.drawString(text,x,y);

                y+=tileSize;
                g2.setFont(f20);

                text = "Gunakan Arrow Key (atas, bawah, kanan, kiri) untuk\n"+ 
                "memilih menu/aksi yang ingin dilakukan, dan tekan\n" + 
                "Enter untuk memilihnya. Untuk keluar, tekan Escape.\n\n" +
                "Untuk menggerakkan SIM, gunakan Key\n"+
                "W, A, S, dan D pada Keyboard.";

                for (String line: text.split("\n")){
                    g2.drawString(line,centerX(g2, line),y+=40);
                }
            } else if (kh.getArrowNum()==1){
                g2.setFont(f80);
                text = "Tentang Permainan";
                x = centerX(g2,text);
                y = 3*tileSize-24;
                g2.setColor(c1);
                g2.drawString(text,x+2,y+2);
                g2.setColor(c6);
                g2.drawString(text,x,y);

                y+=2*tileSize;
                g2.setFont(f20);

                text = "Kamu dapat menjalankan Simulasi kehidupan sehari-\n" +
                "hari sebagai sebuah Sim. Tujuan dari permainan ini\n"
                + "adalah menjaga kesejahteraan Sim agar tidak mati.";

                for (String line: text.split("\n")){
                    g2.drawString(line,centerX(g2, line),y+=tileSize);
                }
            } else if (kh.getArrowNum()==2){
                g2.setFont(f80);
                text = "Kesejahteraan SIM";
                x = centerX(g2,text);
                y = 3*tileSize-24;
                g2.setColor(c1);
                g2.drawString(text,x+2,y+2);
                g2.setColor(c6);
                g2.drawString(text,x,y);

                y+=tileSize;
                g2.setFont(f20);

                text = "Kesejahteraan Sim terdiri dari 4 bagian, yaitu\n" +
                 "mood, kekenyangan, kesehatan dan kebersihan. Mood\n" +
                 "menunjukkan kesehatan mental dari Sim, kekenyangan\n"+
                 "menunjukkan apakah Sim kurang gizi atau tidak,\n" +
                 "kesehatan menunjukkan seberapa penyakitan sebuah\n" +
                 "Sim, dan kebersihan menunjukan seberapa jorok Sim\n" +
                 "tersebut. Keempat hal tersebut diukur dalam range\n" +
                 "0-100. Ketika salah satu tingkat kesejahteraan\n" +
                 "mencapai 0, Sim akan mati.";

                 for (String line: text.split("\n")){
                    g2.drawString(line,centerX(g2, line),y+=30);
                 }
                } else if (kh.getArrowNum()==3){
                    g2.setFont(f80);
                    text = "Aktivitas dengan Objek";
                    x = centerX(g2,text);
                    y = 3*tileSize-24;
                    g2.setColor(c1);
                    g2.drawString(text,x+2,y+2);
                    g2.setColor(c6);
                    g2.drawString(text,x,y);
    
                    y+=tileSize;
                    g2.setFont(f20);
    
                    text = "Terdapat objek-objek yang dapat membantu Sim\n" +
                     "bertahan hidup. Objek tersebut terbagi menjadi 3\n" +
                     "kategori, yaitu furnitur, bahan makanan, dan\n"+
                     "masakan. Furnitur digunakan untuk melakukan\n" +
                     "aktivitas yang dapat digunakan untuk meningkatkan\n" +
                     "atau menurunkan kesejahteraan Sim. Bahan makanan\n" +
                     "merupakan bahan-bahan yang dapat diolah menjadi\n" +
                     "sebuah masakan.\n";    
                     for (String line: text.split("\n")){
                        g2.drawString(line,centerX(g2, line),y+=30);
                    } 
                }else if (kh.getArrowNum()==4){
                    g2.setFont(f80);
                    text = "Memperoleh Objek";
                    x = centerX(g2,text);
                    y = 3*tileSize-24;
                    g2.setColor(c1);
                    g2.drawString(text,x+2,y+2);
                    g2.setColor(c6);
                    g2.drawString(text,x,y);
    
                    y+=tileSize;
                    g2.setFont(f20);
    
                    text = "Sim dapat memperoleh objek dengan membeli atau \n" +
                     "memasaknya. Objek yang bisa dibeli adalah furnitur\n" +
                     "dan bahan makanan, sedangkan masakan hanya bisa\n"+
                     "diperoleh dengan aktivitas memasak. Jangan lupa\n" +
                     "beli barang membutuhkan UANG. Uang harus\n" +
                     "didapatkan dengan cara bekerja. Karena bahkan \n"+
                     "dalam permainan ini kita semua masih seorang\n"+
                     "korporat ><.";
                     for (String line: text.split("\n")){
                        g2.drawString(line,centerX(g2, line),y+=30);
                    }
                }else if (kh.getArrowNum()==5){
                        g2.setFont(f80);
                        text = "Kehidupan SIM";
                        x = centerX(g2,text);
                        y = 3*tileSize;
                        g2.setColor(c1);
                        g2.drawString(text,x+2,y+2);
                        g2.setColor(c6);
                        g2.drawString(text,x,y);
        
                        y+=tileSize;
                        g2.setFont(f20);
        
                        text = "Tiap Sim memiliki rumah mereka masing-masing.\n" +
                         "Rumah ini terdiri dari ruangan-ruangan yang dapat\n" +
                         "kamu tata untuk mendukung kesehariannya. Sim\n"+
                         "dapat berjalan berpindah ruangan, dan kalau\n" +
                         "aktivitas yang dapat digunakan untuk meningkatkan\n" +
                         "kesepian, bisa kok berkunjung ke rumah lainnya.\n";
                         for (String line: text.split("\n")){
                            g2.drawString(line,centerX(g2, line),y+=40);
                        }
                
            }
            
            
            g2.setFont(f40);
            text = "> Klik Enter atau ESC untuk Kembali";
            x = centerX(g2,text);
            y = 10*tileSize+24;
            g2.drawString(text,x,y);
            

        } else if (gp.getGs() == 3){
            g2.setColor(c5);
            g2.fillRect(0,0,screenWidth,screenHeight);

            g2.setFont(f80);
            String text = "Pilih SIM-mu";
            int x = centerX(g2,text);
            int y = 3*tileSize;
            g2.setColor(c1);
            g2.drawString(text,x+3,y+3);
            g2.setColor(c6);
            g2.drawString(text,x,y);


            g2.setFont(f20);

            x = tileSize*5;
            y = 4*tileSize;
            Image img =  am.setup("/larrow", tileSize*1, tileSize*1);
            g2.drawImage(img, x, y, tileSize*1, tileSize*1, null);

            x = tileSize*10;
            img =  am.setup("/rarrow", tileSize*1, tileSize*1);
            g2.drawImage(img, x, y, tileSize*1, tileSize*1, null);


            Sim s = gp.getSimList().get(kh.getArrowNum());

            x = tileSize*7;
            y -= 24;
            String path = String.format("/images/sim/%d/down1", s.getSpriteIndex());
            img =  am.setup(path, tileSize*2, tileSize*2);
            g2.drawImage(img, x, y, tileSize*2, tileSize*2, null);


            drawSimInfo(g2, 7*tileSize, 2*tileSize, 5*tileSize, 9*tileSize, 13*tileSize, 40, s);  


        } else if (gp.getGs() == 4){
            g2.setColor(c5);
            g2.fillRect(0,0,screenWidth,screenHeight);

            g2.setFont(f80);
            String text = "Buat SIM baru";
            int x = centerX(g2,text);
            int y = tileSize +20;
            g2.setColor(c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(c6);
            g2.drawString(text,x,y);


            // pilih avatar
            g2.setFont(f40);
            text = "Pilih avatarmu:";
            x = 2*tileSize;
            y = 7*tileSize - 15;
            g2.drawString(text,x,y);

            x = tileSize*5;
            y = 8*tileSize;
            Image img =  am.setup("/larrow", tileSize*1, tileSize*1);
            g2.drawImage(img, x, y, tileSize*1, tileSize*1, null);

            x = tileSize*10;
            img =  am.setup("/rarrow", tileSize*1, tileSize*1);
            g2.drawImage(img, x, y, tileSize*1, tileSize*1, null);

            x = tileSize*7;
            y -= tileSize/2;
            String path = String.format("/images/sim/%d/down1", kh.getArrowNum()+1);
            img =  am.setup(path, tileSize*2, tileSize*2);
            g2.drawImage(img, x, y, tileSize*2, tileSize*2, null);

            text = "> Klik ENTER untuk lanjut";
            x = centerX(g2,text);
            y += 3*tileSize;
            g2.drawString(text,x-10,y);



            // nama

            text = "Pilih namamu:";
            x = 2*tileSize;
            y = 3*tileSize -15;
            g2.drawString(text,x,y);

            x = tileSize*2;
            y = 3*tileSize;
            img =  am.setup("/innama", tileSize*12, tileSize*2);
            g2.drawImage(img, x, y, tileSize*12, tileSize*2, null);

            x = 3*tileSize;
            y += tileSize +5;
            if (kh.getInput().equals("")){
                text = "ketik namamu...";
            } else{
                text = kh.getInput();
                g2.setColor(c1);
            }
            g2.drawString(text,x,y);

            if(kh.isErrorCaught()){
                

                g2.setFont(f20);
                x = tileSize;
                y += tileSize+20;

                
                text = "sudah ada sim dengan nama itu! silahkan pilih nama lain.";
                g2.setColor(c6); 
                g2.drawString(text,x+1, y+1);
                g2.setColor(c3);
                g2.drawString(text,x, y);

                g2.setFont(f40);

                g2.setColor(c6);                
            }
            


        } else if (gp.getGs() == 5){
            //stats
            g2.setColor(c3);
            g2.fillRect(0,0,screenWidth,screenHeight);

            g2.setFont(f80);
            String text = "Selamat bermain!";
            int x = centerX(g2,text);
            int y = 2*tileSize;
            g2.setColor(c1);
            g2.drawString(text,x+3,y+3);
            g2.setColor(c6);
            g2.drawString(text,x,y);


            g2.setFont(f20);


            Sim s = gp.getSim();

            x = tileSize*7;
            y = 3*tileSize;
            String path = String.format("/images/sim/%d/down1", s.getSpriteIndex());
            Image img =  am.setup(path, tileSize*2, tileSize*2);
            g2.drawImage(img, x, y, tileSize*2, tileSize*2, null);


            drawSimInfo(g2, 6*tileSize, 2*tileSize, 5*tileSize, 9*tileSize, 13*tileSize, 40, s);

            g2.setFont(f40);
            text = "> Klik ENTER untuk lanjut";
            x = centerX(g2,text);
            y = 11*tileSize;
            g2.drawString(text,x-10,y-24);


        } else if (gp.getGs() == 6){
            //play dalam ruangan world
            gp.updateOpsiAksi();

            g2.setColor(c3);
            g2.fillRect(0,0,screenWidth,screenHeight);

            // gp.getTileManager().draw(g2);
            gp.getRoom().draw(g2);
            gp.getSim().draw(g2);


            //simmm

            Sim s  = gp.getSim();


            g2.setColor(c7);
            g2.fillRect(tileSize, tileSize*8 +24, tileSize*14, tileSize*3);
            g2.setColor(c1);
            g2.fillRect(tileSize+12, tileSize*9-12, tileSize*13+24, tileSize*2+24);

            g2.setFont(f20);
            g2.setColor(c6);

            drawSimInfo(g2, 9*tileSize +12, 2*tileSize, 5*tileSize, 9*tileSize, 13*tileSize, 20, s); 


            //aksi
            drawSubWindow(g2, gp.getSubState());

        } else if (gp.getGs() == 7){

            gp.updateMenu();

            g2.setColor(c3);
            g2.fillRect(0,0,screenWidth,screenHeight);

            outlinedRect(g2, tileSize*4-24, tileSize, tileSize*9, tileSize*9, 24, 12, c7, c2);

            g2.setFont(f20);
            String text = "Apakah kamu yakin ingin keluar?";
            int x = centerX(g2,text);
            int y = 3*tileSize;
            g2.setColor(c1);
            g2.drawString(text,x+3,y+3);
            g2.setColor(c6);
            g2.drawString(text,x,y);

            g2.setColor(c1);
            g2.fillRect(5*tileSize-24, tileSize*4-4 + kh.getArrowNum()*24, tileSize*7 , 24);

            g2.setFont(f20);
            g2.setColor(c6);
            x = 5*tileSize;
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
            g2.setColor(c3);
            g2.fillRect(0,0,screenWidth,screenHeight);

            Sim s = gp.getSim();
            Inventory items = s.getInventory();

            g2.setFont(f80);
            String text = "inventory " + s.getNamaLengkap();
            int x = centerX(g2,text);
            int y = tileSize+12;
            g2.setColor(c2);
            g2.drawString(text,x+3,y+3);
            g2.setColor(c6);
            g2.drawString(text,x,y);

            drawInventory(g2, "inventory", items);

        } else if (gp.getGs() == 10){
            // world choose
            drawWorldChoose(g2);
        } else if (gp.getGs() == 11){
            //shop
            
            g2.setColor(c3);
            g2.fillRect(0,0,screenWidth,screenHeight);

            Sim s = gp.getSim();
            Inventory items = s.getInventory();

            g2.setFont(f80);
            String text = s.getNamaLengkap() + " belanja";
            int x = centerX(g2,text);
            int y = tileSize+12;
            g2.setColor(c2);
            g2.drawString(text,x+3,y+3);
            g2.setColor(c6);
            g2.drawString(text,x,y);

            drawInventory(g2, "shop", items);
        }
        else if (gp.getGs() == 12){
            // Meninggal

            g2.setColor(c9);
            g2.fillRect(0,0,screenWidth,screenHeight);

//            Sim s = gp.getSim();

            g2.setFont(f40);
//            String text = s.getNamaLengkap() + " sudah meninggal dengan tenang";
            String text = "Nadira" + " sudah meninggal dengan tenang";
            int x = centerX(g2,text);
            int y = tileSize*4;
            g2.setColor(c2);
            g2.drawString(text,x+3,y+3);
            g2.setColor(c6);
            g2.drawString(text,x,y);

            x = screenWidth/2 - 2*tileSize;
            y = 6*tileSize;
            Image img =  am.setup("/deadcat", tileSize*2, tileSize*2);
            g2.drawImage(img, x, y, tileSize*4, tileSize*4, null);
        }
        else if (gp.getGs() == 15){
            // Loading Screen
            g2.setColor(c5);
            g2.fillRect(0,0,screenWidth,screenHeight);

            g2.setFont(f40);
            String text = "Loading...Please kindly wait!";

            int x = centerX(g2,text);
            int y = 5*tileSize;
            g2.setColor(c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(c6);
            g2.drawString(text,x,y);

            x = screenWidth/2 - tileSize;
            y = 6*tileSize;
            Image img =  am.setup("/sandclock", tileSize*2, tileSize*2);
            g2.drawImage(img, x, y, tileSize*2, tileSize*2, null);
        }
    }

    private void drawSubWindow(Graphics2D g2, int subState){
        // subState: 0-none, 1-tambahan, 2-pilihEditan, 3-pilihBarangPasang, 4-lokasiPasang, 5-lokasiBuang, 6-lokasiEdit, 7-lokasiBaru
        // 8-cari kerja, 9-pilihMakanan, 10-pilihMenuMakanan
        // 11-tampilkan waktu
        // 13-durasiAksi, 14-aksiCounter, 15-aksiBerhasil, 16-batalkanAksi??

        g2.setColor(c7);
        g2.fillRect(tileSize*9, 24, tileSize*6, tileSize*8);
        g2.setColor(c5);
        g2.fillRect(tileSize*9 +12, 36, tileSize*6-24, tileSize*8-12);
        String text; int x; int y;
        g2.setColor(c2);

        if (subState<=2 || subState==8 || subState==16){
            g2.setColor(c6);
            if(subState==8){
                text = "Pilih pekerjaan barumu:";

                //error kalo sama //////////////////////////////////
            } else{
                text = "Aksi:";
            }
            x = 10*tileSize-12;
            y = tileSize+24;
            g2.drawString(text,x,y);

            g2.setColor(c1);
            g2.fillRect(10*tileSize-12, tileSize*2 - 8 + kh.getArrowNum()*28, tileSize*4 +24, 24);

            g2.setColor(c6);
            x += 12;
            y +=32;

            for (String optAksi : gp.getOpsiAksi()){
                g2.drawString(optAksi,x,y);
                y+=28;
            }

        } else if (subState>=4 && subState<7) {
            int i = kh.getPointer() / 6;
            int j = kh.getPointer() % 6;

            g2.setColor(c5);
            g2.fillRect(tileSize * 2 + j * tileSize, 72 + i * tileSize, tileSize, tileSize);


            if (subState==4){
            text = "memilih lokasi benda\nyang ingin dipasang\n";

            } else if (subState==5) {
            text = "memilih lokasi benda\nyang ingin dihapus:";
            } else {
                text = "memilih lokasi benda\nyang ingin dipindahkan:";

            }
            g2.setColor(c6);
            x = 10*tileSize-18;
            y = tileSize*4;
            for (String line: text.split("\n")){
                g2.drawString(line,x,y+=20);
            }

        } else if (subState==3){
            text = "memilih furnitur\nyang ingin dipasang";
            x = 10*tileSize-22;
            y = tileSize+4;
            g2.setColor(c6);
            for (String line: text.split("\n")){
                g2.drawString(line,x,y+=20);
            }
            Inventory items = gp.getSim().getInventory();

            g2.setColor(c1);
            g2.fillRect(tileSize*9+12, tileSize*2+8, 4*tileSize+72, 4*tileSize+14);
            for (int i = 0; i< 3; i++){
                for (int j=0; j<4; j++){
                    x = tileSize*9 +16 + j*64;
                    y = tileSize*2 + i*64+12;

                    if (items.isItemSisa(i*4 + j)){
                        outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c2);
                    } else{
                        outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c7);
                    }

                    Item item = items.getInventory().get(i*4+j);

                    if (kh.getPointer() == i*4 + j){
                        g2.setColor(c5);
                        g2.fillRect(x+3, y+3, 58, 58);

                        // if (items.isItemAda(i*4 + j)){
                        //     g2.setColor(c2);
                        // } else{
                        //     g2.setColor(c7);
                        // }
                        outlinedRect(g2, tileSize, 24, tileSize*8, tileSize*8+12, 12, 12, c7, c2);

                        g2.drawImage(item.getImage(), tileSize+ (tileSize*8-item.getIW())/2, (tileSize*6-item.getIH())/2, null);

                        g2.setColor(c6);
                        g2.setFont(f20);
                        int x2 = tileSize*2-12;
                        int y2 = tileSize*5+20;
                        for (String line : item.getInfo().split("\n")){
                            g2.drawString(line, x2, y2 += 20);
                        }
                    }
                    g2.drawImage(item.getImage(), x+6, y+6, 52, 52, null);

                }
            }

            x = 10*tileSize;
            y = tileSize;
            g2.setColor(c6);
            text = "> ENTER untuk memilih";
            g2.drawString(text, x-22, y+tileSize*6 +15);
            text = "> ESC untuk kembali";
            g2.drawString(text, x-22, y+tileSize*6 +35);

        }   else if (subState==9) {
            text = "memilih makanan\nuntuk dimakan:";
            x = 10 * tileSize - 22;
            y = tileSize + 24;
            g2.setColor(c6);
            for (String line : text.split("\n")) {
                g2.drawString(line, x, y += 20);
            }
            Inventory items = gp.getSim().getInventory();

            g2.setColor(c1);
            g2.fillRect(tileSize * 9 + 12, tileSize * 4 - 32, 4 * tileSize + 72, 4 * tileSize - 50);
            if (kh.getPointer() < 8) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i * 4 + j < 8) {
                            x = tileSize * 9 + 16 + j * 64;
                            y = tileSize * 2 + i * 64 + 100 - 32;

                            if (items.isItemAda(i * 4 + j + 12)) {
                                outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c2);
                            } else {
                                outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c7);
                            }

                            Item item = items.getInventory().get(i * 4 + j + 12);

                            if (kh.getPointer() == i * 4 + j) {
                                g2.setColor(c5);
                                g2.fillRect(x + 3, y + 3, 58, 58);

                                if (items.isItemAda(i * 4 + j + 12)) {
                                    g2.setColor(c2);
                                } else {
                                    g2.setColor(c7);
                                }
                                outlinedRect(g2, tileSize, 24, tileSize * 8, tileSize * 8 + 12, 12, 12, c7, c2);

                                g2.drawImage(item.getImage(), tileSize + (tileSize * 8 - item.getIW()) / 2, tileSize + (tileSize * 3 - item.getIH()) / 2, null);

                                g2.setColor(c6);
                                g2.setFont(f20);
                                int x2 = tileSize * 2 - 12;
                                int y2 = tileSize * 4 + 20;
                                for (String line : item.getInfo().split("\n")) {
                                    g2.drawString(line, x2, y2 += 20);
                                }
                            }
                            g2.drawImage(item.getImage(), x + 6, y + 6, 52, 52, null);

                        }
                    }
                }
                Image img = am.setup("/darrow", tileSize * 2, tileSize);
                g2.drawImage(img, 11 * tileSize + 36, 7 * tileSize - 24, 24, 12, null);
            } else {
                for (int i = 2; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i * 4 + j < 13) {
                            x = tileSize * 9 + 16 + j * 64;
                            y = tileSize * 2 + i * 64 + 100 - 32 - 129;

                            if (items.isItemAda(i * 4 + j + 12)) {
                                outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c2);
                            } else {
                                outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c7);
                            }

                            Item item = items.getInventory().get(i * 4 + j + 12);

                            if (kh.getPointer() == i * 4 + j) {
                                g2.setColor(c5);
                                g2.fillRect(x + 3, y + 3, 58, 58);

                                if (items.isItemAda(i * 4 + j + 12)) {
                                    g2.setColor(c2);
                                } else {
                                    g2.setColor(c7);
                                }
                                outlinedRect(g2, tileSize, 24, tileSize * 8, tileSize * 8 + 12, 12, 12, c7, c2);

                                g2.drawImage(item.getImage(), tileSize + (tileSize * 8 - item.getIW()) / 2, tileSize + (tileSize * 3 - item.getIH()) / 2, null);

                                g2.setColor(c6);
                                g2.setFont(f20);
                                int x2 = tileSize * 2 - 12;
                                int y2 = tileSize * 4 + 20;
                                for (String line : item.getInfo().split("\n")) {
                                    g2.drawString(line, x2, y2 += 20);
                                }
                            }
                            g2.drawImage(item.getImage(), x + 6, y + 6, 52, 52, null);

                        }
                    }
                }
                Image img = am.setup("/uarrow", tileSize * 2, tileSize);
                g2.drawImage(img, 11 * tileSize + 36, 3 * tileSize - 5, 24, 12, null);
            }

            x = 10 * tileSize;
            y = tileSize;
            g2.setColor(c6);
            text = "> ENTER untuk memilih";
            g2.drawString(text, x - 22, y + tileSize * 6 + 15);
            text = "> ESC untuk kembali";
            g2.drawString(text, x - 22, y + tileSize * 6 + 35);

        }
        else if (subState==7){
            g2.setColor(c6);
            text = "Pilih lokasi:";
            x = 10*tileSize-18;
            y = tileSize+24;
            g2.drawString(text,x,y);

            g2.setColor(c1);
            g2.fillRect(10*tileSize-12, tileSize*2 - 8 + kh.getArrowNum()*28, tileSize*4 +24, 24);

            g2.setColor(c6);
            x += 12;
            y +=32;

            for (String optAksi : gp.getOpsiAksi()) {
                g2.drawString(optAksi, x, y);
                y += 28;
            }

            y=tileSize*5 +24;
            text = "Beri nama ruangan:";
            g2.drawString(text, x, y-10);
            g2.setColor(c4);
            g2.fillRect(x, y, tileSize*4 +24, tileSize);
            g2.setColor(c1);
            g2.drawString(kh.getInput(), x+20, y+25);

            g2.setColor(c6);
            text = "> Klik ENTER";;
            y = tileSize*7 +30;
            g2.drawString(text,x-10,y);

        // } else if (subState==8){
        }
        else if (subState==10){
            text = "memilih menu\nuntuk dimasak:";
            x = 10*tileSize-22;
            y = tileSize+24;
            g2.setColor(c6);
            for (String line: text.split("\n")){
                g2.drawString(line,x,y+=20);
            }
            Inventory items = gp.getSim().getInventory();

            g2.setColor(c1);
            g2.fillRect(tileSize*9+12, tileSize*4-32, 4*tileSize+72, 4*tileSize-50);
            for (int i = 0; i< 2; i++){
                for (int j=0; j<4; j++){
                    if (i*4+j <5){
                        x = tileSize*9 +16 + j*64;
                        y = tileSize*2 + i*64 + 100-32;
    
                        if (items.isBisaMasak(i*4 + j+20)){
                            outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c2);
                        } else{
                            outlinedRect(g2, x, y, 64, 64, 3, 3, c1, c7);
                        }
    
                        Item item = items.getInventory().get(i*4+j+20);
    
                        if (kh.getPointer() == i*4 + j){
                            g2.setColor(c5);
                            g2.fillRect(x+3, y+3, 58, 58);
    
                            if (items.isBisaMasak(i*4 + j+20)){
                                g2.setColor(c2);
                            } else{
                                g2.setColor(c7);
                            }
                            outlinedRect(g2, tileSize, 24, tileSize*8, tileSize*8+12, 12, 12, c7, c2);
    
                            g2.drawImage(item.getImage(), tileSize+ (tileSize*8-item.getIW())/2, tileSize+ (tileSize*3-item.getIH())/2, null);
    
                            g2.setColor(c6);
                            g2.setFont(f20);
                            int x2 = tileSize*2-12;
                            int y2 = tileSize*4+20;
                            for (String line : item.getInfo().split("\n")){
                                g2.drawString(line, x2, y2 += 20);
                            }
                        }
                        g2.drawImage(item.getImage(), x+6, y+6, 52, 52, null);
    
                    }
                }
            }            

            x = 10*tileSize;
            y = tileSize;
            g2.setColor(c6);
            text = "> ENTER untuk memilih";
            g2.drawString(text, x-22, y+tileSize*6 +15);
            text = "> ESC untuk kembali";
            g2.drawString(text, x-22, y+tileSize*6 +35);

        } else if (subState==11){
            g2.setFont(f20);
            x = 9*tileSize+24;
            y = tileSize*2-24;
            g2.setColor(c6);
            text = "pilih teman\nuntuk dikunjungi!"; //masukinnnnnnnn
            for (String line : text.split("\n")){
                g2.drawString(line,x+ x+ centerX(g2, line, tileSize*5),y+=24);
            }
            

            Sim s = gp.getSim(kh.getArrowNum());

            String path = String.format("/images/sim/%d/down1", s.getSpriteIndex());
            Image img =  am.setup(path, tileSize*2, tileSize*2);
            g2.drawImage(img, x+tileSize*3/2, y+24, tileSize*2, tileSize*2, null);

            y+=tileSize*4-24;
            text = "rumah " + s.getNamaLengkap(); //masukinnnnnnnn
            g2.drawString(text,x + centerX(g2, text, tileSize*5),y);
            text = "posisi: " + s.getPosisiRumah().infoPoint();
            g2.drawString(text,x + centerX(g2, text, tileSize*5),y+28);

            text = "> ENTER untuk memilih";
            g2.drawString(text, x-22, y+tileSize*6 +15);
            text = "> ESC untuk kembali";
            g2.drawString(text, x-22, y+tileSize*6 +35);

            // } else if (subState==12){
        //     text = ""
        } else if (subState==13){
            g2.setFont(f20);
            text = "masukkan durasi " ;
            x = 10*tileSize-12;
            y = tileSize*3;
            g2.setColor(c6);
            g2.drawString(text,x,y);
            text = kh.getInput() +":";
            g2.drawString(text, x, y+24);

            g2.setColor(c1);
            g2.fillRect(x+20, y+tileSize, 3*tileSize, tileSize);

            g2.setColor(c6);
            g2.drawString(String.valueOf(kh.getIn1()), x+32, y+tileSize +28);

        } else if (subState==14){
            // g2.setColor(new Color (0,0,0,200));
            // x =3*tileSize+24;
            // y = 3*tileSize;
            // g2.fillRect(x,y,y,y);

            x = 10*tileSize-12;
            y = tileSize+24;
    
            g2.setColor(c6);
            text = gp.getSim().getNamaLengkap();

            if (!gp.getSim().getIsDoAksiAktif()){
                y+=24;
                g2.drawString(text, tileSize*12 - ((int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2), y+2*tileSize);  
                text = " sudah selesai " ;
                g2.drawString(text, tileSize*12 - ((int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2), y+2*tileSize+24);
                text = kh.getInput() + "!";
                g2.drawString(text, tileSize*12 - ((int)g2.getFontMetrics().getStringBounds(text,g2).getWidth()/2), y+3*tileSize);
            } else{
                if (gp.getActionCounter()==0){}
                else {
                    g2.drawString(text, tileSize * 12 - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2), y + 72);

                    text = " sedang " + kh.getInput() + "...";
                    g2.drawString(text, tileSize * 12 - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2), y + 2 * tileSize);

                    text = gp.getActionCounter() + " detik";
                    g2.drawString(text, tileSize * 12 - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2), y + 4 * tileSize);
                    text = "tersisa";
                    g2.drawString(text, tileSize * 12 - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2), y + 4 * tileSize + 24);
                }
            }

                      

        } else if (subState==15){
            text = "Aksi berhasil dilakukan!";
        } else if (subState==16){
        // } else if (subState==17){
        // } else if (subState==18){
        }

    }

    private void drawLokasi(){

    }

    private void drawInventory(Graphics2D g2, String type, Inventory items){
        g2.setColor(c1);
            g2.fillRect(tileSize, tileSize +24, tileSize*14, tileSize*9+24);



            for (int i = 0; i< 5; i++){
                for (int j=0; j<5; j++){
                    int x = tileSize*7 + j*72;
                    int y = tileSize*2 + i*72;

                    if (items.isItemAda(i*5 + j)){
                        outlinedRect(g2, x, y, 72, 72, 3, 3, c1, c2);
                    } else{
                        outlinedRect(g2, x, y, 72, 72, 3, 3, c1, c7);
                    }

                    Item item = items.getInventory().get(i*5+j);

                    if (kh.getPointer() == i*5 + j){
                        g2.setColor(c5);
                        g2.fillRect(x+3, y+3, 66, 66);

                        if (items.isItemAda(i*5 + j)){
                            g2.setColor(c2);
                        } else{
                            g2.setColor(c7);
                        }
                        g2.fillRect(tileSize +27, tileSize*2+3, tileSize*5+18, tileSize*7+18);


                        g2.drawImage(item.getImage(), tileSize+ (tileSize*7-item.getIW())/2, tileSize*2 + (tileSize*5-item.getIH())/2, null);
                        g2.setColor(c6);
                        g2.setFont(f20);
                        int x2 = tileSize*2-12;
                        int y2 = tileSize*6;
                        for (String line : item.getInfo().split("\n")){
                            g2.drawString(line, x2, y2 += 20);
                        }

                        String text="";
                        x2= tileSize*5;
                        y2= tileSize*10 -12;

                        g2.drawString("Pilih aksi:", tileSize*2, tileSize*10 +12);
                        g2.setColor(c5);

                        if (type =="inventory"){ 
                             g2.fillRect(x2, y2 -7 + kh.getArrowNum()*20, tileSize*9, 20);
                            
                            if (item instanceof BahanMakanan){
                                text = "Beli\nMakan\nKembali";
                            } else if (item instanceof Masakan){
                                text = "Masak\nMakan\nKembali";
                            } else if (item instanceof Furnitur){
                                text = "Beli\nPasang\nKembali";
                            }
                            y2-=15;
                            g2.setColor(c6);
                            for (String line : text.split("\n")){
                                g2.drawString(line, x2+48, y2 += 20);
                            }
                        } else if (type =="shop"){
                            g2.fillRect(x2, y2 + kh.getArrowNum()*20, tileSize*9, 20);
                            
                            if (item instanceof BahanMakanan){
                                text = "Beli\nKembali";
                            } else if (item instanceof Masakan){
                                text = "Barang tidak bisa dibeli\nKembali";
                            } else if (item instanceof Furnitur){
                                text = "Beli\nKembali";
                            }
                            y2-=8;
                            g2.setColor(c6);
                            for (String line : text.split("\n")){
                                g2.drawString(line, x2+48, y2 += 20);
                            }                            
                        }
                    }        

                    g2.drawImage(item.getImage(), x+6, y+6, 60, 60, null);
                }
            }
    }

    private void drawWorldChoose(Graphics2D g2){
        g2.setColor(c3);
        g2.fillRect(0,0,screenWidth,screenHeight);

        outlinedRect(g2, tileSize*2, tileSize*3-12, tileSize*12, tileSize*6+24, 24, 12, c2, c4);

        g2.setFont(f80);
        String text = "Pilih Lokasi Rumah";
        int x = centerX(g2,text);
        int y = 24+ 4*tileSize;
        g2.setColor(c1);
        g2.drawString(text,x+3,y+3);
        g2.setColor(c6);
        g2.drawString(text,x,y);

        g2.setColor(c2);
        g2.fillRect(tileSize*3 +24, tileSize*6+12, tileSize*4, tileSize);
        g2.fillRect(tileSize*8 +24, tileSize*6+12, tileSize*4, tileSize);


        g2.setFont(f40);
        g2.setColor(c6);
        text = "input x:";
        x = 24+ 3*tileSize;
        y = 6*tileSize;
        g2.drawString(text, x, y);
        g2.drawString(String.valueOf(kh.getIn1()), x+12, y+48);

        text = "input y:";
        x = 24+ 8*tileSize;
        g2.drawString(text, x, y);
        g2.drawString(String.valueOf(kh.getIn2()), x+12, y+48);

        text = ">            <";
        if (kh.getArrowNum()==0){
            g2.drawString(text, 3*tileSize-8, y+44);
        } else {
            g2.drawString(text, 8*tileSize-8, y+44);
        }

        text = "> Klik ENTER untuk lanjut";
            x = centerX(g2,text);
            y = 11*tileSize;
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

        text = "Gaji      :";
        x = x1;
        y+=space;
        g2.drawString(text,x,y);

        text = String.valueOf(s.getPekerjaan().getGaji());
        x = x2;
        g2.drawString(text,x,y);

        text = "Rumah     :";
        x = x1;
        y += space;
        g2.drawString(text,x,y);

        text = s.getPosisiRumah().infoPoint();
        // text = "<1,1>";
        x = x2;
        g2.drawString(text,x,y);

        text = "Posisi    :";
        x = x1;
        y+=space;
        g2.drawString(text, x, y);

        text = s.getCurRoom().getRoomName();
        x = x2;
        g2.drawString(text, x, y);

        text = "Uang        :";
        x = x3;
        y = y1;
        g2.drawString(text,x,y);

        text = String.valueOf(s.getUang());
        x = x4;
        g2.drawString(text,x,y);

        text = "Mood        :";
        x = x3;
        y += space;
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

        int x = screenWidth/2 - len/2;
        return x;
    }

    public int centerX(Graphics2D g2, String text, int width){
        int len = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

        int x = width/2 - len/2;
        return x;
    }
}

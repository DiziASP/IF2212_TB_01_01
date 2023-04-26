import javax.swing.*; //frame, label, img, border
import javax.swing.border.*;
import java.awt.*; //color
import java.awt.event.*;

public class Menu{

    GamePanel gp;

    Font f20, f40, f80;

    CP cp = new CP();

    String screen = "welcome";

    int commandNum = 0;

    // JButton b1 = new JButton("Start Game!");
    // JButton b2 = new JButton("Help");
    // JButton b3 = new JButton("Exit");



    public Menu(GamePanel gp) {

        this.gp = gp;
        f20 = new Font("Courier New", Font.PLAIN, 20);
        f40 = new Font("Courier New", Font.BOLD, 30);
        f80 = new Font("Courier New", Font.BOLD, 42);

        

        // JFrame fMenu = new JFrame("Welcome to Sim-Plicity");
        // fMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // fMenu.setSize(1300, 1000);
        // fMenu.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // fMenu.setLayout(new BorderLayout());
        // fMenu.setResizable(false);
        // fMenu.setLocationRelativeTo(null);
        // fMenu.setBackground(cp.c1);

        // fMenu.pack();

        // String text = "Welcome to Sim-Plicity!";
        // int x = getXforCenteredText(text);
        // int y = 3*tileSize;
        // fMenu.drawString(text,x,y);




        

    // JFrame fMenu = new JFrame("Welcome to Sim-Plicity!");
    // fMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // // fMenu.setPreferredSize(768, 576);
    // fMenu.setLayout(new BorderLayout());
    // fMenu.setResizable(false);

    // ImageIcon logo = new ImageIcon("../resources/cat.jpg");
    // fMenu.setIconImage(logo.getImage());

    // JPanel p1 = new JPanel();
    // JPanel p2 = new JPanel();
    // JPanel p3 = new JPanel();
    // JPanel p4 = new JPanel();

    // JPanel pRapiin = new JPanel();
    // pRapiin.add(p1, BorderLayout.NORTH);
    // pRapiin.add(p2, BorderLayout.WEST);
    // pRapiin.add(p3, BorderLayout.EAST);
    // pRapiin.add(p4, BorderLayout.SOUTH);
    // JPanel pMain = new JPanel(new GridLayout(4, 1));

    // pMain.setBackground(cp.c1);
    // pMain.setPreferredSize(new Dimension(400, 600));
    // pRapiin.add(pMain, BorderLayout.CENTER);

    // JLabel lTitle = new JLabel("Selamat datang di");
    // JLabel lTitle2 = new JLabel("Selamat datang di");

    // lTitle.setFont(new Font("Courier New", Font.PLAIN, 24));
    // lTitle.setForeground(cp.c6);

    // b1.setFont(new Font("Courier New", Font.PLAIN, 24));
    // b1.setForeground(cp.c6);
    // b1.setFocusable(false);
    // b1.setBorder(BorderFactory.createLineBorder(cp.c6, 1, true));
    // b1.setBackground(cp.c1);
    // b1.setSize(200, 70);
    // b1.setBounds(20, 20, 10, 10);

    // pMain.add(lTitle, BorderLayout.CENTER);
    // pMain.add(b1);
    // pMain.add(b2);
    // pMain.add(b3);

    // JPanel pFoot = new JPanel();
    // pFoot.setBackground(cp.c6);
    // pFoot.setPreferredSize(new Dimension(100, 30));

    // JLabel lFoot = new JLabel(" dibuat oleh Nadira, Naura, Dizi, Mario, Kadek.");
    // lFoot.setFont(new Font("Courier New", Font.PLAIN, 16));
    // lFoot.setForeground(cp.c1);

    // pFoot.add(lFoot, BorderLayout.WEST);

    // fMenu.add(pRapiin, BorderLayout.CENTER);
    // fMenu.add(pFoot, BorderLayout.SOUTH);

    // fMenu.setVisible(true);

    // }
    }

    public void draw(Graphics2D g2){
        //WELCOME

        if (screen == "welcome"){
            g2.setColor(cp.c5);
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            g2.setFont(f80);
            String text = "Welcome to Sim-Plicity!";
            
            int x = getXforCenteredText(g2,text);
            int y = 3*gp.tileSize;
            g2.setColor(cp.c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(cp.c6);
            g2.drawString(text,x,y);

            x = gp.screenWidth/2 - gp.tileSize;
            y = 4*gp.tileSize;
            Image img = new ImageIcon("../resources/cat.png").getImage();
            g2.drawImage(img, x, y, gp.tileSize*2, gp.tileSize*2, null);

            g2.setFont(f40);
            text = "Play Game!";
            x = getXforCenteredText(g2,text);
            y += 4*gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==0){
                g2.drawString(">",x-40,y);
            }

            text = "Help";
            x = getXforCenteredText(g2,text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==1){
                g2.drawString(">",x-40,y);
            }

            text = "Exit";
            x = getXforCenteredText(g2,text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==2){
                g2.drawString(">",x-40,y);
            }

        } else if (screen == "setup") {

            g2.setColor(cp.c5);
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            g2.setFont(f80);
            String text = "Pilih SIM-mu ^^";
            
            int x = getXforCenteredText(g2,text);
            int y = 3*gp.tileSize;
            g2.setColor(cp.c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(cp.c6);
            g2.drawString(text,x,y);

            x = gp.screenWidth/2 - gp.tileSize*2;
            y = 4*gp.tileSize;
            Image img = new ImageIcon("../resources/cat2.png").getImage();
            g2.drawImage(img, x, y, gp.tileSize*4, gp.tileSize*2, null);

            g2.setFont(f40);
            text = "Pilih SIM";
            x = getXforCenteredText(g2,text);
            y += 4*gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==0){
                g2.drawString(">",x-40,y);
            }

            text = "Buat SIM baru";
            x = getXforCenteredText(g2,text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==1){
                g2.drawString(">",x-40,y);
            }

            text = "Kembali";
            x = getXforCenteredText(g2,text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==2){
                g2.drawString(">",x-40,y);
            }

        } else if (screen == "help") {
            g2.setColor(cp.c5);
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            g2.setFont(f80);
            String text = "Help [placeholder]";
            int x = getXforCenteredText(g2,text);
            int y = 3*gp.tileSize;
            g2.setColor(cp.c1);
            g2.drawString(text,x+2,y+2);
            g2.setColor(cp.c6);
            g2.drawString(text,x,y);

            
            g2.setFont(f20);
            text = "Kamu dapat melakukan aksi apapun yang kamu inginkan";
            x = getXforCenteredText(g2,text);
            y += 2*gp.tileSize;
            g2.drawString(text,x,y);

            text = "sebagai SIM yang kamu pilih. Kreasikan rumah impianmu!";
            x = getXforCenteredText(g2,text);
            y += gp.tileSize;
            g2.drawString(text,x,y);

            text = "Tetapi jangan lupa untuk menjaga diri ya^^";
            x = getXforCenteredText(g2,text);
            y += gp.tileSize;
            g2.drawString(text,x,y);
            

            g2.setFont(f40);
            text = "Kembali";
            x = getXforCenteredText(g2,text);
            y += 2*gp.tileSize;
            g2.drawString(text,x,y);
            if (commandNum==0){
                g2.drawString(">",x-40,y);
            }

        } else if (screen == "baru"){
            
        }





    }

    // public void actionPerformed(ActionEvent e) {
    //     if (e.getSource() == b1) {
    //         System.out.println("click");
    //     }
    // }
    
    public int getXforCenteredText(Graphics2D g2, String text){
        int len = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

        int x = gp.screenWidth/2 - len/2;
        return x;
    }

}

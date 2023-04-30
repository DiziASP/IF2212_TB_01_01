package if2212_tb_01_01.items;

import if2212_tb_01_01.items.furnitur.Furnitur;
import if2212_tb_01_01.utils.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static if2212_tb_01_01.utils.Constant.*;

public abstract class Item {
    private String nama;
    private String kategori;
    private int amount;
    private int iw;
    private int ih;

    private BufferedImage image;

    /**
     * Constructor untuk Item
     * @param nama
     * @param kategori
     * @param imagePath
     * @param imageWidth
     * @param imageHeight
     */
    public Item(String nama, String kategori, String imagePath, int imageWidth, int imageHeight){
        this.nama = nama;
        this.kategori = kategori;
        this.image = setup(imagePath, imageWidth, imageHeight);
        this.iw = imageWidth;
        this.ih = imageHeight;
        this.amount = 0;
    }

    public void draw(Graphics2D g2d, int positionX, int positionY){

        int width = g2d.getClipBounds().width;
        int height = g2d.getClipBounds().height;

        int roomX = (width - tileSize * 14) / 2;
        int roomY = (height - tileSize * 11) / 2;
        g2d.drawImage(this.image, (roomX + positionX * tileSize), (roomY + positionY * tileSize), null);


    }

    public void update(){}

    /**
     * Getter method for nama
     * @return nama
     */
    public String getNama() {
        return this.nama;
    }

    /**
     * Setter method for nama
     * @param nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Getter method for kategori
     * @return
     */
    public String getKategori() {
        return this.kategori;
    }

    /**
     * Setter method for kategori
     * @param kategori
     */
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getAmount(){
        return this.amount;
    }

    public void incAmount(){
        this.amount++;
    }

    public void decAmount(){
        this.amount--;
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public int getIW(){
        return this.iw;
    }
    public int getIH(){
        return this.ih;
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return UtilityTool.scaleImage(image, width, height);
    }

    public abstract String getInfo();

    public abstract void printInfo();
}
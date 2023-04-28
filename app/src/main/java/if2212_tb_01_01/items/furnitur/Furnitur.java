package if2212_tb_01_01.items.furnitur;

import if2212_tb_01_01.items.Item;

import java.awt.*;

import static if2212_tb_01_01.utils.Constant.*;

public abstract class Furnitur extends Item {
    private Rectangle interactionArea;

    private Point posisi;
    private String namaAksi;
    private int harga;
    private int panjang;
    private int lebar;
    private int rotate;
    private int amountPut;

    public Furnitur(String nama, String kategori, String imagePath, int imageWidth, int imageHeight){
        super(nama, kategori, imagePath, imageWidth, imageHeight);
    }

    //Membuat list Furnitur

    //Getter method for listFurnitur

    //Getter method for aksi
    public String getNamaAksi(){
        return namaAksi;
    }

    //Setter method for aksi
    public void setNamaAksi(String aksi){
        this.namaAksi = aksi;
    }

    // Getter method for harga
    public int getHarga(){
        return harga;
    }
    // Setter method for harga
    public void setHarga(int harga){
        this.harga = harga;
    }

    // Getter method for panjang
    public Integer getPanjang(){
        return panjang;
    }

    // Setter method for panjang
    public void setPanjang(int panjang){
        this.panjang = panjang;
    }
    // Getter method for lebar
    public Integer getLebar(){
        return lebar;
    }

    // Setter method for lebar
    public void setLebar(int lebar){
        this.lebar = lebar;
    }

    // Getter method for isVertikal
    // public boolean isVertikal(){
    //     return isVertikal;
    // }

    // // Setter method for isVertikal
    // public void setVertikal(boolean isVertikal){
    //     this.isVertikal = isVertikal;
    // }

    public String getInfo(){
        return "Nama: " + this.getNama() + "\n" +
                "Jumlah: " + this.getAmount() + "\n" +
                "Sisa di Inventory: " + (this.getAmount()-this.getAmountPut()) + "\n" + 
                "Harga: " + this.harga + "\n" +
                "Panjang: " + this.panjang + "\n" +
                "Lebar: " + this.lebar + "\n" ;
                // "Posisi: " + this.posisi + "\n" +
                // "Vertikal: " + this.isVertikal + "\n";
    }
    public void printInfo(){
        System.out.println(getInfo());
    }

    // Getter method for posisi
    public Point getPosisi() {
        return posisi;
    }

    // Setter method for posisi
    public void setPosisi(Point posisi) {
        this.posisi = posisi;
    }

    public Rectangle getInteractionArea(){
        return this.interactionArea;
    }

    public int getAmountPut(){
        return this.amountPut;
    }

    public void incAmountPut(){
        this.amountPut++;
    }

    public void decAmountPut(){
        this.amountPut--;
    }


    public void setInteractionArea(Rectangle interactionArea){
        this.interactionArea = interactionArea;
    }

    public void setInteractionArea(Integer x, Integer y){
        this.interactionArea.setRect(x, y, tileSize, tileSize);
    }

    @Override
    public void draw(Graphics2D g2d, int positionX, int positionY){
        super.draw(g2d, positionX, positionY);
        // Debugging
//        g2d.setColor(Color.YELLOW);
//        g2d.fill(this.interactionArea);
    }
}

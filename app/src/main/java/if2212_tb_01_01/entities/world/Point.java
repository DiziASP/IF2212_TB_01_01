package if2212_tb_01_01.entities.world;

import java.util.*;
public class Point {

    /* Point Attributes */
    Integer x;
    Integer y;

    /**
     * <h2>Point Constructor</h2>
     * <p>
     * Create a new Point Object
     * </p>
     * 
     * @param x : absis of the point
     * @param y : ordinat of the point
     * @return Point Object
     */
    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point point){
        this.x = point.x;
        this.y = point.y;
    }

    /**
     * <h2>X Getter</h2>
     * <p>
     * Get X value from Point Object
     * </p>
     * 
     * @return X value of Point Object
     */
    public Integer getX() {
        return x;
    }

    /**
     * <h2>Y Getter</h2>
     * <p>
     * Get Y value from Point Object
     * </p>
     * 
     * @return Y value of Point Object
     */
    public Integer getY() {
        return y;
    }

    /**
     * <h2>X Setter</h2>
     * <p>
     * Set X value from Point Object
     * </p>
     * 
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * <h2>Y Setter</h2>
     * <p>
     * Set Y value from Point Object
     * </p>
     * 
     */
    public void setY(Integer y) {
        this.y = y;
    }

    public void setPoint(Integer x, Integer y){
        setX(x);
        setY(y);
    }

    public boolean isNear(Point p) {
        return (Math.abs(this.x - p.x) <= 1 && Math.abs(this.y - p.y) <= 1);
    }

    public String toString(){
        return ("("+ this.x +"," + this.y +")");
    }

    public boolean isPointEqual(Point point){
        return (this.x.equals(point.x) && this.y.equals(point.y));
    }

    public boolean isPointEqual(int x, int y){
        return (this.x.equals(x) && this.y.equals(y));
    }

    public static Point makePoint(String input){
        StringBuilder x = new StringBuilder("");
        StringBuilder y = new StringBuilder("");
        int i = 0;
        Point point = new Point(-1,-1);
        boolean salahInput = false;
        if(input.equals("")){
            salahInput = true;
        }
        else{
            while(!salahInput && i <input.length()){
                if((input.charAt(i) >= '0') && (input.charAt(i) <= '9')){
                    x.append(input.charAt(i));
                    i++;
                }
                else if(input.charAt(i) == ','){
                    i++;
                    while(!salahInput && i <input.length()){
                        if((input.charAt(i) >= '0') && (input.charAt(i) <= '9')){
                            // y += input.charAt(i);
                            y.append(input.charAt(i));
                            i++;
                        }
                        else{
                            salahInput = true;
                        }
                    }
                }
                else{
                    salahInput = true;
                }
            }
        }
        if(!salahInput){
            String X = x.toString();
            String Y = y.toString();
            point.setX(Integer.parseInt(X));
            point.setY(Integer.parseInt(Y));
        }
        return point;
    }

    public static Point makeRandomizePoint(){
        Point point;
        Random rand = new Random();
        int min = 0;
        int max = 6;
        int randomNumX = rand.nextInt((max - min) + 1) + min;
        int randomNumY = rand.nextInt((max - min) + 1) + min;
        point = new Point(randomNumX,randomNumY);
        return point;
    }
}

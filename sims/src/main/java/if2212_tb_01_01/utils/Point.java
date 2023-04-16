package if2212_tb_01_01.utils;

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

    public boolean isNear(Point p) {
        return (Math.abs(this.x - p.x) <= 1 && Math.abs(this.y - p.y) <= 1);
    }
}
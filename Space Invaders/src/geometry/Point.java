package geometry;
/**
 * A Point class.
 *
 * @author hadar
 */
public class Point {
    private double x;
    private double y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other a point to measure the distance to
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other a point to measure the distance to
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     *
     * @param xNew set x param
     */
    public void setX(double xNew) {
        this.x = xNew;
    }

    /**
     *
     * @param yNew set y param
     */
    public void setY(double yNew) {
        this.y = yNew;
    }

    /**
    * rounds the x value and the y value.
    *
    * @param p the given point
    * @return the rounded point
    */
   public Point roundPoint(Point p) {
       double tempX = Math.round(p.getX() * Math.pow(10, 10)) / Math.pow(10, 10);
       double tempY = Math.round(p.getY() * Math.pow(10, 10)) / Math.pow(10, 10);
       return new Point(tempX, tempY);
   }
 }
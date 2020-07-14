package geometry;

/**
 *
 * @author hadar
 *
 */
public class Velocity {
    private double dx;
    private double dy;
    private double newSpeed;

    /**
     * constructor.
     *
     * @param dx the change in x values
     * @param dy the change in y values
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
        this.newSpeed = Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * constructor.
     *
     * @param angle the given angle
     * @param speed the given speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = -Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     *
     * @return the speed
     */
    public double getSpeed() {
        return this.newSpeed;
    }

    /**
     *
     * @param s the new speed
     */
    public void setSpeed(double s) {
        this.newSpeed = s;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p a given point
     * @param dt number of seconds.
     * @return new point
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     *
     * @return dx value
     */
    public double getdX() {
        return this.dx;
    }

    /**
    *
    * @param dxNew the change of x values
    */
   public void setdX(double dxNew) {
       this.dx = dxNew;
   }

   /**
    *
    * @return dy value
    */
   public double getdY() {
       return this.dy;
   }

    /**
    *
    * @param dyNew the change of y values
    */
   public void setdY(double dyNew) {
       this.dy = dyNew;
   }
}
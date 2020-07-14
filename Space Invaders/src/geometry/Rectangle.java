package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * A Rectangle class.
 *
 * @author hadar
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;
    private double width;
    private double height;
    private Line upWidth;
    private Line downWidth;
    private Line leftHeight;
    private Line rightHeight;

    /**
     * Constructor.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
   public Rectangle(Point upperLeft, double width, double height) {
       this.upperLeft = upperLeft;
       this.width = width;
       this.height = height;

       upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
       bottomLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
       bottomRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

       this.upWidth = new Line(upperLeft, upperRight);
       this.downWidth = new Line(bottomLeft, bottomRight);
       this.leftHeight = new Line(upperLeft, bottomLeft);
       this.rightHeight = new Line(upperRight, bottomRight);
   }

   /**
    *
    * @return the up side of the rectangle
    */
   public Line getUpSide() {
       return this.upWidth;
   }

   /**
    *
    * @return the down side of the rectangle
    */
   public Line getDownSide() {
       return this.downWidth;
   }

   /**
    *
    * @return the left side of the rectangle
    */
   public Line getLeftSide() {
       return this.leftHeight;
   }

   /**
    *
    * @return the right side of the rectangle
    */
   public Line getRightSide() {
       return this.rightHeight;
   }

   /**
    *
    * @param line a given line
    * @return a list of intersection points with the specified line
    */
   public List<Point> intersectionPoints(Line line) {
       List<Point> intersectionPointsList = new ArrayList<>();
       if (line.intersectionWith(upWidth) != null) {
           intersectionPointsList.add(line.intersectionWith(upWidth));
       }
       if (line.intersectionWith(downWidth) != null) {
           intersectionPointsList.add(line.intersectionWith(downWidth));
       }
       if (line.intersectionWith(leftHeight) != null) {
           intersectionPointsList.add(line.intersectionWith(leftHeight));
       }
       if (line.intersectionWith(rightHeight) != null) {
           intersectionPointsList.add(line.intersectionWith(rightHeight));
       }
       if (intersectionPointsList.size() >= 2) {
           ArrayList<Point> pointsToDelete = new ArrayList<>();
           for (int i = 0; i < intersectionPointsList.size() - 1; i++) {
               for (int j = 1; j < intersectionPointsList.size(); j++) {
                   if (intersectionPointsList.get(j).equals(intersectionPointsList.get(i))) {
                       pointsToDelete.add(intersectionPointsList.get(j));
                   }
               }
           }
           for (int i = 0; i < pointsToDelete.size(); i++) {
               intersectionPointsList.remove(pointsToDelete.get(i));
           }
       }
       return intersectionPointsList;
   }

   /**
    *
    * @return the width of the rectangle
    */
   public double getWidth() {
       return this.width;
   }

   /**
    *
    * @return the height of the rectangle
    */
   public double getHeight() {
       return this.height;
   }

   /**
   *
   * @return the upper right point
   */
   public Point getUpperRight() {
       return upperRight;
   }

   /**
    *
    * @param newUpperRight set the upper right point
    */
   public void setUpperRight(Point newUpperRight) {
       this.upperRight = newUpperRight;
   }

   /**
   *
   * @return the bottom left point
   */
   public Point getBottomLeft() {
       return bottomLeft;
   }

   /**
    *
    * @param newBottomLeft set the bottom left point
    */
   public void setBottomLeft(Point newBottomLeft) {
       this.bottomLeft = newBottomLeft;
   }

   /**
   *
   * @return the bottom right point
   */
   public Point getBottomRight() {
       return bottomRight;
   }

   /**
    *
    * @param newBottomRight set the bottom right point
    */
   public void setBottomRight(Point newBottomRight) {
       this.bottomRight = newBottomRight;
   }

   /**
    *
    * @return the upper-left point of the rectangle
    */
   public Point getUpperLeft() {
       return this.upperLeft;
   }
}
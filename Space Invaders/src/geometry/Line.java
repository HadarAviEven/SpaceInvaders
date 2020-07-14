package geometry;
import java.util.List;

/**
 * A Line class.
 *
 * @author hadar
 */
public class Line {
    private Point start;
    private Point end;
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    /**
     * Construct a line given two points.
     *
     * @param start a start point
     * @param end an end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Construct a line given two x and y coordinates.
     *
     * @param x1 the x value of the first point
     * @param y1 the y value of the first point
     * @param x2 the x value of the second point
     * @param y2 the y value of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        Point mid = new Point(x, y);
        return mid;
    }

    /**
    *
    * @return the value of x1
    */
   public double getX1() {
       return this.x1;
   }

    /**
    *
    * @return the value of y1
    */
   public double getY1() {
       return this.y1;
   }

    /**
    *
    * @return the value of x2
    */
   public double getX2() {
       return this.x2;
   }

    /**
    *
    * @return the value of y2
    */
   public double getY2() {
       return this.y2;
   }

    /**
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     *
     * @param other another line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param other another line
     * @return the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Double m1 = getM(this);
        Double m2 = getM(other);
        if (m1 == m2) {
            return null;
        }
        if (m1 == null) {
            if (m2 != 0) {
                Point intersectPoint = getIntersectPointWithPermanentX(this, other);
                return intersectPoint;
            } else {
                Point intersectPoint = getIntersectPointWithVerticalLines(this, other);
                return intersectPoint;
            }
        }
        if (m2 == null) {
            if (m1 != 0) {
                Point intersectPoint = getIntersectPointWithPermanentX(other, this);
                return intersectPoint;
            } else {
                Point intersectPoint = getIntersectPointWithVerticalLines(other, this);
                return intersectPoint;
            }
        }
        if (m1 == 0) {
            Point intersectPoint = getIntersectPointWithPermanentY(this, other);
            return intersectPoint;
        }
        if (m2 == 0) {
            Point intersectPoint = getIntersectPointWithPermanentY(other, this);
            return intersectPoint;
        }
        Point intersectPoint = getIntersectPoint(this, other);
        if (isInBetween(this, intersectPoint) && isInBetween(other, intersectPoint)) {
            return intersectPoint;
        }
        return null;
    }

    /**
     *
     * @param line a given line
     * @return the slope of the line
     */
    public Double getM(Line line) {
        if (line.end.getX() - line.start.getX() == 0) {
            return null;
        }
        return (line.end.getY() - line.start.getY()) / (line.end.getX() - line.start.getX());
    }

    /**
     *
     * @param line a given line
     * @param point a given point
     * @return true if the given point is on the line, false otherwise
     */
    public boolean isInBetween(Line line, Point point) {
        double maxX = Math.max(line.start.getX(), line.end.getX());
        double minX = Math.min(line.start.getX(), line.end.getX());
        double maxY = Math.max(line.start.getY(), line.end.getY());
        double minY = Math.min(line.start.getY(), line.end.getY());
        if (point.getX() <= maxX && point.getX() >= minX
                && point.getY() <= maxY && point.getY() >= minY) {
            return true;
        }
        return false;
    }

    /**
    *
    * @param line1 the first line
    * @param line2 the second line
    * @return the intersection point of the two given lines
    */
   public Point getIntersectPointWithPermanentX(Line line1, Line line2) {
       double permanentX = line1.start.getX();
       if (!isXInBetween(line2, permanentX)) {
           return null;
       }
       double tempY = getM(line2) * (permanentX - line2.start.getX()) + line2.start.getY();
       if (!isYInBetween(line1, tempY)) {
           return null;
       }
       return new Point(permanentX, tempY);
    }

    /**
    *
    * @param line1 the first line
    * @param line2 the second line
    * @return the intersection point of the two given lines
    */
   public Point getIntersectPointWithPermanentY(Line line1, Line line2) {
       double permanentY = line1.start.getY();
       if (!isYInBetween(line2, permanentY)) {
           return null;
       }
       double tempX = line2.start.getX() + ((permanentY - line2.start.getY()) / getM(line2));
       if (!isXInBetween(line1, tempX)) {
           return null;
       }
       return new Point(tempX, permanentY);
    }

    /**
    *
    * @param line1 the first line
    * @param line2 the second line
    * @return the intersection point of the two given lines
    */
   public Point getIntersectPointWithVerticalLines(Line line1, Line line2) {
      double permanentX = line1.start.getX();
      double permanentY = line2.start.getY();
      if (!isXInBetween(line2, permanentX) || !isYInBetween(line1, permanentY)) {
          return null;
      }
      return new Point(permanentX, permanentY);
   }

    /**
    *
    * @param line a given line
    * @param x a given coordinate
    * @return true if the x coordinate is on the line, false otherwise
    */
   public boolean isXInBetween(Line line, double x) {
       double maxX = Math.max(line.start.getX(), line.end.getX());
       double minX = Math.min(line.start.getX(), line.end.getX());
       if (x <= maxX && x >= minX) {
           return true;
       }
       return false;
   }

   /**
   *
   * @param line a given line
   * @param y a given coordinate
   * @return true if the y coordinate is on the line, false otherwise
   */
  public boolean isYInBetween(Line line, double y) {
      double maxY = Math.max(line.start.getY(), line.end.getY());
      double minY = Math.min(line.start.getY(), line.end.getY());
      if (y <= maxY && y >= minY) {
          return true;
      }
      return false;
  }

    /**
     *
     * @param line1 the first line
     * @param line2 the second line
     * @return the intersection point of the two given lines
     */
    private Point getIntersectPoint(Line line1, Line line2) {
        Point firstLinePoint = line1.start;
        Point secondLinePoint = line2.start;

        double x1New = firstLinePoint.getX();
        double y1New = firstLinePoint.getY();
        double m1 = getM(line1);

        double x2New = secondLinePoint.getX();
        double y2New = secondLinePoint.getY();
        double m2 = getM(line2);

        double intersectedX = ((m1 * x1New) - (m2 * x2New) + y2New - y1New) / (m1 - m2);
        double intersectedY = (m1 * intersectedX) - (m1 * x1New) + y1New;

        return new Point(intersectedX, intersectedY);
    }

    /**
     *
     * @param other another line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     *
     * @param rect a given rectangle
     * @return the closest intersection point to the
     * start of the line or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        switch (list.size()) {
            case 0:
                return null;
            case 1:
                return list.get(0);
            case 2:
                if (list.get(0).distance(this.start) < list.get(1).distance(this.start)) {
                    return list.get(0);
                } else {
                    return list.get(1);
                }
            default:
                return null;
        }
    }
 }
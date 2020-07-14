package sprites;
import java.util.ArrayList;
import java.util.List;

import geometry.Line;
import geometry.Point;

/**
 *
 * @author hadar
 *
 */
public class CollisionInfo {
    private List<Collidable> collidables;
    private Line line;
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     *
     * @param collidables a list of collidables
     * @param line a given line
     */
    public CollisionInfo(List<Collidable> collidables, Line line) {
        this.collidables = collidables;
        this.line = line;
        findCollision();
    }

    /**
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * finds the closest collision.
     */
    private void findCollision() {
        List<Point> collidablePoints = new ArrayList<Point>();
        for (Collidable collidable : collidables) {
            Point collidablePoint = line.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collidablePoint != null) {
                collidablePoints.add(collidablePoint);
            }
        }
        Point closestPoint = null;
        if (collidablePoints.size() >= 1) {
            closestPoint = collidablePoints.get(0);
            double minimumDistance = line.start().distance(closestPoint);
            for (int i = 1; i < collidablePoints.size(); i++) {
                double distance = line.start().distance(collidablePoints.get(i));
                if (distance < minimumDistance) {
                    minimumDistance = distance;
                    closestPoint = collidablePoints.get(i);
                }
            }
        }
        if (closestPoint != null) {
            for (Collidable collidable : collidables) {
                Point collidablePoint = line.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                if (collidablePoint != null && collidablePoint.equals(closestPoint)) {
                    collisionObject = collidable;
                    collisionPoint = closestPoint;
                }
            }
        }
    }
}
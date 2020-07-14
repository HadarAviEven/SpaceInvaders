package sprites;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 *
 * @author hadar
 *
 */
public interface Collidable {

    /**
    *
    * @return the "collision shape" of the object.
    */
   Rectangle getCollisionRectangle();

   /**
    *
    * @param hitter the given ball
    * @param collisionPoint the given collision point
    * @param currentVelocity the given current velocity
    * @return the new velocity expected after the hit
    */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
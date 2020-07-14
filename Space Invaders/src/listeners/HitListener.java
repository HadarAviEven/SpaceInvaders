package listeners;

import sprites.Ball;
import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public interface HitListener {

    /**
     *
     * @param beingHit a given block
     * @param hitter a given ball
     *
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     */
   void hitEvent(Block beingHit, Ball hitter);
}
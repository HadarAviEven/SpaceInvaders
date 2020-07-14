package animation;

import biuoop.DrawSurface;

/**
 *
 * @author hadar
 *
 */
public interface Animation {

    /**
     *
     * @param d a given drawSurface.
     * @param dt number of seconds.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     *
     * @return if the loop should stop.
     */
    boolean shouldStop();
}

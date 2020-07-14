package sprites;
import biuoop.DrawSurface;
import game.GameLevel;

/**
 *
 * @author hadar
 *
 */
public interface Sprite {

    /**
     *
     * @param d draws the sprite on the given DrawSurface
     */
   void drawOn(DrawSurface d);

   /**
    * notify the block that time has passed.
    * @param dt number of seconds.
    */
   void timePassed(double dt);

   /**
    *
    * @param g the game
    */
   void addToGame(GameLevel g);
}